package sh.yannick.phoenix.components;

import sh.yannick.phoenix.common.laf.LookAndFeelWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setMargin(new Insets(5, 5, 5, 5));
    }

    public Button(Icon icon) {
        super(icon);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);

        Color color = LookAndFeelWrapper.defaultWrapper().primaryHighlightColor();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setContentAreaFilled(true);
                setBackground(color);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setContentAreaFilled(false);
            }
        });
    }
}
