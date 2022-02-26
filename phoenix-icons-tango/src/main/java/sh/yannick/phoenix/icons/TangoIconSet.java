package sh.yannick.phoenix.icons;

/**
 * Binding for the Tango Desktop Project.
 * <p>
 * Icons are taken from artifact <code>org.freedesktop.tango:tango-icon-theme</code> and accessible beneath
 * <code>/org/freedesktop/tango</code>.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class TangoIconSet implements IconSet {
    private static final String PATH = "/org/freedesktop/tango/%dx%d/%s/%s.png";

    private static final String ACTIONS_GROUP = "actions";

    @Override
    public String getPath(DefaultIcon defaultIcon) {
        return switch (defaultIcon) {
            case HOME -> PATH.formatted(22, 22, ACTIONS_GROUP, "go-home");
            case ADD -> PATH.formatted(22, 22, ACTIONS_GROUP, "list-add");
            case EDIT -> PATH.formatted(22, 22, ACTIONS_GROUP, "edit-paste");
            case DELETE -> PATH.formatted(22, 22, ACTIONS_GROUP, "list-remove");
        };
    }
}
