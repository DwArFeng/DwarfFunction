/**
 * <b>DwarfUtil工具包</b>
 * <br> DwarfUtil
 * <p> 这个包是DwArFeng —— 也就是作者我，在编写程序时提炼出来的，我开发这个工具包的目的是使自己的开发速度
 * 得到提高。我总结了我写过的程序，抽取了其中最常用或者实现起来很复杂的类，把它们做成工具包，以便于下一次使用。
 * 
 * <br> 这个包中的类有可能是最常用的，也有可能是不常用 —— 但是实现起来很复杂的类。
 * <br> 我于早些时候编写了这个类的祖先 —— DwArFunc ， 但是由于编程水平所限，DwArFunc不能说是一个成功的类，
 * 它既没有多语言支持，包的层次也非常的混乱，算不上一个好的工具包。也是这个原因，我打算开发它的第二代，也即是DwarfFunction Reload。
 * 
 * <p> DwarfFunction的整体效果差强人意，使用起来效果还不错，但是一直处于Alpha版本，包的结构也是在进行不断的改动。
 * 然而，DwarfFunction工具包发展的越来越壮大，工具类型也越来越全面。慢慢地，这个包中的工具已经不仅仅限于常用
 * 工具了，逐渐增加了数学工具、算式工具、物理工具等。
 * <br> 逐渐的，DwarfFunction包的结构已经无法容纳这些工具了，需要一个全新的工具包结构来容纳这些不断增加的专用
 * 工具包。
 * 
 * <p><b>DwarfUtil工具包</b>
 * <br>该工具包采用多包结构，常用工具包被封装在{@linkplain com.dwarfeng.dutil.basic}包中，其它的不同的专用包被分别封装在其它的
 * 包中，而每个工具子包都打包成相应的jar包。当用户想使用常用的工具包时，只需要导入dutil_basic.jar即可，当用户想使用特定的专用
 * 工具包时，只需导入工具包名和其所需的前置包即可。
 * 
 * <table cellpadding="0" cellspacing="3" border="0" style="text-align: left; width: 100%;">
 *   <caption><b> 常用的专用工具包名一览：</b></caption>
 *   <tbody>
 *     <tr>
 *       <th style="vertical-align: top; background-color: rgb(204, 204, 255);
 *           text-align: center; ">包名<br>
 *       </th>
 *       <th style="vertical-align: top; background-color: rgb(204, 204, 255);
 *           text-align: center;">用途<br>
 *       </th>
 *     </tr>
 *     <tr>
 *       <td style="vertical-align: middle; width = 66%">
 *              <code> {@linkplain com.dwarfeng.dutil.math}<br></code>
 *       </td>
 *       <td style="vertical-align: middle;">
 *              <code>数学工具包<br></code>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td style="vertical-align: middle; background-color: rgb(238, 238, 255);">
 *              <code>{@linkplain com.dwarfeng.dutil.foth}<br></code>
 *       </td>
 *       <td style="vertical-align: middle; background-color: rgb(238, 238, 255);">
 *              <code>带有格式的数学工具包<br></code>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td style="vertical-align: middle;">
 *              <code>{@linkplain com.dwarfeng.dutil.phic}<br></code>
 *       </td>
 *       <td style="vertical-align: middle;">
 *              <code>物理工具包<br></code>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td style="vertical-align: middle; background-color: rgb(238, 238, 255);">
 *              <code>{@linkplain com.dwarfeng.dutil.detool}<br></code>
 *       </td>
 *       <td style="vertical-align: middle; background-color: rgb(238, 238, 255);">
 *              <code>代码调试工具包<br></code>
 *       </td>
 *     </tr>
 *   </tbody>
 * </table>
 * 
 * @author DwArFeng
 * @since 1.8
 */
package com.dwarfeng.dutil;