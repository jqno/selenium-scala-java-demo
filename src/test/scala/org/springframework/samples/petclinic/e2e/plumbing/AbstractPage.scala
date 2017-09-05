package org.springframework.samples.petclinic.e2e.plumbing

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.{WebDriver, Dimension => SeleniumDimension}
import org.scalatest.OptionValues
import org.scalatest.selenium.{Page, WebBrowser}
import org.scalatest.time.{Millis, Span}

trait AbstractPage extends Page with WebBrowser with OptionValues {
  implicit lazy val driver = AbstractPage.driver

  val homepage = "http://localhost:4444"
}

object AbstractPage extends WebBrowser {
  // Set up the selenium webdriver
  ChromeDriverManager.getInstance.setup()
  implicit val driver: WebDriver = new ChromeDriver

  // Sometimes tests fail on different devices due to different browser window sizes,
  // so let's make sure they're always the same
  driver.manage.window.setSize(new SeleniumDimension(1290, 1024))

  // Disable Selenium's implicit wait because it often waits long times for no reason
  implicitlyWait(Span(1, Millis))

  // Close the browser when the tests are done
  sys.addShutdownHook { quit() }

  // Enables the `go to page` syntax outside of the Page Objects without exposing the WebBrowser
  object navigator {
    def to(page: Page): Unit = {
      go to page
    }
  }

  def screenshot(testName: String): Unit = {
    setCaptureDir("target")
    val timestamp = LocalDateTime.now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"))
    capture to s"$timestamp - $testName.png"
  }
}
