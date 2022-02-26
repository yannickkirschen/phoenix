package sh.yannick.phoenix.showcase.person;

import sh.yannick.phoenix.core.application.Dialog;
import sh.yannick.phoenix.layouts.VerticalLayout;

import javax.swing.*;

@Dialog(title = "Demo dialog 2")
public class DemoDialog2 extends JPanel {
    public DemoDialog2() {
        super();
        setLayout(new VerticalLayout(this));

        add(new JLabel("The second dialog"));
    }
}
