package sh.yannick.phoenix.common.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import sh.yannick.phoenix.common.*;

@ResourcePrefix("classpath")
@RequiredArgsConstructor
public class ClassPathConfigurationLoader extends ConfigurationLoader {
    private final String file;

    @Override
    public Configuration load() {
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new Parameters()
                .properties()
                .setFileName(file)
                .setEncoding("UTF-8")
                .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
                .setThrowExceptionOnMissing(true));
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException e) {
            throw new PhoenixException(e);
        }
    }
}
