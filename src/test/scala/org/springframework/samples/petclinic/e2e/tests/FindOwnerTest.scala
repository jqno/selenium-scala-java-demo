package org.springframework.samples.petclinic.e2e.tests

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.FindOwnersPage
import org.springframework.samples.petclinic.e2e.plumbing.EndToEndTest

class FindOwnerTest extends EndToEndTest {

  val page = new FindOwnersPage


  behavior of "Find Owner"

  it should "return all owners matching a given name" in {
    go to page
    page.findOwner("Davis")

    eventually {
      page.listOfOwners should (include ("Betty Davis") and include ("Harold Davis"))
    }
  }

  it should "return all owners when no name is given" in {
    go to page
    page.findAllOwners()  // This triggers our artificial wait

    eventually {
      page.numberOfOwners shouldBe 10
    }
  }

  it should "go to the owner page if an owner is clicked" in {
    go to page
    page.findOwner("Davis")

    val ownerPage = eventually { page.clickOwner("Betty Davis") }

    ownerPage.id shouldBe 2
    ownerPage.info shouldBe Owner.bettyDavis
  }

}
