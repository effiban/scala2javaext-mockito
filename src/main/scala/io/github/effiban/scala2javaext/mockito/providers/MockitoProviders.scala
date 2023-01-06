package io.github.effiban.scala2javaext.mockito.providers

import io.github.effiban.scala2java.spi.providers.{AdditionalImportersProvider, ExtendedProviders}

trait MockitoProviders extends ExtendedProviders {

  override def additionalImportersProvider(): AdditionalImportersProvider = MockitoAdditionalImportersProvider
}
