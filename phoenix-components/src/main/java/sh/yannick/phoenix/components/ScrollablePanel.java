package sh.yannick.phoenix.components;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class ScrollablePanel extends JPanel implements Scrollable {
    @Getter
    private ScrollableSizeHint scrollableHeight = ScrollableSizeHint.NONE;

    @Getter
    private ScrollableSizeHint scrollableWidth = ScrollableSizeHint.NONE;

    private IncrementInfo horizontalBlock;
    private IncrementInfo horizontalUnit;

    private IncrementInfo verticalBlock;
    private IncrementInfo verticalUnit;

    public ScrollablePanel() {
        this(new FlowLayout());
    }

    public ScrollablePanel(LayoutManager layout) {
        super(layout);

        IncrementInfo block = new IncrementInfo(IncrementType.PERCENT, 100);
        IncrementInfo unit = new IncrementInfo(IncrementType.PERCENT, 10);

        setScrollableBlockIncrement(SwingConstants.HORIZONTAL, block);
        setScrollableBlockIncrement(SwingConstants.VERTICAL, block);

        setScrollableUnitIncrement(SwingConstants.HORIZONTAL, unit);
        setScrollableUnitIncrement(SwingConstants.VERTICAL, unit);
    }

    /**
     * Set the ScrollableSizeHint enum for the height. The enum is used to determine the boolean value that is returned
     * by the getScrollableTracksViewportHeight() method. The valid values are:
     * <p>
     * ScrollableSizeHint.NONE - return "false", which causes the height of the JPanel to be used when laying out the
     * children ScrollableSizeHint.FIT - return "true", which causes the height of the viewport to be used when laying
     * out the children ScrollableSizeHint.STRETCH - return "true" when the viewport height is greater than the height
     * of the JPanel, "false" otherwise.
     *
     * @param scrollableHeight as represented by the ScrollableSizeHint enum.
     */
    public void setScrollableHeight(ScrollableSizeHint scrollableHeight) {
        this.scrollableHeight = scrollableHeight;
        revalidate();
    }

    /**
     * Set the ScrollableSizeHint enum for the width. The enum is used to determine the boolean value that is returned
     * by the getScrollableTracksViewportWidth() method. The valid values are:
     * <p>
     * ScrollableSizeHint.NONE - return "false", which causes the width of the JPanel to be used when laying out the
     * children ScrollableSizeHint.FIT - return "true", which causes the width of the viewport to be used when laying
     * out the children ScrollableSizeHint.STRETCH - return "true" when the viewport width is greater than the width of
     * the JPanel, "false" otherwise.
     *
     * @param scrollableWidth as represented by the ScrollableSizeHint enum.
     */
    public void setScrollableWidth(ScrollableSizeHint scrollableWidth) {
        this.scrollableWidth = scrollableWidth;
        revalidate();
    }

    /**
     * Get the block IncrementInfo for the specified orientation
     *
     * @return the block IncrementInfo for the specified orientation
     */
    public IncrementInfo getScrollableBlockIncrement(int orientation) {
        return orientation == SwingConstants.HORIZONTAL ? horizontalBlock : verticalBlock;
    }

    /**
     * Specify the information needed to do block scrolling.
     *
     * @param orientation specify the scrolling orientation. Must be either: SwingConstants.HORIZONTAL or
     *                    SwingConstants.VERTICAL.
     * @param amount      a value used with the IncrementType to determine the scrollable amount
     * @param type        specify how the amount parameter in the calculation of the scrollable amount. Valid values
     *                    are: IncrementType.PERCENT - treat the amount as a % of the viewport size IncrementType.PIXEL
     *                    - treat the amount as the scrollable amount
     */
    public void setScrollableBlockIncrement(int orientation, IncrementType type, int amount) {
        IncrementInfo info = new IncrementInfo(type, amount);
        setScrollableBlockIncrement(orientation, info);
    }

    /**
     * Specify the information needed to do block scrolling.
     *
     * @param orientation specify the scrolling orientation. Must be either: SwingConstants.HORIZONTAL or
     *                    SwingConstants.VERTICAL.
     * @param info        An IncrementInfo object containing information of how to calculate the scrollable amount.
     */
    public void setScrollableBlockIncrement(int orientation, IncrementInfo info) {
        switch (orientation) {
            case SwingConstants.HORIZONTAL -> horizontalBlock = info;
            case SwingConstants.VERTICAL -> verticalBlock = info;
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Get the unit IncrementInfo for the specified orientation
     *
     * @return the unit IncrementInfo for the specified orientation
     */
    public IncrementInfo getScrollableUnitIncrement(int orientation) {
        return orientation == SwingConstants.HORIZONTAL ? horizontalUnit : verticalUnit;
    }

    /**
     * Specify the information needed to do unit scrolling.
     *
     * @param orientation specify the scrolling orientation. Must be either: SwingConstants.HORIZONTAL or
     *                    SwingConstants.VERTICAL.
     * @param amount      a value used with the IncrementType to determine the scrollable amount
     * @param type        specify how the amount parameter in the calculation of the scrollable amount. Valid values
     *                    are: IncrementType.PERCENT - treat the amount as a % of the viewport size IncrementType.PIXEL
     *                    - treat the amount as the scrollable amount
     */
    public void setScrollableUnitIncrement(int orientation, IncrementType type, int amount) {
        IncrementInfo info = new IncrementInfo(type, amount);
        setScrollableUnitIncrement(orientation, info);
    }

    /**
     * Specify the information needed to do unit scrolling.
     *
     * @param orientation specify the scrolling orientation. Must be either: SwingConstants.HORIZONTAL or
     *                    SwingConstants.VERTICAL.
     * @param info        An IncrementInfo object containing information of how to calculate the scrollable amount.
     */
    public void setScrollableUnitIncrement(int orientation, IncrementInfo info) {
        switch (orientation) {
            case SwingConstants.HORIZONTAL -> horizontalUnit = info;
            case SwingConstants.VERTICAL -> verticalUnit = info;
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visible, int orientation, int direction) {
        return switch (orientation) {
            case SwingConstants.HORIZONTAL -> getScrollableIncrement(horizontalUnit, visible.width);
            case SwingConstants.VERTICAL -> getScrollableIncrement(verticalUnit, visible.height);
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visible, int orientation, int direction) {
        return switch (orientation) {
            case SwingConstants.HORIZONTAL -> getScrollableIncrement(horizontalBlock, visible.width);
            case SwingConstants.VERTICAL -> getScrollableIncrement(verticalBlock, visible.height);
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    protected int getScrollableIncrement(IncrementInfo info, int distance) {
        if (info.type == IncrementType.PIXELS) {
            return info.amount;
        } else {
            return distance * info.amount / 100;
        }
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        if (scrollableWidth == ScrollableSizeHint.NONE) {
            return false;
        }

        if (scrollableWidth == ScrollableSizeHint.FIT) {
            return true;
        }

        if (getParent() instanceof JViewport) {
            return getParent().getWidth() > getPreferredSize().width;
        }

        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        if (scrollableHeight == ScrollableSizeHint.NONE) {
            return false;
        }

        if (scrollableHeight == ScrollableSizeHint.FIT) {
            return true;
        }

        if (getParent() instanceof JViewport) {
            return getParent().getHeight() > getPreferredSize().height;
        }

        return false;
    }

    public enum ScrollableSizeHint {
        NONE,
        FIT,
        STRETCH
    }

    public enum IncrementType {
        PERCENT,
        PIXELS
    }

    /**
     * Helper class to hold the information required to calculate the scroll amount.
     */
    record IncrementInfo(IncrementType type, int amount) {
    }
}

