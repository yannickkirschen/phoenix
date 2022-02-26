package sh.yannick.phoenix.common.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration2.*;
import sh.yannick.phoenix.common.*;

import java.util.*;

@ResourcePrefix("env")
@RequiredArgsConstructor
public class EnvConfigurationLoader extends ConfigurationLoader {
    private final String prefix;

    @Override
    protected Configuration load() throws PhoenixException {
        PropertiesConfiguration configuration = new PropertiesConfiguration();

        Map<String, String> variables;
        if (prefix == null || prefix.isEmpty() || "all".equals(prefix)) {
            variables = System.getenv();
        } else {
            variables = filterVariablesStartingWith(prefix, System.getenv());
        }

        variables = lowercaseAndInsertDots(variables);

        for (Map.Entry<String, String> variableEntry : variables.entrySet()) {
            configuration.addProperty(variableEntry.getKey(), variableEntry.getValue());
        }

        return configuration;
    }

    private Map<String, String> filterVariablesStartingWith(String prefix, Map<String, String> variables) {
        Map<String, String> filtered = new LinkedHashMap<>();

        for (Map.Entry<String, String> variableEntry : variables.entrySet()) {
            if (variableEntry.getKey().startsWith(prefix)) {
                filtered.put(variableEntry.getKey(), variableEntry.getValue());
            }
        }

        return filtered;
    }

    private Map<String, String> lowercaseAndInsertDots(Map<String, String> variables) {
        Map<String, String> properties = new LinkedHashMap<>();

        for (Map.Entry<String, String> variableEntry : variables.entrySet()) {
            properties.put(variableEntry.getKey().replace("_", ".").toLowerCase(Locale.ROOT), variableEntry.getValue());
        }

        return properties;
    }
}
