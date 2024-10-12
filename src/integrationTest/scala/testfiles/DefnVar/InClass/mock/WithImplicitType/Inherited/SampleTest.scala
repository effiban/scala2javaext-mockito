package testfiles.DefnVar.InClass.mock.WithImplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private var x = mock[Foo]
}