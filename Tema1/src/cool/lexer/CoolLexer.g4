lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;
    import java.util.regex.Pattern;
}

@members{
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }

    private void processString() {
        String parsedString = getText();
        if (parsedString.length() > 1024) {
            raiseError("String constant too long");
            return;
        }

        if (parsedString.contains("\u0000")) {
            raiseError("String contains null character");
            return;
        }

        StringBuilder trimmed = new StringBuilder(parsedString);
                trimmed.deleteCharAt(0);
                trimmed.deleteCharAt(trimmed.length() - 1);
                StringBuilder finalResultBuilder = new StringBuilder();

                for (int i = 0; i < trimmed.length(); i++) {
                    char currentChar = trimmed.charAt(i);

                    if (currentChar == '\\' && i + 1 < trimmed.length()) {
                        char nextChar = trimmed.charAt(i + 1);

                        switch (nextChar) {
                            case 'n':
                                finalResultBuilder.append('\n');
                                break;
                            case 't':
                                finalResultBuilder.append('\t');
                                break;
                            case 'b':
                                finalResultBuilder.append('\b');
                                break;
                            case 'f':
                                finalResultBuilder.append('\f');
                                break;
                            case 'r':
                                finalResultBuilder.append('\r');
                                break;
                            default:
                                finalResultBuilder.append(nextChar);
                        }

                        i++; // Skip the next character
                    } else {
                        finalResultBuilder.append(currentChar);
                    }
                }
        setText(finalResultBuilder.toString());
    }
}

BOOL : 'true' | 'false';

IF: 'if';

THEN: 'then';

ELSE: 'else';

FI: 'fi';

CLASS: 'class';

INHERITS: 'inherits';

IN: 'in';

ISVOID: 'isvoid';

LET: 'let';

LOOP: 'loop';

POOL: 'pool';

WHILE: 'while';

CASE: 'case';

ESAC: 'esac';

NEW: 'new';

OF: 'of';

NOT: 'not';

fragment SELF: 'self';

fragment SELF_TYPE: 'SELF_TYPE';

TYPE : ([A-Z] (LETTER | '_' | DIGIT)*);

fragment LETTER : [a-zA-Z];
ID : ((LETTER | '_')(LETTER | '_' | DIGIT)* | SELF | SELF_TYPE);

fragment DIGIT : [0-9];
INT : DIGIT+;

fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;

STRING: '"'
        ('\\"' | '\\' (' ')* NEW_LINE | . )*?
        ('"' { processString(); } | ~'\\' (' ')* NEW_LINE { raiseError("Unterminated string constant"); } | EOF { raiseError("EOF in string constant"); });

COMPLEMENT: '~';

DOT: '.';

AT: '@';

COLON : ':';

SEMICOLON : ';';

COMMA : ',';

ASSIGN : '<-';

LPAREN : '(';

RPAREN : ')';

LBRACE : '{';

RBRACE : '}';

PLUS : '+';

MINUS : '-';

MULT : '*';

DIV : '/';

EQUAL : '=';

LT : '<';

LE : '<=';

CASE_BRANCH: '=>';

fragment NEW_LINE : '\r'?'\n';

LINE_COMMENT: '--' .*? (NEW_LINE | EOF) -> skip;

UNMATCHED_BLOCK_COMMENT: ('*)' | BLOCK_COMMENT '*)') { raiseError("Unmatched *)"); };

BLOCK_COMMENT: '(*' (BLOCK_COMMENT | .)*? ('*)' { skip(); } | EOF { raiseError("EOF in comment"); });

WS : [ \n\f\r\t]+ -> skip;

INVALID_CHARACTER: . { raiseError("Invalid character: " + getText()); };