package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.typeinferrers.NameTypeInferrer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoNameTypeInferrer extends NameTypeInferrer {

  override def infer(termName: Term.Name): Option[Type] =
    termName match {
      case q"ArgCaptor" => Some(t"Captor")
      case _ => None
    }
}
