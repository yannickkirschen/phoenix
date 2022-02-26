package sh.yannick.phoenix.common.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration2.*;
import sh.yannick.phoenix.common.*;

import java.util.*;

@ResourcePrefix("jvm")
@RequiredArgsConstructor
public class JvmPropertyConfigurationLoader extends ConfigurationLoader {
    private final String prefix;

    @Override
    protected Configuration load() throws PhoenixException {
        PropertiesConfiguration configuration = new PropertiesConfiguration();

        Properties properties;
        if (prefix == null || prefix.isEmpty() || "all".equals(prefix)) {
            properties = System.getProperties();
        } else {
            properties = getPropertiesStartingWith(prefix, System.getProperties());
        }

        for (Map.Entry<Object, Object> propertyEntry : properties.entrySet()) {
            configuration.addProperty((String) propertyEntry.getKey(), propertyEntry.getValue());
        }

        return configuration;
    }

    private Properties getPropertiesStartingWith(String prefix, Properties properties) {
        Properties filteredProperties = new Properties();

        for (Map.Entry<Object, Object> propertyEntry : properties.entrySet()) {
            if (((String) propertyEntry.getKey()).startsWith(prefix)) {
                filteredProperties.put(propertyEntry.getKey(), propertyEntry.getValue());
            }
        }

        return filteredProperties;
    }
}
