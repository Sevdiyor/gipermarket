package controllers

import models._
import play.api.Logger
import play.api.db.slick.DBAction
import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

import scala.slick.lifted.TableQuery


//April 5
object First extends  Controller{
  val sweets = TableQuery[SweetTable]

  def list = DBAction { implicit rs=>
    Logger.info(s"SHOW_ALL= ${sweets.list}")
    Ok(views.html.Shirinliklar(sweets.list))
  }

  def showAddForm = Action{
    Ok(views.html.add())
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val imgUrl = formParams.get("imgUrl")(0)
    val cost = formParams.get("cost")(0)
    val sweetId = (sweets returning sweets.map(_.id)) += Sweet(None, name, imgUrl, cost)
    Redirect(routes.First.list())
  }

  def remove(id: Int)= DBAction{ implicit request =>
    sweets.filter(_.id === id).delete;
    Redirect(routes.First.list())

  }
}
