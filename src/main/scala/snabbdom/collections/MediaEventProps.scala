package snabbdom.collections

import snabbdom.Util.{GenericEventCallback, eventProp}

/**
  * Media Events - triggered by media like videos, images and audio. These apply to
  * all HTML elements, but they are most common in media elements, like <audio>,
  * <embed>, <img>, <object>, and <video>.
  */
trait MediaEventProps extends SharedEventProps {

  /**
    * Script to be run on abort
    */
  lazy val onAbort = eventProp[GenericEventCallback]("abort")

  /**
    * Script to be run when a file is ready to start playing (when it has buffered enough to begin)
    */
  lazy val onCanPlay = eventProp[GenericEventCallback]("canplay")

  /**
    * Script to be run when a file can be played all the way to the end without pausing for buffering
    */
  lazy val onCanPlayThrough = eventProp[GenericEventCallback]("canplaythrough")

  /**
    * Script to be run when the cue changes in a <track> element
    */
  lazy val onCueChange = eventProp[GenericEventCallback]("cuechange")

  /**
    * Script to be run when the length of the media changes
    */
  lazy val onDurationChange = eventProp[GenericEventCallback]("durationchange")

  /**
    * Script to be run when something bad happens and the file is suddenly unavailable (like unexpectedly disconnects)
    */
  lazy val onEmptied = eventProp[GenericEventCallback]("emptied")

  /**
    * Script to be run when the media has reach the end (a useful event for messages like "thanks for listening")
    */
  lazy val onEnded = eventProp[GenericEventCallback]("ended")

  /**
    * Script to be run when media data is loaded
    */
  lazy val onLoadedData = eventProp[GenericEventCallback]("loadeddata")

  /**
    * Script to be run when meta data (like dimensions and duration) are loaded
    */
  lazy val onLoadedMetadata = eventProp[GenericEventCallback]("loadedmetadata")

  /**
    * Script to be run just as the file begins to load before anything is actually loaded
    */
  lazy val onLoadStart = eventProp[GenericEventCallback]("loadstart")

  /**
    * Script to be run when the media is paused either by the user or programmatically
    */
  lazy val onPause = eventProp[GenericEventCallback]("pause")

  /**
    * Script to be run when the media is ready to start playing
    */
  lazy val onPlay = eventProp[GenericEventCallback]("play")

  /**
    * Script to be run when the media actually has started playing
    */
  lazy val onPlaying = eventProp[GenericEventCallback]("playing")

  /**
    * Script to be run when the browser is in the process of getting the media data
    */
  lazy val onProgress = eventProp[GenericEventCallback]("progress")

  /**
    * Script to be run each time the playback rate changes (like when a user switches to a slow motion or fast forward mode)
    */
  lazy val onRateChange = eventProp[GenericEventCallback]("ratechange")

  /**
    * Script to be run when the seeking attribute is set to false indicating that seeking has ended
    */
  lazy val onSeeked = eventProp[GenericEventCallback]("seeked")

  /**
    * Script to be run when the seeking attribute is set to true indicating that seeking is active
    */
  lazy val onSeeking = eventProp[GenericEventCallback]("seeking")

  /**
    * Script to be run when the browser is unable to fetch the media data for whatever reason
    */
  lazy val onStalled = eventProp[GenericEventCallback]("stalled")

  /**
    * Script to be run when fetching the media data is stopped before it is completely loaded for whatever reason
    */
  lazy val onSuspend = eventProp[GenericEventCallback]("suspend")

  /**
    * Script to be run when the playing position has changed (like when the user fast forwards to a different point in the media)
    */
  lazy val onTimeUpdate = eventProp[GenericEventCallback]("timeupdate")

  /**
    * Script to be run each time the volume is changed which (includes setting the volume to "mute")
    */
  lazy val onVolumeChange = eventProp[GenericEventCallback]("volumechange")

  /**
    * Script to be run when the media has paused but is expected to resume (like when the media pauses to buffer more data)
    */
  lazy val onWaiting = eventProp[GenericEventCallback]("waiting")
}
