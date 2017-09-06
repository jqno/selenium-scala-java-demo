package org.springframework.samples.petclinic.e2e.data

case class Owner(firstName: String, lastName: String, address: String, city: String, telephone: String) {
  def fullName = s"$firstName $lastName"
}

object Owner {
  val bettyDavis = Owner("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749")
}
