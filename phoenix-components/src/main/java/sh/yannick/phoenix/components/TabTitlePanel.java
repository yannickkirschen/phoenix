package sh.yannick.phoenix.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

public class TabTitlePanel extends JPanel {
    private static final int CLOSE_BUTTON_SIZE = 17;

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton button) {
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton button) {
                button.setBorderPainted(false);
            }
        }
    };

    private final TabHandle tabHandle;

    public TabTitlePanel(TabHandle tabHandle, JTabbedPane pane) {
        this(tabHandle, pane, true);
    }

    public TabTitlePanel(TabHandle tabHandle, JTabbedPane pane, boolean closeable) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.tabHandle = tabHandle;

        setOpaque(false);

        JLabel label = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(TabTitlePanel.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };

        add(label);

        if (closeable) {
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            add(new TabButton());
        }

        setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
    }

    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            setPreferredSize(new Dimension(CLOSE_BUTTON_SIZE, CLOSE_BUTTON_SIZE));
            setToolTipText("Close this tab");

            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tabHandle.closeTab(TabTitlePanel.this);
        }

        @Override
        public void updateUI() {
            // We don't want to update UI for this button
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();

            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }

            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);

            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }
}
