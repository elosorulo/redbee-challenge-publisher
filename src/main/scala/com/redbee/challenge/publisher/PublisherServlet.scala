package com.redbee.challenge.publisher

import com.redbee.challenge.publisher.streamer.Streamer
import org.scalatra._
import org.slf4j.LoggerFactory

class PublisherServlet(streamer: Streamer) extends ScalatraServlet {

  val logger = LoggerFactory.getLogger(getClass)

  get("/health-check") {
    logger.info("Executing health check.")
  }

  get("/start") {
    logger.info("Executing start request.")
    streamer.start()
  }

  get("/stop") {
    logger.info("Executing stop request.")
    streamer.stop()
  }

}
