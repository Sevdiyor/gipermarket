package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("home"))
  }

  def kafe = Action {
    Ok(views.html.Kafe())
  }

  def signIn = Action {
    Ok(views.html.signin())
  }

  def signUp = Action {
    Ok(views.html.signup())
  }

}