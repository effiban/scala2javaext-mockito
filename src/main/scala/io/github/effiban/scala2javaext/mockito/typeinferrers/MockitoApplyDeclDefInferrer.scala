package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.contexts.TermApplyInferenceContext
import io.github.effiban.scala2java.spi.entities.PartialDeclDef
import io.github.effiban.scala2java.spi.typeinferrers.ApplyDeclDefInferrer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoApplyDeclDefInferrer extends ApplyDeclDefInferrer {

  override def infer(termApply: Term.Apply, context: TermApplyInferenceContext = TermApplyInferenceContext()): PartialDeclDef = {
    (termApply.fun, termApply.args) match {
      case (Term.ApplyType(q"mock" | q"isA" | q"any", List(tpe)), Nil) => declDefWithNoParamsReturning(tpe)
      case (Term.ApplyType(q"spy" | q"eqTo" | q"refEq" | q"same", List(tpe)), _) => declDefWithParamAndReturnType(tpe)
      case (q"spy" | q"eqTo" | q"refEq" | q"same", _) => declDefInferredFromContext(context)
      case (q"ArgCaptor.apply[$tpe]", Nil) => declDefWithNoParamsReturning(t"Captor[$tpe]")
      case (q"anyIterable[$tpe]", _) => declDefWithNoParamsReturning(t"Iterable[$tpe]")
      case (q"anyList[$tpe]", _) => declDefWithNoParamsReturning(t"List[$tpe]")
      case (q"anySeq[$tpe]", _) => declDefWithNoParamsReturning(t"Seq[$tpe]")
      case (q"anySet[$tpe]", _) => declDefWithNoParamsReturning(t"Set[$tpe]")
      case (Term.ApplyType(q"anyMap", List(keyType, valType)), _) =>
        declDefWithNoParamsReturning(Type.Apply(t"Map", List(keyType, valType)))
      case _ => PartialDeclDef()
    }
  }


  private def declDefWithParamAndReturnType(tpe: Type) = {
    PartialDeclDef(maybeParamTypes = List(Some(tpe)), maybeReturnType = Some(tpe))
  }

  private def declDefWithNoParamsReturning(returnType: Type) = PartialDeclDef(maybeReturnType = Some(returnType))

  private def declDefInferredFromContext(context: TermApplyInferenceContext) = {
    PartialDeclDef(maybeParamTypes = context.maybeArgTypes,
      maybeReturnType = context.maybeArgTypes.headOption.flatten)
  }
}
