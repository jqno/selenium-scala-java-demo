package org.springframework.samples.petclinic.e2e.pages

import org.springframework.samples.petclinic.e2e.plumbing.AbstractPage

class OwnerPage(val url: String) extends AbstractPage {
  require(url startsWith s"$homepage/owners/")

  val id = url.substring(url.lastIndexOf("/") + 1, url.length).toInt

  val info = Owner(
    find("ownerName").value.text,
    find("ownerAddress").value.text,
    find("ownerCity").value.text,
    find("ownerTelephone").value.text)

}

case class Owner(name: String, address: String, city: String, telephone: String)
