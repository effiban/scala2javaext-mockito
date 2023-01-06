package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.typeinferrers.ApplyTypeTypeInferrer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoApplyTypeTypeInferrer extends ApplyTypeTypeInferrer {

  override def infer(termApplyType: Term.ApplyType): Option[Type] = termApplyType match {
    case Term.ApplyType(q"any" | q"isA" | q"mock" | q"spy", List(tpe)) => Some(tpe)
    case q"anyIterable[$tpe]" => Some(t"Iterable[$tpe]")
    case q"anyList[$tpe]" => Some(t"List[$tpe]")
    case q"anySeq[$tpe]" => Some(t"Seq[$tpe]")
    case q"anySet[$tpe]" => Some(t"Set[$tpe]")
    case Term.ApplyType(q"anyMap", List(keyType, valType)) => Some(Type.Apply(t"Map", List(keyType, valType)))
    case _ => None
  }
}
