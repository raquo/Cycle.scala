package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait MemoryStream[T] extends XStream[T] {

  @JSName("map")
  override def mapJs[U](project: js.Function1[T, U]): MemoryStream[U] = js.native

  override def mapTo[U](projectedValue: U): MemoryStream[U] = js.native

  override def take(amount: Int): MemoryStream[T] = js.native

  override def endWhen(other: XStream[_]): MemoryStream[T] = js.native

  override def replaceError[E](replace: js.Function1[E, XStream[T]]): MemoryStream[T] = js.native // @TODO should `E` type exist here? In Typescript it's `any`

  override def debug(spy: js.Function1[T, Any]): MemoryStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy

  override def debug(label: String): MemoryStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy

  override def debug(): MemoryStream[T] = js.native
}

