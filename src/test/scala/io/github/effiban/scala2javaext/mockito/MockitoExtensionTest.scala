package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2javaext.mockito.predicate.{MockitoImporterExcludedPredicate, MockitoTemplateInitExcludedPredicate}
import io.github.effiban.scala2javaext.mockito.providers.MockitoAdditionalImportersProvider
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.{MockitoClassTransformer, MockitoDefnValToDeclVarTransformer, MockitoDefnValTransformer}

import scala.meta.{Source, XtensionQuasiquoteTerm}

class MockitoExtensionTest extends UnitTestSuite {

  private val mockitoExtension = new MockitoExtension
  import mockitoExtension._

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

  test("additionalImportersProvider() should return MockitoAdditionalImportersProvider") {
    additionalImportersProvider() shouldBe MockitoAdditionalImportersProvider
  }

  test("importerExcludedPredicate() should return MockitoImporterExcludedPredicate") {
    importerExcludedPredicate() shouldBe MockitoImporterExcludedPredicate
  }

  test("classTransformer() should return MockitoClassTransformer") {
    classTransformer() shouldBe MockitoClassTransformer
  }

  test("templateInitExcludedPredicate() should return MockitoTemplateInitExcludedPredicate") {
    templateInitExcludedPredicate() shouldBe MockitoTemplateInitExcludedPredicate
  }

  test("defnValTransformer() should return MockitoDefnValTransformer") {
    defnValTransformer() shouldBe MockitoDefnValTransformer
  }

  test("defnValToDeclVarTransformer() should return MockitoDefnValToDeclVarTransformer") {
    defnValToDeclVarTransformer() shouldBe MockitoDefnValToDeclVarTransformer
  }
}
