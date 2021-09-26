package com.huaying1988.multTravJsonParse.syntax;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.huaying1988.multTravJsonParse.lex.JsonLex;
import com.huaying1988.multTravJsonParse.lex.Token;
import com.huaying1988.multTravJsonParse.lex.UnexpectedException;

/**
 * �﷨״̬��������״̬ת�����Լ���ز����ĵ���
 * @author huaying1988.com
 *
 */
public class StateMachine {
	private JsonLex lex = null;
	private Operator opt = null;
	private Integer status = null;
	public StateMachine(String str){
		if (str == null)
			throw new NullPointerException("�﷨�������캯�����ܴ���null");
		lex = new JsonLex(str);
		opt = new Operator(lex);
	}
	
	public Object parse(){
		Token tk = null;
		status = STATE.BGN;
		Integer oldStatus = status;
		while((tk=lex.next())!=Token.EOF){
			if(tk == null){
				throw lex.generateUnexpectedException("���ֲ���ʶ���token����" + lex.getCurChar() + "��");
			}
			if(status == STATE.VAL || status == STATE.EOF || status == STATE.ERR){
				throw lex.generateUnexpectedException("��ǰ״̬��"+STATE.castLocalStr(oldStatus)+"��,�ڴ���������;ȴ����"+tk.toLocalString());
			}
			oldStatus = status;
			status = STATE.STM[oldStatus][tk.getType()];
			if(status == STATE.ERR){
				throw lex.generateUnexpectedException("��ǰ״̬��"+STATE.castLocalStr(oldStatus)+"��,�ڴ���"+(STATE.ETS[oldStatus]==null?"����":STATE.ETS[oldStatus])+"��;ȴ����"+tk.toLocalString());
			}
			try {
				Method m = STATE.TKOL[tk.getType()];
				if(m!=null){//����Token����
					status = (Integer)m.invoke(opt, oldStatus, status, tk);
				}
				m = STATE.STOL[status];
				if(m!=null){//Ŀ��״̬����
					status = (Integer)m.invoke(opt, oldStatus, status, tk);
				}
			} catch (IllegalArgumentException e) {
				throw lex.generateUnexpectedException("��������á�����Ƿ�����",e);
			} catch (IllegalAccessException e) {
				throw lex.generateUnexpectedException("��������á�˽�з����޷�����",e);
			} catch (InvocationTargetException e) {
				if(e.getTargetException() instanceof UnexpectedException){
					throw (UnexpectedException)e.getTargetException();
				}else{
					throw lex.generateUnexpectedException("����ʱ�쳣",e);
				}
			}
		}
		return opt.getCurValue();
	}
}
