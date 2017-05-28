package example.mock.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * mock私有方法
 * mock final方法和私有方法类似，不再赘言
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Example.class)
public class TestPrivateMethod {
    @Test
    public void demoPrivateMethodMocking() throws Exception {
        final String methodName = "privateMethod";
        final String input = "today";
        final String expected = "happy";

        Example example = PowerMockito.spy(new Example());
        // Setup the expectation to the private method using the method name
        when(example, methodName, input).thenReturn(expected);
        Assert.assertEquals(expected, example.invokePrivateMethod(input));
        // Optionally verify that the private method was actually called
        verifyPrivate(example).invoke(methodName, input);
    }
}

class Example {
    private String privateMethod(String input) {
        return input;
    }

    public String invokePrivateMethod(String input) {
        return privateMethod(input);
    }
}