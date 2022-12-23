package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TermApplyTypeToTermApplyTransformer

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermApplyTypeToTermApplyTransformer extends TermApplyTypeToTermApplyTransformer {

  override def transform(termApplyType: Term.ApplyType): Option[Term.Apply] = {
    import termApplyType._
    (fun, targs) match {
      case (aFun@(q"mock" | q"spy" | q"ArgCaptor"), tpe :: Nil) =>
        Some(Term.Apply(aFun, List(Term.ApplyType(q"classOf", List(tpe)))))
      case _ => None
    }
  }
}
