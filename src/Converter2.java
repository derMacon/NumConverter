import java.util.ArrayList;
import java.util.List;

public class Converter2 {

    private Mode mode;

    public Converter2(Mode mode) {
        this.mode = mode;
    }

    public List<CharacterSet> conv(List<CharacterSet> num) {
        return convFromDec(convToDec(num));
    }

    private int convToDec(List<CharacterSet> num) {
        int res = 0;
        for (int i = 0; i < num.size(); i++) {
            res += Math.pow(mode.getSourceBase(), num.size() - i - 1) * num.get(i).getOrdVal();
        }
        return res;
    }

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
