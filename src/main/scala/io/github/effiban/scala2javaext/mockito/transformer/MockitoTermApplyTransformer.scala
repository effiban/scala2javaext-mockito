package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TermApplyTransformer

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermApplyTransformer extends TermApplyTransformer {

  override def transform(termApply: Term.Apply): Term.Apply = {
    termApply match {
      case q"ArgCaptor($args)" => q"ArgCaptor.apply($args)"
      case q"eqTo($args)" => q"eq($args)"
      // This is to avoid getting an extra incorrect 'classOf' added by the nested transformer
      case Term.Apply(Term.ApplyType(q"spy", _), args) => Term.Apply(q"spy", args)
      case aTermApply => aTermApply
    }
  }
}
