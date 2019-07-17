This is a minimal [Giter8][g8] template for Full Stack Scala with ScalaJS in the front & Akka HTTP in the back. Binding is done using Autowire (Ajax/RPC calls) and BooPickle (binary object serialization).

```
sbt new tbje/full-stack.g8
```

It will create the project in a subdir called full-stack.

```
cd full-stack
sbt
reStart
```

Client code in `js`.
Server code in `jvm`.
Shared code in `shared`.

[g8]: http://www.foundweekends.org/giter8/

Changes:
2018-06-22: TBje Upgrade sbt 1.1.6 and ScalaJS 0.6.23 + others.

2019-07-08: FvdB Upgrade sbt 1.2.8 and ScalaJS 0.6.28 + plugins, Scala 2.12.8.