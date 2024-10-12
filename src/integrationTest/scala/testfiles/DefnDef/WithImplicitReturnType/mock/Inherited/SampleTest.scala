package testfiles.DefnDef.WithImplicitReturnType.mock.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  def newMock(): Foo = mock[Foo]
}