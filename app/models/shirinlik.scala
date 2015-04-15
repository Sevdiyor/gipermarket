package models


import play.api.db.slick.Config.driver.simple._

case class Smth(id: Option[Int],
                  name: String,
                  imgUrl: String,
                  cost: String,
                  sectionId: Int)

case class anyForDisp(any: Smth,
                      sectionName: String)

case class Section(id: Option[Int],
                   name: String)

class SectionTable(tag: Tag) extends Table[Section](tag, "SECTIONS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def * = (id.?, name) <> (Section.tupled, Section.unapply _)

}

class AnyTable(tag: Tag) extends Table[Smth](tag, "ANY"){
  val sections = TableQuery[SectionTable]

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME", O.Default(""))
  def imgUrl = column[String]("IMGURL", O.Default(""))
  def cost = column[String]("COST", O.Default(""))
  def sectionId = column[Int]("SECTION_ID",  O.NotNull)

  def * = (id.?, name, imgUrl, cost, sectionId) <> (Smth.tupled, Smth.unapply _)

  def section = foreignKey("ANY_FK_SECTION_ID", sectionId, sections)(_.id)


}
