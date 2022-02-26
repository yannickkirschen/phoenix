package sh.yannick.phoenix.common.configuration;

import org.apache.commons.configuration2.Configuration;
import org.reflections.Reflections;
import sh.yannick.phoenix.common.*;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Provides a centralized way to load configuration from various sources.
 * <p>
 * Implementations of the {@link ConfigurationLoader} allow loading configuration from various sources, such as the
 * classpath or the file system.
 * <p>
 * By using  {@link ResourcePrefix}, this class will also scan for all loaders in a default package and load them. You
 * can also add custom loaders by calling {@link #add(Class)}. Configuration resources including a prefix may be loaded
 * by calling {@link #load(String)}.
 * <p>
 * We use the configuration format provided by the Apache Commons Configuration project. See the <a
 * href="https://commons.apache.org/proper/commons-configuration/">project homepage</a> for more details.
 * <p>
 * This class does not make assumptions on where the sources are stored (e.g. local computer, network, database, etc.)
 * and how the configuration looks like (e.g. properties file, XML, YAML, etc.). Moreover, this class does not make any
 * assumptions on how a resource identifier looks like neither (e.g. {@link String} or {@link java.net.URL}), as it must
 * be passed to an implementation via constructor argument or setter.
 * <p>
 * Usually, implementations must be annotated with {@link ResourcePrefix} to allow other classes the usage of prefixes,
 * such as <code>classpath:</code> or <code>file:</code> in their resources.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public abstract class ConfigurationLoader {
    private static final String DEFAULT_LOADER_PACKAGE = "sh.yannick.phoenix.common.configuration";

    // We want to load all built-in loaders by default
    private static final Map<String, Class<? extends ConfigurationLoader>> LOADERS = loadDefaultLoaders();

    /**
     * Loads a configuration.
     * <p>
     * If the resource name starts with an already registered prefix, the corresponding loader will be chosen to load
     * the configuration. If no prefix is provided, {@link ClassPathConfigurationLoader} will be used as a default. You
     * can add custom loaders by calling {@link #add(Class)}:
     *
     * @param resource name of the resource to load
     * @return the configuration represented by the specified resource
     * @throws PhoenixException if the resource contains more than one colon, if the loader has no constructor matching
     *                          () or (String) or if the configuration cannot be loaded for some reason (usually wrapped
     *                          with the actual exception)
     */
    public static Configuration load(String resource) {
        for (Map.Entry<String, Class<? extends ConfigurationLoader>> loaderEntry : LOADERS.entrySet()) {
            if (resource.startsWith("%s:".formatted(loaderEntry.getKey()))) {
                String[] resourceSplit = resource.split(":");
                if (resourceSplit.length != 2) {
                    throw new PhoenixException("Malformed resource: %s. Only one colon is allowed.", resource);
                }

                String prefix = resourceSplit[0];
                String actualResource = resourceSplit[1];

                Class<? extends ConfigurationLoader> loaderClass = LOADERS.get(prefix);
                for (Constructor<?> constructor : loaderClass.getConstructors()) {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();

                    if (parameterTypes.length == 0) {
                        // We don't pass the resource to the constructor
                        return Reflection.newInstance(LOADERS.get(prefix)).load();
                    }

                    if (parameterTypes.length == 1 && parameterTypes[0] == String.class) {
                        // We pass the resource to the constructor
                        return Reflection.newInstance(LOADERS.get(prefix), actualResource).load();
                    }
                }

                throw new PhoenixException("Unable to instantiate configuration loader %s as there is no constructor matching () or (String).", loaderClass);
            }
        }

        return new ClassPathConfigurationLoader(resource).load();
    }

    /**
     * Adds a configuration loader.
     * <p>
     * The loader must be annotated with {@link ResourcePrefix} and the prefix must not be already registered. Once
     * added, you can load configuration from that loader by calling {@link #load(String)} and using a resource with
     * your custom prefix.
     *
     * @param loaderClass custom loader annotated with {@link ResourcePrefix} providing a non-existing prefix.
     * @throws PhoenixException if the loader class is not annotated with {@link ResourcePrefix} or the specified prefix
     *                          is already registered.
     */
    public static void add(Class<? extends ConfigurationLoader> loaderClass) throws PhoenixException {
        ResourcePrefix annotation = loaderClass.getAnnotation(ResourcePrefix.class);
        if (annotation == null) {
            throw new PhoenixException("Loader class %s must be annotated with ResourcePrefix.", loaderClass);
        }

        if (LOADERS.containsKey(annotation.value())) {
            throw new PhoenixException("Prefix %s already registered.", annotation.value());
        }

        LOADERS.put(annotation.value(), loaderClass);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Class<? extends ConfigurationLoader>> loadDefaultLoaders() {
        Map<String, Class<? extends ConfigurationLoader>> loaders = new LinkedHashMap<>();
        Set<Class<?>> loaderClasses = new Reflections(DEFAULT_LOADER_PACKAGE).getTypesAnnotatedWith(ResourcePrefix.class);

        for (Class<?> clazz : loaderClasses) {
            if (!ConfigurationLoader.class.isAssignableFrom(clazz)) {
                throw new PhoenixException("Class %s is annotated with ResourcePrefix but not extending ConfigurationLoader.");
            }

            String prefix = clazz.getAnnotation(ResourcePrefix.class).value();
            Class<? extends ConfigurationLoader> loaderClazz = (Class<? extends ConfigurationLoader>) clazz;
            loaders.put(prefix, loaderClazz);
        }

        return loaders;
    }

    protected abstract Configuration load() throws PhoenixException;
}
