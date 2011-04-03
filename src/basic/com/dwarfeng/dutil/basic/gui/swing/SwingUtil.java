package com.dwarfeng.dutil.basic.gui.swing;

import java.util.Locale;
import java.util.Objects;

import javax.swing.JFileChooser;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.StringFieldKey;

/**
 * swing工具包。
 * <p> 该包中包含关于对swing组件进行操作的常用方法。
 * <p> 由于是只含有静态方法的工具包，所以该类无法被继承。
 * @author DwArFeng
 * @since 0.0.2-beta
 */
public final class SwingUtil {

	//禁止外部实例化。
	private SwingUtil() {}

	/**
	 * 设置指定的文件选择器的语言环境。
	 * <p> 允许参数 <code>locale</code>为 <code>null</code>。
	 * @param fileChooser 指定的文件选择器。
	 * @param locale 指定的语言环境。
	 * @throws NullPointerException 入口参数 <code>fileChooser</code> 为 <code>null</code>。
	 */
	public static void setJFileChooserLocal(JFileChooser fileChooser, Locale locale){
		Objects.requireNonNull(fileChooser, DwarfUtil.getStringField(StringFieldKey.SwingUtil_0));
		fileChooser.setLocale(locale);
		fileChooser.updateUI();
	}
	
}
