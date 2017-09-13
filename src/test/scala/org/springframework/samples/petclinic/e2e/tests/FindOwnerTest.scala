package org.springframework.samples.petclinic.e2e.tests

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.FindOwnersPage
import org.springframework.samples.petclinic.e2e.plumbing.EndToEndTest

class FindOwnerTest extends EndToEndTest {

  val page = new FindOwnersPage


  behavior of "Find Owner"

  it should "return all owners matching a given name" in {
    Given("the 'Find Owner' page")
    go to page

    When("we search for 'Davis'")
    page.findOwner("Davis")

    Then("eventually 'Betty Davis' and 'Harold Davis' turn up")
    eventually {
      page.listOfOwners should (include ("Betty Davis") and include ("Harold Davis"))
    }
  }

  it should "return all owners when no name is given" in {
    Given("the 'Find Owner' page")
    go to page

    When("we request a list of all owners")
    page.findAllOwners()  // This triggers our artificial wait

    Then("eventually we find ten owners")
    eventually {
      page.numberOfOwners shouldBe 10
    }
  }

  it should "go to the owner page if an owner is clicked" in {
    Given("the 'Find Owner' page")
    go to page
    And("a search for 'Davis'")
    page.findOwner("Davis")
    And("the search results are fully loaded")
    page.waitUntilSearchResultsAreFullyLoaded()

    When("we click on owner 'Betty Davis'")
    val showOwnerPage = page.clickOwner("Betty Davis")

    Then("see information about Betty Davis")
    showOwnerPage.id shouldBe 2
    showOwnerPage.info shouldBe Owner.bettyDavis
  }

  it should "go to the 'Add Owner' page if the 'Add Owner' button is clicked" in {
    Given("the 'Find Owner' page")
    go to page

    When("we click the 'Add Owner' button")
    val editOwnerPage = page.clickAddOwner()

    Then("we go to an empty 'Add Owner' page")
    eventually {
      editOwnerPage.id shouldBe None
    }
  }

}
