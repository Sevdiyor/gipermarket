package controllers

/**
 * Created by XaKKeR on 4/14/2015.
 */

import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class Sections extends Controller {
  val sections = TableQuery[SectionTable]

  def list = DBAction { implicit rs =>
    Ok(views.html.listSections(sections.list))
  }

  def showAddForm = DBAction { implicit rs=>
    Ok(views.html.addSection())
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val anyId = (sections returning sections.map(_.id)) += Section(None, name)
    Logger.info(s"LastId = $anyId")
    Redirect(routes.Sections.list())
  }


  def remove(id: Int) = DBAction { implicit request =>
    sections.filter(_.id === id).delete;
    Redirect(routes.Sections.list())
  }

  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val name = formParams.get("name")(0)

    val country = Section(Some(id), name)
    sections.filter(_.id === id).update(country)

    Redirect(routes.Sections.list())
  }

  def showEditForm(id: Int) = DBAction { implicit request =>
    val byId = sections.findBy(_.id)
    val country = byId(id).list.head

    Ok(views.html.editSection(country))
  }

}
