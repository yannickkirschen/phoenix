package sh.yannick.phoenix.core;

import sh.yannick.phoenix.components.Icon;
import sh.yannick.phoenix.core.application.ApplicationMenuBar;
import sh.yannick.phoenix.core.components.*;

import javax.swing.*;
import java.awt.*;

public class RootFrame extends JFrame {
    public RootFrame(PhoenixConfiguration configuration) {
        String title = configuration.getString("phoenix.window.title");
        int width = configuration.getInt("phoenix.window.width");
        int height = configuration.getInt("phoenix.window.height");
        boolean resizable = configuration.getBoolean("phoenix.window.resizable");

        setTitle(title);
        setSize(width, height);
        setResizable(resizable);

        String iconName = configuration.getString("phoenix.application.icon");
        Image icon = Icon.createIcon(iconName).getImage();
        Taskbar taskbar = Taskbar.getTaskbar();
        if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
            taskbar.setIconImage(icon);
        }

        setIconImage(icon);

        boolean hasMenu = configuration.getBoolean("phoenix.application.menu");
        if (hasMenu) {
            setJMenuBar(ApplicationMenuBar.getInstance());
        }

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        Desktop.getDesktop().setPreferencesHandler(e -> new PreferencesDialog(this).setVisible(true));
        Desktop.getDesktop().setAboutHandler(e -> new AboutDialog(this).setVisible(true));
    }
}
