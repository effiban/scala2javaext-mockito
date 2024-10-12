package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.contexts.TermApplyTransformationContext
import io.github.effiban.scala2java.spi.entities.QualifiedTermApply
import io.github.effiban.scala2java.spi.transformers.QualifiedTermApplyTransformer

import scala.meta.{Term, Type, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoQualifiedTermApplyTransformer extends QualifiedTermApplyTransformer {

  override def transform(qualifiedTermApply: QualifiedTermApply, context: TermApplyTransformationContext = TermApplyTransformationContext()): Option[QualifiedTermApply] = {
    transformByQualifiedName(qualifiedTermApply)
      .orElse(transformByQualifierTypeAndName(context.maybeQualifierType, qualifiedTermApply))
  }

  private def transformByQualifiedName(qualifiedTermApply: QualifiedTermApply) =
    (qualifiedTermApply.qualifiedName, qualifiedTermApply.typeArgs, qualifiedTermApply.args) match {

      case (q"org.mockito.MockitoSugar.mock", tpe :: Nil, _) =>
        Some(qualifiedTermApplyWithClassOf(q"org.mockito.Mockito.mock", tpe))
      case (Term.Select(q"org.mockito.MockitoSugar", name), typeArgs, args) =>
        Some(QualifiedTermApply(Term.Select(q"org.mockito.Mockito", name), typeArgs, args))

      case (q"org.mockito.ArgumentMatchersSugar.eqTo", _, arg :: Nil) =>
        Some(QualifiedTermApply(q"org.mockito.ArgumentMatchers.eq", List(arg)))
      case (Term.Select(q"org.mockito.ArgumentMatchersSugar", name@(q"isA" | q"any")), tpe :: Nil, _) =>
        Some(qualifiedTermApplyWithClassOf(Term.Select(q"org.mockito.ArgumentMatchers", name), tpe))
      case (q"org.mockito.ArgumentMatchersSugar.anySeq", tpe :: Nil, _) =>
        Some(QualifiedTermApply(q"org.mockito.ArgumentMatchers.anyList", tpe :: Nil))
      case (Term.Select(q"org.mockito.ArgumentMatchersSugar", name), typeArgs, args) =>
        Some(QualifiedTermApply(Term.Select(q"org.mockito.ArgumentMatchers", name), typeArgs, args))

      case (q"org.mockito.captor.ArgCaptor.apply", tpe :: Nil, _) =>
      Some(qualifiedTermApplyWithClassOf(q"org.mockito.ArgumentCaptor.forClass", tpe))

    case _ => None
  }

  private def transformByQualifierTypeAndName(maybeQualifierType: Option[Type], qualifiedTermApply: QualifiedTermApply) =
    (maybeQualifierType, qualifiedTermApply.qualifiedName.name, qualifiedTermApply.typeArgs, qualifiedTermApply.args) match {
      case (Some(t"org.mockito.MockitoSugar"), q"mock", tpe :: Nil, _) =>
        Some(qualifiedTermApplyWithClassOf(q"org.mockito.Mockito.mock", tpe))
      case (Some(t"org.mockito.MockitoSugar"), name, typeArgs, args) =>
        Some(QualifiedTermApply(Term.Select(q"org.mockito.Mockito", name), typeArgs, args))

      case (Some(t"org.mockito.ArgumentMatchersSugar"), q"eqTo", _, arg :: Nil) =>
        Some(QualifiedTermApply(q"org.mockito.ArgumentMatchers.eq", List(arg)))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), name@(q"isA" | q"any"), tpe :: Nil, _) =>
        Some(qualifiedTermApplyWithClassOf(Term.Select(q"org.mockito.ArgumentMatchers", name), tpe))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), q"anySeq", tpe :: Nil, _) =>
        Some(QualifiedTermApply(q"org.mockito.ArgumentMatchers.anyList", tpe :: Nil))
      case (Some(t"org.mockito.ArgumentMatchersSugar"), name, typeArgs, args) =>
        Some(QualifiedTermApply(Term.Select(q"org.mockito.ArgumentMatchers", name), typeArgs, args))

      case _ => None
    }

  private def qualifiedTermApplyWithClassOf(aFun: Term.Select, tpe: Type) = QualifiedTermApply(aFun, Nil, List(Term.ApplyType(q"classOf", List(tpe))))
}
