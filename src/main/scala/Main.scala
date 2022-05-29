import Functions.{getOrderId, getProductsByMonthInterval, parseDate}
import scalikejdbc._

import java.time.LocalDateTime
import scala.Console.println
import scala.io.StdIn.readLine

object Main extends App {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton(
    "jdbc:postgresql://localhost:5432/postgres?currentSchema=dott_db_test",
    "alexsander",
    "Alex1999+"
  )

  implicit val session: AutoSession.type = AutoSession

  def productsAvailable: List[String] = DB readOnly { implicit session =>
    sql"select distinct(product_name) from products"
      .map(rs => rs.string("product_name"))
      .list
      .apply()
  }

  println("\t\t\t\t Orders by month range based on product \t\t\t\n")

  println(s"Available products: \n")
  println(productsAvailable.foreach(println) + "\n")

  val productName: String = readLine(
    "Enter the name of the product to be searched for: \n"
  )
  println("product's name -> " + productName)

  val dataInterval: (LocalDateTime, LocalDateTime) = parseDate(
    readLine(
      "Enter the date range for searching: example -> \"2018-01-01 00:00:00\" \"2019-01-01 00:00:00\" \n"
    ).split(" (?=(?:\"[^\"]*\"|[^\"])*$)").toList
  )
  println("date informed for search -> " + dataInterval)

  val monthInterval: List[String] = readLine(
    "Enter how this range will be displayed in months:  example -> (\"1-3\", \"4-6\", \"7-12\", \">12\") \n"
  ).split("[,()]").toList.map(_.trim).drop(1)
  println("range grouped by month -> " + monthInterval)

  val orderId: List[Int] = getOrderId(productName)

  val ProductsByMonthInterval: List[Map[String, Any]] =
    getProductsByMonthInterval(orderId, dataInterval, monthInterval)
  for (e <- ProductsByMonthInterval) {
    println(s"${e("mes")} months: ${e("pedidos")} orders")
  }
}
