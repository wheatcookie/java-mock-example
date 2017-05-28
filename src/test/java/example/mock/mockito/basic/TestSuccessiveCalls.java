package example.mock.mockito.basic;

import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * 连续调用
 */
public class TestSuccessiveCalls {

    @Test
    public void test() {
        List<Integer> mockList = mock(List.class);
        //下面这种写法，只有最后一个有效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
//        调用多少次，也是返回2
        assertThat(mockList.get(0) == 2).isEqualTo(true);
        assertThat(mockList.get(0) == 2).isEqualTo(true);
        assertThat(mockList.get(0) == 2).isEqualTo(true);

//        下面这种写法，第一次调用返回0，之后所有调用返回1
        when(mockList.get(1)).thenReturn(0).thenReturn(1);

        assertThat(mockList.get(1) == 0).isEqualTo(true);
        assertThat(mockList.get(1) == 1).isEqualTo(true);
        assertThat(mockList.get(1) == 1).isEqualTo(true);
    }
}
