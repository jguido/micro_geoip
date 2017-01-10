package services

import java.io.File

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import geoip.services.GeoipService
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class GeoipServiceSpec extends WordSpec with Matchers with ScalatestRouteTest with GeoipService with BeforeAndAfterAll {
  "GeoipService" should {
    "Return ip information for a GET request" in {
      val attendedResponse = """{"city":"Toronto","latitude":43.6383056640625,"timezone":"America/Toronto","countryName":"Canada","longitude":-79.43009948730469,"postalCode":"M6K","countryCode":"CA","region":"ON","regionName":"Ontario"}"""

      Get("/query?ip=64.99.80.121") ~> geoipRoutes ~> check { //scala.org
        responseAs[String] shouldEqual attendedResponse
        status shouldEqual StatusCodes.OK
      }
    }
  }
  override implicit val geoFile: File = new File(getClass.getResource("/GeoLiteCity.dat").getPath)
}
