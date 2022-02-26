package sh.yannick.phoenix.common.custom;

import org.apache.commons.configuration2.Configuration;
import sh.yannick.phoenix.common.PhoenixException;
import sh.yannick.phoenix.common.configuration.ConfigurationLoader;

public class CustomInvalidConfigurationLoader extends ConfigurationLoader {
    @Override
    protected Configuration load() throws PhoenixException {
        return null;
    }
}
