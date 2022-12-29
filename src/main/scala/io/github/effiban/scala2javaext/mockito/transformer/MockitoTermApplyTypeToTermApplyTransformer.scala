package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TermApplyTypeToTermApplyTransformer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoTermApplyTypeToTermApplyTransformer extends TermApplyTypeToTermApplyTransformer {

  override def transform(termApplyType: Term.ApplyType): Option[Term.Apply] = {
    import termApplyType._
    (fun, targs) match {
      case (aFun@(q"any" | q"isA" | q"mock" |  q"spy" | q"ArgCaptor"), tpe :: Nil) => Some(termApplyWithClassOf(aFun, tpe))
      case (q"anyIterable", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"Iterable[$tpe]"))
      case (q"anyList", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"List[$tpe]"))
      case (q"anySeq", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"Seq[$tpe]"))
      case (q"anySet", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"Set[$tpe]"))
      case (q"anyMap", keyType :: valueType :: Nil) => Some(termApplyWithClassOf(q"any", Type.Apply(t"Map", List(keyType, valueType))))
      case _ => None
    }
  }

  private def termApplyWithClassOf(aFun: Term, tpe: Type) = Term.Apply(aFun, List(Term.ApplyType(q"classOf", List(tpe))))
}
