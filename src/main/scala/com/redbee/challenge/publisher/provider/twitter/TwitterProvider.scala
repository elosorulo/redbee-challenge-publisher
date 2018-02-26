package com.redbee.challenge.publisher.provider.twitter

import javax.inject.Inject

import com.danielasfregola.twitter4s.entities.enums.Language
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage
import com.danielasfregola.twitter4s.entities.{Tweet, User}
import com.danielasfregola.twitter4s.{TwitterRestClient, TwitterStreamingClient}
import com.redbee.challenge.publisher.notification.NotificationService
import com.redbee.challenge.publisher.provider.Provider
import com.redbee.challenge.publisher.user.api.BackendUser
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class TwitterProvider @Inject() (notificationService: NotificationService) extends Provider {

  private implicit val executor = ExecutionContext.global
  private val logger = LoggerFactory.getLogger(getClass)
  private val restClient = TwitterRestClient()
  private val streamingClient = TwitterStreamingClient()

  override def start(users: List[BackendUser]): Unit = {
    logger.info("Starting.")

    val exec = restClient.users(users.map(u => u.userName), true)

    exec onComplete {
      case Success(twitterUsers) =>
        val twitterUserIds: Seq[Long] = twitterUsers.data.map(u => userId(u))
        streamingClient.filterStatuses(follow = twitterUserIds, tracks = tracks(users),
          languages = Seq(Language.English, Language.Spanish))(handleTweet(users))
      case Failure(t) =>
        logger.error(s"Error executing users operation on Twitter rest client. Message: ${t.getMessage}.")
    }
  }

  private def tracks(users: List[BackendUser]): Seq[String] = {
    users.flatMap(u => u.interests.hashTags)
  }

  private def userId(u: User): Long = {
    println(s"Id: ${u.id}.")
    u.id
  }

  private def handleTweet(backendUsers: List[BackendUser]): PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => tweet.user match {
      case Some(u) => if(u.followers_count > 5000) {
        matchUsers(tweet, backendUsers)
      }
      case None =>
    }
  }

  private def matchUsers(tweet: Tweet, users: List[BackendUser]): Unit = {
    logger.info(s"Matching users for tweet: ${tweet.text}")
    val notifications = NotificationMapper.getNotifications(tweet, users)
    notificationService.publish(notifications)
  }

  override def updateTopics(users: List[BackendUser]): Unit = {
    logger.info("Updating topics.")
  }

  override def stop(): Unit = {
    logger.info("Stopping.")
  }
}
