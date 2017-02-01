package com.raquo.cycle.http

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class HTTPError private (
  val response: Response,
  val rawException: RawHTTPError
) extends js.Object

object HTTPError {
  def apply(rawHTTPError: RawHTTPError): HTTPError = {
    new HTTPError(rawHTTPError.response, rawHTTPError)
  }
}
