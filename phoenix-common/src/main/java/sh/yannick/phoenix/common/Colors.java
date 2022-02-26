package sh.yannick.phoenix.common;

import java.awt.*;
import java.util.List;

/**
 * A utility class to work with Swing/AWT colors.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public final class Colors {
    private Colors() {
    }

    /**
     * Creates a color from a list of three or four RGB(A) color values.
     *
     * @param rgba list of three or four RGB(A) values
     * @return the color
     * @throws IllegalArgumentException  if "r", "g", "b" or "a" are outside the range 0 to 255, inclusive
     * @throws IndexOutOfBoundsException if the list does not have a length of 3 or 4
     * @throws NullPointerException      if the list is <code>null</code>
     */
    public static Color fromRGBAList(List<String> rgba) {
        if (rgba.size() < 3 || rgba.size() > 4) {
            throw new IndexOutOfBoundsException("A color must look like this: <r>:<g>:<b>(:<a>)");
        }

        if (rgba.size() == 3) {
            return new Color(
                Integer.parseInt(rgba.get(0)),
                Integer.parseInt(rgba.get(1)),
                Integer.parseInt(rgba.get(2)));
        }

        return new Color(
            Integer.parseInt(rgba.get(0)),
            Integer.parseInt(rgba.get(1)),
            Integer.parseInt(rgba.get(2)),
            Integer.parseInt(rgba.get(3)));
    }
}
