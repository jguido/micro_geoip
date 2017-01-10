package geoip.services

import java.io.File

import akka.actor.ActorSystem
import akka.http.scaladsl.model.headers.HttpOriginRange
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import ch.megard.akka.http.cors.CorsDirectives._
import ch.megard.akka.http.cors.CorsSettings
import com.snowplowanalytics.maxmind.iplookups.{IpLocation, IpLookups}
import config.Loggable
import geoip.domain.{GeoipFormats, LocaleIpLocation}


trait GeoipService extends GeoipFormats with Loggable {

  implicit val geoFile: File

  implicit val system: ActorSystem
  val settings = CorsSettings.defaultSettings.copy(allowedOrigins = HttpOriginRange.*, allowCredentials = false)

  def geoipRoutes = cors(settings) {
    path("query") {
      get {
        parameter('ip) { ip =>
          val ipLookups: IpLookups = IpLookups(geoFile = Some(geoFile.getCanonicalPath), ispFile = None,
            orgFile = None, domainFile = None, memCache = false, lruCache = 20000)
          val a: Option[IpLocation] = for (loc <- ipLookups.performLookups(ip)._1) yield loc
          a match {
            case Some(ipLocation) =>
              val localIpLocation = LocaleIpLocation(
                ipLocation.countryCode,
                ipLocation.countryName,
                ipLocation.region,
                ipLocation.city,
                ipLocation.latitude,
                ipLocation.longitude,
                ipLocation.timezone,
                ipLocation.postalCode,
                ipLocation.dmaCode,
                ipLocation.areaCode,
                ipLocation.metroCode,
                ipLocation.regionName
              )
              complete(HttpResponse(StatusCodes.OK, entity = HttpEntity(ipLocationFormat.write(localIpLocation).toString())))
            case _ =>
              complete(HttpResponse(StatusCodes.BadRequest))
          }
        }
      }
    }
  }
}
