/**
 * Converts a number to another numerical system
 */
public class Converter {

    private static final String ERROR = "Invalid input";

    /**
     * Converts a given number in string format to a target num system using the corresponding mode.
     * @param num number that will be converted (in String format to make it possible to convert hex)
     * @param mode mode containing the source / target num system and the minimum blocksize
     * @return Converted Number in a String representation to make it possible to display hex
     */
    public static String conv(String num, Mode mode) {
        return convFromDec(convToDec(num, mode.getSourceBase()), mode.getTargetBase(), mode.getBlockSize());
    }

    /**
     * Converts a given decimal number to another numerical system with a given min. blocksize
     * @param dec decimal number to convert
     * @param base base of the target num system
     * @param blockSize minimum blocksize of the number
     * @return converted number
     */
    private static String convFromDec(String dec, int base, int blockSize) {
        try {
            String output = convFromDec(Integer.parseInt(dec), base);
            while (output.isEmpty() || output.length() % blockSize > 0) {
                output = 0 + output;
            }
            return output;
        } catch(NumberFormatException e) {
            return ERROR;
        }
    }

    /**
     * Converts a given decimal number to another numerical system ignoring the min. blocksize
     * @param dec decimal number to convert
     * @param base base of the target num system
     * @return converted number
     */
    public static String convFromDec(int dec, int base) {
        if(0 == dec) {
            return "";
        }
        if (1 == dec) {
            return String.valueOf(dec);
        }
        return convFromDec(dec / base, base) + convDecToHex(dec % base, base);
    }

    /**
     * Converts a given two digit decimal number into the a number of the numerical system of the given base. If base
     * is greater the 10 the extra digits will start with the letter 'A' (see hexadecimal system).
     * @param num number to convert
     * @param base base of the target system
     * @return converted number
     */
    public static char convDecToHex(int num, int base) {
        if(num < 10 && num < base) {
            return (char)(num + '0');
        }
        num -= 10; // generate offset to start with letters
        return (char)('A' + num);
    }

    /**
     * Converts a number from another num system to a the corresponding number in the decimal system
     * @param num number to convert into a decimal number
     * @param base base of the source num system
     * @return corresponding number in the decimal system
     */
    public static String convToDec(String num, int base) {
        int currDigit;
        int res = 0;
        for (int i = 0; i < num.length(); i++) {
            currDigit = num.charAt(i) - '0';
            if (currDigit < 10) {
                res += Math.pow(base, num.length() - i - 1) * Double.valueOf(currDigit);
            }
        }
        return String.valueOf(res);
    }
}
