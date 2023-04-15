package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.contexts.TermApplyInferenceContext
import io.github.effiban.scala2java.spi.entities.PartialDeclDef
import io.github.effiban.scala2javaext.mockito.matchers.PartialDeclDefScalatestMatcher.equalPartialDeclDef
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.typeinferrers.MockitoApplyDeclDefInferrer.infer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoApplyDeclDefInferrerTest extends UnitTestSuite {

  private val FooType = t"Foo"

  private val Scenarios = Table(
    ("TermApply", "MaybeInputArgType", "ExpectedMaybeParamTypes", "ExpectedMaybeReturnType"),
    (q"mock[Foo]()", None, Nil, Some(FooType)),
    (q"spy[Foo](foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"spy[Foo](foo)", None, List(Some(FooType)), Some(FooType)),
    (q"spy(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"spy(foo)", None, List(None), None),
    (q"ArgCaptor.apply[Foo]()", None, Nil, Some(t"Captor[$FooType]")),
    (q"eqTo(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"eqTo(foo)", None, List(None), None),
    (q"refEq(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"refEq(foo)", None, List(None), None),
    (q"same(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"same(foo)", None, List(None), None),
    (q"isA[Foo]()", None, Nil, Some(FooType)),
    (q"any[Foo]()", None, Nil, Some(FooType)),
    (q"anyIterable[Foo]()", None, Nil, Some(t"Iterable[$FooType]")),
    (q"anyList[Foo]()", None, Nil, Some(t"List[$FooType]")),
    (q"anyMap[MyKey, MyValue]()", None, Nil, Some(t"Map[MyKey, MyValue]")),
    (q"anySeq[Foo]()", None, Nil, Some(t"Seq[$FooType]")),
    (q"anySet[Foo]()", None, Nil, Some(t"Set[$FooType]")),
    (q"identity[Foo]()", None, Nil, None),
    (q"identity()", None, Nil, None),
    (q"aaa[Foo]()", None, Nil, None),
    (q"aaa()", None, Nil, None)
  )

  forAll(Scenarios) { (termApply: Term.Apply,
                       maybeInputArgType: Option[Type],
                       expectedMaybeParamTypes: List[Option[Type]],
                       expectedMaybeReturnType: Option[Type]) =>
    val context = TermApplyInferenceContext(maybeArgTypes = List(maybeInputArgType))
    val expectedPartialDeclDef = PartialDeclDef(maybeParamTypes = expectedMaybeParamTypes, maybeReturnType = expectedMaybeReturnType)
    test(s"$termApply with context $context should be inferred as the partial method signature: $expectedPartialDeclDef") {
      infer(termApply, context) should equalPartialDeclDef(expectedPartialDeclDef)
    }
  }
}
