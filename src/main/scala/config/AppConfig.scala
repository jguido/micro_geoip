package config

class AppConfig extends BaseConfig{
  this: TypeSafeConfigProvider =>

  override val httpConfig: HttpConfig = new DefaultHttpConfig(conf.getConfig("http"))
  override val geofileConfig: GeofileConfig = new DefaultGeofileConfig(conf.getConfig("geoip"))
}

trait BaseConfig {
  val httpConfig: HttpConfig
  val geofileConfig: GeofileConfig

}





