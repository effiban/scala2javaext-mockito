package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.contexts.TermApplyTransformationContext
import io.github.effiban.scala2java.spi.transformers.TermApplyTransformer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoTermApplyTransformer extends TermApplyTransformer {

  override def transform(termApply: Term.Apply, context: TermApplyTransformationContext = TermApplyTransformationContext()): Term.Apply = {
    transformTypedWithoutArgs(termApply)
      .orElse(transformOther(termApply))
      .getOrElse(termApply)
  }

  private def transformTypedWithoutArgs(termApply: Term.Apply) = {
    termApply match {
      case Term.Apply(Term.ApplyType(fun, targs), Nil) => (fun, targs) match {
        case (aFun@(q"any" | q"isA" | q"mock"), tpe :: Nil) => Some(termApplyWithClassOf(aFun, tpe))
        case (q"ArgCaptor.apply", tpe :: Nil) => Some(termApplyWithClassOf(q"ArgumentCaptor.forClass", tpe))
        case (q"anyIterable", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"Iterable[$tpe]"))
        case (q"anyList", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"List[$tpe]"))
        case (q"anySeq", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"Seq[$tpe]"))
        case (q"anySet", tpe :: Nil) => Some(termApplyWithClassOf(q"any", t"Set[$tpe]"))
        case (q"anyMap", keyType :: valueType :: Nil) => Some(termApplyWithClassOf(q"any", Type.Apply(t"Map", List(keyType, valueType))))
        case _ => None
      }
      case _ => None
    }
  }

  private def transformOther(termApply: Term.Apply) = {
    (termApply.fun, termApply.args) match {
      case (Term.ApplyType(q"spy", _), args) => Some(Term.Apply(q"spy", args))
      case (q"eqTo", args) => Some(Term.Apply(q"eq", args))
      case _ => None
    }
  }

  private def termApplyWithClassOf(aFun: Term, tpe: Type) = Term.Apply(aFun, List(Term.ApplyType(q"classOf", List(tpe))))
}
