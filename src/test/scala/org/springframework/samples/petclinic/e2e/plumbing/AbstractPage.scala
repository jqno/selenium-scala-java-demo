package org.springframework.samples.petclinic.e2e.plumbing

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.{WebDriver, Dimension => SeleniumDimension}
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.{Millis, Span}

trait AbstractPage {
  implicit lazy val driver = AbstractPage.driver
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
}
