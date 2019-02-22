public class CharacterSetFactory {
    public static CharacterSet translate(int ord, int base) {
        CharacterSet out = null; 
        if(HexTable.SUPPORTED_BASE.contains(base)){
            out = HexTable.values()[ord];
        } else {
            // todo unchecked exception
        }
        return out;
    }
}
