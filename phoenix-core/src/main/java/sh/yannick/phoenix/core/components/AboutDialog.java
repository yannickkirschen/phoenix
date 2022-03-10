package sh.yannick.phoenix.core.components;

import sh.yannick.phoenix.components.ModalDialog;
import sh.yannick.phoenix.layouts.FillLayout;

import javax.swing.*;

public class AboutDialog extends ModalDialog {
    public AboutDialog(JFrame frame) {
        super(frame);

        setTitle("About");
        setSize(200, 200);
        setLayout(new FillLayout());
        setLocationRelativeTo(null);

        add(new JLabel("About"));
    }
}
