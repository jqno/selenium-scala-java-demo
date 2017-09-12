# Purpose
This repo is a companion to my Java Magazine article about ScalaTest's Selenium DSL. It's a fork of [a Spring Pet Clinic demo implementation](https://github.com/spring-petclinic/spring-petclinic-reactjs), with a Java back-end and a ReactJS front-end.

# How to run
* Make sure Maven and NPM are installed on your system.
* Open three terminal windows in the project directory.
* The first is for the back-end:
  * `./mvnw spring-boot:run`
* The second is for the front-end:
  * `cd client`
  * `npm install` (if you haven't done so yet)
  * `PORT=4444 npm start`
* The third is for the actual selenium tests:
  * `mvn scalatest:test`
 
# Caveat
The Selenium tests make changes to the in-memory database, but doesn't clean them up (yet). Therefore, if you want to run the tests a second time, you have to restart the back-end server as well.
