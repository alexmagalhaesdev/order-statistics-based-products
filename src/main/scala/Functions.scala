import scalikejdbc.{DB, scalikejdbcSQLInterpolationImplicitDef}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Functions {
  def parseDate(date: List[String]): (LocalDateTime, LocalDateTime) = {
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val initialData = LocalDateTime.parse(
      date.head.substring(1, date.head.length() - 1),
      pattern
    )
    val finalData = LocalDateTime.parse(
      date.last.substring(1, date.head.length() - 1),
      pattern
    )
    (initialData, finalData)
  }

  def getOrderId(product: String): List[Int] = DB readOnly { implicit session =>
    sql"""SELECT i.order_id
              FROM products p,itens i WHERE p.product_name = $product
          AND p.item_id = i.id"""
      .map(rs => rs.string("order_id").toInt)
      .list
      .apply()
  }

  def getProductsByMonthInterval(
                                  orderId: List[Int],
                                  dataInterval: (LocalDateTime, LocalDateTime),
                                  monthInterval: List[String]
                                ): List[Map[String, Any]] = {
    val monthIntervalFormatted = monthInterval.map(_.split("[-<>]").toList)
    val ProductsByMonthInterval: List[Map[String, Any]] = DB readOnly {
      implicit session =>
        sql"""SELECT count(id) AS pedidos,
       CASE
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted.head.head}
              AND ${monthIntervalFormatted.head(1)} THEN ${monthInterval.head}
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted(1).head}
              AND ${monthIntervalFormatted(1)(1)} THEN ${monthInterval(1)}
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted(2).head}
              AND ${monthIntervalFormatted(2)(1)} THEN ${monthInterval(2)}
           ELSE ${monthInterval(3)}
       END mes
      FROM orders
      WHERE id in ($orderId) AND order_date BETWEEN ${dataInterval._1} AND ${dataInterval._2}
      GROUP BY 2
      ORDER BY 1"""
          .map(_.toMap())
          .list()
          .apply()
    }
    ProductsByMonthInterval
  }
}
