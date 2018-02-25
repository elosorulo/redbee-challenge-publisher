package com.redbee.challenge.publisher.provider.twitter

import javax.inject.Inject

import com.danielasfregola.twitter4s.TwitterRestClient
import com.redbee.challenge.publisher.notification.NotificationService
import com.redbee.challenge.publisher.provider.Provider
import com.redbee.challenge.publisher.user.api.BackendUser
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class TwitterProvider @Inject() (notificationService: NotificationService) extends Provider {

  implicit val executor = ExecutionContext.global

  val logger = LoggerFactory.getLogger(getClass)

  override def start(users: List[BackendUser]): Unit = {
    logger.info("Starting.")
    val restClient = TwitterRestClient()
    val exec = restClient.users(users.map(u => u.userName), true)

    exec onComplete {
      case Success(twitterUsers) => println(twitterUsers.data.foreach(u => println(s"Id: ${u.id}.")))
      case Failure(t) =>
        logger.error(s"Error executing users operation on Twitter rest client. Message: ${t.getMessage}.")
    }
  }

  override def updateTopics(users: List[BackendUser]): Unit = {
    logger.info("Updating topics.")
  }

  override def stop(): Unit = {
    logger.info("Stopping.")
  }
}
