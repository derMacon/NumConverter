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

    public String conv(String num) throws InvalidNumException {
        num = num.trim();
        List<Alphabet> lst = new ArrayList<>();
        boolean useTwosComp = num.charAt(0) == '-';

        // when negative -> use two's complement / ignore -
        if (useTwosComp) {
            num = num.substring(1);
        }
        // load up list of digits
        for (int i = 0; i < num.length(); i++) {
            lst.add(Alphabet.lookup(num.charAt(i)));
        }

        if(useTwosComp) {
            lst = addTwosCompl(lst);
        }
        lst = convList(lst);

        StringBuilder output = new StringBuilder();
        for (Alphabet curr : lst) {
            output.append(curr);
        }
        return output.toString();
    }

    private List<Alphabet> addTwosCompl(List<Alphabet> num) {
        Mode temp = this.mode;
        this.mode = new Mode(this.mode.getSourceBase(), 2, this.mode.getBlockSize());
        List<Alphabet> out = convList(num);
        // increment num
        int i = out.size() - 1;
        while(out.size() >= 0 && out.get(i).equals(Alphabet.one)) {
            out.set(i, Alphabet.zero);
        }

        // set one on the current index or add a new one at the start if necessary
        if(i == 0) {
            out.add(0, Alphabet.one);
        } else {
            out.set(0, Alphabet.one);
        }

        this.mode = new Mode(2, temp.getSourceBase(), temp.getBlockSize());
        return out;
    }

    /**
     * Converts a number of any supported numeral system to any other numeral system.
     *
     * @param num number to convert, is already splitted up into the different digits.
     * @return number in the target numeral system
     */
    public List<Alphabet> convList(List<Alphabet> num) {
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
        while (output.size() % mode.getBlockSize() > 0) {
            output.add(0, Alphabet.zero);
        }
        return output;
    }
}
