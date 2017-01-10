package config

import com.typesafe.config

trait TypeSafeConfigProvider {
  def conf: config.Config
}
