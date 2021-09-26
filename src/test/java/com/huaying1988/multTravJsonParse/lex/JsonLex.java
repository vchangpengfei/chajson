package com.huaying1988.multTravJsonParse.lex;

import java.util.Stack;


/**
 * Json�ʷ�������
 * 
 * @author huaying1988.com
 * 
 */
public class JsonLex {
	// ��ǰ�к�
	private int lineNum = 0;
	// ���ڼ�¼ÿһ�е���ʼλ��
	private Stack<Integer> colMarks = new Stack<Integer>();
	// ���ڱ�������α�
	private int startLine = 0;
	// ���ڱ�������α�
	private int startCol = 0;
	// ��ǰ�ַ��α�
	private int cur = -1;
	// ���浱ǰҪ�������ַ���
	private String str = null;
	// ���浱ǰҪ�������ַ����ĳ���
	private int len = 0;

	/**
	 * JsonLex���캯��
	 * 
	 * @param str
	 *            Ҫ�������ַ���
	 */
	public JsonLex(String str) {
		if (str == null)
			throw new NullPointerException("�ʷ��������캯�����ܴ���null");
		this.str = str;
		this.len = str.length();
		this.startLine = 0;
		this.startCol = 0;
		this.cur = -1;
		this.lineNum = 0;
		this.colMarks.push(0);
	}

	public char getCurChar(){
		if (cur >= len - 1) {
			return 0;
		}else{
			return str.charAt(cur);
		}
	}
	public Token parseSymbol(char c) {
		switch (c) {
		case '[':
			return Token.ARRS;
		case ']':
			return Token.ARRE;
		case '{':
			return Token.OBJS;
		case '}':
			return Token.OBJE;
		case ',':
			return Token.SPLIT;
		case ':':
			return Token.DESC;
		}
		return null;
	}

	public boolean isLetterUnderline(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_');
	}

	public boolean isNumLetterUnderline(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
				|| (c >= '0' && c <= '9') || c == '_');
	}

	public boolean isNum(char c) {
		return (c >= '0' && c <= '9');
	}

	public boolean isDecimal(char c) {
		return ((c >= '0' && c <= '9') || (c == '.'));
	}

	private void checkEnd() {
		if (cur >= len - 1) {
			throw generateUnexpectedException("δԤ�ڵĽ������ַ���δ����");
		}
	}

	public UnexpectedException generateUnexpectedException(String str) {
		return new UnexpectedException(cur,startLine, startCol, str);
	}

	public UnexpectedException generateUnexpectedException(String str,
			Throwable e) {
		return new UnexpectedException(cur,startLine, startCol, str, e);
	}

	private String getStrValue(char s) {
		int start = cur;
		char c;
		while ((c = nextChar()) != 0) {
			if (c == '\\') {// ����б���Լ�������ַ�
				c = nextChar();
			} else if (s == c) {
				return str.substring(start + 1, cur);
			}
		}
		checkEnd();
		return null;
	}

	private String getNumValue() {
		int start = cur;
		char c;
		while ((c = nextChar()) != 0) {
			if (!isDecimal(c)) {
				return str.substring(start, revertChar());
			}
		}
		checkEnd();
		return null;
	}

	private Token getDefToken() {
		int start = cur;
		char c;
		while ((c = nextChar()) != 0) {
			if (!isNumLetterUnderline(c)) {
				String value = str.substring(start, revertChar());
				if ("true".equals(value)) {
					return Token.TRUE;
				} else if ("false".equals(value)) {
					return Token.FALSE;
				} else if ("null".equals(value)) {
					return Token.NIL;
				} else {
					return new Token(TOK.STR, value);
				}
			}
		}
		checkEnd();
		return null;
	}

	/**
	 * ��ȡ��һ���ֽڣ�ͬʱ���� �С��� ����
	 * 
	 * @return ��һ���ֽڣ�����ʱ����0
	 */
	private char nextChar() {
		if (cur >= len-1) {
			return 0;
		}
		++cur;
		char c = str.charAt(cur);
		if (c == '\n') {
			++lineNum;
			colMarks.push(cur);
		}
		return c;
	}
	/**
	 * ����һ���ֽڣ�ͬʱ���� �С��� ���������س���ǰ���ַ��α�
	 * 
	 * @return ��һ���ֽڣ�����ʱ����0
	 */
	private int revertChar() {
		if (cur <= 0) {
			return 0;
		}
		int rcur = cur--;
		char c = str.charAt(rcur);
		if (c == '\n') {
			--lineNum;
			colMarks.pop();
		}
		return rcur;
	}

	public static boolean isSpace(char c) {
		return (c == ' ' || c == '\t' || c == '\n');
	}

	// str \"(\\\"|[^\"])*\"
	// def [_a-zA-Z][_a-zA-Z0-9]*
	// num -?[0-9]+(\.[0-9]+)?
	// space [ \t\n]+
	/**
	 * ��ȡ��һ��Token��������
	 */
	public Token next() {
		if (lineNum == 0) {
			lineNum = 1;
			return Token.BGN;
		}
		char c;
		while ((c = nextChar()) != 0) {
			startLine = lineNum;
			startCol = getColNum();
			if (c == '"' || c == '\'') {
				return new Token(TOK.STR, getStrValue(c));
			} else if (isLetterUnderline(c)) {
				return getDefToken();
			} else if (isNum(c) || c=='-') {
				return new Token(TOK.NUM, getNumValue());
			} else if (isSpace(c)) {
				continue;
			} else {
				return parseSymbol(c);
			}
		}
		if (c == 0) {
			return Token.EOF;
		}
		return null;
	}

	public int getLineNum() {
		return lineNum;
	}

	public int getColNum() {
		return cur - colMarks.peek();
	}

	public int getCur() {
		return cur;
	}

	public String getStr() {
		return str;
	}

	public int getLen() {
		return len;
	}
}
