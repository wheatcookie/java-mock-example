package example.mock.mockito.inject;

import example.model.Dog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

/**
 * Spring中的用法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSpringInject {
//    装配了一个Mock的dog
    @Autowired
    private Dog dog;

    @Test
    public void test() {
        assertThat(dog.getName()).isEqualTo(null);
        doReturn("funny").when(dog).getName();
        assertThat(dog.getName()).isEqualTo("funny");
    }
}
