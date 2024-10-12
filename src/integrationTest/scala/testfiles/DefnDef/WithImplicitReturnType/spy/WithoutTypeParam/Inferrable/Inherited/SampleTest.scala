package testfiles.DefnDef.WithImplicitReturnType.spy.WithoutTypeParam.Inferrable.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  def newSpy(): Foo = spy(Foo(1))
}