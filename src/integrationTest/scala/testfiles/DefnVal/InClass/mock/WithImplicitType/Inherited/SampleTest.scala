package testfiles.DefnVal.InClass.mock.WithImplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private val x = mock[Foo]
}