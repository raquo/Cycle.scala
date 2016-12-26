package cycle.dom

import cycle.base.XStreamAdapter
import cycle.xstream.adapter.XStreamAdapter
import snabbdom.VNode
import com.raquo.xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/dom/lib/transposition", JSImport.Namespace)
object RawTransposition extends js.Object {

  type RawTransposeVNodeFunction = js.Function1[VNode, XStream[VNode]]

  def makeTransposeVNode(runStreamAdapter: XStreamAdapter): RawTransposeVNodeFunction = js.native
}

object Transposition {

  private val rawTransposeVNode: RawTransposition.RawTransposeVNodeFunction =
    RawTransposition.makeTransposeVNode(XStreamAdapter)

  @inline def transpose(vnode: VNode): XStream[VNode] =
    rawTransposeVNode(vnode)
}
