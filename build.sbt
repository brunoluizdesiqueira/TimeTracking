name := """timetracking"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.adrianhurt" % "play-bootstrap_2.11" % "1.0-P25-B3",
  "com.typesafe.play" % "play-mailer_2.11" % "5.0.0-M1",
  "com.feth" % "play-authenticate_2.11" % "0.8.3"
)

// add resolver for easymail snapshots
resolvers += Resolver.sonatypeRepo("snapshots")

// display deprecated or poorly formed Java
javacOptions ++= Seq("-Xlint:unchecked")
javacOptions ++= Seq("-Xlint:deprecation")
javacOptions ++= Seq("-Xdiags:verbose")

EclipseKeys.preTasks := Seq(compile in Compile)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java       
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)
