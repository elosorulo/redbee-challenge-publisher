package com.redbee.challenge.publisher.config

import com.redbee.challenge.publisher.notification.NotificationService
import com.redbee.challenge.publisher.notification.impl.NotificationServiceImpl
import com.redbee.challenge.publisher.provider.Provider
import com.redbee.challenge.publisher.provider.twitter.TwitterProvider
import com.redbee.challenge.publisher.streamer.Streamer
import com.redbee.challenge.publisher.streamer.impl.StreamerImpl
import com.redbee.challenge.publisher.user.service.UserService
import com.redbee.challenge.publisher.user.service.impl.UserServiceImpl
import net.codingwell.scalaguice.ScalaModule

class PublisherModule extends ScalaModule {
  override def configure(): Unit = {
    bind[Streamer].to[StreamerImpl].asEagerSingleton()
    bind[Provider].to[TwitterProvider].asEagerSingleton()
    bind[UserService].to[UserServiceImpl].asEagerSingleton()
    bind[NotificationService].to[NotificationServiceImpl].asEagerSingleton()
  }
}
