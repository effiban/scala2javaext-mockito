package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermNameHasApplyMethodTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("TermName", "ExpectedHasApplyMethod"),
    (q"ArgCaptor", true),
    (q"mock", false),
    (q"blabla", false)
  )

  forAll(Scenarios) { case (termName: Term.Name, expectedHasApplyMethod: Boolean) =>
    test(s"$termName should ${if (expectedHasApplyMethod) "have" else "not have"} an apply method") {
      MockitoTermNameHasApplyMethod(termName) shouldBe expectedHasApplyMethod
    }
  }

}
