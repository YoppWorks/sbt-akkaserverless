package com.yoppworks.sbt

import com.typesafe.sbt.{GitPlugin, SbtNativePackager}
import sbt._
import sbt.Keys._
import sbt.AutoPlugin
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.docker.DockerPlugin
import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport._
import org.scalafmt.sbt.ScalafmtPlugin
import sbtbuildinfo.BuildInfoPlugin
import sbtdynver.DynVerPlugin
import akka.grpc.sbt.AkkaGrpcPlugin
import akka.grpc.sbt.AkkaGrpcPlugin.autoImport._
import com.typesafe.sbt.packager.Keys.{dockerRepository, packageName}
import sbt.nio.Keys.{ReloadOnSourceChanges, onChangedBuildSource}
import sbtprotoc.ProtocPlugin.autoImport.PB


/** The AkkaServerless Plugin */
object AkkaServerlessPlugin extends AutoPlugin {
  override def requires: Plugins =
    JavaAppPackaging && DockerPlugin && DynVerPlugin && ScalafmtPlugin && BuildInfoPlugin && GitPlugin &&
      SbtNativePackager && AkkaGrpcPlugin

  val akka = "2.6.16"
  val akkaGrpc = "2.0.0"
  val akkaHttp = "10.2.6"
  val akkaServerless = "0.7.0-beta.17"
  val alpakka = "3.0.1"
  val commonsIo = "2.4"
  val config = "1.4.0"
  val grpc = "1.38.1"
  val jackson = "2.10.5.1"
  val javaxActivation = "1.2.0"
  val jaxbApi = "2.3.0"
  val logback = "1.2.3"
  val scala212 = "2.12.13"
  val scala213 = "2.13.4"
  val scalaPBRuntime = "0.11.3"
  val scalaTest = "3.2.9"
  val scalaPbJson = "0.11.0"

  object AkkaServerless {
    val ProtocolVersionMajor = 0
    val ProtocolVersionMinor = 7
    val FrameworkVersion = akkaServerless
  }

  object Module {
    val akkaServerless = "com.akkaserverless"
    val scalapb = "com.thesamet.scalapb"
    val akka = "com.typesafe.akka"
    val akkaGrpc = "com.lightbend.akka.grpc"
    val googleGrpc= "io.grpc"
  }

  private val scalaPbLibs = Seq(
    Module.scalapb %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "compile;protobuf",
    Module.scalapb %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion % "compile;protobuf",
    Module.scalapb %% "scalapb-validate-core" % scalapb.validate.compiler.BuildInfo.version % "compile;protobuf",
    Module.scalapb %% "scalapb-json4s" % scalaPbJson,
    Module.scalapb %% "compilerplugin" % scalapb.compiler.Version.scalapbVersion
  )

  private val akkaGrpcLibs = Seq(
    Module.akka	%% "akka-discovery"	% akka,
    Module.akka	%% "akka-http-core" % akkaHttp,
    Module.akka %% "akka-http" % akkaHttp,
    Module.akka %% "akka-stream" % akka,
    Module.akkaGrpc %% "akka-grpc-runtime" % akkaGrpc,
    Module.googleGrpc % "grpc-core" 	% grpc,
    Module.googleGrpc % "grpc-netty-shaded"	% grpc
  )

  val dependencies = akkaGrpcLibs ++ scalaPbLibs ++ Seq(
    Module.akkaServerless % "akkaserverless-proxy-protocol" % AkkaServerless.FrameworkVersion % "compile;protobuf",
    Module.akkaServerless % "akkaserverless-sdk-protocol" % AkkaServerless.FrameworkVersion % "compile;protobuf",
    "ch.qos.logback" % "logback-classic" % logback,
    "org.scalatest" %% "scalatest" % scalaTest % Test,
  )

  override def projectSettings = Seq(
    dockerBaseImage := "adoptopenjdk/openjdk11:jre-11.0.8_10-ubi",
    dockerUsername := Some("akkaserverless"),
    dockerUpdateLatest := true,
    scalaVersion := "2.13.5",
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    ThisBuild / DynVerPlugin.autoImport.dynverSeparator := "-",
    ThisBuild / versionScheme := Some("semver-spec"),
    ThisBuild / evictionErrorLevel := Level.Warn,
    excludeLintKeys ++= Set(packageName, dockerRepository),
    libraryDependencies ++= dependencies,
    Compile / akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Scala),
    Compile / akkaGrpcGeneratedSources := Seq(AkkaGrpc.Client, AkkaGrpc.Server),
    Test / akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Scala),
    Test / akkaGrpcGeneratedSources := Seq(AkkaGrpc.Client, AkkaGrpc.Server),
    Test / PB.protoSources ++= (Compile / PB.protoSources).value,
    Test / mainClass := Some("shopping.Main"),

    Compile / PB.targets := Seq(
      scalapb.gen() -> (Compile / sourceManaged).value / "scalapb",
      scalapb.validate.gen() -> (Compile / sourceManaged).value / "scalapb"
    ),

    // disable javadoc/scaladoc for projects published as docker images
    Compile / packageDoc / publishArtifact := false


  )
}
