package org.springframework.samples.petclinic.e2e.tests

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.{FindOwnersPage, ShowOwnerPage}
import org.springframework.samples.petclinic.e2e.plumbing.EndToEndTest

class EditOwnerTest extends EndToEndTest {

  val showPage = new ShowOwnerPage(7) // Jeff Black
  val findPage = new FindOwnersPage


  behavior of "Edit Owner"

  it should "not display the old data after an owner was edited" in {
    go to showPage
    showPage.info shouldBe Owner.jeffBlack

    val editPage = showPage.clickEditOwner()
    eventually {
      editPage.changeFirstName("Steve")
    }
    editPage.clickSubmit()
    showPage.info shouldBe Owner.jeffBlack.copy(firstName = "Steve")

    go to findPage
    findPage.findAllOwners()
    never {
      findPage.listOfOwners should include ("Jeff Black")
    }
  }

}
