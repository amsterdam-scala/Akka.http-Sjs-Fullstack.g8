This is a minimal [Giter8][g8] template for Full Stack Scala with [Scala.js](https://www.scala-js.org/) in the front & [Akka HTTP](https://doc.akka.io/docs/akka-http/current/) in the back. Running respectively EcmaScript and JVM code. 

Binding is done using Autowire (Ajax/RPC calls) and BooPickle (binary object serialization).

```
sbt new amsterdam-scala/Akka.http-Sjs-Fullstack.g8
```

It will create an sbt project in a (user named) subdirectory e.g. 'scala-full-stack'.

```
cd .\scala-full-stack\
sbt
reStart
```

After compiling it will fire up your default web browser, which displays:

![Screenshot](https://raw.githubusercontent.com/amsterdam-scala/Akka.http-Sjs-Fullstack.g8/master/doc/Scala%20Fullstack.gif)

Client code in `js`.
Server code in `jvm`.
Shared code in `shared`.

[g8]: http://www.foundweekends.org/giter8/

Changes:
2018-06-22: TBje Upgrade sbt 1.1.6 and ScalaJS 0.6.23 + others.

2019-07-08: FvdB Upgrade sbt 1.2.8 and ScalaJS 0.6.28 + plugins, Scala 2.12.8.