package sh.yannick.phoenix.common.laf;

import org.reflections.Reflections;
import sh.yannick.phoenix.common.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Provides a wrapper for any Swing look and feel.
 * <p>
 * Phoenix needs some properties of the current LAF (such as the primary color) that cannot be accessed in a
 * standardized way. The {@link LookAndFeelWrapper} provides such a standardized way as it dynamically loads the
 * appropriate wrapper for the current LAF.
 * <p>
 * This class loads all default wrappers in the package <code>sh.yannick.phoenix.common.laf</code>, that cover Swing's
 * built-in LAFs. When you want to use your own LAF or a LAF that is not wrapped by default, you can add it by calling
 * {@link #addWrapper(Class)} after configuring the LAF but before instantiating the first Phoenix component.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public abstract class LookAndFeelWrapper {
    private static final String DEFAULT_WRAPPER_PACKAGE = "sh.yannick.phoenix.common.laf";

    // We want to load all built-in wrappers by default
    private static final Set<Class<? extends LookAndFeelWrapper>> WRAPPERS = new Reflections(DEFAULT_WRAPPER_PACKAGE).getSubTypesOf(LookAndFeelWrapper.class);

    /**
     * Finds the default wrapper for the current LAF.
     * <p>
     * To determine the current LAF, {@link UIManager#getLookAndFeel()} is used. If no wrapper is found, a {@link
     * PhoenixException} is thrown.
     *
     * @return a wrapper for the current LAF
     * @throws PhoenixException if no wrapper is found for the current LAF
     */
    public static LookAndFeelWrapper defaultWrapper() throws PhoenixException {
        LookAndFeelWrapper wrapper = findWrapperForCurrentLAF();
        if (wrapper == null) {
            throw new PhoenixException("No wrapper found for current Look And Feel (%s)", UIManager.getLookAndFeel().getClass());
        }
        return wrapper;
    }

    /**
     * Adds a LAF wrapper to the set of available wrappers.
     * <p>
     * Once you added a wrapper, it is accessible by {@link #defaultWrapper()}.
     *
     * @param wrapper the wrapper to add
     */
    public static void addWrapper(Class<? extends LookAndFeelWrapper> wrapper) {
        WRAPPERS.add(wrapper);
    }

    private static LookAndFeelWrapper findWrapperForCurrentLAF() {
        Class<? extends LookAndFeel> currentLaf = UIManager.getLookAndFeel().getClass();

        for (Class<? extends LookAndFeelWrapper> clazz : WRAPPERS) {
            LookAndFeelWrapper wrapper = Reflection.newInstance(clazz);

            if (currentLaf.equals(wrapper.wrappedLookAndFeel())) {
                return wrapper;
            }
        }

        return null;
    }

    /**
     * @return the actual LAF this wrapper wraps
     */
    public abstract Class<? extends LookAndFeel> wrappedLookAndFeel();

    /**
     * @return the primary color of the wrapped LAF
     */
    public abstract Color primaryColor();

    /**
     * @return the primary highlight color of the wrapped LAF
     */
    public abstract Color primaryHighlightColor();
}
