package geoip

import java.io.File

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.RouteResult
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import config.{AppConfig, TypeSafeConfigProvider}
import services.GeoipService

object Application extends App with GeoipService {
  val appConfig = new AppConfig with TypeSafeConfigProvider {
    override def conf = ConfigFactory.load().getConfig("geoip")
  }

  implicit val system: ActorSystem = ActorSystem("micro-geoip")

  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val bindingFuture = Http().bindAndHandle(RouteResult.route2HandlerFlow(geoipRoutes), "0.0.0.0", appConfig.httpConfig.port)
  override implicit val geoFile: File = new File(appConfig.geofileConfig.path)
}