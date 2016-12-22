package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.|

@js.native
trait XStream[T] extends js.Object {

  def addListener(listener: Listener[T]): Unit = js.native

  def removeListener(listener: Listener[T]): Unit = js.native

  def subscribe(listener: Listener[T]): Subscription[T] = js.native

  @JSName("map")
  def mapJs[U](project: js.Function1[T, U]): XStream[U] = js.native

  def mapTo[U](projectedValue: U): XStream[U] = js.native

  def filter(passes: js.Function1[T, Boolean]): XStream[T] = js.native

  def take(amount: Int): XStream[T] = js.native

  def drop(amount: Int): XStream[T] = js.native

  def last(): XStream[T] = js.native

  def startWith(initial: T): MemoryStream[T] = js.native

  def endWhen(other: XStream[_]): XStream[T] = js.native

  def fold[R](accumulate: js.Function2[R, T, R], seed: R): MemoryStream[R] = js.native

  // @TODO should `E` type exist here? In Typescript it's `any`
  def replaceError[E](replace: js.Function1[E, XStream[T]]): XStream[T] = js.native

  @JSName("flatten")
  private[xstream] def flattenJs[R]: XStream[R] = js.native

  def compose[U](operator: js.Function1[XStream[T], XStream[U]]): XStream[U] = js.native

  def compose[U](operator: js.Function1[XStream[T], MemoryStream[U]]): MemoryStream[U] = js.native

  def remember(): MemoryStream[T] = js.native

  def debug(spy: js.Function1[T, Any]): XStream[T] = js.native

  def debug(label: String): XStream[T] = js.native

  def debug(): XStream[T] = js.native

  def imitate(target: XStream[T]): Unit = js.native

  def shamefullySendNext(value: T): Unit = js.native

  def shamefullySendError[E](error: E): Unit = js.native

  def shamefullySendComplete(): Unit = js.native

  def setDebugListener(listener: Listener[T]): Unit = js.native
}

object XStream {

  // @TODO Method

  // Simple streams

  @inline def of[T](value: T): XStream[T] =
    RawXStream.of(value)

  @inline def never(): XStream[Nothing] =
    RawXStream.never()

  @inline def empty(): XStream[Nothing] =
    RawXStream.empty()

  @inline def throwError[T](error: T): XStream[T] =
    RawXStream.`throw`(error)

  @inline def periodic(period: Int): XStream[Int] =
    RawXStream.periodic(period)

  // create & createWithMemory

  @inline def create[T](): XStream[T] =
    RawXStream.create[T]()

  @inline def create[T](producer: RichProducer[T]): XStream[T] =
    RawXStream.create[T](producer)

  @inline def createWithMemory[T](): MemoryStream[T] =
    RawXStream.createWithMemory[T]()

  @inline def createWithMemory[T](producer: RichProducer[T]): MemoryStream[T] =
    RawXStream.createWithMemory[T](producer)

  // from<X>

  @inline def fromSeq[T](seq: Seq[T]): XStream[T] =
    RawXStream.fromArray(seq.toJSArray)

  @inline def fromPromise[T](promise: js.Promise[T]): XStream[T] =
    RawXStream.fromPromise(promise)

  @inline def fromJSArray[T](array: js.Array[T]): XStream[T] =
    RawXStream.fromArray(array)

  //  @inline def fromJsObservable[T](observable: Any): Stream[T] = js.native // @TODO ES6 observable?

  // Merge

  @inline def merge[T](streams: XStream[T]*): XStream[T] =
    RawXStream.merge(streams: _*)

  // Combine

  @inline def combine[T1, T2](
    stream1: XStream[T1], stream2: XStream[T2]
  ): XStream[(T1, T2)] =
    RawXStream
      .combine(stream1, stream2)
      .mapJs(JSArrayToTuple2[T1, T2] _)

  @inline def combine[T1, T2, T3](
    stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3]
  ): XStream[(T1, T2, T3)] =
    RawXStream
      .combine(stream1, stream2, stream3)
      .mapJs(JSArrayToTuple3[T1, T2, T3] _)

  @inline def combine[T1, T2, T3, T4](
    stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4]
  ): XStream[(T1, T2, T3, T4)] =
    RawXStream
      .combine(stream1, stream2, stream3, stream4)
      .mapJs(JSArrayToTuple4[T1, T2, T3, T4] _)

  //
  //  @inline def combine[T1, T2, T3, T4, T5](
  //    stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4], stream5: XStream[T5]
  //  ): Tuple5Stream[T1, T2, T3, T4, T5] = {
  //    new Tuple5Stream(
  //      RawStream.combine(stream1.rawStream, stream2.rawStream, stream3.rawStream, stream4.rawStream, stream5.rawStream)
  //        .map((arr: js.Array[T1 | T2 | T3 | T4 | T5]) => jsArrayToTuple5[T1, T2, T3, T4, T5](arr))
  //    )
  //  }

  // @TODO[performance] encode these as functions instead of methods?

  @inline private def JSArrayToTuple2[T1, T2](arr: js.Array[T1 | T2]): (T1, T2) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2])
  }

  @inline private def JSArrayToTuple3[T1, T2, T3](arr: js.Array[T1 | T2 | T3]): (T1, T2, T3) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3])
  }

  @inline private def JSArrayToTuple4[T1, T2, T3, T4](arr: js.Array[T1 | T2 | T3 | T4]): (T1, T2, T3, T4) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3], arr(3).asInstanceOf[T4])
  }

  //  @inline private def JSArrayToTuple5[T1, T2, T3, T4, T5](arr: js.Array[T1 | T2 | T3 | T4 | T5]): (T1, T2, T3, T4, T5) = {
  //    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3], arr(3).asInstanceOf[T4], arr(4).asInstanceOf[T5])
  //  }
}
