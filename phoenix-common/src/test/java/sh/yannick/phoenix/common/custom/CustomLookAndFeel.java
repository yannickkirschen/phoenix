package sh.yannick.phoenix.common.custom;

import javax.swing.*;

public class CustomLookAndFeel extends LookAndFeel {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
}
