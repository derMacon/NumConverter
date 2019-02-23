package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts a number of any supported numeral system to any other numeral system.
 */
public class Converter {

    /**
     * Mode holding information about the source / target numeral system and the minimum number of digits of the
     * output num.
     */
    private Mode mode;

    public Converter(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * Converts a given number in String formating from the source numerical system to the specified target system in
     * the 'mode' attribut.
     * @param num number that will be converted
     * @return number in the specified target format
     * @throws InvalidNumException
     */
    public String conv(String num) throws InvalidNumException {
        num = num.trim();
        List<Alphabet> lst = new ArrayList<>();
        boolean useTwosComp = num.charAt(0) == '-';

        // when negative -> use two's complement / ignore negative sign
        if (useTwosComp) {
            num = num.substring(1);
        }
        // load up list of digits
        for (int i = 0; i < num.length(); i++) {
            lst.add(Alphabet.lookup(num.charAt(i)));
        }

        // convert regular (pos. num) or with 2's complement
        if (useTwosComp) {
            lst = convTwosCompl(lst);
        } else {
            lst = convList(lst);
        }

        // Form String to display on toplevel
        StringBuilder output = new StringBuilder();
        for (Alphabet curr : lst) {
            output.append(curr);
        }
        return output.toString();
    }


    // --- normal processing (positive integers) ---

    /**
     * Converts a number of any supported numeral system to any other numeral system.
     *
     * @param num number to convert, is already splitted up into the different digits.
     * @return number in the target numeral system
     */
    private List<Alphabet> convList(List<Alphabet> num) {
        return convFromDec(convToDec(num));
    }

    /**
     * Converts a given number of any supported numeral system to a decimal number.
     *
     * @param num number to convert, is already splitted up into the different digits.
     * @return decimal number of the same value as the input num
     */
    private int convToDec(List<Alphabet> num) {
        int res = 0;
        for (int i = 0; i < num.size(); i++) {
            res += Math.pow(mode.getSourceBase(), num.size() - i - 1) * num.get(i).ordinal();
        }
        return res;
    }

    /**
     * Converts a given decimal number to a number with the target source of the given mode.
     *
     * @param dec decimal number that will be converted
     * @return converted decimal in the target numeral system.
     */
    private List<Alphabet> convFromDec(int dec) {
        List<Alphabet> output = new ArrayList<>();
        // generate output num
        do {
            output.add(0, Alphabet.values()[dec % mode.getTargetBase()]);
            dec /= mode.getTargetBase();
        } while (dec > 0);

        // fill up blocksize with zeros
        while (output.size() < mode.getBlockSize()) {
            output.add(0, Alphabet.zero);
        }
        return output;
    }


    // --- two's complement (negative integers) ---

    /**
     * Converts a given number to a the binary two's complement. The target base has to be the binary system,
     * otherwise there will be an InvalidNumException.
     * @param num negative number to convert
     * @return two's complement in the binary system
     * @throws InvalidNumException thrown when the target base is not the binary system
     */
    private List<Alphabet> convTwosCompl(List<Alphabet> num) throws InvalidNumException {
        if (this.mode.getTargetBase() != 2) {
            throw new InvalidNumException("Invalid target base, only possible to convert to binary when using two's " +
                    "complement (negative values only to base2).");
        }
        if (convToDec(num) == 0) {
            // fill up blocksize with zeros
            while (num.size() % mode.getBlockSize() > 0) {
                num.add(0, Alphabet.zero);
            }
            return num;
        }
        return incrementNum(invertBits(convList(num)));
    }

    /**
     * Inverts every bit in the given sequence.
     * @param out list of bits that will be inverted
     * @return sequence with inverted bits
     */
    private List<Alphabet> invertBits(List<Alphabet> out) {
        Alphabet curr;
        for (int i = 0; i < out.size(); i++) {
            assert out.get(i).ordinal() == 1 || out.get(i).ordinal() == 0;
            curr = out.get(i);
            out.set(i, curr.ordinal() == 0 ? Alphabet.one : Alphabet.zero);
        }
        return out;
    }

    /**
     * Increments given binary number by one. If necessary another 1 will be added to the beginning (see JUnit Test
     * with 255 / 256)
     * @param out number to be incremented
     * @return incremented number
     */
    private List<Alphabet> incrementNum(List<Alphabet> out) {
        if (convToDec(out) == 0) {
            out.add(0, Alphabet.one);
        }
        int i = out.size() - 1;
        while (i >= 0 && out.get(i).equals(Alphabet.one)) {
            out.set(i, Alphabet.zero);
            i--;
        }
        if (out.get(i).equals(Alphabet.zero)) {
            out.set(i, Alphabet.one);
        }
        return out;
    }
}
