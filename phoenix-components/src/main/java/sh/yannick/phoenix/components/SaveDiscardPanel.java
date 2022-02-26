package sh.yannick.phoenix.components;

import sh.yannick.phoenix.common.Savable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveDiscardPanel extends JPanel implements Savable {
    private final Button save;
    private final Button discard;

    public SaveDiscardPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        setBorder(new EmptyBorder(10, 0, 10, 0));

        save = new Button("Save");
        discard = new Button("Discard");

        save.setEnabled(false);
        discard.setEnabled(false);

        add(save);
        add(discard);
    }

    public void addSaveActionListener(ActionListener listener) {
        save.addActionListener(listener);
    }

    public void addDiscardActionListener(ActionListener listener) {
        discard.addActionListener(listener);
    }

    public void setDataChanged(boolean changed) {
        if (changed) {
            save.setEnabled(true);
            discard.setEnabled(true);
        } else {
            save.setEnabled(false);
            discard.setEnabled(false);
        }
    }

    @Override
    public void saveChanges() {
        save.setEnabled(false);
        discard.setEnabled(false);
    }

    @Override
    public void discardChanges() {
        save.setEnabled(false);
        discard.setEnabled(false);
    }
}
