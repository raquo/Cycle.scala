package cycle.http

import xstream.XStream

import scala.scalajs.js

@js.native
trait RawResponseStream extends XStream[RawResponse] {
  val request: RequestOptions
}
