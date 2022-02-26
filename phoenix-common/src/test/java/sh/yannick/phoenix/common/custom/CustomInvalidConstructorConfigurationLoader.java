package sh.yannick.phoenix.common.custom;

import org.apache.commons.configuration2.Configuration;
import sh.yannick.phoenix.common.*;
import sh.yannick.phoenix.common.configuration.ConfigurationLoader;

@ResourcePrefix("custom-invalid")
public class CustomInvalidConstructorConfigurationLoader extends ConfigurationLoader {
    public CustomInvalidConstructorConfigurationLoader(Integer i) {
        // This constructor is invalid. The loader cannot be instantiated.
    }

    @Override
    protected Configuration load() throws PhoenixException {
        return null;
    }
}
