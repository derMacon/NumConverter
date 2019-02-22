import java.util.ArrayList;
import java.util.List;

/**
 * Converts a number of any supported numeral system to any other numeral system.
 */
public class Converter2 {

    /**
     * Mode holding information about the source / target numeral system and the minimum number of digits of the
     * output num.
     */
    private Mode mode;

    public Converter2(Mode mode) {
        this.mode = mode;
    }

    /**
     * Converts a number of any supported numeral system to any other numeral system.
     * @param num number to convert, is already splitted up into the different digits.
     * @return number in the target numeral system
     */
    public List<CharacterSet> conv(List<CharacterSet> num) {
        return convFromDec(convToDec(num));
    }

    /**
     * Converts a given number of any supported numeral system to a decimal number.
     * @param num number to convert, is already splitted up into the different digits.
     * @return decimal number of the same value as the input num
     */
    private int convToDec(List<CharacterSet> num) {
        int res = 0;
        for (int i = 0; i < num.size(); i++) {
            res += Math.pow(mode.getSourceBase(), num.size() - i - 1) * num.get(i).getOrdVal();
        }
        return res;
    }

    /**
     * Converts a given decimal number to a number with the target source of the given mode.
     * @param dec decimal number that will be converted
     * @return converted decimal in the target numeral system.
     */
    private List<CharacterSet> convFromDec(int dec) {
        List<CharacterSet> output = new ArrayList<>();
        do {
            output.add(0, CharacterSetFactory.translate(dec % mode.getTargetBase(), mode.getTargetBase()));
            dec /= mode.getTargetBase();
        } while (dec > 0);
        while (output.size() % mode.getBlockSize() > 0) {
            output.add(0, CharacterSetFactory.translate(0, mode.getTargetBase()));
        }
        return output;
    }
}
