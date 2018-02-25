package com.redbee.challenge.publisher.streamer.impl

import com.danielasfregola.twitter4s.TwitterRestClient
import com.redbee.challenge.publisher.streamer.Streamer
import com.redbee.challenge.publisher.user.api.{Interests, User}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}


class StreamerImpl () extends Streamer {

  implicit val executor = ExecutionContext.global
  private val logger = LoggerFactory.getLogger(getClass)

  override def start(): Unit = {
    logger.info("Starting Streamer.")
    val users: List[User] = List(
      User(
        userName = "jorge",
        email = "jorge@mail.com",
        interests = Interests(
          profiles = List("leomessi"),
          hashTags = List("futbol"))),
      User(
        userName = "pepe",
        email = "pepe@mail.com",
        interests = Interests(
          profiles = List("leomessi"),
          hashTags = List("politics"))))
    streamFromTwitter(users)
  }

  override def stop(): Unit = {
    logger.info("Stopping Streamer.")
  }

  private def streamFromTwitter(aggregatorUsers: List[User]): Unit = {
    val restClient = TwitterRestClient()
    val exec = restClient.users(aggregatorUsers.map(u => u.userName), true)

    exec onComplete {
      case Success(users) => println(users.data.foreach(u => println(s"Id: ${u.id}.")))
      case Failure(t) =>
        logger.error(s"Error executing users operation on Twitter rest client. Message: ${t.getMessage}.")
    }
    //val streamingClient = TwitterStreamingClient().filterStatuses()
  }
}
