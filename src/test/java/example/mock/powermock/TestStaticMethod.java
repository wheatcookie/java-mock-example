package example.mock.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * mock 静态方法
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class TestStaticMethod {
    @Test
    public void test() throws Exception {
        PowerMockito.mockStatic(IdGenerator.class);
        PowerMockito.when(IdGenerator.generateNewId()).thenReturn(1);
        int mockResult = IdGenerator.generateNewId();
        assertThat(mockResult == 1).isEqualTo(true);
    }
}

class IdGenerator {
    public static int generateNewId() {
        return 100;
    }
}