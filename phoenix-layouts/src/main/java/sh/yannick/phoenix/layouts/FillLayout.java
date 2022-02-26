package sh.yannick.phoenix.layouts;

import java.awt.*;

/**
 * Components using this layout manager will be stretched to the entire available space.
 * <p>
 * It should be only used for components that take just one child component.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class FillLayout extends GridLayout {
    /**
     * Creates a layout manager that will stretch components to the available space.
     */
    public FillLayout() {
        super(1, 0);
    }
}
