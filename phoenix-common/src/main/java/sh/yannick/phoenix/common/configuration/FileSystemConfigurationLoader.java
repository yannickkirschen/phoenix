package sh.yannick.phoenix.common.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.*;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import sh.yannick.phoenix.common.*;

import java.io.File;

@ResourcePrefix("file")
@RequiredArgsConstructor
public class FileSystemConfigurationLoader extends ConfigurationLoader {
    private final String file;

    @Override
    public Configuration load() {
        if (!new File(file).exists()) {
            throw new PhoenixException("Configuration file %s does not exist on the local file system.", file);
        }

        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new Configurations().propertiesBuilder()
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
