package com.dwarfeng.dutil.basic.cna.model;

import java.util.Collection;
import java.util.List;

import com.dwarfeng.dutil.basic.cna.model.obv.KeyListObverser;
import com.dwarfeng.dutil.basic.prog.ElementWithKey;
import com.dwarfeng.dutil.basic.prog.ObverserSet;

/**
 * 键值列表模型。
 * <p>
 * 模型可以容纳一组有序的拥有主键的元素，并提供一系列的操作方法。 该模型同样可以对元素根据主键进行操作。该模型是一个 {@link List}实现。
 * 
 * @author DwArFeng
 * @param <K>
 *            泛型K，代表元素键的类型。
 * @param <V>
 *            泛型V，代表元素的类型。
 * @since 0.1.0-beta
 */
public interface KeyListModel<K, V extends ElementWithKey<K>> extends List<V>, ObverserSet<KeyListObverser<K, V>> {

	/**
	 * 如果列表包含指定的键，则返回 <code>true</code>。
	 * 
	 * @param key
	 *            指定的键。
	 * @return 如果包含指定的键，则返回 <code>true</code>。
	 */
	public boolean containsKey(Object key);

	/**
	 * 如果列表包含指定 {@link Collection} 的所有键，则返回 <code>true</code>。
	 * 
	 * @param c
	 *            要在列表中检查其包含性的 {@link Collection}
	 * @return 如果列表包含指定 {@link Collection} 的所有键，则返回 <code>true</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean containsAllKey(Collection<?> c);

	/**
	 * 返回此列表中第一次出现的指定键的索引； 如果此列表不包含该键，则返回 <code>-1</code>。
	 * 
	 * @param o
	 *            要搜索的键。
	 * @return 此列表中第一次出现的指定键的索引，如果列表不包含该键，则返回 <code>-1</code>。
	 */
	public int indexOfKey(Object o);

	/**
	 * 返回此列表中最后一次出现的指定键的索引； 如果此列表不包含该键，则返回 <code>-1</code>。
	 * 
	 * @param o
	 *            要搜索的键。
	 * @return 此列表中最后一次出现的指定键的索引，如果列表不包含该键，则返回 <code>-1</code>。
	 */
	public int lastIndexOfKey(Object o);

	/**
	 * 从此列表中移除第一次出现的指定键（如果存在）（可选操作）。
	 * 
	 * @param key
	 *            要从该列表中移除的键。
	 * @return 如果列表包含指定的键，则返回 <code>true</code>。
	 */
	public boolean removeKey(Object key);

	/**
	 * 从列表中移除指定 {@link Collection} 中包含的其所有元素（可选操作）。
	 * 
	 * @param c
	 *            包含从此列表中移除的元素的 {@link Collection}。
	 * @return 如果此列表由于调用而发生更改，则返回 <code>true</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean removeAllKey(Collection<?> c);

	/**
	 * 仅在列表中保留指定 {@link Collection} 中所包含的元素（可选操作）。
	 * 
	 * @param c
	 *            包含将保留在此列表中的元素的 {@link Collection}。
	 * @return 如果此列表由于调用而发生更改，则返回 <code>true</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean retainAllKey(Collection<?> c);

	/**
	 * 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图。
	 * <p>
	 * （如果 fromIndex 和 toIndex 相等，则返回的列表为空）。
	 * 返回的列表由此列表支持，因此返回列表中的非结构性更改将反映在此列表中，反之亦然。 返回的列表支持此列表支持的所有可选列表操作。
	 * <p>
	 * 此方法省去了显式范围操作（此操作通常针对数组存在）。 通过传递 subList 视图而非整个列表，期望列表的任何操作可用作范围操作。
	 * 例如，下面的语句从列表中移除了元素的范围：
	 * 
	 * <pre>
	 * list.subList(from, to).clear();
	 * </pre>
	 * <p>
	 * 可以对 indexOf 和 lastIndexOf 构造类似的语句，而且 Collections 类中的所有算法都可以应用于 subList。
	 * <p>
	 * 如果支持列表（即此列表）通过任何其他方式（而不是通过返回的列表）从结构上修改，
	 * 则此方法返回的列表语义将变为未定义（从结构上修改是指更改列表的大小，或者以其他方式打乱列表， 使正在进行的迭代产生错误的结果）。
	 * 
	 * @param fromIndex
	 *            subList 的低端（包括）。
	 * @param toIndex
	 *            subList 的高端（不包括） 。
	 * @return 列表中指定范围的视图。
	 * @throws IndexOutOfBoundsException
	 *             非法的端点值
	 *             <code>(fromIndex &lt; 0 || toIndex &gt; size || fromIndex &gt; toIndex)</code>
	 */
	@Override
	public KeyListModel<K, V> subList(int fromIndex, int toIndex);
}
