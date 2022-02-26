package sh.yannick.phoenix.common.configuration;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.*;
import sh.yannick.phoenix.common.PhoenixException;

@DisplayName("Test loading configuration from the filesystem")
public class FileSystemConfigurationLoaderTest {
    @Test
    @DisplayName("Test with valid file name")
    public void validFileNameTest() {
        System.out.println(System.getProperty("user.dir"));
        Configuration configuration = new FileSystemConfigurationLoader("src/test/resources/demo.properties").load();
        Assertions.assertEquals("hello", configuration.getString("message"));
    }

    @Test
    @DisplayName("Test with invalid file name")
    public void invalidFileNameTest() {
        Assertions.assertThrows(PhoenixException.class, () -> new FileSystemConfigurationLoader("phoenix-common/src/test/resources/not-existing.properties").load());
    }
}
