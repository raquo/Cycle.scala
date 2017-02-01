package com.raquo.cycle.http

import com.raquo.xstream.XStream

import scala.scalajs.js

// Note: Because RawHTTPError is a js.native trait, it is impossible to distinguish from any other js.Error within the type system
@js.native
trait RawResponseStream extends XStream[Response, Nothing /*RawHTTPError*/] {
  val request: RequestOptions
}
