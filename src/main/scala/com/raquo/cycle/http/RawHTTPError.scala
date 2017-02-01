package com.raquo.cycle.http

import scala.scalajs.js

@js.native
trait RawHTTPError extends js.Error {
  val response: Response
}
