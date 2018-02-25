package com.redbee.challenge.publisher.notification.api

case class Notification (channel: String, content: TweetDto)

case class TweetDto(user: String, text: String)