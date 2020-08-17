import org.scalatestplus.play.PlaySpec
import models.Parser

class ParserSpec extends PlaySpec {
  val ramblerTitle = "Рамблер/новости, почта и поиск — медийный портал: новости России и мира," +
    " электронная почта, погода, развлекательные и коммуникационные сервисы." +
    " Новости сегодня и сейчас"
  val googleTitle = "Google"
  val yandexTitle = "Яндекс"
  val unknownHost = "Unknown Host"
  val parser = Parser("http://www.google.com,https://yandex.kz,www.rambler.ru")

  //  "Hello world" must endWith ("world")
  "Parser" must {
    "Fetch correct title for Rambler`s main page" in {
      parser.parse mustBe Map(
        "http://www.google.com" -> googleTitle,
        "https://yandex.kz" -> yandexTitle,
        "www.rambler.ru" -> ramblerTitle
      )
    }

    "Handle properly unresolved host" in {
      parser.getTitle("www.rablter.ru") mustBe unknownHost
    }

    "Put protocol to the head" in {
      parser.getTitle("www.rambler.ru") mustBe ramblerTitle
    }

    "Resolve unsecure connection" in {
      parser.getTitle("http://www.rambler.ru") mustBe ramblerTitle
    }

    "Throw Unknown Host" in {
      parser.getTitle("https://fictional.we.site") mustBe unknownHost
    }
    "Properly fetch YouTube`s title" in {
      parser.getTitle("https://www.youtube.com") mustBe "YouTube"
    }
  }
}
