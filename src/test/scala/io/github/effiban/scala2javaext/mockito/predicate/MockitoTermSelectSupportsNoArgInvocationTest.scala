package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.contexts.TermSelectInferenceContext
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoTermSelectSupportsNoArgInvocationTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("MaybeQualifierType", "TermSelect", "ExpectedSupportsNoArgInvocation"),
    (Some(t"org.mockito.MockitoSugar"), q"super.mock", true),
    (None, q"org.mockito.captor.ArgCaptor", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.isA", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.any", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.anyIterable", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.anyList", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.anySeq", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.anySet", true),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.anyMap", true),
    (Some(t"org.mockito.MockitoSugar"), q"super.spy", false),
    (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), q"super.eqTo", false)
  )

  forAll(Scenarios) { (maybeQualifierType: Option[Type], termSelect: Term.Select, expectedSupportsNoArgInvocation: Boolean) =>
    test(s"${maybeQualifierType.map(qualType => s"Type $qualType with ").getOrElse("")} $termSelect " +
      s"should ${if (expectedSupportsNoArgInvocation) "support" else "not support"} a no-arg invocation") {
      val context = TermSelectInferenceContext(maybeQualifierType)
      MockitoTermSelectSupportsNoArgInvocation(termSelect, context) shouldBe expectedSupportsNoArgInvocation
    }
  }

}
