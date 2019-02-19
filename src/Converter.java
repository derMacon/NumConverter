public class Converter {

    public static String convFromDec(int dec, int base, int blockSize) {
        String output = convFromDec(dec, base);
        while(output.length() % blockSize > 0) {
            output = 0 + output;
        }
        return output;
    }

    public static String convFromDec(int dec, int base) {
        if(0 == dec || 1 == dec) {
            return String.valueOf(dec);
        }
        return convFromDec(dec / base, base) + dec % base;
    }

    public static String convToDec(String num, int base) {
        int currDigit;
        try{
            currDigit = Integer.parseInt(num.getCh)
        }
    }
}
