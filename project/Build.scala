import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "app-wireframe-java"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
       "com.sun.jersey" % "jersey-bundle" % "1.16",
       "commons-codec" % "commons-codec" % "1.4",
       "com.google.collections" % "google-collections" % "1.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    )

}
