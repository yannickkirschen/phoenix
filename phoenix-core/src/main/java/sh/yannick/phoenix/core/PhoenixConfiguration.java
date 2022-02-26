package sh.yannick.phoenix.core;

import org.apache.commons.configuration2.*;
import sh.yannick.phoenix.common.Colors;
import sh.yannick.phoenix.common.configuration.ConfigurationLoader;

import java.awt.*;
import java.util.List;
import java.util.*;

public final class PhoenixConfiguration {
    private final BaseConfiguration configuration = new BaseConfiguration();

    PhoenixConfiguration() {
    }

    public void load(String resource) {
        load(ConfigurationLoader.load(resource));
    }

    public String getString(String key) {
        return configuration.getString(key);
    }

    public int getInt(String key) {
        return configuration.getInt(key);
    }

    public double getDouble(String key) {
        return configuration.getDouble(key);
    }

    public long getLong(String key) {
        return configuration.getLong(key);
    }

    public float getFloat(String key) {
        return configuration.getFloat(key);
    }

    public byte getByte(String key) {
        return configuration.getByte(key);
    }

    public boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }

    public <T> List<T> getList(Class<T> clazz, String key) {
        return configuration.getList(clazz, key);
    }

    public Color getColor(String key) {
        List<String> rgb = configuration.getList(String.class, key);
        return Colors.fromRGBAList(rgb);
    }

    private void load(Configuration c) {
        for (Iterator<String> it = c.getKeys(); it.hasNext(); ) {
            configuration.clearProperty(it.next());
        }

        configuration.append(c);
    }
}
