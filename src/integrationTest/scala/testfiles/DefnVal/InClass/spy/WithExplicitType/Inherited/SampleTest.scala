package testfiles.DefnVal.InClass.spy.WithExplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private val x: Foo = spy[Foo](Foo(2))
}