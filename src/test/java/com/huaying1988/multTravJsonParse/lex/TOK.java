package com.huaying1988.multTravJsonParse.lex;

/**
 * ����Token������Ϣ�Լ���ؾ�̬����
 * 
 * @author huaying1988.com
 * 
 */
public class TOK {
	public static final int STR = 0;
	public static final int NUM = 1;
	public static final int DESC = 2;
	public static final int SPLIT = 3;
	public static final int ARRS = 4;
	public static final int OBJS = 5;
	public static final int ARRE = 6;
	public static final int OBJE = 7;
	public static final int FALSE = 8;
	public static final int TRUE = 9;
	public static final int NIL = 10;
	public static final int BGN = 11;
	public static final int EOF = 12;
	/**
	 * ����tok���ͣ��洢tok���͵ĸ������������ʱ��ͬ���޸�
	 */
	public static final int TOK_NUM = 13;
	/**
	 * ��tok����ת��Ϊ�ַ�����ת�����飬�������ʱ��ͬ���޸�
	 */
	public static final String[] CAST_STRS = { "STR", "NUM", "DESC", "SPLIT",
			"ARRS", "OBJS", "ARRE", "OBJE", "FALSE", "TRUE", "NIL", "BGN", "EOF" };
	
	/**
	 * ��tok����ת��Ϊ�ַ�����ת�����飬�������ʱ��ͬ���޸�
	 */
	public static final String[] CAST_LOCAL_STRS = { "�ַ���", "����", ":", ",",
			"[", "{", "]", "}", "false", "true", "null", "��ʼ", "����" };

	/**
	 * ��tok����ת��ΪString,��������ʾ�����
	 * 
	 * @return
	 */
	public static String castTokType2Str(int type) {
		if (type < 0 || type > TOK_NUM)
			return "undefine";
		else
			return CAST_STRS[type];
	}
	
	/**
	 * ��tok����ת��ΪString,���ڱ�����Ϣ
	 * 
	 * @return
	 */
	public static String castTokType2LocalStr(int type) {
		if (type < 0 || type > TOK_NUM)
			return "undefine";
		else
			return CAST_LOCAL_STRS[type];
	}
}
