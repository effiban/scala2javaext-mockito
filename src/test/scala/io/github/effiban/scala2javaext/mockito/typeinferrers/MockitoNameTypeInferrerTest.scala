package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.typeinferrers.MockitoNameTypeInferrer.infer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoNameTypeInferrerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("TermName", "ExpectedMaybeType"),
    (q"ArgCaptor", Some(t"Captor")),
    (q"Bla", None)
  )

  forAll(Scenarios) { case (termName: Term.Name, expectedMaybeType: Option[Type]) =>
    expectedMaybeType match {
      case Some(expectedTermApply) =>
        test(s"$termName should be inferred as type: $expectedTermApply") {
          infer(termName).value.structure shouldBe expectedTermApply.structure
        }
      case None =>
        test(s"$termName should not be inferrable") {
          infer(termName) shouldBe None
        }
    }
  }
}
