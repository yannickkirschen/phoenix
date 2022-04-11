package sh.yannick.phoenix.common;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This will scan the classpath recursively in given packages and search for classes being annotated with a specific
 * annotation and implementing a specific interface.
 * <p>
 * Actually, this is a wrapper for {@link Reflections#getTypesAnnotatedWith(Annotation)}.
 *
 * @param <I> the interface the target classes must implement
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class AnnotatedClassLocator<I> {
    private final List<String> packages = new LinkedList<>();

    /**
     * Creates a new {@link AnnotatedClassLocator}.
     * <p>
     * There a re no packages set for scanning. You have to add one or more packages by calling {@link #pkg(String)} or
     * {@link #pkg(Collection)}.
     */
    public AnnotatedClassLocator() {
    }

    /**
     * Adds a package to the list of packages to be scanned.
     *
     * @param pkg the package to scan
     * @return the class itself
     */
    public AnnotatedClassLocator<I> pkg(String pkg) {
        packages.add(pkg);
        return this;
    }

    /**
     * Adds a {@link Collection} of packages to the list of packages to be scanned.
     *
     * @param packages the packages to scan
     * @return the class itself
     */
    public AnnotatedClassLocator<I> pkg(Collection<String> packages) {
        this.packages.addAll(packages);
        return this;
    }

    /**
     * Locates all implementations of the given interface type that are annotated with the given annotation.
     *
     * @param interfaceType   interface that target classes must implement
     * @param annotationClass annotation that target classes must be annotated with
     * @return all classes that match the criteria and an empty list if no classes are found
     */
    @SuppressWarnings("unchecked")
    public List<Class<? extends I>> locate(Class<? extends I> interfaceType, Class<? extends Annotation> annotationClass) {
        List<Class<? extends I>> views = new LinkedList<>();

        for (String pkg : packages) {
            Set<Class<?>> classes = new Reflections(pkg)
                .getTypesAnnotatedWith(annotationClass)
                .stream()
                .filter(interfaceType::isAssignableFrom)
                .collect(Collectors.toSet());

            for (Class<?> clazz : classes) {
                views.add((Class<? extends I>) clazz);
            }
        }

        return views;
    }
}
