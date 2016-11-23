package xstream

import XStream.fromRawStream

class TupleStream4[T1, T2, T3, T4](rawStream: RawStream[(T1, T2, T3, T4)])
  extends XStream[(T1, T2, T3, T4)](rawStream) {

  @inline def map[U](project: (T1, T2, T3, T4) => U): XStream[U] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def map[U1, U2](project: (T1, T2, T3, T4) => (U1, U2)): TupleStream2[U1, U2] =
    fromRawStream(rawStream.map(project.tupled))

  @inline  def map[U1, U2, U3](project: (T1, T2, T3, T4) => (U1, U2, U3)): TupleStream3[U1, U2, U3] =
    fromRawStream(rawStream.map(project.tupled))

  @inline  def map[U1, U2, U3, U4](project: (T1, T2, T3, T4) => (U1, U2, U3, U4)): TupleStream4[U1, U2, U3, U4] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def filter(passes: (T1, T2, T3, T4) => Boolean): TupleStream4[T1, T2, T3, T4] =
    fromRawStream(rawStream.filter(passes.tupled))

  @inline override def debug(): TupleStream4[T1, T2, T3, T4] =
    fromRawStream(rawStream.debug())

  @inline override def debug(label: String): TupleStream4[T1, T2, T3, T4] =
    fromRawStream(rawStream.debug(label))

  @inline def debug(spy: (T1, T2, T3, T4) => Unit): TupleStream4[T1, T2, T3, T4] =
    fromRawStream(rawStream.debug(spy.tupled))
}
