import sbt._
import sbt.Keys._

object V {

  val akka = "2.6.16"
  val akkaGrpc = "2.0.0"
  val akkaHttp = "10.2.6"
  val akkaServerless = "0.7.0-beta.18"
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

  object Plugins {
    val scalafmt = "2.4.2"
    val scoverage = "1.6.1"
    val dynver = "4.0.0"
    val buildInfo = "0.9.0"
    val git = "1.0.1"
    val nativePackager = "1.8.1"
    val akkaGrpc = "2.0.0"
  }

  /*
  private val scalaPbLibs = Seq(
    Module.scalapb %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "compile;protobuf",
    Module.scalapb %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion % "compile;protobuf",
    Module.scalapb %% "scalapb-validate-core" % scalapb.validate.compiler.BuildInfo.version % "compile;protobuf",
    Module.scalapb %% "scalapb-validate-codegen" % "0.3.1",
    Module.scalapb %% "scalapb-json4s" % V.scalaPbJson,
    Module.scalapb %% "compilerplugin" % scalapb.compiler.Version.scalapbVersion
  )

   */

  private val akkaGrpcLibs = Seq(
    Module.akka	%% "akka-discovery"	% V.akka,
    Module.akka	%% "akka-http-core" % V.akkaHttp,
    Module.akka %% "akka-http" % V.akkaHttp,
    Module.akka %% "akka-stream" % V.akka,
    Module.akkaGrpc %% "akka-grpc-runtime" % V.akkaGrpc,
    Module.googleGrpc % "grpc-core" 	% V.grpc,
    Module.googleGrpc % "grpc-netty-shaded"	% V.grpc
  )

  val dependencies = Seq(
    Module.akkaServerless % "akkaserverless-proxy-protocol" % AkkaServerless.FrameworkVersion % "compile;protobuf",
    Module.akkaServerless % "akkaserverless-sdk-protocol" % AkkaServerless.FrameworkVersion % "compile;protobuf",
    "ch.qos.logback" % "logback-classic" % logback,
    "org.scalatest" %% "scalatest" % scalaTest % Test,
  )

}
