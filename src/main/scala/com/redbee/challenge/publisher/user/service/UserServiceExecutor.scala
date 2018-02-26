package com.redbee.challenge.publisher.user.service

import dispatch.Future

trait UserServiceExecutor {
  def execute(): Future[String]
}
