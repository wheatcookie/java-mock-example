package example.mock.mockito.inject;

import example.model.Dog;
import org.junit.Before;
import org.mockito.Spy;
import org.junit.Test;


import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Spy的错误用法
 * 对比Spy的正确用法TestInitSpy
 */
public class TestInitSpy2 {

    @Spy//这样定义是不对的，dog并不是Spy后的对象
    private Dog dog = new Dog();

//    这样定义正确
    private Dog dog2 = spy(new Dog());

    @Before
    public void setUp() throws Exception {
        //对比 TestInitSpy
    }

    @Test
    public void test() {
        assertThat(dog.getName()).isEqualTo("happy");
        assertThat(dog2.getName()).isEqualTo("happy");

//        对象dog不是一个Mock对象，所以下面会抛出异常org.mockito.exceptions.misusing.NotAMockException
//        doReturn("funny").when(dog).getName();

//        dog2的定义正确，下行代码正常执行
        doReturn("funny").when(dog2).getName();
        assertThat(dog2.getName()).isEqualTo("funny");
    }
}
