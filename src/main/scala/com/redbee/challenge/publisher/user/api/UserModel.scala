package com.redbee.challenge.publisher.user.api

case class User(userName: String, email: String, interests: Interests)

case class Interests(profiles: List[String], hashTags: List[String])

case class ExecutionStatus(statusCode: Int, executionType: String, message: Option[String])

case class GetUsersResponse(executionStatus: ExecutionStatus, users: List[User])

trait ExecutionTypes {
  val SUCCEEDED = "SUCCEEDED"
  val WARNING = "WARNING"
  val UNKNOWN_ERROR = "UNKNOWN_ERROR"
  val BUSINESS_ERROR = "BUSINESS_ERROR"
  val INTERNAL_ERROR = "INTERNAL_ERROR"
  val INVALID_INPUT_ERROR = "INVALID_INPUT_ERROR"
}
