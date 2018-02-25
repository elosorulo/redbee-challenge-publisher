package com.redbee.challenge.publisher.provider

import com.redbee.challenge.publisher.user.api.BackendUser

trait Provider {
  def start(users: List[BackendUser]): Unit
  def updateTopics(users: List[BackendUser]): Unit
  def stop(): Unit
}
