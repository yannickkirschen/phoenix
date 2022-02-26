package sh.yannick.phoenix.common;

import org.junit.jupiter.api.*;

@DisplayName("Test reflection utils")
public class ReflectionTest {
    @Test
    @DisplayName("Test instantiation via class reference with default constructor")
    public void classReferenceDefaultConstructorTest() {
        ReflectionDemoClass demo = Reflection.newInstance(ReflectionDemoClass.class);

        Assertions.assertNull(demo.getName());
        Assertions.assertNull(demo.getAge());
    }

    @Test
    @DisplayName("Test instantiation via class reference with constructor")
    public void classReferenceConstructorTest() {
        ReflectionDemoClass demo = Reflection.newInstance(ReflectionDemoClass.class, "Peter", 22);

        Assertions.assertEquals("Peter", demo.getName());
        Assertions.assertEquals(22, demo.getAge());
    }

    @Test
    @DisplayName("Test instantiation via fully qualified class name with default constructor")
    public void fullyQualifiedClassNameDefaultConstructorTest() {
        ReflectionDemoClass demo = Reflection.newInstance("sh.yannick.phoenix.common.ReflectionDemoClass");

        Assertions.assertNull(demo.getName());
        Assertions.assertNull(demo.getAge());
    }

    @Test
    @DisplayName("Test instantiation via fully qualified class name with constructor")
    public void fullyQualifiedClassNameConstructorTest() {
        ReflectionDemoClass demo = Reflection.newInstance("sh.yannick.phoenix.common.ReflectionDemoClass", "Peter", 22);

        Assertions.assertEquals("Peter", demo.getName());
        Assertions.assertEquals(22, demo.getAge());
    }
}
