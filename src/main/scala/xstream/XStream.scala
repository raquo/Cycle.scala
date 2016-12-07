package xstream

import scalajs.js
import scalajs.js.|
import scala.scalajs.js.JSConverters._

// @TODO Add documentation here

class XStream[T] protected (val rawStream: RawStream[T]) {

  def addListener(listener: Listener[T]): Unit =
    rawStream.addListener(listener)

  def removeListener(listener: Listener[T]): Unit =
    rawStream.removeListener(listener)

  def subscribe(listener: Listener[T]): RawSubscription[T] =
    rawStream.subscribe(listener)

  def map[U](project: T => U): XStream[U] =
    XStream.fromRawStream(rawStream.map(project))

  def mapTo[U](projectedValue: U): XStream[U] =
    XStream.fromRawStream(rawStream.mapTo(projectedValue))

  def filter(passes: T => Boolean): XStream[T] =
    XStream.fromRawStream(rawStream.filter(passes))

  def take(amount: Int): XStream[T] =
    XStream.fromRawStream(rawStream.take(amount))

  def drop(amount: Int): XStream[T] =
    XStream.fromRawStream(rawStream.drop(amount))

  def last(): XStream[T] =
    XStream.fromRawStream(rawStream.last())

  def startWith(initial: T): XStream[T] = // MemoryStream
    new XStream(rawStream.startWith(initial))

  def endWhen[U](other: XStream[U]): XStream[T] =
    XStream.fromRawStream(rawStream.endWhen(other.rawStream))

  def fold[R](accumulate: (R, T) => R, seed: R): XStream[R] = // MemoryStream
    new XStream(rawStream.fold(accumulate, seed))

  def replaceError[E](replace: E => XStream[T]): XStream[T] =
    XStream.fromRawStream(rawStream.replaceError[E]((error: E) => replace(error).rawStream))

  // @TODO: This only makes sense if T is Stream[R]. How do enforce this limitation in Scala?
  //  def flatten[R]: XStream[R] =
  //    XStream.createWithRawStream(rawStream.flatten)

  def compose[U](operator: XStream[T] => XStream[U]): XStream[U] = {
    XStream.fromRawStream(rawStream.compose[U] { someRawStream: RawStream[T] =>
      operator(XStream.fromRawStream(someRawStream)).rawStream
    })
  }

  def remember(): XStream[T] = // MemoryStream
    new XStream(rawStream.remember())

  def debug(spy: T => Unit): XStream[T] =
    XStream.fromRawStream(rawStream.debug(spy))

  def debug(label: String): XStream[T] =
    XStream.fromRawStream(rawStream.debug(label))

  def debug(): XStream[T] =
    XStream.fromRawStream(rawStream.debug())

  def imitate(target: XStream[T]): Unit =
    rawStream.imitate(target.rawStream)

  def shamefullySendNext(value: T): Unit =
    rawStream.shamefullySendNext(value)

  def shamefullySendError[E](error: E): Unit =
    rawStream.shamefullySendError(error)

  def shamefullySendComplete(): Unit =
    rawStream.shamefullySendComplete()

  def setDebugListener(listener: Listener[T]): Unit =
    rawStream.setDebugListener(listener)
}

object XStream {

  // Simple streams

  @inline def never(): XStream[Nothing] =
    new XStream[Nothing](RawStream.never())

  @inline def empty(): XStream[Nothing] =
    new XStream[Nothing](RawStream.empty())

  @inline def throwError[T](error: T): XStream[T] =
    new XStream(RawStream.`throw`(error))

  @inline def periodic(period: Int): XStream[Int] =
    new XStream(RawStream.periodic(period))

  // create & createWithMemory

  @inline def create[T](): XStream[T] =
    new XStream(RawStream.create[T]())

  @inline def create[T](producer: Producer[T]): XStream[T] =
    new XStream(RawStream.create[T](producer))

  @inline def createWithMemory[T](): XStream[T] = // MemoryStream
    new XStream(RawStream.createWithMemory[T]())

  @inline def createWithMemory[T](producer: Producer[T]): XStream[T] = // MemoryStream
    new XStream(RawStream.createWithMemory[T](producer))

  // fromRawStream

  @inline def fromRawStream[T](rawStream: RawStream[T]): XStream[T] =
    new XStream[T](rawStream)

  @inline def fromRawStream[T1, T2](rawStream: RawStream[(T1, T2)]): TupleStream2[T1, T2] =
    new TupleStream2[T1, T2](rawStream)

  @inline def fromRawStream[T1, T2, T3](rawStream: RawStream[(T1, T2, T3)]): TupleStream3[T1, T2, T3] =
    new TupleStream3[T1, T2, T3](rawStream)

  @inline def fromRawStream[T1, T2, T3, T4](rawStream: RawStream[(T1, T2, T3, T4)]): TupleStream4[T1, T2, T3, T4] =
    new TupleStream4[T1, T2, T3, T4](rawStream)

  // from<X>

  @inline def fromStream[T](stream: XStream[T]): XStream[T] =
    new XStream(RawStream.from(stream.rawStream))

  //  def fromObservable[T](observable: Any): Stream[T] = js.native // @TODO ES6 observable?

  @inline def fromSeq[T](seq: Seq[T]): XStream[T] =
    new XStream(RawStream.fromArray(seq.toJSArray))

  @inline def fromPromise[T](promise: js.Promise[T]): XStream[T] =
    new XStream(RawStream.fromPromise(promise))

  @inline def fromJSArray[T](array: js.Array[T]): XStream[T] =
    new XStream(RawStream.from(array))

  @inline def of[T](items: T*): XStream[T] =
    new XStream(RawStream.fromArray(items.toJSArray))

  // fromTuple<N>Stream

  @inline def fromTuple2Stream[T1, T2](stream: TupleStream2[T1, T2]): TupleStream2[T1, T2] =
    new TupleStream2[T1, T2](RawStream.from(stream.rawStream))

  @inline def fromTuple3Stream[T1, T2, T3](stream: TupleStream3[T1, T2, T3]): TupleStream3[T1, T2, T3] =
    new TupleStream3[T1, T2, T3](RawStream.from(stream.rawStream))

  @inline def fromTuple4Stream[T1, T2, T3, T4](stream: TupleStream4[T1, T2, T3, T4]): TupleStream4[T1, T2, T3, T4] =
    new TupleStream4[T1, T2, T3, T4](RawStream.from(stream.rawStream))

  // Merge

  @inline def merge[T](streams: XStream[T]*): XStream[T] =
    new XStream(RawStream.merge(streams.map(_.rawStream): _*))

  // Combine

  @inline def combine[T1, T2](
    stream1: XStream[T1], stream2: XStream[T2]
  ): TupleStream2[T1, T2] = new TupleStream2(
    RawStream
      .combine(stream1.rawStream, stream2.rawStream)
      .map(JSArrayToTuple2[T1, T2] _)
  )

  @inline def combine[T1, T2, T3](
    stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3]
  ): TupleStream3[T1, T2, T3] = new TupleStream3(
    RawStream
      .combine(stream1.rawStream, stream2.rawStream, stream3.rawStream)
      .map(JSArrayToTuple3[T1, T2, T3] _)
  )

  @inline def combine[T1, T2, T3, T4](
    stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4]
  ): TupleStream4[T1, T2, T3, T4] = new TupleStream4(
    RawStream
      .combine(stream1.rawStream, stream2.rawStream, stream3.rawStream, stream4.rawStream)
      .map(JSArrayToTuple4[T1, T2, T3, T4] _)
  )

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


