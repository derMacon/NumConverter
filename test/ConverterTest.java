import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    // --- positive num ---
    @Test
    public void testDecToBin_onlyPos_WithBlockSize() {
        assertEquals("00000000", Converter.conv("0", new Mode()));
        assertEquals("00000001", Converter.conv("1", new Mode()));
        assertEquals("00001010", Converter.conv("10", new Mode()));
        assertEquals("11111111", Converter.conv("255", new Mode()));
        assertEquals("0000000100000000", Converter.conv("256", new Mode()));
    }

    @Test
    public void testDecToBin_onlyPos_WithoutBlockSize() {
        assertEquals("0", Converter.conv("0", new Mode(10, 2, 1)));
        assertEquals("1", Converter.conv("1", new Mode(10, 2, 1)));
        assertEquals("1010", Converter.conv("10", new Mode(10, 2, 1)));
        assertEquals("11111111", Converter.conv("255", new Mode(10, 2, 1)));
        assertEquals("100000000", Converter.conv("256", new Mode(10, 2, 1)));
    }

    @Test
    public void testDecToOctal_onlyPos() {
        assertEquals("0", Converter.conv("0", new Mode(10, 8, 1)));
        assertEquals("1", Converter.conv("1", new Mode(10, 8, 1)));
        assertEquals("7", Converter.conv("7", new Mode(10, 8, 1)));
        assertEquals("10", Converter.conv("8", new Mode(10, 8, 1)));
        assertEquals("12", Converter.conv("10", new Mode(10, 8, 1)));
        assertEquals("17", Converter.conv("15", new Mode(10, 8, 1)));
        assertEquals("20", Converter.conv("16", new Mode(10, 8, 1)));
        assertEquals("377", Converter.conv("255", new Mode(10, 8, 1)));
        assertEquals("400", Converter.conv("256", new Mode(10, 8, 1)));
    }

    @Test
    public void testDecToHex_onlyPos() {
        assertEquals("0", Converter.conv("0", new Mode(10, 16, 1)));
        assertEquals("1", Converter.conv("1", new Mode(10, 16, 1)));
        assertEquals("A", Converter.conv("10", new Mode(10, 16, 1)));
        assertEquals("F", Converter.conv("15", new Mode(10, 16, 1)));
        assertEquals("10", Converter.conv("16", new Mode(10, 16, 1)));
        assertEquals("FF", Converter.conv("255", new Mode(10, 16, 1)));
        assertEquals("100", Converter.conv("256", new Mode(10, 16, 1)));
    }


    // --- two's complement ---
    @Test
    public void testDecToBin_onlyNeg() {
        assertEquals("00000000", Converter.conv("-0", new Mode(10, 8, 8)));
        assertEquals("11111111", Converter.conv("-1", new Mode(10, 8, 8)));
        assertEquals("11111001", Converter.conv("-7", new Mode(10, 8, 8)));
        assertEquals("11111000", Converter.conv("-8", new Mode(10, 8, 8)));
        assertEquals("11110110", Converter.conv("-10", new Mode(10, 8, 8)));
        assertEquals("11110001", Converter.conv("-15", new Mode(10, 8, 8)));
        assertEquals("11110000", Converter.conv("-16", new Mode(10, 8, 8)));
        assertEquals("100000001", Converter.conv("-255", new Mode(10, 8, 8)));
        assertEquals("100000000", Converter.conv("-256", new Mode(10, 8, 8)));
    }

}
