package sh.yannick.phoenix.components;

import javax.swing.*;
import java.awt.*;

public interface TabHandle {
    void addTab(String title, JComponent component, Icon icon, String tip);

    void addCloseableTab(String title, JComponent component, Icon icon, String tip);

    void focusTab(JComponent component);

    int getTabCount();

    Component getComponentAt(int index);

    int indexOfTabComponent(JComponent component);

    String getCurrentTabTitle();

    void setCurrentTabTitle(String title);

    void remove(int index);

    void closeTab(JPanel tabComponent);
}
