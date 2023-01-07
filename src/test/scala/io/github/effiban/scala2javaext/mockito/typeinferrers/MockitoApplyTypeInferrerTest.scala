package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.typeinferrers.MockitoApplyTypeInferrer.infer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoApplyTypeInferrerTest extends UnitTestSuite {

  private val FooType = t"Foo"

  private val Scenarios = Table(
    ("TermApply", "MaybeArgTypes", "ExpectedMaybeType"),
    (q"eqTo(foo)", List(Some(FooType)), Some(FooType)),
    (q"eqTo(foo)", List(None), None),

    (q"refEq(foo)", List(Some(FooType)), Some(FooType)),
    (q"refEq(foo)", List(None), None),

    (q"same(foo)", List(Some(FooType)), Some(FooType)),
    (q"same(foo)", List(None), None),

    (q"spy(foo)", List(Some(FooType)), Some(FooType)),
    (q"spy(foo)", List(None), None),

    (q"aaa(foo)", List(Some(FooType)), None),
    (q"aaa(foo)", List(None), None)
  )

  forAll(Scenarios) { case (termApply: Term.Apply, maybeArgTypes: List[Option[Type]], expectedMaybeType: Option[Type]) =>
    expectedMaybeType match {
      case Some(expectedTermApply) =>
        test(s"$termApply with types: $maybeArgTypes should be inferred as type: $expectedTermApply") {
          infer(termApply, maybeArgTypes).value.structure shouldBe expectedTermApply.structure
        }
      case None =>
        test(s"$termApply with types: $maybeArgTypes should not be inferrable") {
          infer(termApply, maybeArgTypes) shouldBe None
        }
    }
  }

}
