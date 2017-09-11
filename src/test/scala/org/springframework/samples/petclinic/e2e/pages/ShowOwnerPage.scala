package org.springframework.samples.petclinic.e2e.pages

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.ShowOwnerPage._
import org.springframework.samples.petclinic.e2e.plumbing.AbstractPage

class ShowOwnerPage(val url: String) extends AbstractPage {
  require(url startsWith urlPrefix)

  def this(id: Int) = {
    this(s"$urlPrefix/$id")
  }

  val id: Int = urlToId(url)

  def info: Owner = {
    val Array(firstName, lastName) = find("ownerName").value.text.split(" ") // Assuming there is exactly 1 space in the name
    Owner(
      firstName,
      lastName,
      find("ownerAddress").value.text,
      find("ownerCity").value.text,
      find("ownerTelephone").value.text)
  }

  def clickEditOwner(): AddEditOwnerPage = {
    click on "editOwner"
    new AddEditOwnerPage(Some(id))
  }
}

object ShowOwnerPage {
  val urlPrefix = s"${AbstractPage.homepage}/owners"

  def urlToId(url: String): Int =
    url.substring(url.lastIndexOf("/") + 1, url.length).toInt
}
