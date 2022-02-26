package sh.yannick.phoenix.core.application;

import javax.swing.*;

public interface DialogLoader {
    void loadDialog(Class<? extends JPanel> clazz);
}
