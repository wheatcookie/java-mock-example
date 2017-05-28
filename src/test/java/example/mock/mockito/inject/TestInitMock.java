package example.mock.mockito.inject;

import example.model.Dog;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

/**
 * MockitoAnnotations.initMocks初始化Mock和Spy的对象
 */
public class TestInitMock {
    @Mock
    private Dog dogMocked1;
    @Mock
    private Dog dogMocked2;
    @Spy
    private Dog dogSpied1;
    @Before
    public void setUp() throws Exception {
        //将Mock和Spy注解的对象全部初始化,对于Mock注解和Spy初始化方式不同,Spy会调用真实的构造方法，Mock不会
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test(){
        assertThat(dogMocked1.getName()).isEqualTo(null);
        assertThat(dogMocked2.getName()).isEqualTo(null);
        assertThat(dogSpied1.getName()).isEqualTo("happy");

        doReturn("happy").when(dogMocked1).getName();
        assertThat(dogMocked1.getName()).isEqualTo("happy");
        doReturn("funny").when(dogSpied1).getName();
        assertThat(dogSpied1.getName()).isEqualTo("funny");
    }
}

