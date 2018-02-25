package com.redbee.challenge.publisher.streamer

trait Streamer {
  def start(): Unit
  def stop(): Unit
}
