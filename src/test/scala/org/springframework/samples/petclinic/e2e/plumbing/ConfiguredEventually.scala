package org.springframework.samples.petclinic.e2e.plumbing

import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Millis, Seconds, Span}

/**
  * A trait that overrides [[Eventually]]'s default `patienceConfig`,
  * so we have the same non-default `patienceConfig` everywhere.
  */
trait ConfiguredEventually extends Eventually {

  override implicit val patienceConfig: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(50, Millis))

}
