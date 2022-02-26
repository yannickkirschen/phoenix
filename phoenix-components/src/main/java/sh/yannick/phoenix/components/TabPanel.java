package sh.yannick.phoenix.components;

import sh.yannick.phoenix.layouts.FillLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class TabPanel extends JPanel implements TabHandle {
    private final JTabbedPane tabs = new JTabbedPane();

    private final List<TabCloseListener> closeListeners = new LinkedList<>();

    public TabPanel() {
        super();

        setLayout(new FillLayout());
        add(tabs);
    }

    public void addTabCloseListener(TabCloseListener listener) {
        closeListeners.add(listener);
    }

    @Override
    public void addTab(String title, JComponent component, Icon icon, String tip) {
        tabs.addTab(title, icon, component, tip);
        tabs.setTabComponentAt(getTabCount() - 1, new TabTitlePanel(this, tabs, false));
    }

    @Override
    public void addCloseableTab(String title, JComponent component, Icon icon, String tip) {
        tabs.addTab(title, icon, component, tip);
        tabs.setTabComponentAt(getTabCount() - 1, new TabTitlePanel(this, tabs));
    }

    @Override
    public void focusTab(JComponent component) {
        tabs.setSelectedComponent(component);
    }

    @Override
    public int getTabCount() {
        return tabs.getTabCount();
    }

    @Override
    public Component getComponentAt(int index) {
        return tabs.getComponentAt(index);
    }

    @Override
    public int indexOfTabComponent(JComponent component) {
        return tabs.indexOfTabComponent(component);
    }

    @Override
    public String getCurrentTabTitle() {
        return tabs.getTitleAt(tabs.getSelectedIndex());
    }

    @Override
    public void setCurrentTabTitle(String title) {
        tabs.setTitleAt(tabs.getSelectedIndex(), title);
    }

    @Override
    public void remove(int index) {
        tabs.remove(index);
    }

    @Override
    public void closeTab(JPanel tabComponent) {
        closeListeners.forEach(l -> l.beforeClose(tabComponent));

        int i = tabs.indexOfTabComponent(tabComponent);
        if (i != -1) {
            tabs.remove(i);
        }

        closeListeners.forEach(l -> l.afterClose(tabComponent));
    }
}
