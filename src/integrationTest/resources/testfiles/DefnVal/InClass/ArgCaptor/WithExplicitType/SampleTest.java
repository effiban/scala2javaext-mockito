package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.captor.Captor;
import org.mockito.captor.ArgCaptor;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {
    @Captor
    private Captor<Foo> fooCaptor;

    public SampleTest() {
    }
}