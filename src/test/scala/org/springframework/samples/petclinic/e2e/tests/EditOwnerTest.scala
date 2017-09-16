package org.springframework.samples.petclinic.e2e.tests

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.{FindOwnersPage, ShowOwnerPage}
import org.springframework.samples.petclinic.e2e.plumbing.EndToEndTest

class EditOwnerTest extends EndToEndTest {

  val showPage = new ShowOwnerPage(7) // Jeff Black
  val findPage = new FindOwnersPage


  behavior of "Edit Owner"

  it should "not display the old data after an owner was edited" in {
    Given("the 'Show Owner' page for Jeff Black")
    go to showPage
    eventually {
      showPage.info shouldBe Owner.jeffBlack
    }


    When("we click 'Edit Owner'")
    val editPage = showPage.clickEditOwner()
    And("the page is fully loaded")
    editPage.waitUntilFullyLoaded()
    And("we change Jeff's first name to Steve")
    eventually {
      editPage.changeFirstName("Steve")
    }
    And("we submit")
    editPage.clickSubmit()


    Then("the details should reflect this change")
    eventually {
      showPage.info shouldBe Owner.jeffBlack.copy(firstName = "Steve")
    }

    And("on the 'Find Owners' page, a 'Jeff Black' should appear")
    go to findPage
    findPage.findAllOwners()  // This triggers our artificial wait
    never {
      findPage.listOfOwners should include ("Jeff Black")
    }
  }

}
