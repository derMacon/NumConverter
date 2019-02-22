import java.util.HashSet;

public interface CharacterSet {
    char getCharRepresentation();
    HashSet<Integer> getBase();
    int getOrdVal();
    CharacterSet getElem(int ord);
}
