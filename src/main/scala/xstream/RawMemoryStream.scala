package xstream

import scala.scalajs.js

@js.native
class RawMemoryStream[T] extends RawStream[T] {

  override def map[U](project: js.Function1[T, U]): RawMemoryStream[U] = js.native

  override def mapTo[U](projectedValue: U): RawMemoryStream[U] = js.native

  override def take(amount: Int): RawMemoryStream[T] = js.native

  override def endWhen(other: RawStream[_]): RawMemoryStream[T] = js.native

  override def replaceError[E](replace: js.Function1[E, RawStream[T]]): RawMemoryStream[T] = js.native // @TODO should `E` type exist here? In Typescript it's `any`

  override def debug(spy: js.Function1[T, Unit]): RawMemoryStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy

  override def debug(label: String): RawMemoryStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy

  override def debug(): RawMemoryStream[T] = js.native
}

