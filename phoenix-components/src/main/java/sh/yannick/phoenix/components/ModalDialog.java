package sh.yannick.phoenix.components;

import javax.swing.*;
import java.awt.*;

public class ModalDialog extends JDialog {
    public ModalDialog(JFrame frame) {
        super(frame, Dialog.ModalityType.DOCUMENT_MODAL);
        setLocationRelativeTo(null);
    }
}
