package snabbdom.collections

import snabbdom.Util.vnode

/**
  * Trait that contains the contents of the `Tags` object, so they can be mixed
  * in to other objects if needed.
  */
trait Tags {

  /**
    * Represents the root of an HTML or XHTML document. All other elements must
    * be descendants of this element.
    *
    *  MDN
    */
  def html = vnode("html")

  /**
    * Represents a collection of metadata about the document, including links to,
    * or definitions of, scripts and style sheets.
    *
    *  MDN
    */
  def head = vnode("head")

  /**
    * Defines the base URL for relative URLs in the page.
    *
    *  MDN
    */
  def base = vnode("base")

  /**
    * Used to link JavaScript and external CSS with the current HTML document.
    *
    *  MDN
    */
  def link = vnode("link")

  /**
    * Defines metadata that can't be defined using another HTML element.
    *
    *  MDN
    */
  def meta = vnode("meta")

  /**
    * Defines either an internal script or a link to an external script. The
    * script language is JavaScript.
    *
    *  MDN
    */
  def script = vnode("script")

  /**
    * Represents the content of an HTML document. There is only one body
    *   element in a document.
    *
    *  MDN
    */
  def body = vnode("body")

  // Sections

  /**
    * Heading level 1
    *
    *  MDN
    */
  def h1 = vnode("h1")

  /**
    * Heading level 2
    *
    *  MDN
    */
  def h2 = vnode("h2")

  /**
    * Heading level 3
    *
    *  MDN
    */
  def h3 = vnode("h3")

  /**
    * Heading level 4
    *
    *  MDN
    */
  def h4 = vnode("h4")

  /**
    * Heading level 5
    *
    *  MDN
    */
  def h5 = vnode("h5")

  /**
    * Heading level 6
    *
    *  MDN
    */
  def h6 = vnode("h6")

  /**
    * Defines the header of a page or section. It often contains a logo, the
    * title of the Web site, and a navigational table of content.
    *
    *  MDN
    */
  def header = vnode("header")

  /**
    * Defines the footer for a page or section. It often contains a copyright
    * notice, some links to legal information, or addresses to give feedback.
    *
    *  MDN
    */
  def footer = vnode("footer")

  // Grouping content

  /**
    * Defines a portion that should be displayed as a paragraph.
    *
    *  MDN
    */
  def p = vnode("p")

  /**
    * Represents a thematic break between paragraphs of a section or article or
    * any longer content.
    *
    *  MDN
    */
  def hr = vnode("hr")

  /**
    * Indicates that its content is preformatted and that this format must be
    * preserved.
    *
    *  MDN
    */
  def pre = vnode("pre")

  /**
    * Represents a content that is quoted from another source.
    *
    *  MDN
    */
  def blockQuote = vnode("blockquote")

  /**
    * Defines an ordered list of items.
    *
    *  MDN
    */
  def ol = vnode("ol")

  /**
    * Defines an unordered list of items.
    *
    *  MDN
    */
  def ul = vnode("ul")

  /**
    * Defines an item of an list.
    *
    *  MDN
    */
  def li = vnode("li")

  /**
    * Defines a definition list; a list of terms and their associated definitions.
    *
    *  MDN
    */
  def dl = vnode("dl")

  /**
    * Represents a term defined by the next dd
    *
    *  MDN
    */
  def dt = vnode("dt")

  /**
    * Represents the definition of the terms immediately listed before it.
    *
    *  MDN
    */
  def dd = vnode("dd")

  /**
    * Represents a figure illustrated as part of the document.
    *
    *  MDN
    */
  def figure = vnode("figure")

  /**
    * Represents the legend of a figure.
    *
    *  MDN
    */
  def figCaption = vnode("figcaption")

  /**
    * Represents a generic container with no special meaning.
    *
    *  MDN
    */
  def div = vnode("div")

  // Text-level semantics

  /**
    * Represents a hyperlink, linking to another resource.
    *
    *  MDN
    */
  def a = vnode("a")

  /**
    * Represents emphasized text.
    *
    *  MDN
    */
  def em = vnode("em")

  /**
    * Represents especially important text.
    *
    *  MDN
    */
  def strong = vnode("strong")

  /**
    * Represents a side comment; text like a disclaimer or copyright, which is not
    * essential to the comprehension of the document.
    *
    *  MDN
    */
  def small = vnode("small")

  /**
    * Strikethrough element, used for that is no longer accurate or relevant.
    *
    *  MDN
    */
  def s = vnode("s")

  /**
    * Represents the title of a work being cited.
    *
    *  MDN
    */
  def cite = vnode("cite")

  /**
    * Represents computer code.
    *
    *  MDN
    */
  def code = vnode("code")

  /**
    * Subscript tag
    *
    *  MDN
    */
  def sub = vnode("sub")

  /**
    * Superscript tag.
    *
    *  MDN
    */
  def sup = vnode("sup")

  /**
    * Italicized text.
    *
    *  MDN
    */
  def i = vnode("i")

  /**
    * Bold text.
    *
    *  MDN
    */
  def b = vnode("b")

  /**
    * Underlined text.
    *
    *  MDN
    */
  def u = vnode("u")

  /**
    * Represents text with no specific meaning. This has to be used when no other
    * text-semantic element conveys an adequate meaning, which, in this case, is
    * often brought by global attributes like class, lang, or dir.
    *
    *  MDN
    */
  def span = vnode("span")

  /**
    * Represents a line break.
    *
    *  MDN
    */
  def br = vnode("br")

  /**
    * Represents a line break opportunity, that is a suggested point for wrapping
    * text in order to improve readability of text split on several lines.
    *
    *  MDN
    */
  def wbr = vnode("wbr")

  // Edits

  /**
    * Defines an addition to the document.
    *
    *  MDN
    */
  def ins = vnode("ins")

  /**
    * Defines a remodef from the document.
    *
    *  MDN
    */
  def del = vnode("del")

  // Embedded content

  /**
    * Represents an image.
    *
    *  MDN
    */
  def img = vnode("img")

  /**
    * Represents a nested browsing context, that is an embedded HTML document.
    *
    *  MDN
    */
  def iframe = vnode("iframe")

  /**
    * Represents a integration point for an external, often non-HTML, application
    * or interactive content.
    *
    *  MDN
    */
  def embed = vnode("embed")

  /**
    * Represents an external resource, which is treated as an image, an HTML
    * sub-document, or an external resource to be processed by a plug-in.
    *
    *  MDN
    */
  def `object` = vnode("object")

  /**
    * Defines parameters for use by plug-ins invoked by object elements.
    *
    *  MDN
    */
  def param = vnode("param")

  /**
    * Represents a video, and its associated audio files and captions, with the
    * necessary interface to play it.
    *
    *  MDN
    */
  def video = vnode("video")

  /**
    * Represents a sound or an audio stream.
    *
    *  MDN
    */
  def audio = vnode("audio")

  /**
    * Allows the authors to specify alternate media resources for media elements
    * like video or audio
    *
    *  MDN
    */
  def source = vnode("source")

  /**
    * Allows authors to specify timed text track for media elements like video or
    * audio
    *
    *  MDN
    */
  def track = vnode("track")

  /**
    * Represents a bitmap area that scripts can use to render graphics like graphs,
    * games or any visual images on the fly.
    *
    *  MDN
    */
  def canvas = vnode("canvas")

  /**
    * In conjunction with area, defines an image map.
    *
    *  MDN
    */
  def map = vnode("map")

  /**
    * In conjunction with map, defines an image map
    *
    *  MDN
    */
  def area = vnode("area")

  // Tabular data

  /**
    * Represents data with more than one dimension.
    *
    *  MDN
    */
  def table = vnode("table")

  /**
    * The title of a table.
    *
    *  MDN
    */
  def caption = vnode("caption")

  /**
    * A set of columns.
    *
    *  MDN
    */
  def colGroup = vnode("colgroup")

  /**
    * A single column.
    *
    *  MDN
    */
  def col = vnode("col")

  /**
    * The table body.
    *
    *  MDN
    */
  def tbody = vnode("tbody")

  /**
    * The table headers.
    *
    *  MDN
    */
  def thead = vnode("thead")

  /**
    * The table footer.
    *
    *  MDN
    */
  def tfoot = vnode("tfoot")

  /**
    * A single row in a table.
    *
    *  MDN
    */
  def tr = vnode("tr")

  /**
    * A single cell in a table.
    *
    *  MDN
    */
  def td = vnode("td")

  /**
    * A header cell in a table.
    *
    *  MDN
    */
  def th = vnode("th")

  // Forms

  /**
    * Represents a form, consisting of controls, that can be submitted to a
    * server for processing.
    *
    *  MDN
    */
  def form = vnode("form")

  /**
    * A set of fields.
    *
    *  MDN
    */
  def fieldSet = vnode("fieldset")

  /**
    * The caption for a fieldset.
    *
    *  MDN
    */
  def legend = vnode("legend")

  /**
    * The caption of a single field
    *
    *  MDN
    */
  def label = vnode("label")

  /**
    * A typed data field allowing the user to input data.
    *
    *  MDN
    */
  def input = vnode("input")

  /**
    * A button
    *
    *  MDN
    */
  def button = vnode("button")

  /**
    * A control that allows the user to select one of a set of options.
    *
    *  MDN
    */
  def select = vnode("select")

  /**
    * A set of predefined options for other controls.
    *
    *  MDN
    */
  def dataList = vnode("datalist")

  /**
    * A set of options, logically grouped.
    *
    *  MDN
    */
  def optGroup = vnode("optgroup")

  /**
    * An option in a select element.
    *
    *  MDN
    */
  def option = vnode("option")

  /**
    * A multiline text edit control.
    *
    *  MDN
    */
  def textArea = vnode("textarea")
}
