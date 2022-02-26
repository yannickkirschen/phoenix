package sh.yannick.phoenix.common.laf;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class MetalLookAndFeelWrapper extends LookAndFeelWrapper {
    public MetalLookAndFeelWrapper() {
        //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }

    @Override
    public Class<? extends LookAndFeel> wrappedLookAndFeel() {
        return MetalLookAndFeel.class;
    }

    @Override
    public Color primaryColor() {
        int red = MetalLookAndFeel.getPrimaryControl().getRed();
        int green = MetalLookAndFeel.getPrimaryControl().getGreen();
        int blue = MetalLookAndFeel.getPrimaryControl().getBlue();

        return new Color(red, green, blue);
    }

    @Override
    public Color primaryHighlightColor() {
        int red = MetalLookAndFeel.getPrimaryControlHighlight().getRed();
        int green = MetalLookAndFeel.getPrimaryControlHighlight().getGreen();
        int blue = MetalLookAndFeel.getPrimaryControlHighlight().getBlue();

        return new Color(red, green, blue);
    }
}
