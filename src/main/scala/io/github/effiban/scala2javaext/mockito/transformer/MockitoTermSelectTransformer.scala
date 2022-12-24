package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TermSelectTransformer

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermSelectTransformer extends TermSelectTransformer {

  override def transform(termSelect: Term.Select): Term.Select = {
    termSelect match {
      case q"ArgCaptor.apply" => q"ArgumentCaptor.forClass"
      case aTermSelect => aTermSelect
    }

  }
}
