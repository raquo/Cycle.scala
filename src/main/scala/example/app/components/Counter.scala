package example.app.components

import com.raquo.xstream.XStream
import com.raquo.xstream.XStream.{combine, merge, periodic}
import com.raquo.cycle.dom.{DOMSink, DOMSinks, DOMSources}
import com.raquo.cycle.dom.Transpose
import com.raquo.cycle.isolate.Isolate
import com.raquo.snabbdom.{Modifier, VNode, styles}
import com.raquo.snabbdom.tags._
import com.raquo.snabbdom.attrs._
import com.raquo.snabbdom.events._
import org.scalajs.dom.MouseEvent

import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.util.Random

@ScalaJSDefined
class Counter private(
  val DOM: DOMSink[Nothing],
  val countStream: XStream[Int]
) extends DOMSinks[Nothing]

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
    val $incClick = sources.DOM.select(".inc").$event(onClick)
    val $decClick = XStream.create[MouseEvent]()
    val $altIncClick = XStream.create[MouseEvent]()

    val $time1 = periodic((intervalFactor * 1000).toInt).startWith(-1).map(i => (i + 1) * 1)
    val $time2 = periodic((intervalFactor * 2000).toInt).startWith(-1).map(i => (i + 1) * 2)
    val $time3 = periodic((intervalFactor * 4000).toInt).startWith(-1).map(i => (i + 1) * 4)

    val $increment = merge($incClick, $altIncClick).mapTo(1).debug("inc")
    val $decrement = $decClick.mapTo(-1).debug("dec")

    val $count = merge($increment, $decrement)
      .startWith(0)
      .fold((acc: Int, nextVal: Int) => acc + nextVal, 0)
      .debug("count")

    val testHover = (e: MouseEvent) => println("some hover")

    val test: XStream[VNode] = $time1.map(time1 => div(s"TIME1: $time1"))

    val $tuple = combine($time1, $time2, $time3)
      .filter((time1: Int, time2: Int, time3: Int) => true)
      //      .debug((time1: Int, time2: Int, time3: Int) => g.console.log("tupler"))
      .map((time1: Int, time2: Int, time3: Int) => div(s"Tupled times: ($time1, $time2, $time3)"))

    // Note that Random.nextInt will NOT be updated if time$* updates, only when count$ updates. Because transposition.
    val $vnode = Transpose(
      div(
        "Foo",
        "bar",
        button(cls := "inc", typ := "button", "+"),
        button(cls := "dec", onClick --> $decClick, typ := "button", "–"),
        $count.map(count => p(s"Count = $count")),
        p(s"rand = ${Random.nextInt()}"),
        "Hello",
        "world",
        test,
        button(id := "xxx", typ := "button", onClick --> $altIncClick, "alt+"),
        a(href := "#yolo", "boooo"),
        Seq[Modifier]("yo", b("lo"), "OMG"),
        Option("maybe"),
        None,
        Some(i("some", onMouseOver := testHover)),
        a(styles.border := "5px solid orange", styles.background := "yellow", href := "#yolo", "hoo"),
        $time1.map(time1 => div(s"TIME1: $time1")),
        $time2.map(time2 => div(s"TIME2: $time2")),
        $time3.map(time3 => div(s"TIME3: $time3")),
        $tuple,
        "<<<"
      )
    )

    new Counter($vnode, $count)
  }
}
