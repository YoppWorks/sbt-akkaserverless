import sbt._
import sbt.Keys._

object V {

  val akka = "2.6.16"
  val akkaGrpc = "2.0.0"
  val akkaHttp = "10.2.6"
  val akkaServerless = "0.7.0"
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

  object Plugins {
    val scalafmt = "2.4.2"
    val scoverage = "1.6.1"
    val dynver = "4.0.0"
    val buildInfo = "0.9.0"
    val git = "1.0.1"
    val nativePackager = "1.8.1"
    val akkaGrpc = "2.1.0"
    val scalaPBRuntime = "0.11.5"
    val scalaTest = "3.2.9"
    val scalaPbJson = "0.11.0"
  }
}
