package sh.yannick.phoenix.core.application;

import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class ViewLocator {
    private final List<String> packages = new LinkedList<>();

    public ViewLocator() {
    }

    public ViewLocator(String pkg) {
        packages.add(pkg);
    }

    public ViewLocator(Collection<String> packages) {
        this.packages.addAll(packages);
    }

    public ViewLocator pkg(String pkg) {
        packages.add(pkg);
        return this;
    }

    public ViewLocator pkg(Collection<String> packages) {
        this.packages.addAll(packages);
        return this;
    }

    @SuppressWarnings("unchecked")
    public List<Class<? extends ViewHandle>> locate() {
        List<Class<? extends ViewHandle>> views = new LinkedList<>();

        for (String pkg : packages) {
            Set<Class<?>> classes = new Reflections(pkg)
                .getTypesAnnotatedWith(View.class)
                .stream()
                .filter(ViewHandle.class::isAssignableFrom)
                .collect(Collectors.toSet());

            for (Class<?> clazz : classes) {
                views.add((Class<? extends ViewHandle>) clazz);
            }

        }

        return views;
    }
}
