package sh.yannick.phoenix.showcase.person;

import sh.yannick.phoenix.core.application.Dialog;
import sh.yannick.phoenix.layouts.VerticalLayout;

import javax.swing.*;

@Dialog(title = "Demo dialog 4")
public class DemoDialog4 extends JPanel {
    public DemoDialog4() {
        super();
        setLayout(new VerticalLayout(this));

        add(new JLabel("The fourth dialog"));
    }
}
