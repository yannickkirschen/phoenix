package sh.yannick.phoenix.core.components;

import sh.yannick.phoenix.components.ModalDialog;
import sh.yannick.phoenix.layouts.FillLayout;

import javax.swing.*;

public class PreferencesDialog extends ModalDialog {
    public PreferencesDialog(JFrame frame) {
        super(frame);

        setTitle("Preferences");
        setSize(200, 200);
        setLayout(new FillLayout());
        setLocationRelativeTo(null);

        add(new JLabel("Settings"));
    }
}
