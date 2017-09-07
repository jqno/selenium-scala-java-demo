package org.springframework.samples.petclinic.e2e.pages

import org.springframework.samples.petclinic.e2e.plumbing.AbstractPage

class FindOwnersPage extends AbstractPage {

  val url = s"${AbstractPage.homepage}/owners/list"


  def findOwner(filter: String): Unit = {
    textField(name("filter")).value = filter
    click on "findOwner"
  }

  def findAllOwners(): Unit = {
    click on "findOwner"
  }

  def listOfOwners: String = {
    find(cssSelector("tbody")).value.text
  }

  def numberOfOwners: Int = {
    findAll(cssSelector("tbody tr")).size
  }

  def clickOwner(name: String): OwnerPage = eventually {
    click on linkText(name)
    new OwnerPage(currentUrl)
  }

  def clickAddOwner(): OwnerPage = eventually {
    click on linkText("Add Owner")
    new OwnerPage(currentUrl)
  }

}
