package com.raquo.cycle.http

import scala.scalajs.js

@js.native
trait HTTPError extends js.Error {
  val response: Response
}
