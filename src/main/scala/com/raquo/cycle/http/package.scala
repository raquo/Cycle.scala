package com.raquo.cycle

import com.raquo.cycle.base.RawDriver
import com.raquo.xstream.XStream
import com.raquo.xstream.EStream

import scala.scalajs.js
import scala.scalajs.js.|

package object http {

  type HTTPSink[Err <: Exception] = EStream[RequestOptions, Err]

  type RawHTTPDriver = RawDriver[HTTPSink[Nothing], HTTPSource]

  implicit class RichHTTPSource(val source: HTTPSource) extends AnyVal {

    @inline def selectByRequest(requestOptions: RequestOptions): XStream[Response] =
      source.filter { actualRequestOptions: RequestOptions =>
        actualRequestOptions == requestOptions
      }.selectAll()

    @inline def selectAll(): XStream[Response] =
      source.select().flatten.replaceAllErrors(replaceHTTPError)

    @inline def selectAllByCategory(category: String): XStream[Response] =
      source.select(category).flatten.replaceAllErrors(replaceHTTPError)

    @inline def filter(predicate: RequestOptions => Boolean): HTTPSource =
      source.filter(predicate)

    private def replaceHTTPError(error: Exception | js.Error): XStream[Response] = {
      val isJSError = !error.isInstanceOf[Exception]
      val errorResponse = error.asInstanceOf[js.Dynamic].response.asInstanceOf[js.UndefOr[Response]]
      val isHTTPError = isJSError && errorResponse.isDefined

      if (isHTTPError) {
        // @TODO[Integrity] is it ok to return a completed stream here?
        XStream.of(errorResponse.get)
      } else if (isJSError) {
        // Some other JS error, re-throw it
        throw js.JavaScriptException(error.asInstanceOf[js.Error])
      } else {
        // Some Scala exception, re-throw it
        throw error.asInstanceOf[Exception]
      }
    }
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
