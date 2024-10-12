package testfiles.DefnVar.InClass.mock.WithExplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private var x: Foo = mock[Foo]
}