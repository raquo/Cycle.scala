package cycle.http

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Attachment (
  val name: String,
  val path: js.UndefOr[String] = js.undefined,
  val filename: js.UndefOr[String] = js.undefined
) extends js.Object

@ScalaJSDefined
class AgentOptions (
  val key: String,
  val cert: String
) extends js.Object

@js.native
trait RawResponse extends js.Object {
  val text: js.UndefOr[String] = js.native
  val body: js.UndefOr[js.Object] = js.native
  val header: js.UndefOr[js.Object] = js.native
  val `type`: js.UndefOr[String] = js.native
  val status: js.UndefOr[Int] = js.native
  val request: js.UndefOr[RequestOptions] = js.native
}

@ScalaJSDefined
class RequestOptions (
  val url: String,
  val method: js.UndefOr[String] = js.undefined,
  val query: js.UndefOr[js.Object] = js.undefined,
  val send: js.UndefOr[js.Object] = js.undefined,
  val headers: js.UndefOr[js.Object] = js.undefined,
  val accept: js.UndefOr[String] = js.undefined,
  val `type`: js.UndefOr[String] = js.undefined,
  val user: js.UndefOr[String] = js.undefined,
  val password: js.UndefOr[String] = js.undefined,
  val field: js.UndefOr[js.Object] = js.undefined,
  val progress: js.UndefOr[Boolean] = js.undefined,
  val attach: js.UndefOr[js.Array[js.Object]] = js.undefined, // Array<Attachment>
  val agent: js.UndefOr[js.Object] = js.undefined, // AgentOptions
  val withCredentials: js.UndefOr[Boolean] = js.undefined,
  val redirects: js.UndefOr[Int] = js.undefined,
  val category: js.UndefOr[String],
  val `lazy`: js.UndefOr[Boolean] = js.undefined
) extends js.Object
