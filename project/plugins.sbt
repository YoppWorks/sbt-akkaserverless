addSbtPlugin("com.lightbend.akka.grpc"  % "sbt-akka-grpc"       % "2.1.0")
addSbtPlugin("com.typesafe.sbt"         % "sbt-native-packager" % "1.7.4")
addSbtPlugin("com.dwijnand"             % "sbt-dynver"          % "4.1.1")
addSbtPlugin("com.typesafe.sbt"         % "sbt-git"             % "1.0.0")
addSbtPlugin("com.eed3si9n"             % "sbt-buildinfo"       % "0.10.0")
addSbtPlugin("com.thesamet"             % "sbt-protoc"          % "1.0.4")
addSbtPlugin("org.xerial.sbt"           % "sbt-sonatype"        % "3.9.10")
addSbtPlugin("com.jsuereth"             % "sbt-pgp"             % "2.0.1")
// For ScalaPB 0.11.x:
libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %% "scalapb-validate-codegen" % "0.3.2"
)

