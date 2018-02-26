package com.redbee.challenge.publisher.notification

import com.redbee.challenge.publisher.notification.api.Notification


trait NotificationService {
  def publish(notifications: List[Notification])
}
