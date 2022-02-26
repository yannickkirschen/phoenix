package sh.yannick.phoenix.core.application;

import sh.yannick.phoenix.common.Savable;
import sh.yannick.phoenix.components.*;

import javax.swing.*;
import java.io.Closeable;

public interface ApplicationHandle {
    void setToolBar(ToolBar toolBar);

    void setStatus(String status);

    void addCloseable(Closeable closeable);

    void setDataChanged(Savable savable, boolean changed);

    JFrame getFrame();

    TabHandle getTabHandle();
}
