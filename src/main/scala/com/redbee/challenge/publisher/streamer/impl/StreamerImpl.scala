package com.redbee.challenge.publisher.streamer.impl

import javax.inject.Inject

import com.redbee.challenge.publisher.provider.Provider
import com.redbee.challenge.publisher.streamer.Streamer
import com.redbee.challenge.publisher.user.api.GetUsersResponse
import com.redbee.challenge.publisher.user.service.UserServiceExecutor
import com.redbee.challenge.publisher.utils.JsonUtils
import io.circe.generic.auto._
import io.circe.parser.decode
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}


class StreamerImpl @Inject() (userServiceExecutor: UserServiceExecutor, provider: Provider) extends Streamer {

  private val logger = LoggerFactory.getLogger(getClass)
  private implicit val executor = ExecutionContext.global

  override def execute(): Unit = {
    logger.info("Starting Streamer.")
    userServiceExecutor.execute() onComplete {
      case Success(content) =>
        logger.info(s"Successfully executed getUsers. $content.")
        provider.start(JsonUtils.handleDecoding(decode[GetUsersResponse](content)).users)
      case Failure(error) =>
        logger.error(s"Failure executing getUsers. ${error.getMessage}")
    }
  }
}
