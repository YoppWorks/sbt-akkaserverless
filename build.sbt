import sbt._
import sbt.Keys._

lazy val `sbt-akkaserverless` = project.in(file("."))
  .enablePlugins(SbtPlugin, GitPlugin)
  .settings(
    name := "sbt-akkaserverless",
    organizationName  := "Yoppworks Inc.",
    organization := "com.yoppworks",
    scalaVersion := "2.12.14",
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
    libraryDependencies ++= Seq(
      "com.thesamet.scalapb" %% "scalapb-validate-codegen" % "0.3.2"
    ),
    // ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org",
    addSbtPlugin("com.typesafe.sbt"         % "sbt-git"             % V.Plugins.git),
    addSbtPlugin("com.typesafe.sbt"         % "sbt-native-packager" % V.Plugins.nativePackager),
    addSbtPlugin("com.lightbend.akka.grpc"  % "sbt-akka-grpc"       % V.Plugins.akkaGrpc),
    addSbtPlugin("com.thesamet"             % "sbt-protoc"          % "1.0.4"),
  )
