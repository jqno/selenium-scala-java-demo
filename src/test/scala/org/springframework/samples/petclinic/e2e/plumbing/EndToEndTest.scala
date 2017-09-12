package org.springframework.samples.petclinic.e2e.plumbing

import org.scalactic.source.Position
import org.scalatest._
import org.scalatest.exceptions.TestFailedDueToTimeoutException
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.duration._

trait EndToEndTest extends FlatSpec with Matchers with ConfiguredEventually with OptionValues with GivenWhenThen {

  private val neverPatienceConfig: PatienceConfig = patienceConfig
  private val defaultAfterDelay: FiniteDuration = 5.second

  override def withFixture(test: NoArgTest): Outcome = {
    val outcome = test()
    if (outcome.isExceptional) {
      AbstractPage.screenshot(test.name)
    }
    outcome
  }

  def go = AbstractPage.navigator

  def never[T](fn: => T, delay: FiniteDuration = defaultAfterDelay, config: PatienceConfig = neverPatienceConfig)(implicit pos: Position): Assertion =
    try {
      Thread.sleep(delay.toMillis)
      eventually(fn)(config, pos)
      Assertions.fail("Condition is never completed successfully, which is not allowed")
    }
    catch {
      case _: TestFailedDueToTimeoutException => Assertions.succeed
    }

}
