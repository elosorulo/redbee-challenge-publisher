package com.redbee.challenge.publisher

import com.redbee.challenge.publisher.streamer.Streamer
import org.mockito.Mockito
import org.scalatest.mockito.MockitoSugar
import org.scalatra.test.scalatest._

class PublisherServletTests extends ScalatraFunSuite with MockitoSugar {

  private val streamer = mock[Streamer]

  addServlet(new PublisherServlet(streamer), "/*")

  test("GET / on PublisherServlet health-check should return status 200"){
    Mockito.doNothing().when(streamer).execute()
    Mockito.doNothing().when(streamer).stop()
    get("/health-check"){
      status should equal (200)
    }
  }

}
