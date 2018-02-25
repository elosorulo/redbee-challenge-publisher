package com.redbee.challenge.publisher.config

import com.google.inject.Guice
import com.redbee.challenge.publisher.streamer.Streamer
import net.codingwell.scalaguice.InjectorExtensions._
import org.slf4j.LoggerFactory

trait Initializer {

  val logger = LoggerFactory.getLogger(getClass)

  protected def initialize(): Streamer = {
    logger.info("Injecting Dependencies.")
    logger.info("Creating injector.")
    val injector = Guice.createInjector(new PublisherModule)
    logger.info("Getting Streamer instance.")
    injector.instance[Streamer]
  }
}
