package config

import com.typesafe.config.Config

class DefaultGeofileConfig(config: Config) extends GeofileConfig {

  override def path: String = config.getString("path")
}

trait GeofileConfig {
  def path: String
}
