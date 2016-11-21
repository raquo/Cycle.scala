package cycle.xstream.adapter

import cycle.base.{Observer, OptionalDisposeFunction, OriginStream, StreamSubscribeFunction, Subject}
import xstream.{Listener, MemoryStream, Producer, RawListener, XStream}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.Dynamic.{global => g}

@ScalaJSDefined
class XStreamAdapter extends js.Object {

  // @TODO how to make this this is not DCE-d?
  /** This is needed to satisfy `isObjectEmpty(streamAdapter)` check inside cycle.js */
  val isObjectEmpty: Boolean = false

  /** Convert Origin stream to XStream */
  def adapt[T](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T]]
  ): XStream[T] = {
//    g.console.log("> XStreamAdapter.adapt")
//    g.console.log(originStream)
//    g.console.log(originStreamSubscribe)

    XStream.create(Producer.fromOriginStream(originStream, originStreamSubscribe))
  }

  /** Convert Origin stream to XStream – already converted */
  def adapt[T](
    originStream: XStream[T],
    originStreamSubscribe: StreamSubscribeFunction[XStream[T], Observer[T]]
  ): XStream[T] = {
//    g.console.log("> XStreamAdapter.adapt (2)")
    originStream
  }

  /** Create memory stream */
  def remember[T](stream: XStream[T]): MemoryStream[T] = {
//    g.console.log("> XStreamAdapter.remember")
    stream.remember()
  }

  /**
    * ???
    */
  def makeSubject[T](): Subject[T] = {
//    g.console.log("> XStreamAdapter.makeSubject")
    val stream = XStream.create[T]()
    val cycleObserver = new XStreamCycleObserver[T](stream)
    val cycleSubject = new Subject(stream, cycleObserver)
    cycleSubject
  }

  /** Check if the stream is a valid XStream – case of origin stream */
  def isValidStream(stream: OriginStream): Boolean = {
//    g.console.log("> XStreamAdapter.isValidStream: false")
//    g.console.log(stream)
    false
  }

  /** Check if the stream is a valid XStream – case of XStream */
  def isValidStream[T](stream: XStream[T]): Boolean = {
//    g.console.log("> XStreamAdapter.isValidStream: true")
    true
  }

  /** Subscribe a Cycle observer to a XStream */
  def streamSubscribe[T](stream: XStream[T], observer: Observer[T]): OptionalDisposeFunction = {
//    g.console.log("> XStreamAdapter.streamSubscribe")
    val listener = Listener.fromCycleObserver(observer)
    stream.addListener(listener)
    js.Any.fromFunction0(() => stream.removeListener(listener))
  }
}
