package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.typeinferrers.ApplyTypeInferrer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm}

object MockitoApplyTypeInferrer extends ApplyTypeInferrer {

  override def infer(termApply: Term.Apply, maybeArgTypes: List[Option[Type]]): Option[Type] =
    (termApply.fun, maybeArgTypes) match {
      case (q"eqTo" | q"refEq" | q"same" | q"spy", List(Some(tpe))) => Some(tpe)
      case _ => None
    }
}
