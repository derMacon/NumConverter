package logic;

public enum Alphabet {
    zero('0'),
    one('1'),
    two('2'),
    three('3'),
    four('4'),
    five('5'),
    six('6'),
    seven('7'),
    eight('8'),
    nine('9'),
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
    a('a'),
    b('b'),
    c('c'),
    d('d'),
    e('e'),
    f('f'),
    g('g'),
    h('h'),
    i('i'),
    j('j'),
    k('k'),
    l('l'),
    m('m'),
    n('n'),
    o('o'),
    p('p'),
    q('q'),
    r('r'),
    s('s'),
    t('t'),
    u('u'),
    v('v'),
    w('w'),
    x('x'),
    y('y'),
    z('z');

    private char representation;

    Alphabet(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }

    public static Alphabet lookup(char c) throws InvalidNumException {
        for(Alphabet curr : values()) {
            if(curr.representation == c) {
                return curr;
            }
        }
        throw new InvalidNumException("No such element");
    }

    @Override
    public String toString() {
        return String.valueOf(this.representation);
    }
}
