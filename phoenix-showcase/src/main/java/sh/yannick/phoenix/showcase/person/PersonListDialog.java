package sh.yannick.phoenix.showcase.person;

import sh.yannick.phoenix.common.Savable;
import sh.yannick.phoenix.components.*;
import sh.yannick.phoenix.core.application.Dialog;

import javax.swing.*;
import java.awt.*;
import java.io.Closeable;
import java.util.List;
import java.util.*;

@Dialog(title = "Person List")
public class PersonListDialog extends JPanel implements Savable, Closeable {
    private final PersonView view;

    private final EditableList<String> list = new EditableList<>();
    private final SaveDiscardPanel saveDiscard = new SaveDiscardPanel();

    private final List<String> persons = new LinkedList<>();

    public PersonListDialog(PersonView view) {
        super();
        this.view = view;

        setLayout(new BorderLayout());

        readPersons();

        persons.forEach(list::addElement);

        list.addActionListener(EditableList.Operation.ADD, listener -> {
            String person = JOptionPane.showInputDialog(
                view.getApplicationHandle().getFrame(),
                "Enter the name for the new person",
                "Add a new person",
                JOptionPane.PLAIN_MESSAGE);
            if (person != null && !person.isEmpty()) {
                list.addElement(person);
                persons.add(person);
                view.getApplicationHandle().setDataChanged(this, true);
                saveDiscard.setDataChanged(true);
            }
        });
        list.addActionListener(EditableList.Operation.EDIT, listener -> {
            String selection = list.getList().getSelectedValue();
            String person = (String) JOptionPane.showInputDialog(
                view.getApplicationHandle().getFrame(),
                "Enter the person's new name",
                "Edit a person", JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                selection);

            if (!selection.equals(person)) {
                list.getModel().setElementAt(person, list.getList().getSelectedIndex());
                persons.set(persons.indexOf(selection), person);
                view.getApplicationHandle().setDataChanged(this, true);
                saveDiscard.setDataChanged(true);
            }
        });
        list.addActionListener(EditableList.Operation.DELETE, listener -> {
            String selection = list.getList().getSelectedValue();
            int answer = JOptionPane.showConfirmDialog(
                view.getApplicationHandle().getFrame(),
                "Dou you really want ot delete '%s'?".formatted(selection),
                "Delete item",
                JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                list.getModel().removeElement(selection);
                persons.remove(selection);
                view.getApplicationHandle().setDataChanged(this, true);
                saveDiscard.setDataChanged(true);
            }
        });

        add(list, BorderLayout.CENTER);

        saveDiscard.addSaveActionListener(listener -> saveChanges());
        saveDiscard.addDiscardActionListener(listener -> discardChanges());

        add(saveDiscard, BorderLayout.SOUTH);
        view.getApplicationHandle().addCloseable(this);
    }

    @Override
    public void saveChanges() {
        new PersonRepository().save(persons);
        view.getApplicationHandle().setDataChanged(this, false);
        saveDiscard.setDataChanged(false);
    }

    @Override
    public void discardChanges() {
        persons.clear();
        readPersons();
        list.getModel().clear();
        persons.forEach(list::addElement);

        view.getApplicationHandle().setDataChanged(this, false);
        saveDiscard.setDataChanged(false);
    }

    @Override
    public void close() {
        //
    }

    private void readPersons() {
        persons.addAll(new PersonRepository().findAll());
    }
}
