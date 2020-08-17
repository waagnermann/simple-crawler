package controllers

import javax.inject._
import models.{Parser, UrlData}
import play.api.data.Form
import play.api.mvc._
import play.api.data.Forms._

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController
 with play.api.i18n.I18nSupport
{

  val urlForm = Form(
    mapping(
      "urls" -> text,
    )(UrlData.apply)(UrlData.unapply)
  )
//  val mapTable = Map("a" -> "b", "c" -> "d", "e" -> "f")

  def index() = Action { implicit request =>
    Ok(views.html.user(urlForm))
  }

  def userPost() = Action(parse.form(urlForm)) { implicit request =>
    val userData = request.body
    Redirect(routes.HomeController.info(userData.urls))
  }


  def info(urls: String) = Action { implicit request =>
    val parser = Parser(urls)
    val mapTable = parser.parse
    Ok(views.html.info(urls)(mapTable))
  }

}
