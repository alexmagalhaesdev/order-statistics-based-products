import scalikejdbc.{DB, scalikejdbcSQLInterpolationImplicitDef}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Functions {
  def parseDate(date: List[String]): (LocalDateTime, LocalDateTime) = {
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val initialData = LocalDateTime.parse(date.head.substring(1, date.head.length()-1),pattern )
    val finalData = LocalDateTime.parse(date.last.substring(1, date.head.length()-1), pattern)
    (initialData, finalData)
  }

  def getProductsByMonthInterval(
                                  productName: String,
                                  dataInterval: (LocalDateTime, LocalDateTime),
                                  monthInterval: List[String]
                                ): List[Map[String, Any]] = {

    val monthIntervalFormatted = monthInterval.map(_.split("[-<>]").toList)

    val ProductsByMonthInterval: List[Map[String, Any]] = DB readOnly {
      implicit session =>
        sql"""SELECT count(id) AS pedidos,
       CASE
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted.head.head}
           AND ${
          monthIntervalFormatted
            .head(1)
        } THEN ${monthInterval.head}
           WHEN to_char(order_date, 'MM') BETWEEN ${
          monthIntervalFormatted(
            1
          ).head
        } AND ${monthIntervalFormatted(1)(1)} THEN ${monthInterval(1)}
           WHEN to_char(order_date, 'MM') BETWEEN ${
          monthIntervalFormatted(
            2
          ).head
        } AND ${monthIntervalFormatted(2)(1)} THEN ${monthInterval(2)}
           ELSE ${monthInterval(3)}
       END mes
      FROM dott_db_test.orders
              JOIN
        (SELECT order_id
         FROM dott_db_test.itens
         JOIN
           (SELECT item_id
            FROM dott_db_test.products
            WHERE product_name = $productName) AS products_filter
            ON dott_db_test.itens.id = products_filter.item_id)
            AS foo ON dott_db_test.orders.id = order_id
      WHERE order_date BETWEEN ${dataInterval._1} AND ${dataInterval._2}
      GROUP BY 2
      ORDER BY 1"""
          .map(_.toMap())
          .list()
          .apply()
    }
    ProductsByMonthInterval
  }
}