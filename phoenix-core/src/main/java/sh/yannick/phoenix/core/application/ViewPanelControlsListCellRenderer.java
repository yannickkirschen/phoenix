package sh.yannick.phoenix.core.application;

import sh.yannick.phoenix.common.laf.LookAndFeelWrapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewPanelControlsListCellRenderer extends JLabel implements ListCellRenderer<ViewPanelControl> {
    private final DialogLoader dialogLoader;
    private final Color primaryColor = LookAndFeelWrapper.defaultWrapper().primaryColor();

    public ViewPanelControlsListCellRenderer(DialogLoader dialogLoader) {
        super();
        this.dialogLoader = dialogLoader;

        setOpaque(true);
        setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ViewPanelControl> list, ViewPanelControl value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getText());

        if (isSelected) {
            setBackground(primaryColor);
            dialogLoader.loadDialog(value.getDialogClass());
        } else {
            setBackground(null);
        }

        return this;
    }
}
