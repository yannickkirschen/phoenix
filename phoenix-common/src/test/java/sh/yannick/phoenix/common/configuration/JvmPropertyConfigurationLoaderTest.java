package sh.yannick.phoenix.common.configuration;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.*;

@DisplayName("Test loading configuration from JVM parameters")
public class JvmPropertyConfigurationLoaderTest {
    @Test
    @DisplayName("Test with valid key and no resource name")
    public void validKeyNoResourceNameTest() {
        System.setProperty("phoenix.test.message", "hello");
        Configuration configuration = new JvmPropertyConfigurationLoader(null).load();
        Assertions.assertEquals("hello", configuration.getString("phoenix.test.message"));
    }

    @Test
    @DisplayName("Test with valid key and resource name")
    public void validKeyResourceNameTest() {
        System.setProperty("phoenix.test.message", "hello");
        Configuration configuration = new JvmPropertyConfigurationLoader("phoenix").load();
        Assertions.assertEquals("hello", configuration.getString("phoenix.test.message"));
    }
}
