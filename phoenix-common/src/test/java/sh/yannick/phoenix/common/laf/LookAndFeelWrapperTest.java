package sh.yannick.phoenix.common.laf;

import org.junit.jupiter.api.*;
import sh.yannick.phoenix.common.PhoenixException;
import sh.yannick.phoenix.common.custom.*;

import javax.swing.*;

@DisplayName("Test LAF wrapper")
public class LookAndFeelWrapperTest {
    @Test
    @DisplayName("Test default wrapper retrieval")
    public void defaultWrapperRetrievalTest() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        LookAndFeelWrapper wrapper = LookAndFeelWrapper.defaultWrapper();
        Assertions.assertNotNull(wrapper);
    }

    @Test
    @DisplayName("Test custom wrapper retrieval")
    public void customWrapperRetrievalTest() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("sh.yannick.phoenix.common.custom.CustomLookAndFeel");

        Assertions.assertThrows(PhoenixException.class, LookAndFeelWrapper::defaultWrapper);

        LookAndFeelWrapper.addWrapper(CustomLookAndFeelWrapper.class);
        LookAndFeelWrapper wrapper = LookAndFeelWrapper.defaultWrapper();
        Assertions.assertNotNull(wrapper);
        Assertions.assertEquals(CustomLookAndFeel.class, wrapper.wrappedLookAndFeel());
    }
}
