# Cycle.scala – Scala.js interface to Cycle.js
  
This is a more or less complete interface to Cycle.js, as well as its DOM and HTTP drivers. The code has a few example components that work. There are many @TODO-s in the code, but Cycle.scala actually works pretty well as-is.

However, after digging into Cycle.js throughout this project I've realized that I just want a different library. Cycle.js is true to its design goals, and I thought I shared them, but turns out that I don't.

I also didn't like how Cycle's DOM driver works with Snabbdom. It is rather inefficient, re-creating way too many virtual nodes on every little change. I believe there's a better way to wire together Snabbdom and FRP. So I decided to build [Laminar](https://github.com/raquo/laminar), a new FRP library based on that idea. Half way into that, I've realized that the whole concept of virtual DOM does not map well to my use case, so I built [Scala DOM Builder](https://github.com/raquo/scala-dom-builder), a low level DOM manipulation and tree-tracking library, and reworked Laminar to use that instead of Snabbdom, eliminating a lot of complexity.

So I probably won't be further developing this project in the foreseeable future. Cycle.js guys had some pretty exciting things planned for the future, so maybe I'll revisit this eventually. 

## Further work

If you wanted to pick this up, you should start by replacing the stuff in the `com/raquo/snabbdom` directory with my [Snabbdom.scala](https://github.com/raquo/Snabbdom.scala) project which is basically the same thing extracted into a separate package.

Adding some tests would also be nice. Cycle.js has its own tests but this interface is a thick enough layer that it deserves some tests.

Other than that there aren't really any pressing concerns, just a bunch of minor things to improve.

## License

Cycle.scala is provided under MIT license. Comments for DOM definitions are courtesy of Mozzilla contributors and are provided under a Creative Commons Attribution-ShareAlike license.
