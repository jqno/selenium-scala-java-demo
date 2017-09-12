package org.springframework.samples.petclinic.e2e.tests

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.AddEditOwnerPage
import org.springframework.samples.petclinic.e2e.plumbing.EndToEndTest

class AddOwnerTest extends EndToEndTest {

  val page = new AddEditOwnerPage(None)


  behavior of "Add Owner"

  it should "successfully add an owner" in {
    Given("an empty 'Add Owner' page")
    go to page

    When("we fill in details for a valid owner")
    page.fillIn(Owner.someValidOwner)
    And("we submit")
    val newPage = page.clickSubmit()

    Then("we see the new owner's details on a 'Show Owner' page")
    eventually {
      newPage.info shouldBe Owner.someValidOwner
    }
  }

  it should "not validate if the phone number isn't numeric" in {
    Given("an owner with an invalid telephone number")
    val invalid = Owner.someValidOwner.copy(telephone = "invalid")
    And("an empty 'Add Owner' page")
    go to page

    When("we fill in the invalid owner")
    page.fillIn(invalid)

    Then("we see that the telephone number is invalid")
    eventually {
      page.isTelephoneInvalid shouldBe true
    }
  }
}
