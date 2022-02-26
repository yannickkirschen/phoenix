package sh.yannick.phoenix.common.custom;

import sh.yannick.phoenix.common.laf.LookAndFeelWrapper;

import javax.swing.*;
import java.awt.*;

public class CustomLookAndFeelWrapper extends LookAndFeelWrapper {
    @Override
    public Class<? extends LookAndFeel> wrappedLookAndFeel() {
        return CustomLookAndFeel.class;
    }

    @Override
    public Color primaryColor() {
        return new Color(255, 255, 255);
    }

    @Override
    public Color primaryHighlightColor() {
        return new Color(50, 50, 50);
    }
}
