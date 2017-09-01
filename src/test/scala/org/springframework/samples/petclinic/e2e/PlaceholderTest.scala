package org.springframework.samples.petclinic.e2e

import org.scalatest.{FlatSpec, Matchers}

class PlaceholderTest extends FlatSpec with Matchers {

  behavior of "The Maven Build"

  it should "fail on a ScalaTest test so that we can see that it is picked up" in {
    fail("The build works!")
  }
}
