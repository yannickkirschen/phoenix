package sh.yannick.phoenix.core.application;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE_USE)
@Retention(RUNTIME)
public @interface Dialog {
    String title() default "Unknown dialog";
}
