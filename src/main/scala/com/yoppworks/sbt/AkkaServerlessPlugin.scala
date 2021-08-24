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
    Compile / akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Scala),
    Compile / akkaGrpcGeneratedSources := Seq(AkkaGrpc.Client),
    Test / akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Scala),
    Test / akkaGrpcGeneratedSources := Seq(AkkaGrpc.Client),
    Test / PB.protoSources ++= (Compile / PB.protoSources).value,
    Test / mainClass := Some("shopping.Main"),

      // disable javadoc/scaladoc for projects published as docker images
    Compile / packageDoc / publishArtifact := false
  )
}
