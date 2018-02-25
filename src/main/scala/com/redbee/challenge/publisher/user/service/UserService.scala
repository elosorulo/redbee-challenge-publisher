package com.redbee.challenge.publisher.user.service

import com.redbee.challenge.publisher.user.api.BackendUser

trait UserService {
  def getUsers(): List[BackendUser]
}
