package sh.yannick.phoenix.core;

import sh.yannick.phoenix.core.application.*;

import javax.swing.*;
import java.util.Collection;

public record UILoader(Collection<Class<? extends ViewHandle>> views, PhoenixConfiguration configuration) {
    public JFrame load() {
        JFrame frame = new RootFrame(configuration);

        ApplicationPanel applicationPanel = new ApplicationPanel(views, frame);
        frame.add(applicationPanel);
        frame.addWindowListener(new RootFrameWindowListener(applicationPanel));

        return frame;
    }
}
