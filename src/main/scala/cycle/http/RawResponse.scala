package cycle.http

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait RawResponse extends js.Object {
  val text: js.UndefOr[String] = js.native
  val body: js.UndefOr[js.Object] = js.native
  val header: js.UndefOr[js.Dictionary[String]] = js.native
  @JSName("type")
  val typ: js.UndefOr[String] = js.native
  val status: js.UndefOr[Int] = js.native
  val request: js.UndefOr[RequestOptions] = js.native
}
