package sh.yannick.phoenix.common.configuration;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.*;
import sh.yannick.phoenix.common.PhoenixException;
import sh.yannick.phoenix.common.custom.*;

@DisplayName("Test loading configuration from various sources with prefixes")
public class ConfigurationLoaderTest {
    @Test
    @DisplayName("Test with classpath prefix")
    public void classpathPrefixTest() {
        Configuration configuration = ConfigurationLoader.load("classpath:demo.properties");
        Assertions.assertEquals("hello", configuration.getString("message"));
    }

    @Test
    @DisplayName("Test with file system prefix")
    public void fileSystemPrefixTest() {
        Configuration configuration = ConfigurationLoader.load("file:phoenix-common/src/test/resources/demo.properties");
        Assertions.assertEquals("hello", configuration.getString("message"));
    }

    @Test
    @DisplayName("Test without prefix")
    public void withoutPrefixTest() {
        Configuration configuration = ConfigurationLoader.load("demo.properties");
        Assertions.assertEquals("hello", configuration.getString("message"));
    }

    @Test
    @DisplayName("Test with custom prefix (loader class not annotated)")
    public void customPrefixLoaderNotAnnotatedTest() {
        Assertions.assertThrows(PhoenixException.class, () -> ConfigurationLoader.add(CustomInvalidConfigurationLoader.class));
    }

    @Test
    @DisplayName("Test with custom prefix (loader class has invalid constructor)")
    public void customPrefixLoaderInvalidConstructor() {
        ConfigurationLoader.add(CustomInvalidConstructorConfigurationLoader.class);
        Assertions.assertThrows(PhoenixException.class, () -> ConfigurationLoader.load("custom-invalid:demo.properties"));
    }

    @Test
    @DisplayName("Test with custom prefix")
    public void customPrefixTest() {
        ConfigurationLoader.add(CustomConfigurationLoader.class);
        Configuration configuration = ConfigurationLoader.load("custom:demo.properties");
        Assertions.assertNull(configuration);
    }

    @Test
    @DisplayName("Test with invalid prefix")
    public void invalidPrefixTest() {
        Assertions.assertThrows(PhoenixException.class, () -> ConfigurationLoader.load("invalid:demo.properties"));
    }

    @Test
    @DisplayName("Test with invalid resource name")
    public void invalidResourceNameTest() {
        Assertions.assertThrows(PhoenixException.class, () -> ConfigurationLoader.load("custom:invalid:demo.properties"));
    }

    @Test
    @DisplayName("Test with already registered custom prefix")
    public void customAlreadyRegisteredPrefix() {
        ConfigurationLoader.add(Custom2ConfigurationLoader.class);
        Assertions.assertThrows(PhoenixException.class, () -> ConfigurationLoader.add(Custom2ConfigurationLoader.class));
    }
}
