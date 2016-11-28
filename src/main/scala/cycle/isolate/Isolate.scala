package cycle.isolate

import cycle.base.{IsolatableSource, Sinks, Sources}

object Isolate {

  private var counter = 1

  def apply[Sis <: Sinks, Sos <: Sources](
    main: Sos => Sis,
    scope: String = newScope()
  )(
    sources: Sos
  ): Sis = {
    val isolatedSources = isolateAllSources(sources, scope)
    val isolatedMain = isolateAllSinks(isolatedSources, main, scope)
    isolatedMain(isolatedSources)
  }

  def reset(): Unit = counter = 1

  def newScope(): String = {
    counter += 1
    s"scala-cycle-$counter" //@TODO[Integrity] Is this compatible with Cycle.js?
  }

  def isolateAllSources[Sos <: Sources](sources: Sos, scope: String): Sos = {
    // for each source, call .isolateSource()
    // @TODO
    sources
  }

  def isolateAllSinks[Sis <: Sinks, Sos <: Sources](
    sources: Sos,
    main: Sos => Sis,
    scope: String
  ): (Sos => Sis) = {
    // for each sink, call isolateSink on the corresponding source
    // @TODO
    main
  }

  //  def isolateSource[Src <: IsolatableSource[Src, _]](source: Src): Src = {
  //    source.isolateSource(source, scope)
  //  }
  //
  //  def isolateSink[Src <: IsolatableSource[Src, Sink], Sink](source: Src, sink: Sink): Sink = {
  //    source.isolateSink(sink, scope)
  //  }
}
