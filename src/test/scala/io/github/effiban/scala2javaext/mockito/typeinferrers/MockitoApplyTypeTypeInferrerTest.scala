package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.typeinferrers.MockitoApplyTypeTypeInferrer.infer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoApplyTypeTypeInferrerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("TermApplyType", "ExpectedMaybeType"),
    (q"any[Foo]", Some(t"Foo")),
    (q"anyIterable[Foo]", Some(t"Iterable[Foo]")),
    (q"anyList[Foo]", Some(t"List[Foo]")),
    (q"anyMap[MyKey, MyValue]", Some(t"Map[MyKey, MyValue]")),
    (q"anySeq[Foo]", Some(t"Seq[Foo]")),
    (q"anySet[Foo]", Some(t"Set[Foo]")),
    (q"isA[Foo]", Some(t"Foo")),
    (q"mock[Foo]", Some(t"Foo")),
    (q"spy[Foo]", Some(t"Foo")),
    (q"identity[Foo]", None),
    (q"aaa[Foo]", None)
  )

  forAll(Scenarios) { case (termApplyType: Term.ApplyType, expectedMaybeType: Option[Type]) =>
    expectedMaybeType match {
      case Some(expectedTermApply) =>
        test(s"$termApplyType should be inferred as type: $expectedTermApply") {
          infer(termApplyType).value.structure shouldBe expectedTermApply.structure
        }
      case None =>
        test(s"$termApplyType should not be inferrable") {
          infer(termApplyType) shouldBe None
        }
    }
  }

}
