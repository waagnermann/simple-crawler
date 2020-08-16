package models

import net.ruippeixotog.scalascraper.browser.JsoupBrowser

class Parser(urls: String) {
  private val urlArr = urls split ','
  private val browser = JsoupBrowser()

  def getTitle(url: String): String = {
    val validUrl = if ((url startsWith "http://") || (url startsWith "https://")) url else "http://" + url
    try {
      val doc = browser.get(validUrl)
      doc.title
    } catch {
      case ex: java.net.UnknownHostException => "Unknown Host"
    }
  }

  def parse: Map[String, String] = {
    urlArr.foldLeft(Map.empty[String, String])((table, url) => table updated(url, getTitle(url)))
  }

  override def toString: String = s"urls:\n ${urlArr mkString "\n"}"
}

object Parser {
  def apply(urls: String): Parser = new Parser(urls)
}
