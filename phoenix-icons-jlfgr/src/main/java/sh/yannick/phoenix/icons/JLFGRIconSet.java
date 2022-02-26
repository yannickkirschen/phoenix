package sh.yannick.phoenix.icons;

/**
 * Binding for the Java look and feel Graphics Repository.
 * <p>
 * Icons are taken from artifact <code>net.java.linoleum:jlfgr</code> and accessible beneath
 * <code>/toolbarButtonGraphics</code>.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class JLFGRIconSet implements IconSet {
    private static final String PATH = "/toolbarButtonGraphics/%s/%s%d.gif";

    private static final String GENERAL_GROUP = "general";
    private static final String NAVIGATION_GROUP = "navigation";

    @Override
    public String getPath(DefaultIcon defaultIcon) {
        return switch (defaultIcon) {
            case HOME -> PATH.formatted(NAVIGATION_GROUP, "Home", 24);
            case ADD -> PATH.formatted(GENERAL_GROUP, "Add", 24);
            case EDIT -> PATH.formatted(GENERAL_GROUP, "Edit", 24);
            case DELETE -> PATH.formatted(GENERAL_GROUP, "Delete", 24);
        };
    }
}
