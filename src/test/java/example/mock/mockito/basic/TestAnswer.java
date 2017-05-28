package example.mock.mockito.basic;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 使用Answer定义期望的返回值
 */
public class TestAnswer {
    @Test
    public void test1() {
        List mockList = mock(List.class);
        //使用Answer定义期望的返回值；使用匿名类
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello:" + args[0];
            }
        });
        assertThat(mockList.get(0).equals("hello:0")).isEqualTo(true);
        assertThat(mockList.get(1).equals("hello:1")).isEqualTo(true);
    }

    @Test
    public void test2() {
        List mock = mock(List.class);
        doAnswer(new CustomAnswer()).when(mock).get(anyInt());
        assertThat(mock.get(4).equals("num>3")).isEqualTo(true);
    }
}

class CustomAnswer implements Answer<String> {
    public String answer(InvocationOnMock invocation) throws Throwable {
        Object[] args = invocation.getArguments();
        Integer num = (Integer) args[0];
        if (num > 3) {
            return "num>3";
        } else {
            throw new RuntimeException();
        }
    }
}
