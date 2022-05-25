package db

import scalikejdbc.{DB, scalikejdbcSQLInterpolationImplicitDef}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Repository {
  def getProductsAvailable: List[String] = DB readOnly { implicit session =>
    sql"select distinct(product_name) from dott_db_test.products"
      .map(rs => rs.string("product_name"))
      .list
      .apply()
  }

  def parseDate(date: List[String]): (LocalDateTime, LocalDateTime) = {
    println("meu DADO", date)
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val initialData = LocalDateTime.parse(date.head, pattern)
    val finalData = LocalDateTime.parse(date.last, pattern)
    (initialData, finalData)
  }

  def setMonthInterval(date: List[String]): List[List[String]] = {
    val monthInterval = date.map(_.split("-").toList)
    monthInterval
  }

  def getProductsByMonthInterval(
                                  productName: String,
                                  dataInterval: List[String],
                                  monthInterval: List[String]
                                ): List[Map[String, Any]] = {

    val monthIntervalFormatted = monthInterval.map(_.split("-").toList)
    println("entra na minha função")
    val date: (LocalDateTime, LocalDateTime) = parseDate(dataInterval)

    val ProductsByMonthInterval: List[Map[String, Any]] = DB readOnly {
      implicit session =>
        sql"""SELECT count(id) AS pedidos,
       CASE
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted.head.head}
           AND ${monthIntervalFormatted
          .head(1)} THEN ${monthInterval.head}
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted(
          1
        ).head} AND ${monthIntervalFormatted(1)(1)} THEN ${monthInterval(1)}
           WHEN to_char(order_date, 'MM') BETWEEN ${monthIntervalFormatted(
          2
        ).head} AND ${monthIntervalFormatted(2)(1)} THEN ${monthInterval(2)}
           ELSE '>12'
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
      WHERE order_date BETWEEN ${date._1} AND ${date._2}
      GROUP BY 2
      ORDER BY 1"""
          .map(_.toMap())
          .list()
          .apply()
    }
    ProductsByMonthInterval
  }
}