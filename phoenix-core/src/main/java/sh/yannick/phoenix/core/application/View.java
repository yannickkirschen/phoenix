package sh.yannick.phoenix.core.application;

import javax.swing.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE_USE)
@Retention(RUNTIME)
public @interface View {
    String title() default "Unknown view";

    String icon() default "";

    String tip() default "";

    Class<? extends JPanel>[] dialogs();
}
