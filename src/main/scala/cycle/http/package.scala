package cycle

import cycle.base.RawDriver
import _root_.xstream.XStream

import scala.scalajs.js

package object http {

  type HTTPSink = XStream[RequestOptions]

  type RawHTTPDriver = RawDriver[HTTPSink, HTTPSource]

  implicit class RichHTTPSource(val source: HTTPSource) extends AnyVal {

    @inline def filter(predicate: RequestOptions => Boolean): HTTPSource =
      source.filter(predicate)
  }

  implicit class RichResponse (val response: Response) extends AnyVal {

    /** JS object containing the parsed response
      * Note: This field is non-empty only if Content-Type is "application/json" or "application/x-www-form-urlencoded"
      */
    @inline def maybeBody: Option[js.Object] = response.body.toOption

    /** Map of response headers with keys lower-cased, e.g. content-length */
    @inline def headers: Map[String, String] = response.headers.toMap
  }
}
