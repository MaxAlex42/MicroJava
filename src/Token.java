public class Token {
    public int kind;
    public int line;
    public int col;
    public String val;
    public int numVal;

    static final int
    none = 0,
    ident = 1, number = 2, charCon = 3,
    plus = 4,   assign = 17,    break_ = 29,
    minus = 5,  pplus = 18,     class_ = 30,
    times = 6,  mminus = 19,    else_ = 31,
    slash = 7,  semicolon = 20, final_ = 32,
    rem = 8,    comma = 21,     if_ = 33,
    eql = 9,    period = 22,    new_ = 34,
    neq = 10,   lpar = 23,      print_ = 35,
    lss = 11,   rpar = 24,      program = 36,
    leq = 12,   lbrack = 25,    read_ = 37,
    gtr = 13,   rbrack = 26,    return_ = 38,
    geq = 14,   lbrace = 27,    void_ = 39,
    and = 15,   rbrace = 28,    while_ = 40,
    or = 16,
    eof = 41;
}
