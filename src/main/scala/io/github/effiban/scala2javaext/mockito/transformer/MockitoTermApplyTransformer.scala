package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TermApplyTransformer

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermApplyTransformer extends TermApplyTransformer {

  override def transform(termApply: Term.Apply): Term.Apply = {
    termApply match {
      case q"ArgCaptor($args)" => q"ArgCaptor.apply($args)"
      case q"eqTo($args)" => q"eq($args)"
      case aTermApply => aTermApply
    }
  }
}
