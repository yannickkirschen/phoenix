package sh.yannick.phoenix.common.configuration;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.*;

@DisplayName("Test loading configuration from environment variables")
public class EnvConfigurationLoaderTest {
    @Test
    @DisplayName("Test with valid key and no resource name")
    public void validKeyNoResourceNameTest() {
        Configuration configuration = new EnvConfigurationLoader(null).load();
        Assertions.assertEquals("hello", configuration.getString("phoenix.test.message"));
    }

    @Test
    @DisplayName("Test with valid key and resource name")
    public void validKeyResourceNameTest() {
        Configuration configuration = new EnvConfigurationLoader("PHOENIX").load();
        Assertions.assertEquals("hello", configuration.getString("phoenix.test.message"));
    }
}
