package com.mscping

import models.Parser
import net.ruippeixotog.scalascraper.browser.JsoupBrowser

object SimpleScraper extends App {
  val parser = Parser("http://www.google.com,https://yandex.kz,www.rambler.ru")
  println(parser.parse)
//  println(Parser("").getTitle("www.rambtler.ru"))
}
