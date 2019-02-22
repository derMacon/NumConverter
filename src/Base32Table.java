import java.util.Arrays;
import java.util.HashSet;

/**
 * https://www.garykessler.net/library/base64.html
 */
public enum Base32Table implements CharacterSet {

    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    I('I'),
    J('J'),
    K('K'),
    L('L'),
    M('M'),
    N('N'),
    O('O'),
    P('P'),
    Q('Q'),
    R('R'),
    S('S'),
    T('T'),
    U('U'),
    V('V'),
    W('W'),
    X('X'),
    Y('Y'),
    Z('Z'),
    two('2'),
    three('3'),
    four('4'),
    five('5'),
    six('6'),
    seven('7');

    public static final HashSet<Integer>SUPPORTED_BASE =new HashSet<>(Arrays.asList(2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16));

    private char representation;

    Base32Table(char representation) {
        this.representation = representation;
    }

    @Override
    public char getCharRepresentation() {
        return this.representation;
    }

    @Override
    public HashSet<Integer> getBase() {
        return SUPPORTED_BASE;
    }

    @Override
    public int getOrdVal() {
        return this.ordinal();
    }

    @Override
    public CharacterSet getElem(int ord) {
        return values()[ord];
    }


    }
