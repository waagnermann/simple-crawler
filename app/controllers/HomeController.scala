package controllers

import javax.inject._
import models.UserData
import play.api.data.Form
import play.api.mvc._
import play.api.data.Forms._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController
 with play.api.i18n.I18nSupport
{

  val userForm = Form(
    mapping(
      "name" -> text,
      "age"  -> number
    )(UserData.apply)(UserData.unapply)
  )

  def index() = Action { implicit request =>
    Ok(views.html.user(userForm))
  }

  def userPost() = Action(parse.form(userForm)) { implicit request =>
    val userData = request.body
//    Redirect(s"/info/${userData.name}/${userData.age}")
    Redirect(routes.HomeController.info(userData.name, userData.age))
  }

  def info(name: String, age: Int) = Action { implicit request =>
    Ok(views.html.info(name)(age))
  }

}
