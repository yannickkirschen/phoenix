package sh.yannick.phoenix.components;

import lombok.Getter;
import sh.yannick.phoenix.icons.DefaultIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditableList<E> extends JPanel {
    @Getter
    private final DefaultListModel<E> model = new DefaultListModel<>();

    @Getter
    private final JList<E> list;

    private final JPanel buttonbar = new JPanel();

    private Button addButton;
    private Button editButton;
    private Button deleteButton;

    @SuppressWarnings("unchecked")
    public EditableList() {
        this((ListCellRenderer<E>) new DefaultListCellRenderer());
    }

    public EditableList(ListCellRenderer<E> renderer) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createRaisedBevelBorder());

        JList<E> list = new JList<>(model);
        list.getSelectionModel().addListSelectionListener(listener -> {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        });
        list.setCellRenderer(renderer);
        list.setLayoutOrientation(JList.VERTICAL);
        this.list = list;

        JPanel buttonbarWrapper = new JPanel(new BorderLayout());
        buttonbarWrapper.add(buttonbar, BorderLayout.EAST);
        add(buttonbarWrapper, BorderLayout.NORTH);
        add(new ScrollableList<>(list), BorderLayout.CENTER);
    }

    public void addActionListener(Operation operation, ActionListener listener) {
        createButtonIfNotExist(operation);

        switch (operation) {
            case ADD -> addButton.addActionListener(listener);
            case EDIT -> editButton.addActionListener(listener);
            case DELETE -> deleteButton.addActionListener(listener);
        }
    }

    public void addElement(E e) {
        model.addElement(e);
    }

    private void createButtonIfNotExist(Operation operation) {
        switch (operation) {
            case ADD -> {
                if (addButton == null) {
                    addButton = new Button(Icon.createIcon(DefaultIcon.ADD));
                    buttonbar.add(addButton);
                }
            }
            case EDIT -> {
                if (editButton == null) {
                    editButton = new Button(Icon.createIcon(DefaultIcon.EDIT));
                    editButton.setEnabled(false);
                    buttonbar.add(editButton);
                }
            }
            case DELETE -> {
                if (deleteButton == null) {
                    deleteButton = new Button(Icon.createIcon(DefaultIcon.DELETE));
                    deleteButton.setEnabled(false);
                    buttonbar.add(deleteButton);
                }
            }
        }
    }

    public enum Operation {
        ADD,
        EDIT,
        DELETE
    }
}
