import db.Repository.{getProductsAvailable, getProductsByMonthInterval}
import scalikejdbc._

import scala.Console.println
import scala.io.StdIn.readLine

object Main extends App {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton(
    "jdbc:postgresql://localhost:5432/postgres",
    "alexsander",
    "Alex1999+"
  )

  implicit val session: AutoSession.type = AutoSession

  val productsAvailable: List[String] = getProductsAvailable

  println("\t\t\t\t Orders by month range based on product \t\t\t\n")

  println(s"Available products: \n")
  println(productsAvailable.foreach(println) + "\n")

  val productName: String  = readLine("Enter the name of the product to be searched for: example -> \"2018-01-01 00:00:00\" \"2019-01-01 00:00:00\" \n")
  println("product's name -> " + productName)

  val dataInterval: List[String] = readLine("Enter the date range for searching: example -> (“1-3”, “4-6”, “7-12”, “>12”) \n").split(" (?=(?:\"[^\"]*\"|[^\"])*$)").toList
  println("date informed for search -> " + dataInterval)


  val monthInterval: List[String] = readLine("Enter how this range will be displayed in months:  \n").split("[,()]").toList.drop(1)
  println("range grouped by month -> " + monthInterval)


  val ProductsByMonthInterval: List[Map[String, Any]] = getProductsByMonthInterval(productName,dataInterval,monthInterval)
  for (e <- ProductsByMonthInterval) yield {
    println(s"${e("mes")} months: ${e("pedidos")} orders")
  }
}