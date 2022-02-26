package sh.yannick.phoenix.core.application;

import lombok.RequiredArgsConstructor;
import sh.yannick.phoenix.common.PhoenixException;

import javax.swing.*;
import java.lang.reflect.*;
import java.util.*;

@RequiredArgsConstructor
public class DialogHolder {
    private final ViewHandle view;
    private final Map<Class<? extends JPanel>, JPanel> dialogs = new LinkedHashMap<>();

    public JPanel retrieveDialog(Class<? extends JPanel> clazz) {
        if (dialogs.containsKey(clazz)) {
            return dialogs.get(clazz);
        }

        JPanel panel = null;
        try {
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == 0) {
                    panel = clazz.getConstructor().newInstance();
                } else if (constructor.getParameterCount() == 1) {
                    Class<?>[] types = constructor.getParameterTypes();
                    if (ViewHandle.class.isAssignableFrom(types[0])) {
                        panel = clazz.getConstructor(view.getClass()).newInstance(view);
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PhoenixException(e);
        }

        if (panel == null) {
            throw new PhoenixException("Unable to instantiate %s as there is no default constructor of constructor matching (? extends sh.yannick.phoenix.component.view.ViewHandle).", clazz);
        }

        dialogs.put(clazz, panel);
        return panel;
    }
}
