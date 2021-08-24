import sbt._
import sbt.Keys._

lazy val `sbt-akkaserverless` = project.in(file("."))
  .enablePlugins(SbtPlugin, GitPlugin, AkkaGrpcPlugin, ScalafmtPlugin)
  .settings(
    name := "sbt-akkaserverless",
    organizationName  := "Yoppworks Inc.",
    organization := "com.yoppworks",
    scalaVersion := "2.12.12",
    sbtPlugin := true,
    onChangedBuildSource in Global := ReloadOnSourceChanges,
    Global / excludeLintKeys ++= Set(organization),
    scriptedBufferLog := false,
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++ Seq(
          "-Xmx1024M",
          "-Dplugin.version=" + version.value,
          "-Dsbt.ivy.home=" + sbt.Keys.ivyPaths.value.ivyHome.getOrElse("~/.ivy2")
      )
    },
    addSbtPlugin("org.scalameta"            % "sbt-scalafmt"        % V.Plugins.scalafmt ),
    addSbtPlugin("com.dwijnand"             % "sbt-dynver"          % V.Plugins.dynver),
    addSbtPlugin("com.eed3si9n"             % "sbt-buildinfo"       % V.Plugins.buildInfo),
    addSbtPlugin("com.typesafe.sbt"         % "sbt-git"             % V.Plugins.git),
    addSbtPlugin("com.typesafe.sbt"         % "sbt-native-packager" % V.Plugins.nativePackager),
    addSbtPlugin("com.lightbend.akka.grpc"  % "sbt-akka-grpc"       % V.Plugins.akkaGrpc),
  )
