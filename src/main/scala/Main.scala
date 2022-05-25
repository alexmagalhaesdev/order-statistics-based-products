import db.Repository.getProductsAvailable
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

  println("\t\t\t\t Pedidos por intervalo de mês com base no produto \t\t\t\n")

  println(s"Produtos disponiveis: \n")
  println(productsAvailable.foreach(println) + "\n")

  val productName: String  = readLine("Informe o nome do produto a ser buscado: ")
////  println("nome do produto -> " + productName)
//  val dataInterval = io.Source.stdin.getLines.toList.map(_.trim).mkString("\n")
//
//  print(dataInterval)

//  val monthInterval = readLine("Informe como esse intervalo será exibido em meses:  \n").split("\\(\\)").toList
//  println(monthInterval)
//  println(monthInterval.length)
//  println(monthInterval.getClass)
//  println("intervalo agrupado por mês -> " + monthInterval)


  val dataInterval = List("2015-01-01 00:00:00", "2023-01-01 00:00:00")
  val someIntervals = List("1-3", "4-6", "7-12", ">12")

//  val ProductsByMonthInterval: List[Map[String, Any]] = getProductsByMonthInterval(productName,dataInterval,someIntervals)
//  for (e <- ProductsByMonthInterval) yield {
//    println(s"${e("mes")} meses: ${e("pedidos")} pedidos")
//  }
}