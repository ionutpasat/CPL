// Generated from D:/UPB/CPL/Idea/AST-skel\CPLangLexer.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, FI=4, BOOL=5, TYPE=6, ID=7, INT=8, FLOAT=9, STRING=10, 
		SEMI=11, COMMA=12, ASSIGN=13, LPAREN=14, RPAREN=15, LBRACE=16, RBRACE=17, 
		PLUS=18, MINUS=19, MULT=20, DIV=21, EQUAL=22, LT=23, LE=24, LINE_COMMENT=25, 
		BLOCK_COMMENT=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "BOOL", "TYPE", "LETTER", "ID", "DIGIT", 
			"INT", "DIGITS", "EXPONENT", "FLOAT", "STRING", "SEMI", "COMMA", "ASSIGN", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "MULT", "DIV", 
			"EQUAL", "LT", "LE", "NEW_LINE", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'then'", "'else'", "'fi'", null, null, null, null, null, 
			null, "';'", "','", "'='", "'('", "')'", "'{'", "'}'", "'+'", "'-'", 
			"'*'", "'/'", "'=='", "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "THEN", "ELSE", "FI", "BOOL", "TYPE", "ID", "INT", "FLOAT", 
			"STRING", "SEMI", "COMMA", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"PLUS", "MINUS", "MULT", "DIV", "EQUAL", "LT", "LE", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CPLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CPLangLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 13:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 30:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("there are no strings in CPLang, but shhh.."); 
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 System.err.println("EOF in comment"); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000\u001b\u00ef\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0002\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"[\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005i\b\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0003\u0007o\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007t\b\u0007\n\u0007\f\u0007w\t\u0007\u0001\b\u0001\b\u0001\t"+
		"\u0004\t|\b\t\u000b\t\f\t}\u0001\n\u0004\n\u0081\b\n\u000b\n\f\n\u0082"+
		"\u0001\u000b\u0001\u000b\u0003\u000b\u0087\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u008e\b\f\u0003\f\u0090\b\f\u0001\f\u0001"+
		"\f\u0003\f\u0094\b\f\u0001\f\u0003\f\u0097\b\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0005\r\u009d\b\r\n\r\f\r\u00a0\t\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001c\u0003\u001c\u00c4\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u00cc\b\u001d\n\u001d\f\u001d"+
		"\u00cf\t\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u00d3\b\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0005\u001e\u00dc\b\u001e\n\u001e\f\u001e\u00df\t\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u00e5\b\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0004\u001f\u00ea\b\u001f\u000b\u001f\f\u001f"+
		"\u00eb\u0001\u001f\u0001\u001f\u0003\u009e\u00cd\u00dd\u0000 \u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0000\u000f"+
		"\u0007\u0011\u0000\u0013\b\u0015\u0000\u0017\u0000\u0019\t\u001b\n\u001d"+
		"\u000b\u001f\f!\r#\u000e%\u000f\'\u0010)\u0011+\u0012-\u0013/\u00141\u0015"+
		"3\u00165\u00177\u00189\u0000;\u0019=\u001a?\u001b\u0001\u0000\u0004\u0002"+
		"\u0000AZaz\u0001\u000009\u0002\u0000++--\u0003\u0000\t\n\r\r  \u0100\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?"+
		"\u0001\u0000\u0000\u0000\u0001A\u0001\u0000\u0000\u0000\u0003D\u0001\u0000"+
		"\u0000\u0000\u0005I\u0001\u0000\u0000\u0000\u0007N\u0001\u0000\u0000\u0000"+
		"\tZ\u0001\u0000\u0000\u0000\u000bh\u0001\u0000\u0000\u0000\rj\u0001\u0000"+
		"\u0000\u0000\u000fn\u0001\u0000\u0000\u0000\u0011x\u0001\u0000\u0000\u0000"+
		"\u0013{\u0001\u0000\u0000\u0000\u0015\u0080\u0001\u0000\u0000\u0000\u0017"+
		"\u0084\u0001\u0000\u0000\u0000\u0019\u0093\u0001\u0000\u0000\u0000\u001b"+
		"\u0098\u0001\u0000\u0000\u0000\u001d\u00a4\u0001\u0000\u0000\u0000\u001f"+
		"\u00a6\u0001\u0000\u0000\u0000!\u00a8\u0001\u0000\u0000\u0000#\u00aa\u0001"+
		"\u0000\u0000\u0000%\u00ac\u0001\u0000\u0000\u0000\'\u00ae\u0001\u0000"+
		"\u0000\u0000)\u00b0\u0001\u0000\u0000\u0000+\u00b2\u0001\u0000\u0000\u0000"+
		"-\u00b4\u0001\u0000\u0000\u0000/\u00b6\u0001\u0000\u0000\u00001\u00b8"+
		"\u0001\u0000\u0000\u00003\u00ba\u0001\u0000\u0000\u00005\u00bd\u0001\u0000"+
		"\u0000\u00007\u00bf\u0001\u0000\u0000\u00009\u00c3\u0001\u0000\u0000\u0000"+
		";\u00c7\u0001\u0000\u0000\u0000=\u00d6\u0001\u0000\u0000\u0000?\u00e9"+
		"\u0001\u0000\u0000\u0000AB\u0005i\u0000\u0000BC\u0005f\u0000\u0000C\u0002"+
		"\u0001\u0000\u0000\u0000DE\u0005t\u0000\u0000EF\u0005h\u0000\u0000FG\u0005"+
		"e\u0000\u0000GH\u0005n\u0000\u0000H\u0004\u0001\u0000\u0000\u0000IJ\u0005"+
		"e\u0000\u0000JK\u0005l\u0000\u0000KL\u0005s\u0000\u0000LM\u0005e\u0000"+
		"\u0000M\u0006\u0001\u0000\u0000\u0000NO\u0005f\u0000\u0000OP\u0005i\u0000"+
		"\u0000P\b\u0001\u0000\u0000\u0000QR\u0005t\u0000\u0000RS\u0005r\u0000"+
		"\u0000ST\u0005u\u0000\u0000T[\u0005e\u0000\u0000UV\u0005f\u0000\u0000"+
		"VW\u0005a\u0000\u0000WX\u0005l\u0000\u0000XY\u0005s\u0000\u0000Y[\u0005"+
		"e\u0000\u0000ZQ\u0001\u0000\u0000\u0000ZU\u0001\u0000\u0000\u0000[\n\u0001"+
		"\u0000\u0000\u0000\\]\u0005I\u0000\u0000]^\u0005n\u0000\u0000^i\u0005"+
		"t\u0000\u0000_`\u0005F\u0000\u0000`a\u0005l\u0000\u0000ab\u0005o\u0000"+
		"\u0000bc\u0005a\u0000\u0000ci\u0005t\u0000\u0000de\u0005B\u0000\u0000"+
		"ef\u0005o\u0000\u0000fg\u0005o\u0000\u0000gi\u0005l\u0000\u0000h\\\u0001"+
		"\u0000\u0000\u0000h_\u0001\u0000\u0000\u0000hd\u0001\u0000\u0000\u0000"+
		"i\f\u0001\u0000\u0000\u0000jk\u0007\u0000\u0000\u0000k\u000e\u0001\u0000"+
		"\u0000\u0000lo\u0003\r\u0006\u0000mo\u0005_\u0000\u0000nl\u0001\u0000"+
		"\u0000\u0000nm\u0001\u0000\u0000\u0000ou\u0001\u0000\u0000\u0000pt\u0003"+
		"\r\u0006\u0000qt\u0005_\u0000\u0000rt\u0003\u0011\b\u0000sp\u0001\u0000"+
		"\u0000\u0000sq\u0001\u0000\u0000\u0000sr\u0001\u0000\u0000\u0000tw\u0001"+
		"\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"v\u0010\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000xy\u0007\u0001"+
		"\u0000\u0000y\u0012\u0001\u0000\u0000\u0000z|\u0003\u0011\b\u0000{z\u0001"+
		"\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\u0014\u0001\u0000\u0000\u0000\u007f\u0081"+
		"\u0003\u0011\b\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001"+
		"\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001"+
		"\u0000\u0000\u0000\u0083\u0016\u0001\u0000\u0000\u0000\u0084\u0086\u0005"+
		"e\u0000\u0000\u0085\u0087\u0007\u0002\u0000\u0000\u0086\u0085\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0003\u0015\n\u0000\u0089\u0018\u0001\u0000\u0000"+
		"\u0000\u008a\u008f\u0003\u0015\n\u0000\u008b\u008d\u0005.\u0000\u0000"+
		"\u008c\u008e\u0003\u0015\n\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0001\u0000\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000\u008f"+
		"\u008b\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090"+
		"\u0094\u0001\u0000\u0000\u0000\u0091\u0092\u0005.\u0000\u0000\u0092\u0094"+
		"\u0003\u0015\n\u0000\u0093\u008a\u0001\u0000\u0000\u0000\u0093\u0091\u0001"+
		"\u0000\u0000\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0097\u0003"+
		"\u0017\u000b\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u001a\u0001\u0000\u0000\u0000\u0098\u009e\u0005"+
		"\"\u0000\u0000\u0099\u009a\u0005\\\u0000\u0000\u009a\u009d\u0005\"\u0000"+
		"\u0000\u009b\u009d\t\u0000\u0000\u0000\u009c\u0099\u0001\u0000\u0000\u0000"+
		"\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000\u0000\u0000"+
		"\u009e\u009f\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000"+
		"\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0005\"\u0000\u0000\u00a2\u00a3\u0006\r\u0000\u0000\u00a3"+
		"\u001c\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005;\u0000\u0000\u00a5\u001e"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005,\u0000\u0000\u00a7 \u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u0005=\u0000\u0000\u00a9\"\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0005(\u0000\u0000\u00ab$\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ad\u0005)\u0000\u0000\u00ad&\u0001\u0000\u0000\u0000\u00ae\u00af\u0005"+
		"{\u0000\u0000\u00af(\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005}\u0000"+
		"\u0000\u00b1*\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005+\u0000\u0000\u00b3"+
		",\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005-\u0000\u0000\u00b5.\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b7\u0005*\u0000\u0000\u00b70\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0005/\u0000\u0000\u00b92\u0001\u0000\u0000\u0000\u00ba"+
		"\u00bb\u0005=\u0000\u0000\u00bb\u00bc\u0005=\u0000\u0000\u00bc4\u0001"+
		"\u0000\u0000\u0000\u00bd\u00be\u0005<\u0000\u0000\u00be6\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c0\u0005<\u0000\u0000\u00c0\u00c1\u0005=\u0000\u0000\u00c1"+
		"8\u0001\u0000\u0000\u0000\u00c2\u00c4\u0005\r\u0000\u0000\u00c3\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\n\u0000\u0000\u00c6:\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0005/\u0000\u0000\u00c8\u00c9\u0005/\u0000"+
		"\u0000\u00c9\u00cd\u0001\u0000\u0000\u0000\u00ca\u00cc\t\u0000\u0000\u0000"+
		"\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cf\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000"+
		"\u00ce\u00d2\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d3\u00039\u001c\u0000\u00d1\u00d3\u0005\u0000\u0000\u0001\u00d2"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d5\u0006\u001d\u0001\u0000\u00d5"+
		"<\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005/\u0000\u0000\u00d7\u00d8\u0005"+
		"*\u0000\u0000\u00d8\u00dd\u0001\u0000\u0000\u0000\u00d9\u00dc\u0003=\u001e"+
		"\u0000\u00da\u00dc\t\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000"+
		"\u00db\u00da\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000\u0000\u0000"+
		"\u00dd\u00de\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000"+
		"\u00de\u00e4\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e1\u0005*\u0000\u0000\u00e1\u00e5\u0005/\u0000\u0000\u00e2\u00e3"+
		"\u0005\u0000\u0000\u0001\u00e3\u00e5\u0006\u001e\u0002\u0000\u00e4\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e7\u0006\u001e\u0001\u0000\u00e7>\u0001"+
		"\u0000\u0000\u0000\u00e8\u00ea\u0007\u0003\u0000\u0000\u00e9\u00e8\u0001"+
		"\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001"+
		"\u0000\u0000\u0000\u00ed\u00ee\u0006\u001f\u0001\u0000\u00ee@\u0001\u0000"+
		"\u0000\u0000\u0016\u0000Zhnsu}\u0082\u0086\u008d\u008f\u0093\u0096\u009c"+
		"\u009e\u00c3\u00cd\u00d2\u00db\u00dd\u00e4\u00eb\u0003\u0001\r\u0000\u0006"+
		"\u0000\u0000\u0001\u001e\u0001";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}