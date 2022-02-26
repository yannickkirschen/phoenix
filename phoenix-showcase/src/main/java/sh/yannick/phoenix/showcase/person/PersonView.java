package sh.yannick.phoenix.showcase.person;

import lombok.*;
import sh.yannick.phoenix.core.application.*;

@View(title = "Persons", tip = "Work with all persons",
    dialogs = {
        PersonListDialog.class,
        DemoDialog2.class,
        DemoDialog3.class,
        DemoDialog4.class
    })
public class PersonView implements ViewHandle {
    @Getter
    @Setter
    private ApplicationHandle applicationHandle;
}
