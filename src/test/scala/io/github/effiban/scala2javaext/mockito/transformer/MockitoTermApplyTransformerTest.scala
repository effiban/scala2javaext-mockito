package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoTermApplyTransformer.transform

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermApplyTransformerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("Input", "ExpectedOutput"),
    (q"mock[Foo]()", q"mock(classOf[Foo])"),
    (q"spy(4)", q"spy(4)"),
    (q"spy[Int](4)", q"spy(4)"),
    (q"ArgCaptor.apply[Foo]()", q"ArgumentCaptor.forClass(classOf[Foo])"),
    (q"isA[Foo]()", q"isA(classOf[Foo])"),
    (q"eqTo(33)", q"eq(33)"),
    (q"any[Foo]()", q"any(classOf[Foo])"),
    (q"anyIterable[Foo]()", q"any(classOf[Iterable[Foo]])"),
    (q"anyList[Foo]()", q"any(classOf[List[Foo]])"),
    (q"anyMap[MyKey, MyValue]()", q"any(classOf[Map[MyKey, MyValue]])"),
    (q"anySeq[Foo]()", q"any(classOf[Seq[Foo]])"),
    (q"anySet[Foo]()", q"any(classOf[Set[Foo]])"),
    (q"identity[Foo]()", q"identity[Foo]()"),
    (q"aaa[Foo]()", q"aaa[Foo]()"),
    (q"blabla(4)", q"blabla(4)")
  )

  forAll(Scenarios) {
    (input: Term.Apply, expectedOutput: Term.Apply) =>
      test(s"$input should be transformed to $expectedOutput") {
        transform(input).structure shouldBe expectedOutput.structure
      }
  }
}
