package cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/base", JSImport.Default)
object Cycle extends js.Object {
  def apply[TSources <: Sources, TSinks <: Sinks, TDrivers <: Drivers](
    main: js.Function1[TSources, TSinks],
    drivers: TDrivers,
    options: Options
  ): Execution[TSources, TSinks] = js.native
}
