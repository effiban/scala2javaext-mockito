package testfiles.DefnVal.InClass.mock.WithExplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private val x: Foo = mock[Foo]
}