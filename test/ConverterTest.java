import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    // --- positive num ---
    @Test
    public void testDecToBin_onlyPos_WithBlockSize() throws InvalidNumException {
        Converter c = new Converter(new Mode());
        assertEquals("00000000", c.conv("0"));
        assertEquals("00000001", c.conv("1"));
        assertEquals("00001010", c.conv("10"));
        assertEquals("11111111", c.conv("255"));
        assertEquals("0000000100000000", c.conv("256"));
    }

    @Test
    public void testDecToBin_onlyPos_WithoutBlockSize() throws InvalidNumException {
        Converter c = new Converter(new Mode(10, 2, 1));
        assertEquals("0", c.conv("0"));
        assertEquals("1", c.conv("1"));
        assertEquals("1010", c.conv("10"));
        assertEquals("11111111", c.conv("255"));
        assertEquals("100000000", c.conv("256"));
    }

    @Test
    public void testDecToOctal_onlyPos() throws InvalidNumException {
        Converter c = new Converter(new Mode(10, 8, 1));
        assertEquals("0", c.conv("0"));
        assertEquals("1", c.conv("1"));
        assertEquals("7", c.conv("7"));
        assertEquals("10", c.conv("8"));
        assertEquals("12", c.conv("10"));
        assertEquals("17", c.conv("15"));
        assertEquals("20", c.conv("16"));
        assertEquals("377", c.conv("255"));
        assertEquals("400", c.conv("256"));
    }

    @Test
    public void testDecToHex_onlyPos() throws InvalidNumException {
        Converter c = new Converter(new Mode(10, 16, 1));
        assertEquals("0", c.conv("0"));
        assertEquals("1", c.conv("1"));
        assertEquals("A", c.conv("10"));
        assertEquals("F", c.conv("15"));
        assertEquals("10", c.conv("16"));
        assertEquals("FF", c.conv("255"));
        assertEquals("100", c.conv("256"));
    }


    // --- two's complement ---
    @Test
    public void testDecToBin_onlyNeg() throws InvalidNumException {
        Converter c = new Converter(new Mode(10, 2, 8));
        assertEquals("00000000", c.conv("-0"));
        assertEquals("11111111", c.conv("-1"));
        assertEquals("11111001", c.conv("-7"));
        assertEquals("11111000", c.conv("-8"));
        assertEquals("11110110", c.conv("-10"));
        assertEquals("11110001", c.conv("-15"));
        assertEquals("11110000", c.conv("-16"));
        assertEquals("100000001", c.conv("-255"));
        assertEquals("100000000", c.conv("-256"));
    }

}
