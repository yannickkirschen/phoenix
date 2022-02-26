package sh.yannick.phoenix.core;

import sh.yannick.phoenix.common.PhoenixException;
import sh.yannick.phoenix.components.Icon;
import sh.yannick.phoenix.core.application.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Collection;

public record UILoader(Collection<Class<? extends ViewHandle>> views, PhoenixConfiguration configuration) {
    public JFrame load() {
        String iconName = configuration.getString("phoenix.application.icon");

        String title = configuration.getString("phoenix.window.title");
        int width = configuration.getInt("phoenix.window.width");
        int height = configuration.getInt("phoenix.window.height");
        boolean resizable = configuration.getBoolean("phoenix.window.resizable");

        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setResizable(resizable);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Image icon = Icon.createIcon(iconName).getImage();
        Taskbar taskbar = Taskbar.getTaskbar();
        if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
            taskbar.setIconImage(icon);
        }

        frame.setIconImage(icon);

        boolean hasMenu = configuration.getBoolean("phoenix.application.menu");
        if (hasMenu) {
            frame.setJMenuBar(ApplicationMenuBar.getInstance());
        }

        ApplicationPanel applicationPanel = new ApplicationPanel(views, frame);
        frame.add(applicationPanel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (applicationPanel.hasUnsavedChanges()) {
                        int answer = JOptionPane.showConfirmDialog(frame, "There are unsaved changes.\n\nDo you really want to save before quiting the application?", "Unsaved changes", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (answer == 0) {
                            // "Yes" = exit
                            applicationPanel.handleSave();
                            applicationPanel.close();
                            e.getWindow().dispose();
                        } else if (answer == 1) {
                            // "No" = discard
                            applicationPanel.handleDiscard();
                            applicationPanel.close();
                            e.getWindow().dispose();
                        }
                    } else {
                        applicationPanel.close();
                        e.getWindow().dispose();
                    }
                } catch (IOException ex) {
                    throw new PhoenixException(ex);
                }
            }
        });

        return frame;
    }
}
