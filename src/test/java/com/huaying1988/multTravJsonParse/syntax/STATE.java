package com.huaying1988.multTravJsonParse.syntax;

import java.lang.reflect.Method;

import com.huaying1988.multTravJsonParse.lex.TOK;

/**
 * ����״̬�б�״̬ת������Ⱦ�̬����
 * @author huaying1988.com
 *
 */
public class STATE {
	//��ʼ̬
	public static final Integer BGN = 0;
	//����ֵǰ̬
	public static final Integer ARRBV = 1;
	//����ֵ��̬
	public static final Integer ARRAV = 2;
	//�����ǰ̬
	public static final Integer OBJBK = 3;
	//�������̬
	public static final Integer OBJAK = 4;
	//����ֵǰ̬
	public static final Integer OBJBV = 5;
	//����ֵ��̬
	public static final Integer OBJAV = 6;
	//���̬
	public static final Integer VAL = 7;
	//����̬
	public static final Integer EOF = 8;
	//����̬
	public static final Integer ERR = 9;
	
	//״̬����״̬ת������
	public static final Integer[][] STM = {
			/*INPUT����STR NUM DESC SPLIT ARRS OBJS ARRE OBJE FALSE TRUE NIL BGN*/
			/*BGN*/  {VAL,VAL,ERR,ERR,ARRBV,OBJBK,ERR,ERR,VAL,VAL,VAL,BGN},
			/*ARRBV*/{ARRAV,ARRAV,ERR,ERR,ARRBV,OBJBK,VAL,ERR,ARRAV,ARRAV,ARRAV,ERR},
			/*ARRAV*/{ERR,ERR,ERR,ARRBV,ERR,ERR,VAL,ERR,ERR,ERR,ERR,ERR},
			/*OBJBK*/{OBJAK,OBJAK,ERR,ERR,ERR,ERR,ERR,VAL,ERR,ERR,ERR,ERR},
			/*OBJAK*/{ERR,ERR,OBJBV,ERR,ERR,ERR,ERR,ERR,ERR,ERR,ERR,ERR},
			/*OBJBV*/{OBJAV,OBJAV,ERR,ERR,ARRBV,OBJBK,ERR,ERR,OBJAV,OBJAV,OBJAV,ERR},
			/*OBJAV*/{ERR,ERR,ERR,OBJBK,ERR,ERR,ERR,VAL,ERR,ERR,ERR,ERR},
			/*VAL*/{},//û�к���״̬,������״̬ʱ����״̬ջ�е�״̬���㵱ǰ״̬,ռλ������������
			/*EOF*/{},//û�к���״̬��ռλ������������
			/*ERR*/{}//û�к���״̬��ռλ������������
	};
	//Token��������б�
	/*INPUT ���� STR NUM DESC SPLIT ARRS OBJS ARRE OBJE FALSE TRUE NIL BGN*/
	public static final Method[] TKOL = {
		null,null,null,null,OPT.ARRS,OPT.OBJS,null,null,null,null,null,null
	};
	//Ŀ��״̬ת�������б�
	/*TO:BGN ARRBV ARRAV OBJBK OBJAK OBJBV OBJAV VAL EOF ERR*/
	public static final Method[] STOL = {
		null,null,OPT.ARRAV,null,OPT.OBJAK,null,OPT.OBJAV,OPT.VAL,null,null
	};
	//����Token�����б�
	/*FROM:BGN ARRBV ARRAV OBJBK OBJAK OBJBV OBJAV VAL EOF ERR*/
	public static final String[] ETS = {
		getExpectStr(BGN), getExpectStr(ARRBV), getExpectStr(ARRAV), getExpectStr(OBJBK), getExpectStr(OBJAK), getExpectStr(OBJBV), getExpectStr(OBJAV),TOK.castTokType2LocalStr(TOK.EOF),TOK.castTokType2LocalStr(TOK.EOF),TOK.castTokType2LocalStr(TOK.EOF)
	};
	//״̬�����б�
	/*BGN ARRBV ARRAV OBJBK OBJAK OBJBV OBJAV VAL EOF ERR*/
	public static final String[] STS = {
		"������ʼ","�����ֵ","�����ֵ","�������","����ü�","�����ֵ","�����ֵ","������ֵ","��������","�쳣����"
	};
	//��״̬��ֵת��Ϊ״̬����
	public static String castLocalStr(Integer s){
		return STS[s];
	}
	//��ȡ����Token�����ַ���
	public static String getExpectStr(Integer old){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<STM[old].length;i++){
			Integer s = STM[old][i];
			if(s != ERR){
				sb.append(TOK.castTokType2LocalStr(i)).append('|');
			}
		}
		return sb.length() == 0 ? null : sb.deleteCharAt(sb.length()-1).toString();
	}
}
