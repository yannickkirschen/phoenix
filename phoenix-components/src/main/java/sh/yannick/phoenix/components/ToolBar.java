package sh.yannick.phoenix.components;

import sh.yannick.phoenix.layouts.HorizontalLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ToolBar extends JPanel {
    private final JToolBar bar = new JToolBar();

    public ToolBar() {
        setLayout(new HorizontalLayout(this));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        bar.setFloatable(false);

        JPanel container = new JPanel();
        container.setLayout(new HorizontalLayout(container));
        container.add(bar);
        container.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(container);
    }

    public void addTool(JComponent component) {
        bar.add(component);
    }
}
