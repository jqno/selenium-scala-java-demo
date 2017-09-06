package org.springframework.samples.petclinic.e2e.pages

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.pages.OwnerPage._
import org.springframework.samples.petclinic.e2e.plumbing.AbstractPage

class OwnerPage(val url: String) extends AbstractPage {
  require(url startsWith urlPrefix)

  def this(id: Option[Int]) = {
    this(idToUrl(id))
  }

  val id: Option[Int] = urlToId(url)

  def info: Owner = {
    val Array(firstName, lastName) = find("ownerName").value.text.split(" ") // Assuming there is exactly 1 space in the name
    Owner(
      firstName,
      lastName,
      find("ownerAddress").value.text,
      find("ownerCity").value.text,
      find("ownerTelephone").value.text)
  }

  def fillIn(info: Owner): Unit = {
    textField(name("firstName")).value = info.firstName
    textField(name("lastName")).value = info.lastName
    textField(name("address")).value = info.address
    textField(name("city")).value = info.city
    textField(name("telephone")).value = info.telephone
  }

  def clickAddOwner(): OwnerPage = {
    click on "submitOwner"
    new OwnerPage(currentUrl)
  }

  def isTelephoneInvalid: Boolean = {
    val elt = find(className("help-inline")).value
    elt.isDisplayed && elt.text == "Must be a number with at most 10 digits"
  }
}

object OwnerPage {
  val urlPrefix = s"${AbstractPage.homepage}/owners"

  def idToUrl(id: Option[Int]): String = id match {
    case Some(n) => s"$urlPrefix/$n"
    case None => s"$urlPrefix/new"
  }

  def urlToId(url: String): Option[Int] = url.substring(url.lastIndexOf("/") + 1, url.length) match {
    case "new" => None
    case n => Some(n.toInt)
  }
}
