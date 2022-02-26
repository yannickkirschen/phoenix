package sh.yannick.phoenix.common;

import java.lang.reflect.InvocationTargetException;

/**
 * Utility class to work with reflection.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public final class Reflection {
    private Reflection() {
    }

    /**
     * Creates a new instance of the given class and passes the arguments to the constructor.
     * <p>
     * If no arguments are provided, the default constructor is used. This method uses {@link
     * Class#getConstructor(Class[])} and {@link java.lang.reflect.Constructor#newInstance(Object...)} to create the
     * instance.
     *
     * @param clazz     the class to instantiate
     * @param arguments optional arguments to pass to the constructor
     * @param <T>       type of the class
     * @return a new instance of the given class
     * @throws PhoenixException wrapped with an {@link InstantiationException}, {@link IllegalAccessException}, {@link
     *                          InvocationTargetException} or {@link NoSuchMethodException} that are thrown when
     *                          instantiation fails.
     */
    public static <T> T newInstance(Class<? extends T> clazz, Object... arguments) {
        Class<?>[] argumentClasses = new Class<?>[arguments.length];

        for (int i = 0; i < arguments.length; i++) {
            argumentClasses[i] = arguments[i].getClass();
        }

        try {
            return clazz.getConstructor(argumentClasses).newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PhoenixException(e);
        }
    }

    /**
     * Creates a new instance of a class based on its fully qualified class name and passes the arguments to the
     * constructor.
     * <p>
     * If no arguments are provided, the default constructor is used.
     * <p>
     * See {@link #newInstance(Class, Object...)} on how instantiation works.
     *
     * @param className fully qualified class name to instantiate
     * @param arguments optional arguments to pass to the constructor
     * @param <T>       type of the class
     * @return a new instance of the given class
     * @throws PhoenixException wrapped with an {@link InstantiationException}, {@link IllegalAccessException}, {@link
     *                          InvocationTargetException} or {@link NoSuchMethodException} that are thrown when
     *                          instantiation fails.
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className, Object... arguments) {
        Class<T> clazz;
        try {
            clazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new PhoenixException(e);
        }

        return newInstance(clazz, arguments);
    }
}
