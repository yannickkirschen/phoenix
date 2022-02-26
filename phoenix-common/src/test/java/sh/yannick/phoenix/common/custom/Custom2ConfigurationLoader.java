package sh.yannick.phoenix.common.custom;

import org.apache.commons.configuration2.Configuration;
import sh.yannick.phoenix.common.*;
import sh.yannick.phoenix.common.configuration.ConfigurationLoader;

@ResourcePrefix("custom2")
public class Custom2ConfigurationLoader extends ConfigurationLoader {
    @Override
    protected Configuration load() throws PhoenixException {
        return null;
    }
}
