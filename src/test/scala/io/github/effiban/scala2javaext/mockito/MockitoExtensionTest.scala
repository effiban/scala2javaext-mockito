package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2javaext.mockito.MockitoExtension.shouldBeAppliedTo
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Source, XtensionQuasiquoteTerm}

class MockitoExtensionTest extends UnitTestSuite {

  test("shouldBeAppliedTo() when has one mockito importer should return true") {
    val source = Source(List(q"import org.mockito.Mock"))
    shouldBeAppliedTo(source) shouldBe true
  }

  test("shouldBeAppliedTo() when has two mockito importers should return true") {
    val source = Source(List(
      q"import org.mockito.Mock",
      q"import org.mockito.verify"
    ))
    shouldBeAppliedTo(source) shouldBe true
  }

  test("shouldBeAppliedTo() when has one mockito importer and one other importer should return true") {
    val source = Source(List(
      q"import org.mockito.Mock",
      q"import foo.bar"
    ))
    shouldBeAppliedTo(source) shouldBe true
  }

  test("shouldBeAppliedTo() when has a fully-qualified mockito method invocation should return true") {
    val source = Source(List(
      q"""
      class Foo {
          def bar() {
              org.mockito.Mockito.verify(a).b(3)
          }
      }
      """
    ))
    shouldBeAppliedTo(source) shouldBe true
  }

  test("shouldBeAppliedTo() when has a fully-qualified mockito mock variable definition should return true") {
    val source = Source(List(
      q"""
      class Foo {
        val x: org.mockito.Mock = mock[String]
      }
      """
    ))
    shouldBeAppliedTo(source) shouldBe true
  }

  test("shouldBeAppliedTo() when has no mockito qualified names should return false") {
    val source = Source(List(
      q"import foo1.bar1",
      q"import foo2.bar2"
    ))
    shouldBeAppliedTo(source) shouldBe false
  }
}
