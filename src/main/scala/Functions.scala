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
    val interval = monthInterval.map(_.replaceAll("\"", ""))
    val month = interval
      .map(
        _.replaceAll("<>", "")
          .split("-")
          .map(e => if (e.length < 2) "0" + e else e)
          .toList
      )
      .dropRight(1)
    val ProductsByMonthInterval: List[Map[String, Any]] = DB readOnly {
      implicit session =>
        sql"""SELECT count(id) AS pedidos,
       CASE
           WHEN to_char(order_date, 'MM') BETWEEN ${month.head.head}
              AND ${month.head(1)} THEN ${interval.head}
           WHEN to_char(order_date, 'MM') BETWEEN ${month(1).head}
              AND ${month(1)(1)} THEN ${interval(1)}
           WHEN to_char(order_date, 'MM') BETWEEN ${month(2).head}
              AND ${month(2)(1)} THEN ${interval(2)}
           ELSE ${interval(3)}
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
