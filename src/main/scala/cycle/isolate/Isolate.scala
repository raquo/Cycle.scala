package cycle.isolate

import cycle.base.IsolatableSource

class Isolate(
  val scope: String = Isolate.newScope()
) {
  def source[Src <: IsolatableSource[Src, _]](source: Src): Src = {
    source.isolateSource(source, scope)
  }

  def sink[Src <: IsolatableSource[Src, Sink], Sink](source: Src, sink: Sink): Sink = {
    source.isolateSink(sink, scope)
  }
}

object Isolate {

  private var counter = 1

  def reset() = counter = 1

  def newScope(): String = {
    counter += 1
    s"scala-cycle-$counter"
  }
}
