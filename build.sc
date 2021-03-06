import mill._, scalalib._, scalafmt._

// Using SbtModule because it uses the standard src/main/scala, src/test/scala layout
object storage extends SbtModule with ScalafmtModule{

   def scalaVersion = "2.13.1"
   def circeVersion = "0.13.0"
   def odinVersion = "0.7.0"

   // Keep this code sparkly clean with the POWER OF TYPES.  
   def compileIvyDeps = Agg(ivy"org.wartremover::wartremover:2.4.7")  
   def scalacPluginIvyDeps = Agg(ivy"org.wartremover::wartremover:2.4.7")

   def ivyDeps = Agg(
      ivy"io.circe::circe-core:${circeVersion}",
      ivy"io.circe::circe-generic:${circeVersion}",
      ivy"io.circe::circe-parser:${circeVersion}",
      ivy"io.circe::circe-literal:${circeVersion}",

      // java packages use single : 
      ivy"com.azure:azure-storage-blob:12.6.0",

      ivy"com.softwaremill.sttp.client::core:2.1.0-RC1",
      ivy"com.softwaremill.sttp.client::http4s-backend:2.1.0-RC1",
      ivy"com.softwaremill.sttp.client::circe:2.1.0-RC1",

      ivy"co.fs2::fs2-core:2.3.0",

      ivy"org.typelevel::cats-core:2.1.1",
      ivy"org.typelevel::cats-effect:2.1.2",
      
      ivy"com.github.pureconfig::pureconfig:0.12.2",
      ivy"com.github.valskalla::odin-core:${odinVersion}",
      ivy"com.github.valskalla::odin-slf4j:${odinVersion}",
      ivy"com.github.valskalla::odin-json:${odinVersion}",
      ivy"com.github.valskalla::odin-extras:${odinVersion}",
   )

   def scalacOptions = Seq(
      "-P:wartremover:traverser:org.wartremover.warts.Unsafe", // Wart remover for all Unsafe warts 
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-explaintypes", // Explain type errors in more detail.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
      "-language:experimental.macros", // Allow macro definition (besides implementation and application)
      "-language:higherKinds", // Allow higher-kinded types
      "-language:implicitConversions", // Allow definition of implicit functions called views
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
      "-Xfatal-warnings", // Fail the compilation if there are any warnings.
      "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
      "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
      "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
      "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
      "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
      "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
      "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
      "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
      "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
      "-Xlint:option-implicit", // Option.apply used implicit view.
      "-Xlint:package-object-classes", // Class or object defined in package object.
      "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
      "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
      "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
      "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
      "-Ywarn-numeric-widen", // Warn when numerics are widened.
      "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
      "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
      "-Ywarn-unused:locals", // Warn if a local definition is unused.
      "-Ywarn-unused:params", // Warn if a value parameter is unused.
      "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
      "-Ywarn-unused:privates", // Warn if a private member is unused.
      "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
      "-Ybackend-parallelism", "8", // Enable paralellisation — change to desired number!
      "-Ycache-plugin-class-loader:last-modified", // Enables caching of classloaders for compiler plugins
      "-Ycache-macro-class-loader:last-modified", // and macro definitions. This can lead to performance improvements.
   )

   object test extends Tests {

      def scalaTestVersion = "3.1.1"

      def ivyDeps = Agg(ivy"org.scalatest::scalatest:${scalaTestVersion}")
      def testFrameworks = Seq("org.scalatest.tools.Framework")
   }

}
