package org.springframework.samples.petclinic.e2e

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.WebBrowser
import org.scalatest.{FlatSpec, Matchers}

class PlaceholderTest extends FlatSpec with Matchers with WebBrowser {

  ChromeDriverManager.getInstance.setup()
  implicit lazy val webDriver: WebDriver = new ChromeDriver
  sys.addShutdownHook { quit() }


  behavior of "The Selenium Test"

  it should "click on some buttons" in {
    go to "http://localhost:4444/"
  }

}
