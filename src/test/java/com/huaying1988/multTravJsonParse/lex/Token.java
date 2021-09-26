package com.huaying1988.multTravJsonParse.lex;

/**
 * ����token
 * 
 * @author huaying1988.com
 * 
 */
public class Token {
	public static final Token DESC = new Token(TOK.DESC);
	public static final Token SPLIT = new Token(TOK.SPLIT);
	public static final Token ARRS = new Token(TOK.ARRS);
	public static final Token OBJS = new Token(TOK.OBJS);
	public static final Token ARRE = new Token(TOK.ARRE);
	public static final Token OBJE = new Token(TOK.OBJE);
	public static final Token FALSE = new Token(TOK.FALSE);
	public static final Token TRUE = new Token(TOK.TRUE);
	public static final Token NIL = new Token(TOK.NIL);
	public static final Token BGN = new Token(TOK.BGN);
	public static final Token EOF = new Token(TOK.EOF);

	// ��TOK���ж��������
	private Integer type;
	// ��tok��ֵ
	private String value;

	public Token(int type) {
		this.type = type;
		this.value = null;
	}

	public Token(int type, String value) {
		this.type = type;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String unescape(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '\\') {
				c = str.charAt(++i);
				switch (c) {
					case '"':
						sb.append('"');
						break;
					case '\\':
						sb.append('\\');
						break;
					case '/':
						sb.append('/');
						break;
					case 'b':
						sb.append('\b');
						break;
					case 'f':
						sb.append('\f');
						break;
					case 'n':
						sb.append('\n');
						break;
					case 'r':
						sb.append('\r');
						break;
					case 't':
						sb.append('\t');
						break;
					case 'u':
						String hex = str.substring(i+1, i+5);
						sb.append((char)Integer.parseInt(hex, 16));
						i+=4;
						break;
					default:
						throw new RuntimeException("��\\�������ڴ���\"\\/bfnrtu���е��ַ�������õ���"+c+"��");
				}
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public Object getRealValue(){
		Object curValue = null;
		switch(this.getType()){
		case TOK.TRUE:
			curValue = true;
			break;
		case TOK.FALSE:
			curValue = false;
			break;
		case TOK.NIL:
			curValue = null;
			break;
		case TOK.NUM:
			if(value.indexOf('.')>=0){
				curValue = Double.parseDouble(value);
			}else{
				curValue = Integer.parseInt(value);
			}
			break;
		case TOK.STR:
			curValue = unescape(value);
			break;
		}
		return curValue;
	}

	public String toString() {
		if (this.type > 1) {
			return "[" + TOK.castTokType2Str(this.type) + "]";
		} else {
			return "[" + TOK.castTokType2Str(this.type) + ":" + this.value
					+ "]";
		}
	}
	
	public String toLocalString() {
		if (this.type > 1) {
			return "��" + TOK.castTokType2LocalStr(this.type) + "��";
		} else {
			return "��" + TOK.castTokType2LocalStr(this.type) + ":" + this.value
					+ "��";
		}
	}
}
