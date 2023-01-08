package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.ArgumentCaptor;

@ExtendWith(MockitoExtension.class)
public class SampleTest {
    @Captor
    private ArgumentCaptor<Foo> fooCaptor;

    public SampleTest() {
    }
}