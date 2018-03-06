organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

lazy val `hello` = (project in file("."))
  .aggregate(`abc-api`, `abc-impl`)

lazy val `abc-api` = (project in file("abc-api"))
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `abc-impl` = (project in file("abc-impl"))
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomLogback,
      lagomJavadslTestKit,
      lagomJavadslCluster,
      lombok,
      `akka-cluster-tools`
    ),
    lagomServiceLocatorEnabled in ThisBuild := false
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`abc-api`)

val akkaVersion = "2.5.11"
val lombok = "org.projectlombok" % "lombok" % "1.16.18"
val `akka-cluster-tools` = "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion

def common = Seq(
  javacOptions in compile += "-parameters"
)
