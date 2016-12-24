package cycle.http

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/** SuperAgent.js response object
  * For more detailed docs @see http://visionmedia.github.io/superagent/#response-properties
  */
@js.native
trait Response extends js.Object {

  val request: RequestOptions = js.native


  // --- Content and headers ---

  /** Unparsed response text
    *
    * Note: this field is missing on node.js unless mime type matches "text/", "/json", or "x-www-form-urlencoded".
    * This is not reflected in the type signature.
    * */
  val text: String = js.native

  // @TODO[Docs] test what happens if response content can not be parsed - undefined I guess?
  // @TODO[API] this should somehow integrate with third party Scala JSON libs without overhead of parsing JSON twice

  /** JS object containing the parsed response
    * Note: This field is defined only if Content-Type is "application/json" or "application/x-www-form-urlencoded"
    */
  private[http] val body: js.UndefOr[js.Object] = js.native

  /** Dictionary of response headers with keys lower-cased, e.g. content-length */
  @JSName("header")
  private[http] val headers: js.Dictionary[String] = js.native

  /** The first part of Content-Type response header, e.g. text/html */
  @JSName("type")
  val contentType: String = js.native

  /** The charset from Content-Type response header */
  val charset: String = js.native


  // --- Status codes ---

  val statusCode: Int = js.native

  val statusText: String = js.native

  /** statusCode divided by 100, or zero */
  val statusType: Int = js.native

  /** statusCode is 1xx */
  @JSName("info")
  val isInfo: Boolean = js.native


  // --- Basic status code summary ---

  /** statusCode is 2xx */
  @JSName("ok")
  val isOk: Boolean = js.native

  /** statusCode is 4xx */
  @JSName("clientError")
  val isClientError: Boolean = js.native

  /** statusCode is 5xx */
  @JSName("serverError")
  val isServerError: Boolean = js.native

  /** statusCode is 4xx or 5xx */
  @JSName("error")
  val isError: Boolean = js.native


  // --- Specific status codes ---

  /** statusCode is 202 */
  val accepted: Boolean = js.native

  /** statusCode is 204 or 1223 */
  val noContent: Boolean = js.native

  /** statusCode is 400 */
  val badRequest: Boolean = js.native

  /** statusCode is 401 */
  val unauthorized: Boolean = js.native

  /** statusCode is 406 */
  val notAcceptable: Boolean = js.native

  /** statusCode is 404 */
  val notFound: Boolean = js.native

  /** statusCode is 403 */
  val forbidden: Boolean = js.native
}
