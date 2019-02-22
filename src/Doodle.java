import java.util.Arrays;
import java.util.List;

public class Doodle {

    public static void main(String[] args) {
        Mode mode = new Mode();
        Converter2 c = new Converter2(mode);
        List<CharacterSet> out = c.conv(Arrays.asList(HexTable.nine));
        System.out.println(out);
    }
}
