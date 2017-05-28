package example.mock.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * mock构造方法
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DirectoryStructure.class)
public class TestConstructionMethod {
    @Test
    public void test() throws Exception {
        final String directoryPath = "mocked path";

        File directoryMock = mock(File.class);
        // This is how you tell PowerMockito to mock construction of a new File.
        whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock);
        when(directoryMock.exists()).thenReturn(false);
        when(directoryMock.mkdirs()).thenReturn(true);
//        调用DirectoryStructure的create方法验证返回值
        assertThat(new DirectoryStructure().create(directoryPath)).isEqualTo(true);
//        验证 new File 调用且仅调用了一次，
        verifyNew(File.class).withArguments(directoryPath);
    }
}


class DirectoryStructure {
    public boolean create(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists()) {
            throw new IllegalArgumentException(
                    "\"" + directoryPath + "\" already exists.");
        }
        return directory.mkdirs();
    }
}