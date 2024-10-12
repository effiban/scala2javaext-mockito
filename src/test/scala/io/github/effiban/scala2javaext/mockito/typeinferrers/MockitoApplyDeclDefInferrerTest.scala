package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.contexts.TermApplyInferenceContext
import io.github.effiban.scala2java.spi.entities.PartialDeclDef
import io.github.effiban.scala2javaext.mockito.matchers.PartialDeclDefScalatestMatcher.equalPartialDeclDef
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.typeinferrers.MockitoApplyDeclDefInferrer.infer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoApplyDeclDefInferrerTest extends UnitTestSuite {

  private val FooType = t"Foo"

  private val ValidByQualifiedNameScenarios = Table(
    ("TermApply", "MaybeInputArgType", "ExpectedMaybeParamTypes", "ExpectedMaybeReturnType"),
    (q"org.mockito.MockitoSugar.mock[Foo]()", None, Nil, Some(FooType)),
    (q"org.mockito.MockitoSugar.spy[Foo](foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.MockitoSugar.spy[Foo](foo)", None, List(Some(FooType)), Some(FooType)),
    (q"org.mockito.MockitoSugar.spy(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.MockitoSugar.spy(foo)", None, List(None), None),
    (q"org.mockito.MockitoSugar.verify[Foo](foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.MockitoSugar.verify[Foo](foo)", None, List(Some(FooType)), Some(FooType)),
    (q"org.mockito.MockitoSugar.verify(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.MockitoSugar.verify(foo)", None, List(None), None),
    (q"org.mockito.ArgumentMatchersSugar.eqTo(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.ArgumentMatchersSugar.eqTo(foo)", None, List(None), None),
    (q"org.mockito.ArgumentMatchersSugar.refEq(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.ArgumentMatchersSugar.refEq(foo)", None, List(None), None),
    (q"org.mockito.ArgumentMatchersSugar.same(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (q"org.mockito.ArgumentMatchersSugar.same(foo)", None, List(None), None),
    (q"org.mockito.ArgumentMatchersSugar.isA[Foo]()", None, Nil, Some(FooType)),
    (q"org.mockito.ArgumentMatchersSugar.any[Foo]()", None, Nil, Some(FooType)),
    (q"org.mockito.ArgumentMatchersSugar.anyIterable[Foo]()", None, Nil, Some(t"Iterable[$FooType]")),
    (q"org.mockito.ArgumentMatchersSugar.anyList[Foo]()", None, Nil, Some(t"List[$FooType]")),
    (q"org.mockito.ArgumentMatchersSugar.anyMap[MyKey, MyValue]()", None, Nil, Some(t"Map[MyKey, MyValue]")),
    (q"org.mockito.ArgumentMatchersSugar.anySeq[Foo]()", None, Nil, Some(t"Seq[$FooType]")),
    (q"org.mockito.ArgumentMatchersSugar.anySet[Foo]()", None, Nil, Some(t"Set[$FooType]")),
    (q"org.mockito.captor.ArgCaptor.apply[Foo]()", None, Nil, Some(t"Captor[$FooType]"))
  )

  private val InvalidByQualifiedNameScenarios = Table(
    ("TermApply", "MaybeInputArgType"),
    (q"scala.Predef.identity[Foo]()", None),
    (q"scala.Predef.identity()", None),
    (q"AAA.bbb[Foo]()", None),
    (q"AAA.bbb()", None)
  )

  private val ValidByQualifierTypeAndNameScenarios = Table(
    ("MaybeParentType", "TermApply", "MaybeInputArgType", "ExpectedMaybeParamTypes", "ExpectedMaybeReturnType"),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].mock[Foo]()", None, Nil, Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].spy[Foo](foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].spy[Foo](foo)", None, List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].spy(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].spy(foo)", None, List(None), None),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].verify[Foo](foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].verify[Foo](foo)", None, List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].verify(foo)", Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].verify(foo)", None, List(None), None),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].eqTo(foo)",
      Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].eqTo(foo)", None, List(None), None),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].refEq(foo)",
      Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].refEq(foo)", None, List(None), None),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].same(foo)",
      Some(FooType), List(Some(FooType)), Some(FooType)),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].same(foo)", None, List(None), None),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].isA[Foo]()", None, Nil, Some(FooType)),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].any[Foo]()", None, Nil, Some(FooType)),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].anyIterable[Foo]()",
      None, Nil, Some(t"Iterable[$FooType]")),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].anyList[Foo]()", None, Nil, Some(t"List[$FooType]")),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].anyMap[MyKey, MyValue]()",
      None, Nil, Some(t"Map[MyKey, MyValue]")),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].anySeq[Foo]()", None, Nil, Some(t"Seq[$FooType]")),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super[ArgumentMatchersSugar].anySet[Foo]()", None, Nil, Some(t"Set[$FooType]")),
    (Some(t"org.mockito.captor.Captor"), q"org.mockito.captor.ArgCaptor.apply[Foo]()", None, Nil, Some(t"Captor[$FooType]"))
  )

  private val InvalidByQualifierTypeAndNameScenarios = Table(
    ("MaybeParentType", "TermApply", "MaybeInputArgType"),
    (Some(t"AAA"), q"super.bbb[Foo]()", None),
    (Some(t"AAA"), q"super.bbb()", None)
  )

  forAll(ValidByQualifiedNameScenarios) { (termApply: Term.Apply,
                                           maybeInputArgType: Option[Type],
                                           expectedMaybeParamTypes: List[Option[Type]],
                                           expectedMaybeReturnType: Option[Type]) =>

    val context = TermApplyInferenceContext(maybeArgTypes = List(maybeInputArgType))
    val expectedPartialDeclDef = PartialDeclDef(maybeParamTypes = expectedMaybeParamTypes, maybeReturnType = expectedMaybeReturnType)
    test(s"$termApply with context $context should be inferred as the partial method signature: $expectedPartialDeclDef") {
      infer(termApply, context) should equalPartialDeclDef(expectedPartialDeclDef)
    }
  }

  forAll(InvalidByQualifiedNameScenarios) { (termApply: Term.Apply, maybeInputArgType: Option[Type]) =>

    val context = TermApplyInferenceContext(maybeArgTypes = List(maybeInputArgType))
    test(s"$termApply with context $context should be inferred as empty") {
      infer(termApply, context) should equalPartialDeclDef(PartialDeclDef())
    }
  }

  forAll(ValidByQualifierTypeAndNameScenarios) { (maybeParentType: Option[Type],
                                                  termApply: Term.Apply,
                                                  maybeInputArgType: Option[Type],
                                                  expectedMaybeParamTypes: List[Option[Type]],
                                                  expectedMaybeReturnType: Option[Type]) =>

    val context = TermApplyInferenceContext(maybeParentType = maybeParentType, maybeArgTypes = List(maybeInputArgType))
    val expectedPartialDeclDef = PartialDeclDef(maybeParamTypes = expectedMaybeParamTypes, maybeReturnType = expectedMaybeReturnType)
    test(s"$termApply with context $context should be inferred as the partial method signature: $expectedPartialDeclDef") {
      infer(termApply, context) should equalPartialDeclDef(expectedPartialDeclDef)
    }
  }

  forAll(InvalidByQualifierTypeAndNameScenarios) { (maybeParentType: Option[Type],
                                                    termApply: Term.Apply,
                                                    maybeInputArgType: Option[Type]) =>

    val context = TermApplyInferenceContext(maybeParentType = maybeParentType, maybeArgTypes = List(maybeInputArgType))
    test(s"$termApply with context $context should be inferred as empty") {
      infer(termApply, context) should equalPartialDeclDef(PartialDeclDef())
    }
  }
}
