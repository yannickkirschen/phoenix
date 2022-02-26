package sh.yannick.phoenix.showcase.person;

import sh.yannick.phoenix.core.application.Dialog;
import sh.yannick.phoenix.layouts.VerticalLayout;

import javax.swing.*;

@Dialog(title = "Demo dialog 3")
public class DemoDialog3 extends JPanel {
    public DemoDialog3() {
        super();
        setLayout(new VerticalLayout(this));

        add(new JLabel("The third dialog"));
    }
}
