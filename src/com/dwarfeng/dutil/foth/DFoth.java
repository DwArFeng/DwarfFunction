package com.dwarfeng.dutil.foth;

import com.dwarfeng.dutil.math.MathObject;

/**
 * �ɱ�ӿڡ�
 * <p>ʵ�ָýӿڵĶ���ʱ�ɱ����
 * <p> �����˵���е�������һ�������ǿɱ���󲢲���ζ���������һ���ܱ༭����˿ɱ�ӿ�����һ���������ж�
 * ����ɱ���󵽵��ܲ��ܹ��༭��
 * @author DwArFeng
 * @since 1.8
 */
public interface DFoth extends MathObject{

	/**
	 * ָʾһ���ɱ�����Ƿ��ܹ����༭��
	 * @return �ɱ�����Ƿ��ܹ����༭��
	 */
	public boolean canModify();
	
}