package sh.yannick.phoenix.core;

import lombok.RequiredArgsConstructor;
import sh.yannick.phoenix.common.PhoenixException;
import sh.yannick.phoenix.core.application.ApplicationPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

@RequiredArgsConstructor
public class RootFrameWindowListener extends WindowAdapter {
    private final ApplicationPanel applicationPanel;

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            if (applicationPanel.hasUnsavedChanges()) {
                int answer = JOptionPane.showConfirmDialog(applicationPanel.getFrame(), "There are unsaved changes.\n\nDo you really want to save before quiting the application?", "Unsaved changes", JOptionPane.YES_NO_CANCEL_OPTION);
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
}
