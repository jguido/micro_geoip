package config

import com.typesafe.config.Config

class DefaultHttpConfig(config: Config) extends HttpConfig {

  override def port: Int = config.getInt("port")
}
trait HttpConfig {
  def port: Int
}
