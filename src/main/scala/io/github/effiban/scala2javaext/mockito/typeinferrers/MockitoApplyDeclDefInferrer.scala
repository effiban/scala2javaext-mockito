package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.contexts.TermApplyInferenceContext
import io.github.effiban.scala2java.spi.entities.PartialDeclDef
import io.github.effiban.scala2java.spi.typeinferrers.ApplyDeclDefInferrer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoApplyDeclDefInferrer extends ApplyDeclDefInferrer {

  override def infer(termApply: Term.Apply, context: TermApplyInferenceContext = TermApplyInferenceContext()): PartialDeclDef = {
    inferByQualifiedName(termApply, context)
      .orElse(inferByQualifierTypeAndName(termApply, context))
      .getOrElse(PartialDeclDef())
  }

  private def inferByQualifiedName(termApply: Term.Apply, context: TermApplyInferenceContext) = {
    termApply.fun match {
      case q"org.mockito.MockitoSugar.mock[$tpe]" => Some(declDefWithNoParamsReturning(tpe))
      case q"org.mockito.MockitoSugar.spy[$tpe]" => Some(declDefWithParamAndReturnType(tpe))
      // TODO infer type of 'when'
      case q"org.mockito.MockitoSugar.verify[$tpe]" => Some(declDefWithParamAndReturnType(tpe))

      case q"org.mockito.MockitoSugar.spy" => Some(declDefInferredFromContext(context))
      // TODO infer type of 'when'
      case q"org.mockito.MockitoSugar.verify" => Some(declDefInferredFromContext(context))

      case q"org.mockito.ArgumentMatchersSugar.isA[$tpe]" => Some(declDefWithNoParamsReturning(tpe))
      case Term.ApplyType(Term.Select(q"org.mockito.ArgumentMatchersSugar", q"eqTo" | q"refEq" | q"same"), List(tpe)) =>
        Some(declDefWithParamAndReturnType(tpe))

      case Term.Select(q"org.mockito.ArgumentMatchersSugar", q"eqTo" | q"refEq" | q"same") => Some(declDefInferredFromContext(context))

      case q"org.mockito.ArgumentMatchersSugar.any[$tpe]" => Some(declDefWithNoParamsReturning(tpe))
      case q"org.mockito.ArgumentMatchersSugar.anyIterable[$tpe]" => Some(declDefWithNoParamsReturning(t"Iterable[$tpe]"))
      case q"org.mockito.ArgumentMatchersSugar.anyList[$tpe]" => Some(declDefWithNoParamsReturning(t"List[$tpe]"))
      case q"org.mockito.ArgumentMatchersSugar.anySeq[$tpe]" => Some(declDefWithNoParamsReturning(t"Seq[$tpe]"))
      case q"org.mockito.ArgumentMatchersSugar.anySet[$tpe]" => Some(declDefWithNoParamsReturning(t"Set[$tpe]"))
      case Term.ApplyType(q"org.mockito.ArgumentMatchersSugar.anyMap", List(keyType, valType)) =>
        Some(declDefWithNoParamsReturning(Type.Apply(t"Map", List(keyType, valType))))

      case q"org.mockito.captor.ArgCaptor.apply[$tpe]" => Some(declDefWithNoParamsReturning(t"Captor[$tpe]"))

      case _ => None
    }
  }

  private def inferByQualifierTypeAndName(termApply: Term.Apply, context: TermApplyInferenceContext) = {
    (context.maybeParentType, termApply.fun) match {
      case (Some(t"org.mockito.MockitoSugar"), Term.ApplyType(Term.Select(_, q"mock"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(tpe))
      case (Some(t"org.mockito.MockitoSugar"), Term.ApplyType(Term.Select(_, q"spy"), List(tpe))) =>
        Some(declDefWithParamAndReturnType(tpe))
      case (Some(t"org.mockito.MockitoSugar"), Term.ApplyType(Term.Select(_, q"verify"), List(tpe))) =>
        Some(declDefWithParamAndReturnType(tpe))

      case (Some(t"org.mockito.MockitoSugar"), Term.Select(_, q"spy")) => Some(declDefInferredFromContext(context))
      // TODO infer type of 'when'
      case (Some(t"org.mockito.MockitoSugar"), Term.Select(_, q"verify")) => Some(declDefInferredFromContext(context))

      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"isA"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(tpe))
      case (
        Some(t"org.mockito.ArgumentMatchersSugar"),
        Term.ApplyType(Term.Select(_, q"eqTo" | q"refEq" | q"same"), List(tpe))) =>
        Some(declDefWithParamAndReturnType(tpe))

      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.Select(_, q"eqTo" | q"refEq" | q"same")) =>
        Some(declDefInferredFromContext(context))

      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"any"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(tpe))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"anyIterable"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(t"Iterable[$tpe]"))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"anyList"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(t"List[$tpe]"))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"anySeq"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(t"Seq[$tpe]"))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"anySet"), List(tpe))) =>
        Some(declDefWithNoParamsReturning(t"Set[$tpe]"))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), Term.ApplyType(Term.Select(_, q"anyMap"), List(keyType, valType))) =>
        Some(declDefWithNoParamsReturning(Type.Apply(t"Map", List(keyType, valType))))

      case _ => None
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
