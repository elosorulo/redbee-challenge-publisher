package com.redbee.challenge.publisher.utils

import io.circe
import org.slf4j.LoggerFactory

object JsonUtils {

  val logger = LoggerFactory.getLogger(getClass)

  def handleDecoding[T](body: => Either[circe.Error, T]): T = {
    body.toOption.get
  }
}
