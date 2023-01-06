package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.typeinferrers.{ApplyTypeTypeInferrer, ExtendedTypeInferrers}

trait MockitoTypeInferrers extends ExtendedTypeInferrers {

  override def applyTypeTypeInferrer(): ApplyTypeTypeInferrer = MockitoApplyTypeTypeInferrer
}
