package example.app.components

import com.raquo.xstream.XStream
import com.raquo.cycle.dom.{DOMSink, DOMSinks, DOMSources}
import com.raquo.cycle.http.{RawHTTPError, HTTPSink, HTTPSinks, HTTPSources, RequestOptions}
import com.raquo.cycle.isolate.Isolate

import scala.scalajs.js.annotation.ScalaJSDefined
import com.raquo.snabbdom.tags._

import scala.scalajs.js

@ScalaJSDefined
class RandomUser private(
  val DOM: DOMSink[Nothing],
  val HTTP: HTTPSink[Nothing]
) extends DOMSinks[Nothing] with HTTPSinks[Nothing]

object RandomUser {

  def apply(): (DOMSources with HTTPSources => RandomUser) = Isolate { sources =>

    // @TODO make request in response to button click

    val category = "randomUser"
    val request = new RequestOptions(
      url = "https://randomuser.me/api/",
      method = "GET"
    )
    val $request = XStream.of(request)
    val $response = sources.HTTP.selectByRequest(request)

    val $vnode = $response.map { response =>
      if (response.isOk) {
        val name = response.maybeBody.map(
          body => body.asInstanceOf[js.Dynamic].results.asInstanceOf[js.Array[js.Dynamic]](0).name.first.asInstanceOf[String]
        ).getOrElse("Response was not parsed")

        val headers = response.headers.map {
          case (key: String, value: String) => div(s"$key: $value")
        }

        div(
          h2("Random user"),
          div(b("Status: "), s"${response.statusCode} ${response.statusText}"),
          div(
            b("Headers:"),
            headers
          ),
          div(b("Parsed name: "), name),
          div(b("Raw content: "), pre(response.text))
        )
      } else {
        div(s" - Error loading user (${response.statusCode}) :(")
      }
    }.startWith(
      div("Loading random user...")
    )

    new RandomUser($vnode, $request)
  }
}
