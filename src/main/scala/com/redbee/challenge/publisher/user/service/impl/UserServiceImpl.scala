package com.redbee.challenge.publisher.user.service.impl

import com.redbee.challenge.publisher.user.api.{BackendUser, Interests}
import com.redbee.challenge.publisher.user.service.UserService

class UserServiceImpl extends UserService {
  override def getUsers(): List[BackendUser] = {
    List(
      BackendUser(
        userName = "jorge",
        email = "jorge@mail.com",
        interests = Interests(
          profiles = List("leomessi"),
          hashTags = List("futbol"))),
      BackendUser(
        userName = "pepe",
        email = "pepe@mail.com",
        interests = Interests(
          profiles = List("realdonaldtrump"),
          hashTags = List("politics", "illustration", "memes", "art", "netflix"))))
  }
}
