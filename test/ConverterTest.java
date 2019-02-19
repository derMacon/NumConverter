import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    // --- Decimal to binary / positive num ---
    @Test
    public void testDecToBin() {
        assertEquals("00000000", Converter.convFromDec(0, 2, 8));
        assertEquals("00000001", Converter.convFromDec(1, 2,  8));
        assertEquals("11111111", Converter.convFromDec(255, 2, 8));
        assertEquals("0000000100000000", Converter.convFromDec(256, 2, 8));
    }

}