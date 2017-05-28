package example.mock.mockito.basic;

import org.junit.Test;
import org.mockito.exceptions.verification.NoInteractionsWanted;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * mock对象会记住所有的交互
 */
public class TestInteraction {

    //    验证是否有交互行为
    @Test
    public void testVerify() throws RuntimeException {
        List mockedList = mock(List.class);
        mockedList.get(0);
        verify(mockedList).get(0);
    }

    //    验证零交互
    @Test
    public void testVerifyZero() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        List list3 = mock(List.class);

        list.add(1);
//        调用过list.add(1)
        verify(list).add(1);
//        没有调用list.add(2)
        verify(list, never()).add(2);
//        可以验证一个
        verifyZeroInteractions(list2);
//        也可以验证多个
        verifyZeroInteractions(list2, list3);
    }

    //    验证冗余交互
    @Test
    public void testRedundantInteraction() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        verify(list, times(2)).add(anyInt());
//        检查是否有未被验证的互动行为，因为add(1)和add(2)都会被上面的anyInt()验证到，所以下面的代码会验证通过
        verifyNoMoreInteractions(list);
    }

    @Test(expected = NoInteractionsWanted.class)
    public void testRedundantInteraction2() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        verify(list).add(1);
//        检查是否有未被验证的互动行为，因为add(2)没有被验证，所以下面会验证失败抛出异常
        verifyNoMoreInteractions(list);
    }
}
