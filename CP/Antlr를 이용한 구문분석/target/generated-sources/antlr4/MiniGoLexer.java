// Generated from MiniGo.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniGoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, VAR=21, FUNC=22, FMT=23, VOID=24, 
		INT=25, FOR=26, IF=27, ELSE=28, RETURN=29, OR=30, AND=31, LE=32, GE=33, 
		EQ=34, NE=35, IDENT=36, LITERAL=37, DecimalConstant=38, OctalConstant=39, 
		HexadecimalConstant=40, WS=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'"
	};
	public static final String[] ruleNames = {
		"T__19", "T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", 
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "VAR", "FUNC", "FMT", "VOID", "INT", "FOR", "IF", 
		"ELSE", "RETURN", "OR", "AND", "LE", "GE", "EQ", "NE", "IDENT", "LITERAL", 
		"DecimalConstant", "OctalConstant", "HexadecimalConstant", "WS"
	};


	public MiniGoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniGo.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 20: VAR_action((RuleContext)_localctx, actionIndex); break;
		case 21: FUNC_action((RuleContext)_localctx, actionIndex); break;
		case 22: FMT_action((RuleContext)_localctx, actionIndex); break;
		case 23: VOID_action((RuleContext)_localctx, actionIndex); break;
		case 24: INT_action((RuleContext)_localctx, actionIndex); break;
		case 25: FOR_action((RuleContext)_localctx, actionIndex); break;
		case 26: IF_action((RuleContext)_localctx, actionIndex); break;
		case 27: ELSE_action((RuleContext)_localctx, actionIndex); break;
		case 28: RETURN_action((RuleContext)_localctx, actionIndex); break;
		case 29: OR_action((RuleContext)_localctx, actionIndex); break;
		case 30: AND_action((RuleContext)_localctx, actionIndex); break;
		case 31: LE_action((RuleContext)_localctx, actionIndex); break;
		case 32: GE_action((RuleContext)_localctx, actionIndex); break;
		case 33: EQ_action((RuleContext)_localctx, actionIndex); break;
		case 34: NE_action((RuleContext)_localctx, actionIndex); break;
		case 35: IDENT_action((RuleContext)_localctx, actionIndex); break;
		case 36: LITERAL_action((RuleContext)_localctx, actionIndex); break;
		case 37: DecimalConstant_action((RuleContext)_localctx, actionIndex); break;
		case 38: OctalConstant_action((RuleContext)_localctx, actionIndex); break;
		case 39: HexadecimalConstant_action((RuleContext)_localctx, actionIndex); break;
		case 40: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void OR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9: System.out.println("201402415 Rule 27");  break;
		}
	}
	private void LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 18: System.out.println("201402415 Rule 34-0");  break;
		case 19: System.out.println("201402415 Rule 34-1");  break;
		case 20: System.out.println("201402415 Rule 34-2");  break;
		}
	}
	private void FUNC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: System.out.println("201402415 Rule 19");  break;
		}
	}
	private void VAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: System.out.println("201402415 Rule 18");  break;
		}
	}
	private void FOR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: System.out.println("201402415 Rule 23");  break;
		}
	}
	private void FMT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: System.out.println("201402415 Rule 20");  break;
		}
	}
	private void EQ_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 13: System.out.println("201402415 Rule 31");  break;
		}
	}
	private void INT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: System.out.println("201402415 Rule 22");  break;
		}
	}
	private void RETURN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: System.out.println("201402415 Rule 26");  break;
		}
	}
	private void DecimalConstant_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 21: System.out.println("201402415 Rule 35-0");  break;
		case 22: System.out.println("201402415 Rule 35-1");  break;
		}
	}
	private void IDENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 16: System.out.println("201402415 Rule 33-1");  break;
		case 17: System.out.println("201402415 Rule 33-2");  break;
		case 15: System.out.println("201402415 Rule 33-0");  break;
		}
	}
	private void AND_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10: System.out.println("201402415 Rule 28");  break;
		}
	}
	private void NE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 14: System.out.println("201402415 Rule 32");  break;
		}
	}
	private void OctalConstant_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 23: System.out.println("201402415 Rule 36");  break;
		}
	}
	private void ELSE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: System.out.println("201402415 Rule 25");  break;
		}
	}
	private void LE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11: System.out.println("201402415 Rule 29");  break;
		}
	}
	private void VOID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: System.out.println("201402415 Rule 21");  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 25: System.out.println("201402415 Rule 38-0");  break;
		case 26: System.out.println("201402415 Rule 38-1");  break;
		case 27: System.out.println("201402415 Rule 38-2");  break;
		case 28: System.out.println("201402415 Rule 38-3");  break;
		}
	}
	private void IF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: System.out.println("201402415 Rule 24");  break;
		}
	}
	private void HexadecimalConstant_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 24: System.out.println("201402415 Rule 37");  break;
		}
	}
	private void GE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12: System.out.println("201402415 Rule 30");  break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u011b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3"+
		"\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\7%\u00e0\n%\f%\16%\u00e3\13%"+
		"\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u00ee\n&\3\'\3\'\3\'\3\'\7\'\u00f4\n\'"+
		"\f\'\16\'\u00f7\13\'\3\'\5\'\u00fa\n\'\3(\3(\7(\u00fe\n(\f(\16(\u0101"+
		"\13(\3(\3(\3)\3)\3)\6)\u0108\n)\r)\16)\u0109\3)\3)\3*\3*\3*\3*\3*\3*\3"+
		"*\3*\6*\u0116\n*\r*\16*\u0117\3*\3*\2\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2"+
		"\b\5\2C\\aac|\3\2\62;\3\2\63;\3\2\629\4\2ZZzz\5\2\62;CHch\u0126\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3\2"+
		"\2\2\5W\3\2\2\2\7Y\3\2\2\2\t[\3\2\2\2\13]\3\2\2\2\r_\3\2\2\2\17b\3\2\2"+
		"\2\21e\3\2\2\2\23g\3\2\2\2\25i\3\2\2\2\27k\3\2\2\2\31m\3\2\2\2\33o\3\2"+
		"\2\2\35q\3\2\2\2\37s\3\2\2\2!u\3\2\2\2#w\3\2\2\2%y\3\2\2\2\'{\3\2\2\2"+
		")}\3\2\2\2+\177\3\2\2\2-\u0085\3\2\2\2/\u008c\3\2\2\2\61\u0092\3\2\2\2"+
		"\63\u0099\3\2\2\2\65\u009f\3\2\2\2\67\u00a5\3\2\2\29\u00aa\3\2\2\2;\u00b1"+
		"\3\2\2\2=\u00ba\3\2\2\2?\u00bf\3\2\2\2A\u00c5\3\2\2\2C\u00ca\3\2\2\2E"+
		"\u00cf\3\2\2\2G\u00d4\3\2\2\2I\u00d9\3\2\2\2K\u00ed\3\2\2\2M\u00f9\3\2"+
		"\2\2O\u00fb\3\2\2\2Q\u0104\3\2\2\2S\u0115\3\2\2\2UV\7\61\2\2V\4\3\2\2"+
		"\2WX\7=\2\2X\6\3\2\2\2YZ\7}\2\2Z\b\3\2\2\2[\\\7]\2\2\\\n\3\2\2\2]^\7>"+
		"\2\2^\f\3\2\2\2_`\7/\2\2`a\7/\2\2a\16\3\2\2\2bc\7-\2\2cd\7-\2\2d\20\3"+
		"\2\2\2ef\7\177\2\2f\22\3\2\2\2gh\7_\2\2h\24\3\2\2\2ij\7?\2\2j\26\3\2\2"+
		"\2kl\7@\2\2l\30\3\2\2\2mn\7#\2\2n\32\3\2\2\2op\7\'\2\2p\34\3\2\2\2qr\7"+
		"*\2\2r\36\3\2\2\2st\7+\2\2t \3\2\2\2uv\7,\2\2v\"\3\2\2\2wx\7-\2\2x$\3"+
		"\2\2\2yz\7.\2\2z&\3\2\2\2{|\7/\2\2|(\3\2\2\2}~\7\60\2\2~*\3\2\2\2\177"+
		"\u0080\7x\2\2\u0080\u0081\7c\2\2\u0081\u0082\7t\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\u0084\b\26\2\2\u0084,\3\2\2\2\u0085\u0086\7h\2\2\u0086\u0087"+
		"\7w\2\2\u0087\u0088\7p\2\2\u0088\u0089\7e\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008b\b\27\3\2\u008b.\3\2\2\2\u008c\u008d\7h\2\2\u008d\u008e\7o\2\2\u008e"+
		"\u008f\7v\2\2\u008f\u0090\3\2\2\2\u0090\u0091\b\30\4\2\u0091\60\3\2\2"+
		"\2\u0092\u0093\7x\2\2\u0093\u0094\7q\2\2\u0094\u0095\7k\2\2\u0095\u0096"+
		"\7f\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\31\5\2\u0098\62\3\2\2\2\u0099"+
		"\u009a\7k\2\2\u009a\u009b\7p\2\2\u009b\u009c\7v\2\2\u009c\u009d\3\2\2"+
		"\2\u009d\u009e\b\32\6\2\u009e\64\3\2\2\2\u009f\u00a0\7h\2\2\u00a0\u00a1"+
		"\7q\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\33\7\2\u00a4"+
		"\66\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7h\2\2\u00a7\u00a8\3\2\2\2"+
		"\u00a8\u00a9\b\34\b\2\u00a98\3\2\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7"+
		"n\2\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00b0\b\35\t\2\u00b0:\3\2\2\2\u00b1\u00b2\7t\2\2\u00b2\u00b3\7g\2\2\u00b3"+
		"\u00b4\7v\2\2\u00b4\u00b5\7w\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7p\2\2"+
		"\u00b7\u00b8\3\2\2\2\u00b8\u00b9\b\36\n\2\u00b9<\3\2\2\2\u00ba\u00bb\7"+
		"q\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\b\37\13\2\u00be"+
		">\3\2\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7p\2\2\u00c1\u00c2\7f\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c4\b \f\2\u00c4@\3\2\2\2\u00c5\u00c6\7>\2\2\u00c6"+
		"\u00c7\7?\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b!\r\2\u00c9B\3\2\2\2\u00ca"+
		"\u00cb\7@\2\2\u00cb\u00cc\7?\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\b\"\16"+
		"\2\u00ceD\3\2\2\2\u00cf\u00d0\7?\2\2\u00d0\u00d1\7?\2\2\u00d1\u00d2\3"+
		"\2\2\2\u00d2\u00d3\b#\17\2\u00d3F\3\2\2\2\u00d4\u00d5\7#\2\2\u00d5\u00d6"+
		"\7?\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\b$\20\2\u00d8H\3\2\2\2\u00d9\u00da"+
		"\t\2\2\2\u00da\u00e1\b%\21\2\u00db\u00dc\t\2\2\2\u00dc\u00e0\b%\22\2\u00dd"+
		"\u00de\t\3\2\2\u00de\u00e0\b%\23\2\u00df\u00db\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"J\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\5M\'\2\u00e5\u00e6\b&\24\2\u00e6"+
		"\u00ee\3\2\2\2\u00e7\u00e8\5O(\2\u00e8\u00e9\b&\25\2\u00e9\u00ee\3\2\2"+
		"\2\u00ea\u00eb\5Q)\2\u00eb\u00ec\b&\26\2\u00ec\u00ee\3\2\2\2\u00ed\u00e4"+
		"\3\2\2\2\u00ed\u00e7\3\2\2\2\u00ed\u00ea\3\2\2\2\u00eeL\3\2\2\2\u00ef"+
		"\u00f0\7\62\2\2\u00f0\u00fa\b\'\27\2\u00f1\u00f5\t\4\2\2\u00f2\u00f4\t"+
		"\3\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fa\b\'"+
		"\30\2\u00f9\u00ef\3\2\2\2\u00f9\u00f1\3\2\2\2\u00faN\3\2\2\2\u00fb\u00ff"+
		"\7\62\2\2\u00fc\u00fe\t\5\2\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2"+
		"\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0102\u0103\b(\31\2\u0103P\3\2\2\2\u0104\u0105\7\62\2\2\u0105"+
		"\u0107\t\6\2\2\u0106\u0108\t\7\2\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2"+
		"\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010c\b)\32\2\u010cR\3\2\2\2\u010d\u010e\7\"\2\2\u010e\u0116\b*\33\2"+
		"\u010f\u0110\7\13\2\2\u0110\u0116\b*\34\2\u0111\u0112\7\17\2\2\u0112\u0116"+
		"\b*\35\2\u0113\u0114\7\f\2\2\u0114\u0116\b*\36\2\u0115\u010d\3\2\2\2\u0115"+
		"\u010f\3\2\2\2\u0115\u0111\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119"+
		"\u011a\b*\37\2\u011aT\3\2\2\2\f\2\u00df\u00e1\u00ed\u00f5\u00f9\u00ff"+
		"\u0109\u0115\u0117 \3\26\2\3\27\3\3\30\4\3\31\5\3\32\6\3\33\7\3\34\b\3"+
		"\35\t\3\36\n\3\37\13\3 \f\3!\r\3\"\16\3#\17\3$\20\3%\21\3%\22\3%\23\3"+
		"&\24\3&\25\3&\26\3\'\27\3\'\30\3(\31\3)\32\3*\33\3*\34\3*\35\3*\36\2\3"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}