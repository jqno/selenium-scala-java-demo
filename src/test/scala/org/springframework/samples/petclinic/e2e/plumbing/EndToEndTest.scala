package org.springframework.samples.petclinic.e2e.plumbing

import org.scalatest._
import org.scalatest.exceptions.TestFailedDueToTimeoutException

trait EndToEndTest extends FlatSpec with Matchers with ConfiguredEventually with OptionValues with GivenWhenThen {

  /**
    * Make a screenshot after every failing test.
    */
  override def withFixture(test: NoArgTest): Outcome = {
    val outcome = test()
    if (outcome.isExceptional) {
      AbstractPage.screenshot(test.name)
    }
    outcome
  }

  /**
    * Enable the `go to page` syntax outside of the Page Objects without exposing the WebBrowser.
    */
  def go = AbstractPage.navigator

  /**
    * Make sure that the given `fn` "never" occurs.
    * By "never", we mean "not within the specified timeouts".
    */
  def never[T](fn: => T): Assertion =
    try {
      eventually(fn)
      Assertions.fail("Condition is completed successfully, which is not allowed")
    }
    catch {
      case _: TestFailedDueToTimeoutException => Assertions.succeed
    }

}
