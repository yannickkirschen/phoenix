package sh.yannick.phoenix.common.configuration;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.*;
import sh.yannick.phoenix.common.PhoenixException;

@DisplayName("Test loading configuration from the classpath")
public class ClassPathConfigurationLoaderTest {
    @Test
    @DisplayName("Test with valid file name")
    public void validFileNameTest() {
        Configuration configuration = new ClassPathConfigurationLoader("demo.properties").load();
        Assertions.assertEquals("hello", configuration.getString("message"));
    }

    @Test
    @DisplayName("Test with invalid file name")
    public void invalidFileNameTest() {
        Assertions.assertThrows(PhoenixException.class, () -> new ClassPathConfigurationLoader("not-existing.properties").load());
    }
}
