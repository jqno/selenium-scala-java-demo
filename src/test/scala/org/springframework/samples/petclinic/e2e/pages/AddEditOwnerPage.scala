package org.springframework.samples.petclinic.e2e.pages

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.AddEditOwnerPage._
import org.springframework.samples.petclinic.e2e.plumbing.AbstractPage

class AddEditOwnerPage(val url: String) extends AbstractPage {
  require(url startsWith urlPrefix)

  def this(id: Option[Int]) = {
    this(idToUrl(id))
  }

  val id: Option[Int] = urlToId(url)

  def fillIn(info: Owner): Unit = {
    textField(name("firstName")).value = info.firstName
    textField(name("lastName")).value = info.lastName
    textField(name("address")).value = info.address
    textField(name("city")).value = info.city
    textField(name("telephone")).value = info.telephone
  }

  def clickAddOwner(): ShowOwnerPage = {
    click on "submitOwner"
    eventually { new ShowOwnerPage(currentUrl) }
  }

  def isTelephoneInvalid: Boolean = {
    val elt = find(className("help-inline")).value
    elt.isDisplayed && elt.text == "Must be a number with at most 10 digits"
  }
}

object AddEditOwnerPage {
  val urlPrefix = s"${AbstractPage.homepage}/owners"

  def idToUrl(id: Option[Int]): String = id match {
    case Some(n) => s"$urlPrefix/$n/edit"
    case None => s"$urlPrefix/new"
  }

  def urlToId(url: String): Option[Int] = {
    val parts = url.split("/")
    if (parts.last == "new")
      None
    else
      Some(parts(parts.size - 2).toInt)
  }
}
