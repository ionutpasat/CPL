lexer grammar CPLangLexer;

/* Reguli de funcționare:
 *
 * * Se ia în considerare cel mai lung lexem recunoscut, indiferent de ordinea
 *   regulilor din specificație (maximal munch).
 *
 * * Dacă există mai multe cele mai lungi lexeme, se ia în considerare prima
 *   regulă din specificație.
 */

/* Cuvânt cheie.
 */
IF : 'if';

SEMICOLON : ';';

ASSIGN : '=';

EQUALITY : '==';

PLUS : '+';

MINUS : '-';

MULTIPLICATION : '*';

THEN: 'then';

ELSE : 'else';

FI : 'fi';

fragment INT_ID : 'Int';
fragment FLOAT_ID : 'Float';
fragment BOOL_ID : 'Bool';

//fragment TRUE : 'true';
//fragment FALSE : 'false';
//
//BOOL: TRUE | FALSE;

FALSE : 'false';
TRUE : 'true';

LOWER : '<';

LOWER_OR_EQUAL : '<=';
PHARANTESIS1 : '(';
PHARANTESIS2 : ')';
PHARANTESIS3 : '{';
PHARANTESIS4 : '}';

COMMA : ',';


fragment INT_TYPE : 'Int';
fragment FLOAT_TYPE : 'Float';
fragment BOOL_TYPE : 'Bool';
COMMENT : '//'.*?'\n' -> skip;

TYPE: INT_TYPE | FLOAT_TYPE | BOOL_TYPE;



/* Număr întreg.
 *
 * fragment spune că acea categorie este utilizată doar în interiorul
 * analizorului lexical, nefiind trimisă mai departe analizorului sintactic.
 */
fragment DIGIT : [0-9];
INT : DIGIT+;

/* Identificator.
 */
fragment LETTER: [a-zA-Z];
ID : (LETTER)(LETTER | '_')* | INT_ID | FLOAT_ID | BOOL_ID;

/* Număr real.
 */
fragment DIGITS : DIGIT+;
fragment FRACTION : ('.' DIGITS?)?;
fragment EXPONENT : ('e' ('+' | '-')? DIGITS)?;
REAL : (DIGITS FRACTION EXPONENT) | '.'DIGITS;

/* Șir de caractere.
 *
 * Poate conține caracterul '"', doar precedat de backslash.
 * . reprezintă orice caracter în afară de EOF.
 * *? este operatorul non-greedy, care încarcă să consume caractere cât timp
 * nu a fost întâlnit caracterul ulterior, '"'.
 *
 * Acoladele de la final pot conține secvențe arbitrare de cod Java,
 * care vor fi executate la întâlnirea acestui token.
 */
STRING : '"' ('\\"' | .)*? '"'
    { System.out.println("there are no strings in CPLang, but shhh.."); };

BLOCK_COMMENT : '/*' (BLOCK_COMMENT | .)*? ('*/' | EOF {System.out.println("Error in comment");}) -> skip;

/* Spații albe.
 *
 * skip spune că nu este creat niciun token pentru lexemul depistat.
 */
WS : [ \n\r\t]+ -> skip;

/* Modalitate alternativă de recunoaștere a șirurilor de caractere, folosind
 * moduri lexicale.
 *
 * Un mod lexical, precum cel implicit (DEFAULT_MODE) sau IN_STR, de mai jos,
 * reprezintă stări ale analizorului. Când analizorul se află într-un anumit
 * mod, numai regulile din acel mod se pot activa.
 *
 * Folosim pushMode și popMode pentru intra și ieși din modurile lexicale,
 * în regim de stivă.
 *
 * more spune că deocamdată nu este construit un token, dar lexemul identificat
 * va face parte, cumulativ, din lexemul recunoscut de următoarea regulă.
 *
 * De-abia la recunoașterea caracterului '"' de sfârșit de șir de către regula
 * STR, se va construi un token cu categoria STR și întregul conținut al șirului
 * drept lexem.
 */
/*
STR_OPEN : '"' -> pushMode(IN_STR), more;

mode IN_STR;

STR : '"' -> popMode;
CHAR : ('\\"' | ~'"') -> more;  // ~ = complement
*/
