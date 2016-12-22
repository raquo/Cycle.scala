package cycle.http

import scala.scalajs.js

class Response (rawResponse: RawResponse) {

  val text: Option[String] = rawResponse.text.toOption

  val body: Option[js.Object] = rawResponse.body.toOption

  val header: Option[Map[String, String]] = rawResponse.header.toOption.map(_.toMap)

  val typ: Option[String] = rawResponse.typ.toOption

  val status: Option[Int] = rawResponse.status.toOption

  val request: Option[RequestOptions] = rawResponse.request.toOption
}
