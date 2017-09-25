package org.springframework.samples.petclinic.e2e.plumbing

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.{WebDriver, Dimension => SeleniumDimension}
import org.scalatest.OptionValues
import org.scalatest.selenium.{Page, WebBrowser}
import org.scalatest.time.{Seconds, Span}

trait AbstractPage extends Page with WebBrowser with ConfiguredEventually with OptionValues {
  /**
    * Provide easy access to the webdriver in the companion object.
    */
  implicit lazy val driver: WebDriver = AbstractPage.driver
}

object AbstractPage extends WebBrowser {
  /**
    * Hardcoded url. In production, this would be configurable through an environment variable.
    */
  val homepage = "http://localhost:4444"

  /**
    * Implicit driver, which is required by ScalaTest's Selenium DSL.
    * Almost all the DSL's functions have an implicit [[WebDriver]] parameter.
    */
  implicit val driver: WebDriver = configureDriver

  /**
    * Enable the `go to page` syntax outside of the Page Objects without exposing the WebBrowser.
    */
  object navigator {
    def to(page: Page): Unit = {
      go to page
    }
  }

  /**
    * Take a screenshot with the current time and the test's name to the target directory.
    */
  def screenshot(testName: String): Unit = {
    setCaptureDir("target")
    val timestamp = LocalDateTime.now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"))
    capture to s"$timestamp - $testName.png"
  }

  /**
    * Set up a Chrome WebDriver to use throughout the Selenium tests.
    */
  def configureDriver: WebDriver = {
    // Set up the selenium webdriver.
    ChromeDriverManager.getInstance.setup()
    implicit val driver: WebDriver = new ChromeDriver

    // Sometimes tests fail on different devices due to different browser window sizes,
    // so let's make sure they're always the same.
    driver.manage.window.setSize(new SeleniumDimension(1290, 1024))

    // Disable Selenium's implicit wait because it often waits long times for no reason.
    implicitlyWait(Span(3, Seconds))

    // Close the browser when the tests are done.
    sys.addShutdownHook { quit() }

    // Return.
    driver
  }

}
