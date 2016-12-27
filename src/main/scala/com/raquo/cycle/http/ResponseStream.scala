package com.raquo.cycle.http

import com.raquo.xstream.XStream

import scala.scalajs.js

@js.native
trait ResponseStream extends XStream[Response, HTTPError] {
  val request: RequestOptions
}
