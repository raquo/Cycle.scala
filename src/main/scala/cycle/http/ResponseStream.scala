package cycle.http

import xstream.XStream

import scala.scalajs.js

@js.native
trait ResponseStream extends XStream[Response] {
  val request: RequestOptions
}
