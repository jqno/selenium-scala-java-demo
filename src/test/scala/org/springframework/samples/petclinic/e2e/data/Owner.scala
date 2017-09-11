package org.springframework.samples.petclinic.e2e.data

case class Owner(firstName: String, lastName: String, address: String, city: String, telephone: String) {
  def fullName = s"$firstName $lastName"
}

object Owner {
  val bettyDavis = Owner("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749")
  val jeffBlack = Owner("Jeff", "Black", "1450 Oak Blvd.", "Monona", "6085555387")

  val someValidOwner = Owner("Martin", "Crane", "400 Broad St.", "Seattle", "5551234567")
}
