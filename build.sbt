lazy val cats = project.in(file("cats")).settings(
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation"),
  resolvers           += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  libraryDependencies += "org.spire-math" %% "cats" % "0.4.0-SNAPSHOT"
)

lazy val scalaz = project.in(file("scalaz")).settings(
  scalaVersion        := "2.11.7",
  scalacOptions      ++= Seq("-feature", "-unchecked", "-deprecation"),
  libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.0"
)

lazy val root = project.in(file(".")).aggregate(cats, scalaz)
