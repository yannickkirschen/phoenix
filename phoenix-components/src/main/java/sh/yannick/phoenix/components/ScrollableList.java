package sh.yannick.phoenix.components;

import javax.swing.*;

public class ScrollableList<E> extends JScrollPane {
    public ScrollableList(JList<E> list) {
        super();

        setViewportView(list);
    }
}
