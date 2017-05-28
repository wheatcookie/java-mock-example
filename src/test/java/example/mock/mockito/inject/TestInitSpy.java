package example.mock.mockito.inject;

import example.model.Dog;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


/**
 * Spy的正确用法
 */
public class TestInitSpy {
    @Spy
    private Dog dog;
    @Before
    public void setUp() throws Exception {
        //如果Spy注解的对象没有用new初始化，要用下面这行代码初始化
        MockitoAnnotations.initMocks(this);

    }
    @org.junit.Test
    public void test(){
        assertThat(dog.getName()).isEqualTo("happy");
        doReturn("funny").when(dog).getName();
        assertThat(dog.getName()).isEqualTo("funny");
    }
}
