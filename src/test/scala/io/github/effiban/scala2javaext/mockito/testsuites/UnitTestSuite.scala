package io.github.effiban.scala2javaext.mockito.testsuites

import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterEach, OneInstancePerTest, OptionValues}

class UnitTestSuite extends AnyFunSuite
  with MockitoSugar
  with Matchers
  with OptionValues
  with OneInstancePerTest
  with BeforeAndAfterEach
  with TableDrivenPropertyChecks {
}
