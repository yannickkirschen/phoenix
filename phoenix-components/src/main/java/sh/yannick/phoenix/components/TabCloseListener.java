package sh.yannick.phoenix.components;

import javax.swing.*;

public interface TabCloseListener {
    default void beforeClose(JPanel tabComponent) {
    }

    default void afterClose(JPanel tabComponent) {
    }
}
