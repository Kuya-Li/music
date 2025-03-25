package com.gzu.realtime.util

import java.util.ResourceBundle
/*
解析配置类
 */
object MyPropsUtils {
  private val bundle: ResourceBundle = ResourceBundle.getBundle("config")

  def apply(propsKey: String): String = {
    bundle.getString(propsKey)
  }

  def main(args: Array[String]): Unit = {
    println(MyPropsUtils("kafka.bootstrap-servers"))
  }
}
