package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermSelectHasApplyMethodTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("TermSelect", "ExpectedHasApplyMethod"),
    (q"org.mockito.captor.ArgCaptor", true),
    (q"org.mockito.mock", false),
    (q"blabla.gaga", false)
  )

  forAll(Scenarios) { (termSelect: Term.Select, expectedHasApplyMethod: Boolean) =>
    test(s"$termSelect should ${if (expectedHasApplyMethod) "have" else "not have"} an apply method") {
      MockitoTermSelectHasApplyMethod(termSelect) shouldBe expectedHasApplyMethod
    }
  }

}
