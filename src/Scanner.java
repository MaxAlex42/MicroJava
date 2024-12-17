import java.io.*;

public class Scanner {
    static Reader in;
    static char ch;
    static int line, col;
    static final char eofCh = (char) -1;

    public static void init(Reader r) {
        in = r;
        line = 1;
        col = 0;
        nextCh();
    }

    public static Token next() {
        while (ch <= ' ') nextCh();
        Token t = new Token();
        t.line = line;
        t.col = col;
        switch (ch) {
            case "[A-Z]": case "[a-z]": readName(t); break;
            case "[0-9]": readNumber(t); break;
            case '\\': readCharCon(t); break;
            case ';': nextCh(); t.kind = Token.semicolon; break;
            case '.': nextCh(); t.kind = Token.period; break;
            case eofCh: t.kind = Token.eof; break;
            case '=': nextCh(); if (ch == '=') { nextCh(); t.kind = Token.eql; } else { t.kind = Token.assign; } break;
            case '&': nextCh(); if (ch == '&') { nextCh(); t.kind = Token.and; } else { t.kind = Token.none; } break;

            default: nextCh(); t.kind = Token.none; break;
        }
        return t;
    }

    private static void nextCh() {
        try {
            ch = (char) in.read();
            col++;
            if (ch == '\n') {
                line++;
                col = 0;
            }
        } catch (IOException e) {
            ch = eofCh;
        }
    }

    private static void readName (Token t) {
    }

    private static void readNumber(Token t) {
    }

    private static void readCharCon (Token t) {
    }


}


