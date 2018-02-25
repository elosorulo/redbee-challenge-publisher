package com.redbee.challenge.publisher.streamer.impl

import javax.inject.Inject

import com.redbee.challenge.publisher.provider.Provider
import com.redbee.challenge.publisher.streamer.Streamer
import com.redbee.challenge.publisher.user.service.UserService
import org.slf4j.LoggerFactory


class StreamerImpl @Inject() (userService: UserService, provider: Provider) extends Streamer {

  private val logger = LoggerFactory.getLogger(getClass)

  override def start(): Unit = {
    logger.info("Starting Streamer.")
    provider.start(userService.getUsers())
  }

  override def stop(): Unit = {
    logger.info("Stopping Streamer.")
    provider.stop()
  }
}
