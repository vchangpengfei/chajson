package com.huaying1988.multTravJsonParse;

import com.huaying1988.multTravJsonParse.syntax.StateMachine;

/**
 * JSON�������������,��JSON������Ϊһ����α������������ṹ��������Ч���϶������𣩣�ʵ�ֽ�Ϊ�򵥣���Ū��ԭ��ΪĿ��
 * @author huaying1988.com
 *
 */
public class JSON {
	/**
	 * ����json�ַ�������List/Map��Ƕ�׽ṹ��ֵ��true��false��number��null��
	 * @param str Ҫ������json�ַ���
	 * @return �����Ľ������
	 */
	public static Object parse(String str) {
		if (str == null || "".equals(str.trim()))
			return null;
		else
			return (new StateMachine(str)).parse();
	}
}
