package com.redbee.challenge.publisher.notification

import javax.management.Notification

trait NotificationService {
  def publish(notification: Notification)
}
