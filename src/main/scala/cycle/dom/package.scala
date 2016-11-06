package cycle

package object dom {
  implicit def rawToDOMSource(rawDOMSource: RawDOMSource): DOMSource = new DOMSource(rawDOMSource)
  implicit def DOMSourceToRaw(DOMSource: DOMSource): RawDOMSource = DOMSource.rawSource
}
