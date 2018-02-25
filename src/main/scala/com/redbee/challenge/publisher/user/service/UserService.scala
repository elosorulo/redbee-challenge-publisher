package com.redbee.challenge.publisher.user.service

import com.redbee.challenge.publisher.user.api.User

trait UserService {
  def getUsers(): List[User]
}
