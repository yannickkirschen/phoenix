package sh.yannick.phoenix.showcase.person;

import sh.yannick.phoenix.core.application.ApplicationHandle;

public interface PersonListHandle {
    ApplicationHandle getApplicationHandle();

    void addPerson(String person);

    void setDataChanged(boolean changed);
}
