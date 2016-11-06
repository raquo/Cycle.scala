package snabbdom

import snabbdom.collections._

object Snabbdom {
  object tags extends Tags
  object allTags extends Tags with Tags2
  object attrs extends Attrs with InputAttrs with GlobalAttrs
  object events extends MouseEventProps with KeyboardEventProps with ClipboardEventProps
  object modifiers extends Props with Styles
}
