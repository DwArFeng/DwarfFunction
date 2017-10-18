package com.dwarfeng.dutil.basic.gui.awt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.ImageKey;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 图像工具。
 * 
 * <p>
 * 与图像处理有关的工具。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public final class ImageUtil {

	private static final Image UNKNOWN_IMAGE;
	private static final Image DEFAULT_IMAGE;

	static {
		DEFAULT_IMAGE = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) DEFAULT_IMAGE.getGraphics();

		// 紫色
		g2d.setColor(new Color(255, 0, 255));
		g2d.fillRect(0, 0, 128, 128);
		g2d.dispose();

		UNKNOWN_IMAGE = DwarfUtil.getImage(ImageKey.UNKNOWN);
	}

	/**
	 * 获取代表未知的图片。
	 * 
	 * @param imageSize
	 *            图片的大小。
	 * @return 代表未知的图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Image unknownImage(ImageSize imageSize) {
		Objects.requireNonNull(imageSize, "入口参数 imageSize 不能为 null。");
		return scaleImage(UNKNOWN_IMAGE, imageSize);
	}

	/**
	 * 如果入口图片为 null ，则使用代表未知的图片。
	 * 
	 * @param image
	 *            指定的图片。
	 * @param imageSize
	 *            图片的大小。
	 * @return 指定或代表未知的图片，并被缩放到指定大小。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Image unknownImageIfNull(Image image, ImageSize imageSize) {
		Objects.requireNonNull(imageSize, "入口参数 imageSize 不能为 null。");
		if (Objects.isNull(image))
			image = UNKNOWN_IMAGE;
		return scaleImage(image, imageSize);
	}

	/**
	 * 获取指定路径对应的内部图片。
	 * 
	 * <p>
	 * 内部图片是指在程序中的Jar包内部的图片，可以通过 {@link Class#getResourceAsStream(String)}
	 * 来打开输入流。
	 * 
	 * @param name
	 *            图片的内部路径名称。
	 * @param size
	 *            指定的大小。
	 * @return 指定的路径对应的内部图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Image getInternalImage(Name name, Size2D size) {
		Objects.requireNonNull(name, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_4));
		Objects.requireNonNull(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_1));

		try {
			BufferedImage image = ImageIO.read(DwarfUtil.class.getResourceAsStream(name.getName()));
			return scaleImage(image, size);
		} catch (Exception e) {
			try {
				Image image = DwarfUtil.getImage(ImageKey.IMG_LOAD_FAILED);
				return scaleImage(image, size);
			} catch (Exception e1) {
				return scaleImage(DEFAULT_IMAGE, size);
			}
		}
	}

	/**
	 * 获取指定图片按照指定大小缩放而来的图片。
	 * 
	 * @param image
	 *            指定的图片。
	 * @param size
	 *            需要缩放到的尺寸。
	 * @return 缩放后的图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Image scaleImage(Image image, Size2D size) {
		Objects.requireNonNull(image, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_0));
		Objects.requireNonNull(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_1));

		int width = size.getWidth().intValue();
		int height = size.getHeight().intValue();

		if (image.getWidth(null) == width && image.getHeight(null) == height)
			return image;

		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	/**
	 * 将两张图片缩放成相同的大小之后，进行重叠。
	 * 
	 * @param bottom
	 *            底部图片。
	 * @param top
	 *            顶部图片。
	 * @param size
	 *            图片的大小。
	 * @return 叠加之后的图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Image scaleAndOverlayImage(Image bottom, Image top, Size2D size) {

		Objects.requireNonNull(bottom, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_2));
		Objects.requireNonNull(top, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_3));
		Objects.requireNonNull(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_1));

		int width = size.getWidth().intValue();
		int height = size.getHeight().intValue();

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Image scaleBottom = scaleImage(bottom, size);
		Image scaleTop = scaleImage(top, size);

		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		g2d.drawImage(scaleBottom, 0, 0, null);
		g2d.drawImage(scaleTop, 0, 0, null);
		g2d.dispose();

		return bufferedImage;
	}

	/**
	 * 获取默认的图片。
	 * 
	 * @param imageSize
	 *            图片大小。
	 * @return 默认的图片。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public final static Image defaultImage(ImageSize imageSize) {
		Objects.requireNonNull(imageSize, "入口参数 imageSize 不能为 null。");
		return scaleImage(DEFAULT_IMAGE, imageSize);
	}

	// 禁止外部实例化
	private ImageUtil() {
	}

}
