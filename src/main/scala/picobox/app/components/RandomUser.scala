package picobox.app.components

import com.raquo.xstream.XStream
import com.raquo.cycle.dom.{DOMSink, DOMSinks, DOMSources}
import com.raquo.cycle.http.{HTTPError, HTTPSink, HTTPSinks, HTTPSources, RequestOptions}
import com.raquo.cycle.isolate.Isolate

import scala.scalajs.js.annotation.ScalaJSDefined
import com.raquo.snabbdom.tags._

import scala.scalajs.js

@ScalaJSDefined
class RandomUser private(
  val DOM: DOMSink,
  val HTTP: HTTPSink
) extends DOMSinks with HTTPSinks

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
      val name = response.maybeBody.map(
        body => body.asInstanceOf[js.Dynamic].results.asInstanceOf[js.Array[js.Dynamic]](0).name.first.asInstanceOf[String]
      ).getOrElse("Response was not parsed")

      div(
        h2("Random user"),
        div(b("Status: "), s"${response.statusCode} ${response.statusText}"),
        div(
          b("Headers:"),
          response.headers.map {
            case (key: String, value: String) => div(s"$key: $value")
          }.toSeq // @TODO[API] Inconvenient. Use Iterable, not Seq
        ),
        div(b("Parsed name: "), name),
        div(b("Raw content: "), pre(response.text))
      )
    }.startWith(
      div("Loading random user...")
    ).replaceError { (err: HTTPError) =>
      XStream.of(div(s" - Error loading user (${err.response.statusCode}) :("))
    }

    new RandomUser($vnode, $request)
  }
}
