package com.redbee.challenge.publisher.notification.api

case class Notification (channel: String, content: Tweet)

case class Tweet(user: String, text: String)