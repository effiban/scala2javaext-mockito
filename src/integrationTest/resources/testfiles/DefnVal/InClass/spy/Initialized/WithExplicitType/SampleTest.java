package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.MockitoSugar.spy;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {
    @Spy
    private final Foo x = new Foo(2);

    public SampleTest() {
    }
}