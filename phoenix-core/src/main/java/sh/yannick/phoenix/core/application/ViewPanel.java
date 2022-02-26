package sh.yannick.phoenix.core.application;

import sh.yannick.phoenix.components.ScrollableList;

import javax.swing.*;

public class ViewPanel extends JSplitPane implements DialogLoader {
    private final DialogHolder dialogHolder;

    public ViewPanel(ApplicationHandle applicationHandle, ViewHandle view, View annotation) {
        view.setApplicationHandle(applicationHandle);
        view.initialize();

        dialogHolder = new DialogHolder(view);

        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(getSidebar(annotation));

        if (annotation.dialogs() != null && annotation.dialogs().length > 0) {
            loadDialog(annotation.dialogs()[0]);
        }
    }

    @Override
    public void loadDialog(Class<? extends JPanel> clazz) {
        setRight(dialogHolder.retrieveDialog(clazz));
    }

    private JComponent getSidebar(View annotation) {
        DefaultListModel<ViewPanelControl> model = new DefaultListModel<>();
        loadDialogButtons(model, annotation);

        JList<ViewPanelControl> list = new JList<>(model);
        list.setCellRenderer(new ViewPanelControlsListCellRenderer(this));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setBackground(null);
        list.setSelectedIndex(0);

        ScrollableList<ViewPanelControl> scrollableList = new ScrollableList<>(list);
        scrollableList.setBorder(null);

        return scrollableList;
    }

    private void loadDialogButtons(DefaultListModel<ViewPanelControl> model, View annotation) {
        for (Class<? extends JPanel> clazz : annotation.dialogs()) {
            Dialog dialog = clazz.getAnnotation(Dialog.class);
            model.addElement(new ViewPanelControl(dialog.title(), clazz));
        }
    }

    private void setRight(JPanel panel) {
        int dividerLocation = getDividerLocation();
        setRightComponent(panel);
        setDividerLocation(dividerLocation);
    }
}
