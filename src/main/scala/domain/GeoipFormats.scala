package geoip.domain

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait GeoipFormats extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val ipLocationFormat = jsonFormat12(LocaleIpLocation)
}

case class LocaleIpLocation(
                       countryCode: String,
                       countryName: String,
                       region: Option[String],
                       city: Option[String],
                       latitude: Float,
                       longitude: Float,
                       timezone: Option[String],
                       postalCode: Option[String],
                       dmaCode: Option[Int],
                       areaCode: Option[Int],
                       metroCode: Option[Int],
                       regionName: Option[String]
                     )