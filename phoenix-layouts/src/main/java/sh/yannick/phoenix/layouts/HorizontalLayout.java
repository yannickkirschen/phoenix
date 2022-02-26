package sh.yannick.phoenix.layouts;

import javax.swing.*;
import java.awt.*;

/**
 * Components using this layout manager will have their children laid out along the horizontal axis.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class HorizontalLayout extends BoxLayout {
    /**
     * Creates a layout manager that will lay out components along the horizontal axis.
     *
     * @param target the container that needs to be laid out
     */
    public HorizontalLayout(Container target) {
        super(target, BoxLayout.X_AXIS);
    }
}
