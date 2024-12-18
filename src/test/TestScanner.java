/* MicroJava Scanner Tester
   ========================
   Place this file in a subdirectory MJ that also contains Scanner.class
   Compile with
     javac MJ\TestScanner.java
   Run with
     java test.TestScanner <inputFileName>
----------------------------------------------------------------------*/

package test;

import compiler.Scanner;
import compiler.Token;

import java.io.*;

public class TestScanner {
	private static final int  // token codes
		none      = 0,  // error token
		ident     = 1,  // identifier
		number    = 2,  // number
		charCon   = 3,  // character constant
		plus      = 4,  // +
		minus     = 5,  // -
		times     = 6,  // *
		slash     = 7,  // /
		rem       = 8,  // %
		pplus     = 9,  // ++
		mminus    = 10, // --
		eql       = 11, // ==
		neq       = 12, // !=
		lss       = 13, // <
		leq       = 14, // <=
		gtr       = 15, // >
		geq       = 16, // >=
		and       = 17, // &&
		or        = 18, // ||
		lpar      = 19, // (
		rpar      = 20, // )
		lbrack    = 21, // [
		rbrack    = 22, // ]
		lbrace    = 23, // {
		rbrace    = 24, // }
		assign    = 25, // =
		semicolon = 26, // ;
		comma     = 27, // ,
		period    = 28, // .
		break_    = 29, // ... keywords ...
		class_    = 30,
		else_     = 31,
		final_    = 32,
		if_       = 33,
		new_      = 34,
		print_    = 35,
		program_  = 36,
		read_     = 37,
		return_   = 38,
		void_     = 39,
		while_    = 40,
		eof       = 41; // end-of-file token

	private static String[] tokenName = {
		"none",
		"ident  ",
		"number ",
		"char   ",
		"+",
		"-",
		"*",
		"/",
		"%",
		"++",
		"--",
		"==",
		"!=",
		"<",
		"<=",
		">",
		">=",
		"&&",
		"||",
		"(",
		")",
		"[",
		"]",
		"{",
		"}",
		"=",
		";",
		",",
		".",
		"break",
		"class",
		"else",
		"final",
		"if",
		"new",
		"print",
		"program",
		"read",
		"return",
		"void",
		"while",
		"eof"
	};

	public static void testScanner (String fileName) {
		Token t;
		String source = fileName;
		try {
			Scanner.init(new InputStreamReader(new FileInputStream(fileName) ));
			do {
				t = Scanner.next();
				System.out.print("line " + t.line + ", col " + t.col + ": " + tokenName[t.kind]);
				switch (t.kind) {
					case ident:   System.out.println(t.val); break;
					case number:  System.out.println(t.numVal); break;
					case charCon: System.out.println(t.numVal); break;
					default: System.out.println(); break;
				}
			} while (t.kind != eof);
		} catch (IOException e) {
			System.out.println("-- cannot open input file " + source);
		}
	}
}