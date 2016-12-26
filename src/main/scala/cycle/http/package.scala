package cycle

import cycle.base.RawDriver
import _root_.xstream.XStream

import scala.scalajs.js

package object http {

  type HTTPSink = XStream[RequestOptions]

  type RawHTTPDriver = RawDriver[HTTPSink, HTTPSource]

  implicit class RichHTTPSource(val source: HTTPSource) extends AnyVal {

    @inline def selectByRequest(requestOptions: RequestOptions): XStream[Response] =
      source.filter { actualRequestOptions: RequestOptions =>
        actualRequestOptions == requestOptions
      }.selectAll()

    @inline def selectAll(): XStream[Response] =
      source.select().flatten

    @inline def selectAllByCategory(category: String): XStream[Response] =
      source.selectByCategory(category).flatten

    @inline def filter(predicate: RequestOptions => Boolean): HTTPSource =
      source.filter(predicate)
  }

  implicit class RichResponse (val response: Response) extends AnyVal {

    /** JS object containing the parsed response
      * Note: This field is non-empty only if Content-Type is "application/json" or "application/x-www-form-urlencoded"
      */
    @inline def maybeBody: Option[js.Object] =
      // flatMap is needed to address the case of Some(null)
      response.body.toOption.flatMap(Option(_))

    /** statusCode is 4xx or 5xx */
    @inline def maybeError: Option[js.Error] =
      response.error match {
        case error: js.Error => Some(error)
        case _ => None
      }

    /** Map of response headers with keys lower-cased, e.g. content-length */
    @inline def headers: Map[String, String] =
      response.headers.toMap
  }
}
