package com.dwarfeng.dutil.develop.i18n;

import java.util.Locale;

import com.dwarfeng.dutil.basic.cna.model.KeySetModel;
import com.dwarfeng.dutil.develop.i18n.obv.I18nObverser;

/**
 * 国际化处理器。
 * 
 * <p>
 * 该接口负责处理国际化信息，本身是一个 {@link KeySetModel}。
 * <p>
 * 除了 <code>KeySetModel</code> 之外，该接口还实现了一些维护当前国际化的方法：如获得和设置当前的地区。
 * 
 * <p>
 * 该接口应该添加的观察器是 {@link I18nObverser}，这个类型的观察器除了可以观察
 * <code>KeySetModel</code>的一般改变之外，还可以对 <code>I18nHandler</code> 中特有的改变做出观察。
 * 
 * <p>
 * 通常情况下，<code>currentLocale</code>为 <code>null</code> 应该代表该国际化处理器正在使用默认的语言。
 * 
 * <p>
 * 对于默认语言 <code>null</code>，该处理器对其作出了特别处理。
 * 
 * <blockquote> 1、在任何的情况下，调用 <code>setCurrentLocale(null)</code> 都会返回
 * <code>true</code> —— 即在任何情况下，将当前语言设为默认值都是可以的，即使该处理器没有与之对应的国际化信息。<br>
 * 2、如果此时该国际化处理器中没有与键 <code>null</code> 相对应的 <code>I18nInfo</code>，则方法
 * <code>getCurrentI18n()</code> 返回 <code>null</code>。 <br>
 * 3、如果该国际化接口处理器中移除的语言是该国际化处理器的当前语言，那么该国际化处理器则会把默认语言改变成
 * <code>null</code>。</blockquote>
 * 
 * 
 * @author DwArFeng
 * @since 0.1.1-beta
 */
public interface I18nHandler extends KeySetModel<Locale, I18nInfo> {

	/**
	 * 获取该国际化处理器当前的语言。
	 * 
	 * @return 该国际化处理器的当前语言。
	 */
	public Locale getCurrentLocale();

	/**
	 * 设置当前的语言。
	 * <p>
	 * 如果当前的语言设置成功的话，则该国际化处理器的当前多语言接口会立即更新，此时 调用
	 * {@link I18nHandler#getCurrentI18n()} 即可返回最新的多语言接口。
	 * 
	 * @param locale
	 *            当前的语言。
	 * @return 该操作是否改变了该处理器。
	 */
	public boolean setCurrentLocale(Locale locale);

	/**
	 * 获取该处理器正在使用的国际化接口。
	 * 
	 * @return 该处理器正在使用的国际化接口。
	 */
	public I18n getCurrentI18n();

}
