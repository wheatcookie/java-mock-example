package example.mock.mockito.inject;

import example.model.Car;
import example.model.Engine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * TestInitMock中阐明了使用MockitoAnnotations.initMocks初始化mock对象的方式
 * 这里使用 RunWith(MockitoJUnitRunner.class)注解方式初始化
 */
@RunWith(MockitoJUnitRunner.class)
public class TestInjectMock {//Engine会注入到Car里面
    @InjectMocks
    private Car car = new Car();
    @Mock
    private Engine engine;

    @Test
    public void test() {
//        Mock的Engine对象已经注入到Car对象中
        assertThat(car.getEngine()).isNotEqualTo(null);
    }

}

