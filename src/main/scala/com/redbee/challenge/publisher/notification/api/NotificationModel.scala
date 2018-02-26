package com.redbee.challenge.publisher.notification.api

case class Notification (channel: String, content: TweetDto)

case class TweetDto(user: String, text: String, tweetId: Long)

case class DeepstreamBody(body: List[Record])

case class Record(topic: String, action: String, recordName: String, path: String, version: Int, data: TweetDto)