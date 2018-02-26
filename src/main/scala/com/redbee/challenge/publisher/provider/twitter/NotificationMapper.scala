package com.redbee.challenge.publisher.provider.twitter

import com.danielasfregola.twitter4s.entities.Tweet
import com.redbee.challenge.publisher.notification.api.{Notification, TweetDto}
import com.redbee.challenge.publisher.user.api.BackendUser
import org.slf4j.LoggerFactory

object NotificationMapper {

  val logger = LoggerFactory.getLogger(getClass)

  def getNotifications(tweet: Tweet, users: List[BackendUser]): List[Notification] = {
    users.foreach(u => logger.info(u.userName))
    val screenName: String = tweet.user match {
      case None => ""
      case Some(u) => u.screen_name
    }
    val tweetDto: TweetDto = TweetDto(screenName, tweet.text, tweet.id)
    users.map(u => Notification(u.userName, tweetDto))
  }

  def filterUsers(tweet: Tweet, users: List[BackendUser]): List[BackendUser] = {
    users.filter(u => containsInterest(u, tweet))
  }

  private def containsInterest(user: BackendUser, tweet: Tweet): Boolean = {
    val interestValues: List[String] = user.interests.hashTags ::: user.interests.profiles
    tweet.text.exists(interestValues.contains)
  }
}
