package sh.yannick.phoenix.components;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    public TileButton(String text) {
        super(text);
        setPreferredSize(new Dimension(100, 100));
    }
}
