package com.redbee.challenge.publisher.streamer.impl

import com.redbee.challenge.publisher.streamer.Streamer
import org.slf4j.LoggerFactory

class StreamerImpl () extends Streamer {

  val logger = LoggerFactory.getLogger(getClass)

  override def start(): Unit = {
    logger.info("Starting Streamer.")
  }

  override def stop(): Unit = {
    logger.info("Stopping Streamer.")
  }
}
