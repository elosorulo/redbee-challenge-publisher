package com.redbee.challenge.publisher.user.service.impl

import com.redbee.challenge.publisher.user.service.UserServiceExecutor
import com.typesafe.config.ConfigFactory
import dispatch._
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

class UserServiceExecutorImpl extends UserServiceExecutor {

  private val logger = LoggerFactory.getLogger(getClass)
  private implicit val executor = ExecutionContext.global

  def execute(): Future[String] = {
    logger.info("Executing request on getUsers endpoint.")
    val svc = url(ConfigFactory.load().getString("backend.url"))
    Http.default(svc OK as.String)
  }
}
