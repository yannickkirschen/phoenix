package sh.yannick.phoenix.core.components;

import sh.yannick.phoenix.components.*;

public class StartViewToolBar extends ToolBar {
    public StartViewToolBar() {
        super();
        addTool(new Button(Icon.createIcon("/toolbarButtonGraphics/general/New24.gif")));
    }
}
