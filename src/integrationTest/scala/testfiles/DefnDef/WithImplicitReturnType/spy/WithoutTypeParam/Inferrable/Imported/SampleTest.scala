package testfiles.DefnDef.WithImplicitReturnType.spy.WithoutTypeParam.Inferrable.Imported

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  def newSpy(): Foo = spy(Foo(1))
}