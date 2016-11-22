package picobox.app.components

import xstream.XStream
import cycle.dom.DOMSource
import cycle.isolate.Isolate
import snabbdom.{Modifier, VNode}
import snabbdom.tags._
import snabbdom.attrs._
import snabbdom.events._
import snabbdom.styles
import snabbdom.eventStreamToCallback
import org.scalajs.dom.MouseEvent

import scala.scalajs.js.Dynamic.{global => g}
import scala.util.Random

class Counter(
  private val DOMSource: DOMSource
) {
  private val isolate = new Isolate()
  private val isolatedDOMSource = isolate.source(DOMSource)

  private val incClick$ = isolatedDOMSource.select(".inc").events(onClick)
  private val decClick$ = XStream.create[MouseEvent]()
  private val altIncClick$ = XStream.create[MouseEvent]()

  val time1$: XStream[Int] = XStream.periodic(1000).map(i => i + 1).startWith(0)
  val time2$: XStream[Int] = XStream.periodic(2000).map(i => i + 1).startWith(0)
  val time3$: XStream[Int] = XStream.periodic(3000).map(i => i + 1).startWith(0)

  val count$: XStream[Int] = {
    val increment$: XStream[Int] = XStream.merge(incClick$, altIncClick$).map(ev => 1)
    val decrement$: XStream[Int] = decClick$.map(ev => -1)

    XStream.merge(increment$, decrement$)
      .startWith(0)
      .fold((acc: Int, nextVal: Int) => acc + nextVal, 0)
  }

  val DOM$: XStream[VNode] = isolate.sink(DOMSource, {
    val testHover = (e: MouseEvent) => println("some hover")

    count$.map(count =>
      div(
        "Foo",
        "bar",
        button(cls := "inc", typ := "button", "+"),
        button(cls := "dec", onClick := decClick$, typ := "button", "–"),
        p(s"Count = $count, rand = ${Random.nextInt()}"),
        "Hello",
        "world",
        button(id := "xxx", typ := "button", onClick := altIncClick$, "alt+"),
        a(href := "#yolo", "boooo"),
        Seq[Modifier]("yo", b("lo"), "OMG"),
        Some(i("some", onMouseOver := testHover)),
        Option("maybe"),
        None,
        a(styles.border := "5px solid orange", styles.background := "yellow", href := "#yolo", "hoo"),
        time1$.map(time1 => div(s"TIME1: $time1")),
        time2$.map(time2 => div(s"TIME2: $time2")),
        time3$.map(time3 => div(s"TIME3: $time3"))
      )
    )
  })
}
