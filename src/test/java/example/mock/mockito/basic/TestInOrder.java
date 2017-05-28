package example.mock.mockito.basic;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.mockito.Mockito.*;
import org.mockito.exceptions.verification.VerificationInOrderFailure;

/**
 * 顺序验证
 */
public class TestInOrder {

    @Test
    public void testInOrder() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        //noinspection unchecked
        list.add(1);
        //noinspection unchecked
        list2.add("hello");
        //noinspection unchecked
        list.add(2);
        //noinspection unchecked
        list2.add("world");
        //将需要顺序验证的对象放入InOrder
        InOrder inOrder = inOrder(list, list2);
        //下面的验证顺序不能颠倒，否则会抛出错误
        //noinspection unchecked
        inOrder.verify(list).add(1);
        //noinspection unchecked
        inOrder.verify(list2).add("hello");
        //noinspection unchecked
        inOrder.verify(list).add(2);
        //noinspection unchecked
        inOrder.verify(list2).add("world");
    }

    @Test(expected = VerificationInOrderFailure.class)
    public void testInOrder2() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        //noinspection unchecked
        list.add(1);
        //noinspection unchecked
        list2.add("hello");
        //noinspection unchecked
        list.add(2);
        //noinspection unchecked
        list2.add("world");
        InOrder inOrder = inOrder(list, list2);
        //验证顺序错误，抛出错误
        //noinspection unchecked
        inOrder.verify(list).add(1);
        //noinspection unchecked
        inOrder.verify(list).add(2);
        //noinspection unchecked
        inOrder.verify(list2).add("hello");
        //noinspection unchecked
        inOrder.verify(list2).add("world");
    }
}
