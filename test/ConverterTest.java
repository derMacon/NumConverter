import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    @Test
    public void testDecToBin_OnlyPosInput() {
        assertEquals("00000000", Converter.conv("0", new Mode()));
        assertEquals("00000001", Converter.conv("1", new Mode()));
        assertEquals("00001010", Converter.conv("10", new Mode()));
        assertEquals("11111111", Converter.conv("255", new Mode()));
        assertEquals("0000000100000000", Converter.conv("256", new Mode()));
    }

}
