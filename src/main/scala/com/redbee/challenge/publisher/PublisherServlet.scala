package com.redbee.challenge.publisher

import org.scalatra._

class PublisherServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }

}
