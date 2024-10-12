package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.contexts.TermApplyTransformationContext
import io.github.effiban.scala2java.spi.entities.QualifiedTermApply
import io.github.effiban.scala2javaext.mockito.matchers.QualifiedTermApplyScalatestMatcher.equalQualifiedTermApply
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoQualifiedTermApplyTransformer.transform

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

class MockitoQualifiedTermApplyTransformerTest extends UnitTestSuite {

  private val ValidByQualifierNameScenarios = Table(
    ("TermApply", "ExpectedTermApply"),
    (q"org.mockito.MockitoSugar.mock[Foo]()", q"org.mockito.Mockito.mock(classOf[Foo])"),
    (q"org.mockito.MockitoSugar.spy(4)", q"org.mockito.Mockito.spy(4)"),
    (q"org.mockito.MockitoSugar.spy[Int](4)", q"org.mockito.Mockito.spy[Int](4)"),
    (q"org.mockito.MockitoSugar.when(x.y())", q"org.mockito.Mockito.when(x.y())"),
    (q"org.mockito.MockitoSugar.verify(x)", q"org.mockito.Mockito.verify(x)"),
    (q"org.mockito.captor.ArgCaptor.apply[Foo]()", q"org.mockito.ArgumentCaptor.forClass(classOf[Foo])"),
    (q"org.mockito.ArgumentMatchersSugar.isA[Foo]()", q"org.mockito.ArgumentMatchers.isA(classOf[Foo])"),
    (q"org.mockito.ArgumentMatchersSugar.eqTo(33)", q"org.mockito.ArgumentMatchers.eq(33)"),
    (q"org.mockito.ArgumentMatchersSugar.any[Foo]()", q"org.mockito.ArgumentMatchers.any(classOf[Foo])"),
    (q"org.mockito.ArgumentMatchersSugar.anyIterable[Foo]()", q"org.mockito.ArgumentMatchers.anyIterable[Foo]()"),
    (q"org.mockito.ArgumentMatchersSugar.anyList[Foo]()", q"org.mockito.ArgumentMatchers.anyList[Foo]()"),
    (q"org.mockito.ArgumentMatchersSugar.anyMap[MyKey, MyValue]()", q"org.mockito.ArgumentMatchers.anyMap[MyKey, MyValue]()"),
    (q"org.mockito.ArgumentMatchersSugar.anySeq[Foo]()", q"org.mockito.ArgumentMatchers.anyList[Foo]()"),
    (q"org.mockito.ArgumentMatchersSugar.anySet[Foo]()", q"org.mockito.ArgumentMatchers.anySet[Foo]()")
  )

  private val InvalidByQualifierNameScenarios = Table(
    "TermApply",
    q"org.mockito.matchers.AnyMatchers.gaga(4)",
    q"scala.Predef.identity[Foo]()",
    q"aaa.bbb[Foo]()",
    q"blabla.gaga(4)"
  )

  private val ValidByQualifierTypeScenarios = Table(
    ("MaybeQualifierType", "TermApply", "ExpectedTermApply"),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].mock[Foo]()", q"org.mockito.Mockito.mock(classOf[Foo])"),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].spy(4)", q"org.mockito.Mockito.spy(4)"),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].spy[Int](4)", q"org.mockito.Mockito.spy[Int](4)"),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].when(x.y())", q"org.mockito.Mockito.when(x.y())"),
    (Some(t"org.mockito.MockitoSugar"), q"super[MockitoSugar].verify(x)", q"org.mockito.Mockito.verify(x)"),
    (None, q"org.mockito.captor.ArgCaptor.apply[Foo]()", q"org.mockito.ArgumentCaptor.forClass(classOf[Foo])"),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].isA[Foo]()",
      q"org.mockito.ArgumentMatchers.isA(classOf[Foo])"),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].eqTo(33)",
      q"org.mockito.ArgumentMatchers.eq(33)"),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].any[Foo]()",
      q"org.mockito.ArgumentMatchers.any(classOf[Foo])"),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].anyIterable[Foo]()",
      q"org.mockito.ArgumentMatchers.anyIterable[Foo]()"),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super[ArgumentMatchersSugar].anyList[Foo]()",
      q"org.mockito.ArgumentMatchers.anyList[Foo]()"),
    (Some(t"org.mockito.ArgumentMatchersSugar"),
      q"super.anyMap[MyKey, MyValue]()",
      q"org.mockito.ArgumentMatchers.anyMap[MyKey, MyValue]()"),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super.anySeq[Foo]()", q"org.mockito.ArgumentMatchers.anyList[Foo]()"),
    (Some(t"org.mockito.ArgumentMatchersSugar"), q"super.anySet[Foo]()", q"org.mockito.ArgumentMatchers.anySet[Foo]()"),
  )

  private val InvalidByQualifierTypeScenarios = Table(
    ("MaybeQualifierType", "TermApply"),
    (Some(t"org.mockito.matchers.AnyMatchers"), q"blabla.gaga(4)"),
    (None, q"scala.Predef.identity[Foo]()"),
    (None, q"aaa.bbb[Foo]()"),
    (None, q"blabla.gaga(4)")
  )

  forAll(ValidByQualifierNameScenarios) { (termApply: Term.Apply, expectedTermApply: Term.Apply) =>
    val qualifiedTermApply = asQualifiedTermApply(termApply)
    val expectedQualifiedTermApply = asQualifiedTermApply(expectedTermApply)
    val context = TermApplyTransformationContext()

    test(s"$qualifiedTermApply should be transformed to $expectedQualifiedTermApply") {
      transform(qualifiedTermApply, context).value should equalQualifiedTermApply(expectedQualifiedTermApply)
    }
  }

  forAll(InvalidByQualifierNameScenarios) { (termApply: Term.Apply) =>

    val qualifiedTermApply = asQualifiedTermApply(termApply)
    val context = TermApplyTransformationContext()

    test(s"$qualifiedTermApply should be transformed to None") {
      transform(qualifiedTermApply, context) shouldBe None
    }
  }

  forAll(ValidByQualifierTypeScenarios) { (maybeQualifierType: Option[Type],
                                           termApply: Term.Apply,
                                           expectedTermApply: Term.Apply) =>
    val qualifiedTermApply = asQualifiedTermApply(termApply)
    val expectedQualifiedTermApply = asQualifiedTermApply(expectedTermApply)
    val context = TermApplyTransformationContext(maybeQualifierType)

    test(s"${formatQualifierType(maybeQualifierType)} with $qualifiedTermApply should be transformed to $qualifiedTermApply") {
      transform(qualifiedTermApply, context).value should equalQualifiedTermApply(expectedQualifiedTermApply)
    }
  }

  forAll(InvalidByQualifierTypeScenarios) { (maybeQualifierType: Option[Type], termApply: Term.Apply) =>

    val qualifiedTermApply = asQualifiedTermApply(termApply)
    val context = TermApplyTransformationContext(maybeQualifierType)

    test(s"${formatQualifierType(maybeQualifierType)} with $qualifiedTermApply should be transformed to None") {
      transform(qualifiedTermApply, context) shouldBe None
    }
  }

  private def formatQualifierType(maybeQualifierType: Option[Type]) = {
    maybeQualifierType.map(qualType => s"Type $qualType").getOrElse("Untyped qualifier")
  }

  private def asQualifiedTermApply(termApply: Term.Apply) = {
    val qualifiedName =
      termApply.fun match {
        case termSelect: Term.Select => termSelect
        case Term.ApplyType(termSelect: Term.Select, _) => termSelect
        case _ => throw new IllegalArgumentException(s"A Term.Apply used as input by this test must have a qualified name, but '$termApply' doesn't")
      }

    val typeArgs = termApply.fun match {
      case Term.ApplyType(_, targs) => targs
      case _ => Nil
    }

    QualifiedTermApply(qualifiedName, typeArgs, termApply.args)
  }
}
