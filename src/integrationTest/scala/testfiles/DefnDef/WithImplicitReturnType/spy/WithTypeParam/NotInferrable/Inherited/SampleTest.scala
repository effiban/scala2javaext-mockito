package testfiles.DefnDef.WithImplicitReturnType.spy.WithTypeParam.NotInferrable.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private def newSpy() = {
    val foo: Foo = new Foo()
    spy[Foo](foo)
  }
}