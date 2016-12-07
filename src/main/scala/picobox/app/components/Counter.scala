package picobox.app.components

import xstream.XStream
import cycle.dom.{DOMSink, DOMSinks, DOMSources}
import cycle.isolate.Isolate

import scala.scalajs.js.annotation.ScalaJSDefined
import snabbdom.{DOMEventStream, Modifier, VNode, styles}
import snabbdom.tags._
import snabbdom.attrs._
import snabbdom.events._
import org.scalajs.dom.MouseEvent

import scala.scalajs.js.Dynamic.{global => g}
import scala.util.Random

@ScalaJSDefined
class Counter private(
  val DOM: DOMSink,
  val countStream: XStream[Int]
) extends DOMSinks

// @TODO[WTF] figure out why this will fail if I rename $decClick to decClick$ (trailing dollar sign!?)
//object x {
//  def foo(): Unit = {
//    val $decClick: DOMEventStream[MouseEvent] = ???
//    val cb: MouseEventCallback = ???
//
//    val project = () => button(onClick := $decClick, "–")
//  }
//}

object Counter {

  // @TODO[Performance] is this pattern efficient? Does it create a new function every time it's called?
  // @TODO[API] Explore other options, e.g. class Counter & CounterSinks – might be cleaner and more performant
  def apply(intervalFactor: Double = 1): (DOMSources => Counter) = Isolate { sources =>
    val $incClick = sources.DOM.select(".inc").events(onClick)
    val $decClick = new DOMEventStream[MouseEvent]
    val $altIncClick = new DOMEventStream[MouseEvent]

    val $time1 = XStream.periodic((intervalFactor * 1000).toInt).map(i => i + 1).startWith(0)
    val $time2 = XStream.periodic((intervalFactor * 2000).toInt).map(i => i + 1).startWith(0)
    val $time3 = XStream.periodic((intervalFactor * 3000).toInt).map(i => i + 1).startWith(0)

    val $increment = XStream.merge($incClick, $altIncClick).map(ev => 1)
    val $decrement = $decClick.map(ev => -1)

    val $count = XStream.merge($increment, $decrement)
      .startWith(0)
      .fold((acc: Int, nextVal: Int) => acc + nextVal, 0)

    val testHover = (e: MouseEvent) => println("some hover")

    val test: XStream[VNode] = $time1.map(time1 => div(s"TIME1: $time1"))

    // Note that Random.nextInt will NOT be updated if time$* updates, only when count$ updates. Because transposition.
    val $vnode = $count.map(count =>
      div(
        "Foo",
        "bar",
        button(cls := "inc", typ := "button", "+"),
        button(cls := "dec", onClick := $decClick, typ := "button", "–"),
        p(s"Count = $count"),
        p(s"rand = ${Random.nextInt()}"),
        "Hello",
        "world",
        test,
        button(id := "xxx", typ := "button", onClick := $altIncClick, "alt+"), // @TODO bring this back
        a(href := "#yolo", "boooo"),
        Seq[Modifier]("yo", b("lo"), "OMG"),
        Option("maybe"),
        None,
        Some(i("some", onMouseOver := testHover)), // @TODO bring this back
        a(styles.border := "5px solid orange", styles.background := "yellow", href := "#yolo", "hoo"),
        $time1.map(time1 => div(s"TIME1: $time1")),
        $time2.map(time2 => div(s"TIME2: $time2")),
        $time3.map(time3 => div(s"TIME3: $time3")),
        "FOO"
      )
    )

    new Counter($vnode, $count)
  }
}
