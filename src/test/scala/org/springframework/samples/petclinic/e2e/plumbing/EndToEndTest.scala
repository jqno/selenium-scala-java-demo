package org.springframework.samples.petclinic.e2e.plumbing

import org.scalactic.source.Position
import org.scalatest.concurrent.Eventually
import org.scalatest.exceptions.TestFailedDueToTimeoutException
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{Assertion, Assertions, FlatSpec, Matchers}

import scala.concurrent.duration._

trait EndToEndTest extends FlatSpec with Matchers with Eventually {

  private val neverPatienceConfig: PatienceConfig = PatienceConfig(timeout = Span(1, Seconds), interval = Span(50, Millis))
  private val defaultAfterDelay: FiniteDuration = 1.second

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
