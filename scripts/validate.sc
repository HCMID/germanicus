import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.latin._
import scala.io.Source
import java.io.PrintWriter



// Normally, just "." in an MID project
val repoRoot = "."


// Standard way to validate:
// 1. define readers your project uses
val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
)

// 2. define orthographies your project uses
/*
val orthoMap : Map[String, MidOrthography] = Map(
  "Latin23" -> Latin23
)
*/

// 3. Build a validator. This requires ortho map as well as a CITE library.
val repo = EditorsRepo(repoRoot, readerMap)

def validate(pg: String) = {
  val lib =    repo.library
  val dseValidator = DseValidator(lib)
  val validators = Vector(dseValidator)
  val rslts = LibraryValidator.validate(lib, validators)

  val formatted =  TestResultGroup("Valdation results", rslts)
  val output = "validation/report.md"
  new PrintWriter(output){write(formatted.markdown);close;}
  println("Results of validation are in " + output)


  try {
    val pgUrn = Cite2Urn(pg)
    val ict = repo.dse.ictForSurface(pgUrn)
    val verify = Vector(
      "# Verification for page " + pgUrn.objectComponent,
      "Use this link to check for completeness of your editing:",
      s"- verify [page ${pg}](${ict})"
    ).mkString("\n\n")

    val verifyFile = "validation/verify.md"
    new PrintWriter(verifyFile){write(verify);close;}
    println("Link to verify completeness is in " + verifyFile)
  } catch {
    case t: Throwable => {
      println("Wasn't able to create a verification view.")
      println("Error message was:\n" + t)
    }
  }

}



println("\n\nTo validate and verify you work for a page, run:\n")
println("\tvalidate(\"PAGE_URN\")\n\n")
