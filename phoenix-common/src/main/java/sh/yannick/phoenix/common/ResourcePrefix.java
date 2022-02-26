package sh.yannick.phoenix.common;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A specific annotated resource-loading class should be used when a resource identifier has the {@link #value()} as
 * prefix.
 * <p>
 * For example: a resource identifier for a file on the classpath could look like this:
 * <code>classpath:/my/file.xml</code>. A class annotated with <code>@ResourcePrefix("classpath")</code> would
 * indicate, that this class is able to load resources from the classpath. This is especially useful in combination with
 * {@link sh.yannick.phoenix.common.configuration.ConfigurationLoader} but can be used in any context.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
@Target(TYPE_USE)
@Retention(RUNTIME)
public @interface ResourcePrefix {
    /**
     * @return the prefix of the resource
     */
    String value();
}
