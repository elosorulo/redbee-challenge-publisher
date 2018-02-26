package com.redbee.challenge.publisher.notification.impl

import java.nio.charset.Charset

import com.redbee.challenge.publisher.notification.NotificationService
import com.redbee.challenge.publisher.notification.api.{DeepstreamBody, Notification, Record}
import com.typesafe.config.ConfigFactory
import dispatch.{Http, as, url}
import org.slf4j.LoggerFactory
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class NotificationServiceImpl extends NotificationService {

  val logger = LoggerFactory.getLogger(getClass)
  private implicit val executor = ExecutionContext.global

  override def publish(notifications: List[Notification]): Unit = {
    notifications.foreach(n => logger.info(s"Notification ${n.toString}"))
  }

  private def execute(notifications: List[Notification]): Unit = {
    val req = url(ConfigFactory.load().getString("deepstream.url")).POST

    val body: DeepstreamBody = DeepstreamBody(body = notifications.map(n =>
      Record(topic = n.channel, action = "write",
        recordName = n.content.tweetId.toString,
        path = s"${n.channel}/${n.content.tweetId}", data = n.content, version = 0)))

    req.setContentType("application/json", Charset.forName("UTF-8")) << body.asJson.noSpaces
    Http.default(req OK as.String) onComplete {
      case Success(content) => {
        logger.info(s" Successfully executed publish operation. $content")
      }
      case Failure(error) => {
        logger.error(s"Error executing publish operation. ${error.getMessage}")
      }
    }
  }
}
