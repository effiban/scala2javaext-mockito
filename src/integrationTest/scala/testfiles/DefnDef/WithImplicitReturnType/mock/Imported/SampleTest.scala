package testfiles.DefnDef.WithImplicitReturnType.mock.Imported

import org.mockito.MockitoSugar.mock
import testfilesext.Foo

class SampleTest {

  def newMock(): Foo = mock[Foo]
}