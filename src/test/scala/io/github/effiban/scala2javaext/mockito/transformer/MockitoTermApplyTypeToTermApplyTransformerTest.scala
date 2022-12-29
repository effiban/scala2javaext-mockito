package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoTermApplyTypeToTermApplyTransformer.transform

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermApplyTypeToTermApplyTransformerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("TermApplyType", "ExpectedMaybeTermApply"),
    (q"any[Foo]", Some(q"any(classOf[Foo])")),
    (q"anyIterable[Foo]", Some(q"any(classOf[Iterable[Foo]])")),
    (q"anyList[Foo]", Some(q"any(classOf[List[Foo]])")),
    (q"anyMap[MyKey, MyValue]", Some(q"any(classOf[Map[MyKey, MyValue]])")),
    (q"anySeq[Foo]", Some(q"any(classOf[Seq[Foo]])")),
    (q"anySet[Foo]", Some(q"any(classOf[Set[Foo]])")),
    (q"isA[Foo]", Some(q"isA(classOf[Foo])")),
    (q"mock[Foo]", Some(q"mock(classOf[Foo])")),
    (q"spy[Foo]", Some(q"spy(classOf[Foo])")),
    (q"ArgCaptor[Foo]", Some(q"ArgCaptor(classOf[Foo])")),
    (q"identity[Foo]", None),
    (q"aaa[Foo]", None)
  )

  forAll(Scenarios) { case (termApplyType: Term.ApplyType, expectedMaybeTermApply: Option[Term.Apply]) =>
    expectedMaybeTermApply match {
      case Some(expectedTermApply) =>
        test(s"$termApplyType should be transformed to $expectedTermApply") {
          transform(termApplyType).value.structure shouldBe expectedTermApply.structure
        }
      case None =>
        test(s"$termApplyType should be transformed to None") {
          transform(termApplyType) shouldBe None
        }
    }
  }
}
