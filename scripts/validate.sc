import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._

// Run this script from the root of the MID project repository.
val repoRoot = "."


// Standard way to validate:
// 1. define readers your project uses
val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic),
  "MidVerseLReaderDiplomatic" ->   Vector(MidVerseLReaderDiplomatic)
)

// 2. define orthographies your project uses
val orthoMap : Map[String, MidOrthography] = Map(
  "Latin23" -> Latin23
)

// 3. Build a validator. This requires ortho map as well as a CITE library.
val repo = EditorsRepo(repoRoot, readerMap, orthoMap)



// 4. To write reports to a File, a ValidatorReporter uses a ReportWriter (JVM only)
val reporter = ValidationReporter(repo)


def validate(urnStr: String) : Unit = {
  try {
    val urn = Cite2Urn(urnStr)
    /// Add any additional reports here in
    // an optional otherReports parameter, e.g.,
    //  reporter.validate(urn, otherReports=myReports)
    reporter.validate(urn)


  } catch {
    case t: Throwable => {
      println("Well, that didn't work.  Error was:  " + t)
    }
  }

}
//val pg = "urn:cite2:ecod:sg359pages.v1:36"

println("\n\nTo validate a page:\n")
println("\tvalidate(PAGE_URN)\n\n")
println("(Page URNs will look like \"urn:cite2:mid:bern88pages.v1:2r\")")
