package com.raquo.snabbdom

import com.raquo.xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.|

@js.native
trait VNodeData extends js.Any {
  var attrs: js.UndefOr[js.Dynamic] = js.native
  var on: js.UndefOr[js.Dynamic] = js.native
  var props: js.UndefOr[js.Dynamic] = js.native
  var style: js.UndefOr[js.Dynamic] = js.native
}

@ScalaJSDefined
class VNode(tagName: String) extends Modifier {

  var sel: String = tagName

  var key: js.UndefOr[String] = js.undefined

  var data: VNodeData = js.Dynamic.literal().asInstanceOf[VNodeData]

  var text: js.UndefOr[String] = js.undefined

  var children: js.UndefOr[VNode.Children] = js.undefined

  @inline def applyTo(vnode: VNode): Unit = vnode.addChild(this)
}

object VNode {

  // @TODO>>>

  type Child = VNode | TextNode | XStream[VNode, Nothing] | XStream[TextNode, Nothing] | XStream[VNode | TextNode, Nothing]

  type Children = js.Array[Child]
}



