package com.redbee.challenge.publisher.config

import com.redbee.challenge.publisher.streamer.Streamer
import com.redbee.challenge.publisher.streamer.impl.StreamerImpl
import net.codingwell.scalaguice.ScalaModule

class PublisherModule extends ScalaModule {
  override def configure(): Unit = {
    bind[Streamer].to[StreamerImpl].asEagerSingleton()
  }
}
