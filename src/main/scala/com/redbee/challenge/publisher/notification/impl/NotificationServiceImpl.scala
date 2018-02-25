package com.redbee.challenge.publisher.notification.impl

import javax.management.Notification

import com.redbee.challenge.publisher.notification.NotificationService
import org.slf4j.LoggerFactory

class NotificationServiceImpl extends NotificationService {

  val logger = LoggerFactory.getLogger(getClass)

  override def publish(notification: Notification): Unit = {
    logger.info(s"Notification ${notification.toString}")
  }
}
