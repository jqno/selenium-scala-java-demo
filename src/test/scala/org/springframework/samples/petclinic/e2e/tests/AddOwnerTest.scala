package org.springframework.samples.petclinic.e2e.tests

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.OwnerPage
import org.springframework.samples.petclinic.e2e.plumbing.EndToEndTest

class AddOwnerTest extends EndToEndTest {

  val page = new OwnerPage(None)


  it should "successfully add an owner" in {
    go to page
    page.fillIn(Owner.someValidOwner)
    val newPage = eventually { page.clickAddOwner() }

    eventually {
      newPage.info shouldBe Owner.someValidOwner
    }
  }
}
