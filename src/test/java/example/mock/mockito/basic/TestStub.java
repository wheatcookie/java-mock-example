package example.mock.mockito.basic;

import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * mockito如何自定义返回值，如何抛出一个异常
 */
public class TestStub {

    @Test
    public void testStub() throws RuntimeException {
        List mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("first");
        assertThat(mockedList.get(0).equals("first")).isEqualTo(true);
//        没有stub，默认是返回null
        assertThat(mockedList.get(999) == null).isEqualTo(true);
        when(mockedList.get(anyInt())).thenReturn("happy");
        assertThat(mockedList.get(999).equals("happy")).isEqualTo(true);
//         新的stub会覆盖之前的stub
        assertThat(mockedList.get(0).equals("happy")).isEqualTo(true);
    }

//    stub exception
    @Test(expected = RuntimeException.class)
    public void testStubException() throws RuntimeException {
        List mockedList = mock(List.class);
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        //抛出 runtime exception
        mockedList.get(1);
    }

    @Test(expected = RuntimeException.class)
    public void testStubException2() {
        List mockedList = mock(List.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }
}
