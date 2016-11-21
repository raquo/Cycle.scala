package cycle.base

import scala.scalajs.js

@js.native
trait Execution[TSources <: Sources, TSinks <: Sinks] extends js.Object {

  val sources: TSources = js.native

  val sinks: TSinks = js.native

  def run(): DisposeFunction = js.native
}
