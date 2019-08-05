name := "Hackathon 2019"

crossScalaVersions in ThisBuild := Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions in ThisBuild).value.last


name := "germanicus"
organization := "io.github.hcmid"
version := "1.0.0"

licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "edu.holycross.shot.cite" %% "xcite" % "4.1.0",
  "edu.holycross.shot" %% "scm" % "7.0.1",
  "edu.holycross.shot" %% "ohco2" % "10.13.2",
  "edu.holycross.shot" %% "citeobj" % "7.3.4",
  "edu.holycross.shot" %% "cex" % "6.3.3",

  "edu.holycross.shot" %% "midvalidator" % "7.1.0",

  "edu.holycross.shot" %% "latphone" % "2.5.2",
  "edu.holycross.shot.mid" %% "latin-text-model" % "1.3.0"

)
