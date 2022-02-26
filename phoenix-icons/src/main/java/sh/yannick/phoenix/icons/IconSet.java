package sh.yannick.phoenix.icons;

/**
 * Implementations provide the path to a {@link DefaultIcon}. The icon must be on the classpath and the paths must be
 * absolute.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public interface IconSet {
    /**
     * Retrieves the absolute path of an icon on the classpath.
     *
     * @param defaultIcon icon to get the path of.
     * @return The absolute path.
     */
    String getPath(DefaultIcon defaultIcon);
}
