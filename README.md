# sbt-akkaserverless
An SBT plugin to support building Scala components for Akka Serverless

## Usage
To use `sbt-akkaserverless` you need to:
- Build it locally: `sbt publishLocal`, and
- include it in your `project/plugins.sbt` file like this:
```scala
addSbtPlugin("com.yoppworks" %% "sbt-akkaserverless" % "0.2.0")
```
and then enable it in the projects that need to use it in your build.sbt,
for example like this:
```scala
lazy val `my-project` = 
  project
    .in (this("."))
    .enablePlugins(AkkaServerlessPlugin)
```

## Other Plugins Included In Your Project
- sbt-git
- sbt-native-packager
- sbt-akka-grpc
- sbt-protoc
