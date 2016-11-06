package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("xstream", JSImport.Default)
object RawXStream extends js.Object {
  type FromInput[T] = js.Promise[T] | XStream[T] | js.Array[T] // | Observable[T] @TODO is this some sort of TS observable?

  type Merge2[T1, T2] = js.Function2[T1, T2, XStream[T1 | T2]]
  type Merge3[T1, T2, T3] = js.Function3[T1, T2, T3, XStream[T1 | T2 | T3]]
  type Merge4[T1, T2, T3, T4] = js.Function4[T1, T2, T3, T4, XStream[T1 | T2 | T3 | T4]]
  type Merge5[T1, T2, T3, T4, T5] = js.Function5[T1, T2, T3, T4, T5, XStream[T1 | T2 | T3 | T4 | T5]]

  def create[T](producer: js.UndefOr[Producer[T]]): XStream[T] = js.native
  def createWithMemory[T](producer: js.UndefOr[Producer[T]]): MemoryXStream[T] = js.native
  def never(): XStream[Nothing] = js.native // @TODO is Nothing a correct type?
  def empty(): XStream[Nothing] = js.native // @TODO is Nothing a correct type?
  def `throw`[T](error: T): XStream[T] = js.native // @TODO rename method?
  def from[T](input: FromInput[T]): XStream[T] = js.native
  //  def of[T](items: js.Array[T]*): Stream[T] = js.native // @TODO is this correct signature for TS `(...items: Array<T>)`?
  def fromArray[T](array: js.Array[T]): XStream[T] = js.native
  def fromPromise[T](promise: js.Promise[T]): XStream[T] = js.native
  def fromObservable[T](observable: Any): XStream[T] = js.native // @TODO What observable? Why??
  def periodic(period: Int): XStream[Int] = js.native

  // @TODO Add up to... 16? variations

  def merge[T](streams: XStream[T]*): XStream[T] = js.native // @TODO does this do what I think it does?
  //  def merge[T](streams: js.Array[XStream[T]]): XStream[T] = js.native // @TODO does this do what I think it does?
  def merge[T1, T2](stream1: XStream[T1], stream2: XStream[T2]): XStream[T1 | T2] = js.native
  def merge[T1, T2, T3](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3]): XStream[T1 | T2 | T3] = js.native
  def merge[T1, T2, T3, T4](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4]): XStream[T1 | T2 | T3 | T4] = js.native
  def merge[T1, T2, T3, T4, T5](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4], stream5: XStream[T5]): XStream[T1 | T2 | T3 | T4 | T5] = js.native

  def combine[T](streams: XStream[T]*): XStream[js.Array[T]] = js.native // @TODO does this do what I think it does?
  def combine[T1, T2](stream1: XStream[T1], stream2: XStream[T2]): XStream[js.Array[T1 | T2]] = js.native
  def combine[T1, T2, T3](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3]): XStream[js.Array[T1 | T2 | T3]] = js.native
  def combine[T1, T2, T3, T4](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4]): XStream[js.Array[T1 | T2 | T3 | T4]] = js.native
  def combine[T1, T2, T3, T4, T5](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4], stream5: XStream[T5]): XStream[js.Array[T1 | T2 | T3 | T4 | T5]] = js.native
}
