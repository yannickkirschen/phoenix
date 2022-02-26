package sh.yannick.phoenix.components;

import sh.yannick.phoenix.layouts.FillLayout;

import javax.swing.*;

public class TitledPanel extends JPanel {
    public TitledPanel(String title) {
        setLayout(new FillLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
    }
}
