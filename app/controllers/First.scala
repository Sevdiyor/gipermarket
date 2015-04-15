package controllers

import models._
import play.api.Logger
import play.api.db.slick.DBAction
import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

import scala.slick.lifted.TableQuery


//April 5
class First extends  Controller{
  val somethings = TableQuery[AnyTable]
  val sections=TableQuery[SectionTable]

  def list = DBAction { implicit rs=>
   // Logger.info(s"SHOW_ALL= ${something.list}")
    val anyResult = (for {
      (any, section) <- somethings leftJoin sections on (_.sectionId === _.id)
    } yield (any, section.name)).list
    .map{case(any, sectionName) => anyForDisp(any, sectionName)}
    Ok(views.html.list(anyResult))
  }

  def showAddForm = DBAction{ implicit rs =>
    Ok(views.html.add(sections.list))
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val imgUrl = formParams.get("imgUrl")(0)
    val cost = formParams.get("cost")(0)
    val sectionId = formParams.get("sectionid")(0).toInt
    val sectionI = (somethings returning somethings.map(_.id)) += Smth(None, name, imgUrl, cost, sectionId)
    //Logger.info(s"LastId = $sectionId")
    Redirect(routes.First.list())
  }

  def remove(id: Int)= DBAction{ implicit request =>
    somethings.filter(_.id === id).delete;
    Redirect(routes.First.list())
  }

  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val imgUrl = formParams.get("imgUrl")(0)
    val cost = formParams.get("cost")(0)
    val sectionId = formParams.get("section")(0).toInt

    val any = Smth(Some(id), name, imgUrl, cost, sectionId)
    somethings.filter(_.id === id).update(any)

    Redirect(routes.First.list())
  }

  def showEditForm(anyId: Int) = DBAction { implicit request =>
    val byId = somethings.findBy(_.id)
    val any = byId(anyId).list.head

    Ok(views.html.edit(any, sections.list))
  }
}
