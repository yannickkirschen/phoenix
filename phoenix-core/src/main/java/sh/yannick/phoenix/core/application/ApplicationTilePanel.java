package sh.yannick.phoenix.core.application;

import sh.yannick.phoenix.common.Reflection;
import sh.yannick.phoenix.components.Icon;
import sh.yannick.phoenix.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class ApplicationTilePanel extends TitledPanel {
    private final ApplicationHandle applicationHandle;
    private final TabHandle tabHandle;

    private final Map<Class<? extends ViewHandle>, ViewPanel> loadedViews = new LinkedHashMap<>();

    public ApplicationTilePanel(String title, ApplicationHandle applicationHandle, Collection<Class<? extends ViewHandle>> views) {
        // TODO: fix scrollbar
        // TODO: left-align tiles
        // http://www.java2s.com/Tutorials/Java/Swing_How_to/JScrollPane/Create_JPanel_implements_Scrollable.htm
        super(title);

        this.applicationHandle = applicationHandle;
        this.tabHandle = applicationHandle.getTabHandle();

        setLayout(new GridLayout(1, 1));
        ScrollablePanel scrollablePanel = new ScrollablePanel(new GridLayout(1, 1));
        scrollablePanel.setScrollableWidth(ScrollablePanel.ScrollableSizeHint.FIT);
        scrollablePanel.setScrollableHeight(ScrollablePanel.ScrollableSizeHint.STRETCH);

        JPanel inner = new JPanel();
        if (views != null) {
            fillTiles(inner, views);
        }
        scrollablePanel.add(inner);

        add(new JScrollPane(scrollablePanel));
    }

    void removeView(ViewPanel panel) {
        for (Map.Entry<Class<? extends ViewHandle>, ViewPanel> entry : loadedViews.entrySet()) {
            if (entry.getValue().equals(panel)) {
                loadedViews.remove(entry.getKey(), entry.getValue());
                return;
            }
        }
    }

    private void fillTiles(JPanel panel, Collection<Class<? extends ViewHandle>> views) {
        for (Class<? extends ViewHandle> viewClass : views) {
            View annotation = viewClass.getAnnotation(View.class);
            final Icon icon = !annotation.icon().isEmpty() ? Icon.createIcon(annotation.icon()) : null;

            TileButton button = new TileButton(annotation.title());
            button.addActionListener(l -> {
                ViewPanel viewPanel;
                if (loadedViews.containsKey(viewClass)) {
                    viewPanel = loadedViews.get(viewClass);
                } else {
                    viewPanel = new ViewPanel(applicationHandle, Reflection.newInstance(viewClass), annotation);
                    loadedViews.put(viewClass, viewPanel);
                    tabHandle.addCloseableTab(annotation.title(), viewPanel, icon, annotation.tip());
                }

                tabHandle.focusTab(viewPanel);
            });

            panel.add(button);
        }

//        for (int i = 0; i < 30; i++) {
//            panel.add(new TileButton(Integer.toString(i)));
//        }
    }
}
