package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.captor.Captor;
import org.mockito.captor.ArgCaptor;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {

    public SampleTest() {
    }

    public void aMethod() {
        final Captor<Foo> fooCaptor = ArgCaptor(Foo.class);
    }
}