package cycle.http

import xstream.XStream

class ResponseStream(rawResponseStream: RawResponseStream) {

  val stream: XStream[Response] =
    rawResponseStream.map(new Response(_))

  val request: RequestOptions =
    rawResponseStream.request
}
