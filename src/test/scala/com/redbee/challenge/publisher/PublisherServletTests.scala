package com.redbee.challenge.publisher

import org.scalatra.test.scalatest._

class PublisherServletTests extends ScalatraFunSuite {

  addServlet(classOf[PublisherServlet], "/*")

  test("GET / on PublisherServlet should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}
