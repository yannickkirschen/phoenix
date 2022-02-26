package sh.yannick.phoenix.common;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.List;

@DisplayName("Test Swing color utils")
public class ColorsTest {
    @Test
    @DisplayName("Test with three correct values")
    public void threeValuesTest() {
        Color color = Colors.fromRGBAList(List.of("23", "56", "19"));

        Assertions.assertEquals(23, color.getRed());
        Assertions.assertEquals(56, color.getGreen());
        Assertions.assertEquals(19, color.getBlue());
        Assertions.assertEquals(255, color.getAlpha());
    }

    @Test
    @DisplayName("Test with four correct values")
    public void fourValuesTest() {
        Color color = Colors.fromRGBAList(List.of("23", "56", "19", "234"));

        Assertions.assertEquals(23, color.getRed());
        Assertions.assertEquals(56, color.getGreen());
        Assertions.assertEquals(19, color.getBlue());
        Assertions.assertEquals(234, color.getAlpha());
    }

    @Test

    @DisplayName("Test with three zero values")
    public void threeValuesLowerBoundaryTest() {
        Color color = Colors.fromRGBAList(List.of("0", "0", "0"));

        Assertions.assertEquals(0, color.getRed());
        Assertions.assertEquals(0, color.getGreen());
        Assertions.assertEquals(0, color.getBlue());
        Assertions.assertEquals(255, color.getAlpha());
    }

    @Test
    @DisplayName("Test with four zero values")
    public void fourValuesLowerBoundaryTest() {
        Color color = Colors.fromRGBAList(List.of("0", "0", "0", "0"));

        Assertions.assertEquals(0, color.getRed());
        Assertions.assertEquals(0, color.getGreen());
        Assertions.assertEquals(0, color.getBlue());
        Assertions.assertEquals(0, color.getAlpha());
    }

    @Test
    @DisplayName("Test with three 255 values")
    public void threeValuesUpperBoundaryTest() {
        Color color = Colors.fromRGBAList(List.of("255", "255", "255"));

        Assertions.assertEquals(255, color.getRed());
        Assertions.assertEquals(255, color.getGreen());
        Assertions.assertEquals(255, color.getBlue());
        Assertions.assertEquals(255, color.getAlpha());
    }

    @Test
    @DisplayName("Test with four 255 values")
    public void fourValuesUpperBoundaryTest() {
        Color color = Colors.fromRGBAList(List.of("255", "255", "255", "255"));

        Assertions.assertEquals(255, color.getRed());
        Assertions.assertEquals(255, color.getGreen());
        Assertions.assertEquals(255, color.getBlue());
        Assertions.assertEquals(255, color.getAlpha());
    }

    @Test
    @DisplayName("Test with two values values")
    public void exceptionIfTooFewValuesTest() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Colors.fromRGBAList(List.of("", "")));
    }

    @Test
    @DisplayName("Test with five values")
    public void exceptionIfTooManyValuesTest() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Colors.fromRGBAList(List.of("", "", "", "", "")));
    }

    @Test
    @DisplayName("Test with three incorrect values")
    public void threeIncorrectValuesTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Colors.fromRGBAList(List.of("-1", "256", "45")));
    }

    @Test
    @DisplayName("Test with four incorrect values")
    public void fourIncorrectValuesTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Colors.fromRGBAList(List.of("-1", "256", "45", "276")));
    }
}
