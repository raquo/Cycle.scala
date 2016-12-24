package picobox.app.components

import xstream.XStream
import cycle.dom.{DOMSink, DOMSinks, DOMSources}
import cycle.http.{HTTPSink, HTTPSinks, HTTPSources, RequestOptions, Response}
import cycle.isolate.Isolate

import scala.scalajs.js.annotation.ScalaJSDefined
import snabbdom.tags._

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
    val $request = XStream.fromSeq(Seq(new RequestOptions(
      url = "https://randomuser.me/api/",
      method = "GET",
      category = category
    )))
    val $$response = sources.HTTP.select(category).debug("$$$").map(_.stream).debug("$$")
    val $response = $$response.flatten.debug("$")

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
    )

    new RandomUser($vnode, $request)
  }
}
