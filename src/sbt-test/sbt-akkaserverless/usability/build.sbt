import  com.yoppworks.sbt.AkkaServerlessPlugin


lazy val root = project.in(file("."))
  .enablePlugins(AkkaServerlessPlugin)
