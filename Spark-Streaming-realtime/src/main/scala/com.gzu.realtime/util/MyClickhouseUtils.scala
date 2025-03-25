package com.gzu.realtime.util

import java.sql.{Connection, DriverManager, ResultSet, Statement}

object MyClickhouseUtils {
  // ClickHouse数据库连接信息
  val url = "jdbc:clickhouse://192.168.43.106:8123/music163"
  val username = "default"
  val password = ""

  // 加载驱动器类
  Class.forName("ru.yandex.clickhouse.ClickHouseDriver")

  /**
   * 获取数据库连接
   *
   * @return 数据库连接
   */
  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }

}
