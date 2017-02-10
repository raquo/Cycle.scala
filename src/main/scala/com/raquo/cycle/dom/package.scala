package com.raquo.cycle

import com.raquo.cycle.base.RawDriver
import com.raquo.snabbdom.{EventProp, VNode}
import com.raquo.snabbdom.VNode
import com.raquo.snabbdom.Util.EventCallback
import com.raquo.xstream.XStream
import com.raquo.xstream.EStream
import org.scalajs.dom.raw.{Event, HTMLElement}

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.|

package object dom {

  type DOMSink[Err <: Exception] = EStream[VNode, Err]

  type RawDOMDriver = RawDriver[DOMSink[Nothing], DOMSource]

  implicit class RichDOMSource(val source: DOMSource) extends AnyVal {

    // @TODO[Elegance] Improve code reuse, maybe use a lazy view for $element

    /** Stream of individual elements
      * @return First matched element. If no elements match, return [[None]].
      */
    @inline def $anyElement(): XStream[Option[HTMLElement]] = $element[HTMLElement]()

    /** Stream of a list of elements
      * @return [[Seq]] of matched elements. If no elements match, return an empty [[Seq]].
      */
    @inline def $anyElements(): XStream[Seq[HTMLElement]] = $elements[HTMLElement]()

    /** Stream of individual elements filtered by a particular type.
      * @return First matched element. If no elements match, return [[None]].
      */
    @inline def $element[Element <: HTMLElement : ClassTag](): XStream[Option[Element]] =
      source.elements().map { elementOrArray =>
        val elements = toSeq(elementOrArray).flatMap(el => filter[Element](el))
        elements.headOption
      }

    /** Stream of a list of elements filtered by a particular type.
      * @return [[Seq]] of matched elements. If no elements match, return an empty [[Seq]].
      */
    @inline def $elements[Element <: HTMLElement : ClassTag](): XStream[Seq[Element]] = {
      source.elements().map { elementOrArray =>
        toSeq(elementOrArray).flatMap(el => filter[Element](el))
      }
    }

    @inline def $event[Ev <: Event](
      eventProp: EventProp[EventCallback[Ev]]
    ): XStream[Ev] =
      source.events[Ev](eventProp.key)

    @inline def $event[Ev <: Event](
      eventProp: EventProp[EventCallback[Ev]],
      options: EventOptions
    ): XStream[Ev] =
      source.events[Ev](eventProp.key, options)

    private def toSeq[Element <: HTMLElement](elementOrArray: Element | js.Array[Element]): Seq[Element] = {
      if (js.Array.isArray(elementOrArray.asInstanceOf[js.Any])) {
        elementOrArray.asInstanceOf[js.Array[Element]]
      } else {
        val maybeElement = Option(elementOrArray.asInstanceOf[Element])
        maybeElement.map(Seq(_)).getOrElse(Seq())
      }
    }

//    private def toSingle(elementOrArray: HTMLElement | js.Array[HTMLElement]): Option[HTMLElement] = {
//      if (js.Array.isArray(elementOrArray.asInstanceOf[js.Any])) {
//        val elementAtIndex: js.UndefOr[HTMLElement] = elementOrArray.asInstanceOf[js.Array[HTMLElement]](0)
//        elementAtIndex.toOption
//      } else {
//        Option(elementOrArray.asInstanceOf[HTMLElement])
//      }
//    }

    private def filter[Element <: HTMLElement : ClassTag](element: HTMLElement): Option[Element] = {
      val desiredClass = implicitly[ClassTag[Element]].runtimeClass
      if (desiredClass.isInstance(element)) {
        Some(element.asInstanceOf[Element])
      } else {
        None
      }
    }
  }
}
