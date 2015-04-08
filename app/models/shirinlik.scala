package models


import play.api.db.slick.Config.driver.simple._

case class Sweet(id: Option[Int],
                  name: String,
                  imgUrl: String,
                  cost: Double)

class SweetTable(tag: Tag) extends Table[Sweet](tag, "Sweet"){
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def imgUrl = column[String]("IMGURL", O.Default(""))

  def cost = column[Double]("COST", O.Default(0))

  def * = (id.?, name, imgUrl, cost) <> (Sweet.tupled, Sweet.unapply _)
}