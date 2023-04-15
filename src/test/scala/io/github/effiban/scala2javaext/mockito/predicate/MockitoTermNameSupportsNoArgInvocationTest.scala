package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermNameSupportsNoArgInvocationTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("TermName", "ExpectedSupportsNoArgInvocation"),
    (q"mock", true),
    (q"ArgCaptor", true),
    (q"isA", true),
    (q"any", true),
    (q"anyIterable", true),
    (q"anyList", true),
    (q"anySeq", true),
    (q"anySet", true),
    (q"anyMap", true),
    (q"spy", false),
    (q"eqTo", false)
  )

  forAll(Scenarios) { case (termName: Term.Name, expectedSupportsNoArgInvocation: Boolean) =>
    test(s"$termName should ${if (expectedSupportsNoArgInvocation) "support" else "not support"} a no-arg invocation") {
      MockitoTermNameSupportsNoArgInvocation(termName) shouldBe expectedSupportsNoArgInvocation
    }
  }

}
