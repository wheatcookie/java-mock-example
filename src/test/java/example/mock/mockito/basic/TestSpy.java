package example.mock.mockito.basic;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Partial Mock(部分模拟)可以使用spy和doCallRealMethod两种方式
 * when()thenReturn与doReturn()when()的区别
 */
public class TestSpy {

    //    使用Spy实现Partial Mock
    @Test
    public void testSpy() {
//        spy默认调用真实方法，如果定义stub,
        List<String> list_spied = spy(new LinkedList<String>());
        when(list_spied.size()).thenReturn(100);
        list_spied.add("one");
        list_spied.add("two");
//        spy.size()通过stub实现返回了值100，而spy.get(0)则返回了实际值。
        assertThat(list_spied.get(0).equals("one")).isEqualTo(true);
        assertThat(list_spied.size() == 100).isEqualTo(true);
//        将list_spied打印，内容为：[one, two]
        System.out.println(Arrays.toString(list_spied.toArray()));
    }

    //when()thenReturn与doReturn()when()区别
    @Test
    public void testSpyWhenReturn() {
        Jack spyJack = spy(new Jack());
        System.out.println("before stub");
        when(spyJack.go()).thenReturn(false);
        System.out.println("after stub");
        spyJack.go();
        assertThat(spyJack.go()).isEqualTo(false);
    }

    @Test
    public void testSpyDoReturnWhen() {
        Jack spyJack = spy(new Jack());
        System.out.println("before stub");
        doReturn(false).when(spyJack).go();
        System.out.println("after stub");
        spyJack.go();
        assertThat(spyJack.go()).isEqualTo(false);
    }

    class Jack {
        public boolean go() {
            System.out.println("I am Jack, Let's go!");
            return true;
        }
    }

    //    使用CallRealMethod实现Partial Mock
    @Test
    public void testCallRealMethod() {
        Jerry jerry = mock(Jerry.class);
        doCallRealMethod().when(jerry).goHome();
        doCallRealMethod().when(jerry).doSomeThingB();
        jerry.goHome();
//       doSomeThingA被调用一次,不过方法A被mock，不会执行其真实的行为
        verify(jerry, times(1)).doSomeThingA();
//        doSomeThingB被调用一次
        verify(jerry, times(1)).doSomeThingB();
    }

    class Jerry {
        public void goHome() {
            doSomeThingA();
            doSomeThingB();
        }

        public void doSomeThingA() {
            System.out.println("doSomeThingA");
        }

        public void doSomeThingB() {
            System.out.println("doSomeThingB");
        }

    }

}
