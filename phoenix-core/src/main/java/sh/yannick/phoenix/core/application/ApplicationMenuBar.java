package sh.yannick.phoenix.core.application;

import javax.swing.*;

public class ApplicationMenuBar extends JMenuBar {
    private static final ApplicationMenuBar INSTANCE = new ApplicationMenuBar();

    private ApplicationMenuBar() {
    }

    public static ApplicationMenuBar getInstance() {
        return INSTANCE;
    }
}
