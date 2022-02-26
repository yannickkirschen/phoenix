package sh.yannick.phoenix.core.application;

import sh.yannick.phoenix.common.Savable;
import sh.yannick.phoenix.components.Icon;
import sh.yannick.phoenix.components.*;
import sh.yannick.phoenix.core.components.StartViewToolBar;
import sh.yannick.phoenix.icons.DefaultIcon;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ApplicationPanel extends JPanel implements ApplicationHandle, Closeable {
    private final JLabel status = new JLabel("");

    private final JFrame frame;
    private final TabPanel tabs = new TabPanel();
    private final ApplicationTilePanel tiles;

    private final java.util.List<Savable> savableComponents = new LinkedList<>();
    private final java.util.List<Closeable> closeableComponents = new LinkedList<>();

    private ToolBar toolBar;

    public ApplicationPanel(Collection<Class<? extends ViewHandle>> views, JFrame frame) {
        super();
        this.frame = frame;
        setLayout(new BorderLayout());

        toolBar = new StartViewToolBar();
        add(toolBar, BorderLayout.NORTH);

        tiles = new ApplicationTilePanel("Applications", this, views);
        tabs.addTab("Start", tiles, Icon.createIcon(DefaultIcon.HOME), "Overview of your applications");
        add(tabs, BorderLayout.CENTER);

        add(status, BorderLayout.SOUTH);

        tabs.addTabCloseListener(new TabCloseListener() {
            @Override
            public void beforeClose(JPanel tabComponent) {
                int i = tabs.indexOfTabComponent(tabComponent);
                tiles.removeView((ViewPanel) tabs.getComponentAt(i));
            }
        });
    }

    @Override
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    @Override
    public void setStatus(String status) {
        this.status.setText(status);
    }

    @Override
    public void addCloseable(Closeable closeable) {
        closeableComponents.add(closeable);
    }

    @Override
    public void setDataChanged(Savable savable, boolean changed) {
        if (changed && !savableComponents.contains(savable)) {
            savableComponents.add(savable);
            tabs.setCurrentTabTitle("%s *".formatted(tabs.getCurrentTabTitle()));
        } else if (!changed) {
            savableComponents.remove(savable);
            tabs.setCurrentTabTitle(tabs.getCurrentTabTitle().replace(" *", ""));
        }
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public TabHandle getTabHandle() {
        return tabs;
    }

    @Override
    public void close() throws IOException {
        for (Closeable closeable : closeableComponents) {
            closeable.close();
        }
    }

    public boolean hasUnsavedChanges() {
        return !savableComponents.isEmpty();
    }

    public void handleSave() {
        savableComponents.forEach(Savable::saveChanges);
    }

    public void handleDiscard() {
        savableComponents.forEach(Savable::discardChanges);
    }
}
