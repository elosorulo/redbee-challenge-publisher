package com.redbee.challenge.publisher.provider

trait Provider {
  def start(): Unit
  def updateTopics(profiles: List[String], hashTags: List[String]): Unit
  def stop(): Unit
}
