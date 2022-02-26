package sh.yannick.phoenix.components;

import sh.yannick.phoenix.common.*;
import sh.yannick.phoenix.icons.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Icon extends ImageIcon {
    private static IconSet iconSet;

    public Icon(InputStream in) throws IOException {
        super(ImageIO.read(in));
    }

    public static void setIconSet(IconSet iconSet) {
        Icon.iconSet = iconSet;
    }

    public static Icon createIcon(String name) {
        InputStream in = Icon.class.getResourceAsStream(name);

        try {
            return new Icon(in);
        } catch (IOException e) {
            throw new PhoenixException(e);
        }
    }

    public static Icon createIcon(DefaultIcon defaultIcon) {
        return createIcon(iconSet.getPath(defaultIcon));
    }
}
