package org.springframework.samples.petclinic.e2e.pages

import org.springframework.samples.petclinic.e2e.data.Owner
import org.springframework.samples.petclinic.e2e.plumbing.AbstractPage

class OwnerPage(val url: String) extends AbstractPage {
  require(url startsWith s"$homepage/owners/")

  val id = url.substring(url.lastIndexOf("/") + 1, url.length).toInt

  val info = {
    val Array(firstName, lastName) = find("ownerName").value.text.split(" ") // Assuming there is exactly 1 space in the name
    Owner(
      firstName,
      lastName,
      find("ownerAddress").value.text,
      find("ownerCity").value.text,
      find("ownerTelephone").value.text)
  }

}
