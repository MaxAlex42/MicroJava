package compiler;

import java.io.*;
import java.util.HashMap;

public class Scanner {
    static Reader in;
    static char ch;
    static int line, col;
    static final char EOF_CH = (char) -1;
    private static char[] word;

    public static void init(Reader r) {
        in = r;
        line = 1;
        col = 0;
        word = new char[128];
        nextCh();
    }

    public static Token next() {
        while (ch <= ' ') nextCh();
        Token t = new Token();
        t.line = line;
        t.col = col;
        if (Character.isLetter(ch)) {
            readName(t);
        } else if (Character.isDigit(ch)) {
            readNumber(t);
        } else {
            switch (ch) {
                case '\'': readCharCon(t); break;
                case ';': nextCh(); t.kind = Token.semicolon; break;
                case ',': nextCh(); t.kind = Token.comma; break;
                case '.': nextCh(); t.kind = Token.period; break;
                case '=': nextCh(); if (ch == '=') { nextCh(); t.kind = Token.eql; } else { t.kind = Token.assign; } break;
                case '&': nextCh(); if (ch == '&') { nextCh(); t.kind = Token.and; } else { t.kind = Token.none; } break;
                case '+': nextCh(); if (ch == '+') { nextCh(); t.kind = Token.pplus; } else { t.kind = Token.plus; } break;
                case '-': nextCh(); if (ch == '+') { nextCh(); t.kind = Token.mminus; } else { t.kind = Token.minus; } break;
                case '*': nextCh(); t.kind = Token.times; break;
                case '!': nextCh(); if (ch == '=') { nextCh(); t.kind = Token. neq; } else { t.kind = Token.none; } break;
                case '%': nextCh(); t.kind = Token.rem; break;
                case '<': nextCh(); if (ch == '=') { nextCh(); t.kind = Token.leq; } else { t.kind = Token.lss; } break;
                case '>': nextCh(); if (ch == '=') { nextCh(); t.kind = Token.geq; } else { t.kind = Token.gtr; } break;
                case '|': nextCh(); if (ch == '|') { nextCh(); t.kind = Token.or; } else { t.kind = Token.none; } break;
                case '/': nextCh();
                    if (ch == '/') {
                        do nextCh();
                        while (ch != '\n' && ch != EOF_CH); t = next();
                    } else {
                        t.kind = Token.slash;
                    }
                    break;
                case '(': nextCh(); t.kind = Token.lpar; break;
                case ')': nextCh(); t.kind = Token.rpar; break;
                case '[': nextCh(); t.kind = Token.lbrack; break;
                case ']': nextCh(); t.kind = Token.rbrack; break;
                case '{': nextCh(); t.kind = Token.lbrace; break;
                case '}': nextCh(); t.kind = Token.rbrace; break;

                case EOF_CH: t.kind = Token.eof; break;
                default: nextCh(); t.kind = Token.none; break;
            }
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
            ch = EOF_CH;
        }
    }

    private static void readName (Token t) {
        int i = 0;
        while (ch != '\n' && ch != EOF_CH && ch != ' ') {
            word[i++] = ch;
            nextCh();
        }
        t.val = new String(word, 0, i);
        switch (t.val) {
            case "break":    t.kind = Token.break_; break;
            case "class":    t.kind = Token.class_; break;
            case "else":     t.kind = Token.else_; break;
            case "final":    t.kind = Token.final_; break;
            case "if":       t.kind = Token.if_; break;
            case "new":      t.kind = Token.new_; break;
            case "print":    t.kind = Token.print_; break;
            case "program":  t.kind = Token.program; break;
            case "read":     t.kind = Token.read_; break;
            case "return":   t.kind = Token.return_; break;
            case "void":     t.kind = Token.void_; break;
            case "while":    t.kind = Token.while_; break;
            default:
                t.kind = Token.ident;
                break;
        }
        nextCh();
    }

    private static void readNumber(Token t) {
        int i = 0;
        while (ch != '\n' && ch != EOF_CH && ch != ' ') {
            word[i++] = ch;
            nextCh();
        }
        t.val = new String(word, 0, i);
        try {
            t.numVal = Integer.parseInt(t.val);
        } catch (NumberFormatException e) {
            t.kind = Token.number;
        }
        nextCh();
    }

    private static void readCharCon (Token t) {
        int i = 0;
        t.kind = Token.charCon;
        nextCh();
        while (ch != '\'' && ch != EOF_CH && ch != '\n') {
            word[i++] = ch;
            nextCh();
        }
        if (ch == EOF_CH || ch == '\n') {
            System.out.println("Error in line " + line + " col " + col);
        } else if (i == 1 && ch == '\\') {
            t.numVal = word[0];
        } else if (i == 2 && ch == '\\') {
            if (word[1] == 'r') t.numVal = '\r';
            else if (word[1] == 't') t.numVal = '\t';
            else if (word[1] == 'n') t.numVal = '\n';
            else System.out.println("Error in line " + line + " col " + col);
        } else {
            System.out.println("Error in line " + line + " col " + col);
        }
        if (ch == '\'')
            nextCh();
    }
}
