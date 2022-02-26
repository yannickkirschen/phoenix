package sh.yannick.phoenix.core;

import lombok.Getter;

public final class PhoenixContext {
    private static final String PROPERTIES_DEFAULT_FILE = "classpath:phoenix-defaults.properties";
    private static final String PROPERTIES_FILE = "classpath:phoenix.properties";

    private static final PhoenixContext INSTANCE = new PhoenixContext();

    @Getter
    private final PhoenixConfiguration configuration;

    private PhoenixContext() {
        configuration = new PhoenixConfiguration();
        configuration.load(PROPERTIES_DEFAULT_FILE);
        configuration.load(PROPERTIES_FILE);
    }

    public static PhoenixContext getInstance() {
        return INSTANCE;
    }
}
