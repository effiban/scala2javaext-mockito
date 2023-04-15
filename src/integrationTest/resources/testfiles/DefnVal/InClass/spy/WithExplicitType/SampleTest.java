package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class SampleTest {
    @Spy
    private final Foo x = new Foo(2);
}