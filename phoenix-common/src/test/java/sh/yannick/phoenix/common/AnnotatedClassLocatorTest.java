package sh.yannick.phoenix.common;

import org.junit.jupiter.api.*;
import sh.yannick.phoenix.common.custom.*;

import java.io.Serializable;
import java.util.List;

@DisplayName("Test annotated class locating")
public class AnnotatedClassLocatorTest {
    @Test
    @DisplayName("Test with package containing annotated classes")
    public void validTest() {
        List<Class<? extends Serializable>> classes = new AnnotatedClassLocator<Serializable>()
            .pkg("sh.yannick.phoenix.common")
            .locate(Serializable.class, CustomAnnotation.class);

        Assertions.assertEquals(CustomAnnotatedClass.class, classes.get(0));
    }

    @Test
    @DisplayName("Test with package NOT containing annotated classes")
    public void invalidTest() {
        List<Class<? extends Serializable>> classes = new AnnotatedClassLocator<Serializable>()
            .pkg("sh.yannick.phoenix.common.laf")
            .locate(Serializable.class, CustomAnnotation.class);

        Assertions.assertEquals(0, classes.size());
    }
}
