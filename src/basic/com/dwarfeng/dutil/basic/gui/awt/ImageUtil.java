package com.dwarfeng.dutil.basic.gui.awt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.dwarfeng.dutil.basic.DwarfUtil;
import com.dwarfeng.dutil.basic.ExceptionStringKey;
import com.dwarfeng.dutil.basic.num.IntNumberValue;
import com.dwarfeng.dutil.basic.num.Interval;
import com.dwarfeng.dutil.basic.num.NumberUtil;
import com.dwarfeng.dutil.basic.num.NumberValue;
import com.dwarfeng.dutil.basic.num.OutOfIntervalException;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 图像工具。
 * 
 * <p>
 * 与图像处理有关的工具。
 * 
 * <p>
 * 由于是只含有静态方法的工具包，所以该类无法被继承。
 * 
 * @author DwArFeng
 * @since 0.1.5-beta
 */
public final class ImageUtil {

	private static final class ImageSize2D implements Size2D {

		private final IntNumberValue height;
		private final IntNumberValue width;

		public ImageSize2D(int width, int height) {
			this.width = new IntNumberValue(width);
			this.height = new IntNumberValue(height);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof ImageSize2D))
				return false;
			ImageSize2D other = (ImageSize2D) obj;
			if (height == null) {
				if (other.height != null)
					return false;
			} else if (!height.equals(other.height))
				return false;
			if (width == null) {
				if (other.width != null)
					return false;
			} else if (!width.equals(other.width))
				return false;
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public NumberValue getHeight() {
			return height;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public NumberValue getWidth() {
			return width;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((height == null) ? 0 : height.hashCode());
			result = prime * result + ((width == null) ? 0 : width.hashCode());
			return result;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return "ImageSize2D [width=" + width + ", height=" + height + "]";
		}

	}

	private static final Image DEFAULT_IMAGE;

	static {
		DEFAULT_IMAGE = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) DEFAULT_IMAGE.getGraphics();

		// 紫色
		g2d.setColor(new Color(255, 0, 255));
		g2d.fillRect(0, 0, 128, 128);
		g2d.dispose();
	}

	/**
	 * 获取默认的图片。
	 * 
	 * <p>
	 * 默认的图片是128*128的纯紫色。
	 * 
	 * @return 默认图片。
	 * @deprecated 命名不规范，已经被 {@link #getDefaultImage()} 取代。
	 */
	public final static Image defaultImage() {
		return getDefaultImage(null);
	}

	/**
	 * 获取默认的图片。
	 * 
	 * @param imageSize
	 *            需要缩放到的尺寸，如果该参数为 <code>null</code>，则使用原尺寸。
	 * @return 默认的图片。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 * @deprecated 命名不规范，已经被 {@link #getDefaultImage(Size2D)} 取代。
	 */
	public final static Image defaultImage(ImageSize imageSize) {
		return getDefaultImage(imageSize);
	}

	/**
	 * 获取默认的图片。
	 * 
	 * <p>
	 * 默认的图片是128*128的纯紫色。
	 * 
	 * @return 默认图片。
	 */
	public final static Image getDefaultImage() {
		return getDefaultImage(null);
	}

	/**
	 * 获取默认的图片。
	 * 
	 * @param size
	 *            需要缩放到的尺寸，如果该参数为 <code>null</code>，则使用原尺寸。
	 * @return 默认的图片。
	 * @throws IllegalArgumentException
	 *             入口尺寸不是图片尺寸，或者图片的尺寸未知。
	 */
	public final static Image getDefaultImage(Size2D size) {
		if (Objects.nonNull(size)) {
			requireImageSize2D(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_7));
		}

		if (Objects.isNull(size)) {
			return DEFAULT_IMAGE;
		}

		return scaleImage(DEFAULT_IMAGE, size);
	}

	/**
	 * 获取指定路径对应的内部图片。
	 * 
	 * <p>
	 * 内部图片是指在程序中的Jar包内部的图片，可以通过 {@link Class#getResourceAsStream(String)}
	 * 来打开输入流。
	 * 
	 * @param name
	 *            图片的内部路径名称，不能为 <code>null</code>。
	 * @return 指定的路径对应的内部图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Image getInternalImage(Name name) {
		return getInternalImage(name, null, null);
	}

	/**
	 * 获取指定路径对应的内部图片。
	 * 
	 * <p>
	 * 内部图片是指在程序中的Jar包内部的图片，可以通过 {@link Class#getResourceAsStream(String)}
	 * 来打开输入流。
	 * <p>
	 * 当指定路径的图片加载失败时，则会返回默认的图片。
	 * 
	 * @param name
	 *            图片的内部路径名称，不能为 <code>null</code>。
	 * @param defaultImage
	 *            当指定路径的图片加载失败后，返回的默认图片。
	 * @param size
	 *            需要缩放到的尺寸，如果该参数为 <code>null</code>，则使用原尺寸。
	 * @return 指定的路径对应的内部图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             入口尺寸不是图片尺寸，或者图片的尺寸未知。
	 */
	public final static Image getInternalImage(Name name, Image defaultImage, Size2D size) {
		Objects.requireNonNull(name, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_4));
		if (Objects.nonNull(size)) {
			requireImageSize2D(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_7));
		}

		try {
			BufferedImage image = ImageIO.read(DwarfUtil.class.getResourceAsStream(name.getName()));
			return scaleImage(image, size);
		} catch (Exception e) {
			return scaleImage(defaultImage, size);
		}
	}

	/**
	 * 获取指定路径对应的内部图片。
	 * 
	 * <p>
	 * 内部图片是指在程序中的Jar包内部的图片，可以通过 {@link Class#getResourceAsStream(String)}
	 * 来打开输入流。
	 * <p>
	 * 当指定路径的图片加载失败时，则会返回默认的图片。
	 * 
	 * @param name
	 *            图片的内部路径名称，不能为 <code>null</code>。
	 * @param size
	 *            需要缩放到的尺寸，如果该参数为 <code>null</code>，则使用原尺寸。
	 * @return 指定的路径对应的内部图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             入口尺寸不是图片尺寸，或者图片的尺寸未知。
	 */
	public final static Image getInternalImage(Name name, Size2D size) {
		return getInternalImage(name, DEFAULT_IMAGE, size);
	}

	/**
	 * 判断一个 2D 尺寸是否是一个图片 2D 尺寸。
	 * 
	 * <p>
	 * 该方法会判断 2D 尺寸的宽度和高度是否均大于0，如果不是，则返回 <code>false</code>。
	 * 
	 * @param size
	 *            指定的 2D 尺寸。
	 * @return 该 2D 尺寸是否是一个图片的 2D 尺寸。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static boolean isImageSize2D(Size2D size) {
		Objects.requireNonNull(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_1));
		if (NumberUtil.isInInterval(size.getIntWidth(), Interval.INTERVAL_POSITIVE)
				&& NumberUtil.isInInterval(size.getIntHeight(), Interval.INTERVAL_POSITIVE)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取指定的图片的 2D 尺寸。
	 * 
	 * @param image
	 *            指定的图片。
	 * @return 指定图片的 2D 尺寸，当图片的尺寸为0或未知时，返回 <code>null</code>。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public final static Size2D newImageSize2D(Image image) {
		Objects.requireNonNull(image, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_0));

		int width = image.getWidth(null);
		int height = image.getHeight(null);

		if (width <= 0 || height <= 0) {
			return null;
		}

		return newImageSize2D(width, height);
	}

	/**
	 * 通过宽和高返回一个表示图片的2D尺寸。
	 * 
	 * <p>
	 * 该2D尺寸的宽和高都是整型数，且不小于1。
	 * 
	 * @param width
	 *            指定的宽度。
	 * @param height
	 *            指定的高度。
	 * @return 由指定宽度和指定高度确定的表示图片的 2D 尺寸。
	 * @throws OutOfIntervalException
	 *             图片的宽度或高度小于1。
	 */
	public final static Size2D newImageSize2D(int width, int height) {
		NumberUtil.requireInInterval(width, Interval.INTERVAL_POSITIVE,
				DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_5));
		NumberUtil.requireInInterval(height, Interval.INTERVAL_POSITIVE,
				DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_6));

		return new ImageSize2D(width, height);
	}

	/**
	 * 要求一个 2D 尺寸必须是一个图片的 2D 尺寸。
	 * 
	 * <p>
	 * 图片2D 尺寸指的是 2D 尺寸的宽度和高度是否均大于0的尺寸。
	 * 
	 * @param size
	 *            指定的 2D 尺寸。
	 * @throws IllegalArgumentException
	 *             当指定的 2D 尺寸不是图片 2D 尺寸时抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static void requireImageSize2D(Size2D size) throws IllegalArgumentException {
		requireImageSize2D(size, null);
	}

	/**
	 * 要求一个 2D 尺寸必须是一个图片的 2D 尺寸。
	 * 
	 * <p>
	 * 图片2D 尺寸指的是 2D 尺寸的宽度和高度是否均大于0的尺寸。
	 * 
	 * @param size
	 *            指定的 2D 尺寸。
	 * @param message
	 *            指定的异常信息。
	 * @throws IllegalArgumentException
	 *             当指定的 2D 尺寸不是图片 2D 尺寸时抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static void requireImageSize2D(Size2D size, String message) throws IllegalArgumentException {
		Objects.requireNonNull(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_1));
		if (!isImageSize2D(size))
			throw new IllegalArgumentException(message);
	}

	/**
	 * 将两张图片缩放成相同的大小之后，进行重叠。
	 * 
	 * @param bottom
	 *            底部图片。
	 * @param top
	 *            顶部图片。
	 * @param size
	 *            需要缩放到的尺寸，如果该参数为 <code>null</code>，则使用 <code>bottom</code>
	 *            图片的原尺寸。
	 * @return 叠加之后的图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             入口尺寸不是图片尺寸，或者图片的尺寸未知。
	 */
	public final static Image scaleAndOverlayImage(Image bottom, Image top, Size2D size) {
		Objects.requireNonNull(bottom, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_2));
		Objects.requireNonNull(top, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_3));
		if (Objects.nonNull(size)) {
			requireImageSize2D(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_7));
		}

		int width = size.getWidth().intValue();
		int height = size.getHeight().intValue();

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_8));
		}

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Image scaleBottom;
		Image scaleTop;
		if (Objects.isNull(size)) {
			scaleBottom = scaleImage(bottom, null);
			scaleTop = scaleImage(top, newImageSize2D(bottom));
		} else {
			scaleBottom = scaleImage(bottom, size);
			scaleTop = scaleImage(top, size);
		}

		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		g2d.drawImage(scaleBottom, 0, 0, null);
		g2d.drawImage(scaleTop, 0, 0, null);
		g2d.dispose();

		return bufferedImage;
	}

	/**
	 * 获取指定图片按照指定大小缩放而来的图片。
	 * 
	 * @param image
	 *            指定的图片，如果该参数为 <code>null</code>，则返回 <code>null</code>。
	 * @param size
	 *            需要缩放到的尺寸，如果该参数为 <code>null</code>，则使用原尺寸。
	 * @return 缩放后的图片。
	 * @throws IllegalArgumentException
	 *             入口尺寸不是图片尺寸，或者图片的尺寸未知。
	 */
	public final static Image scaleImage(Image image, Size2D size) {
		if (Objects.nonNull(size)) {
			requireImageSize2D(size, DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_7));
		}

		if (Objects.isNull(image)) {
			return null;
		}

		if (Objects.isNull(size)) {
			return image;
		}

		int width = size.getWidth().intValue();
		int height = size.getHeight().intValue();

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(DwarfUtil.getExecptionString(ExceptionStringKey.IMAGEUTIL_8));
		}

		if (image.getWidth(null) == width && image.getHeight(null) == height)
			return image;

		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	/**
	 * 获取代表未知的图片。
	 * 
	 * @param imageSize
	 *            图片的大小。
	 * @return 代表未知的图片。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @deprecated 使用 {@link #getInternalImage(Name)}来获取此图片。
	 */
	public final static Image unknownImage(ImageSize imageSize) {
		Objects.requireNonNull(imageSize, "入口参数 imageSize 不能为 null。");
		return getInternalImage(InternalIconLib.UNKNOWN_BLUE, DEFAULT_IMAGE, imageSize);
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
	 * @deprecated 使用 {@link #getInternalImage(Name, Size2D)}来获取此图片的缩放版本。
	 */
	public final static Image unknownImageIfNull(Image image, ImageSize imageSize) {
		Objects.requireNonNull(imageSize, "入口参数 imageSize 不能为 null。");
		if (Objects.nonNull(imageSize)) {
			return scaleImage(image, imageSize);
		}
		return getInternalImage(InternalIconLib.UNKNOWN_BLUE, DEFAULT_IMAGE, imageSize);
	}

	// 禁止外部实例化
	private ImageUtil() {
	}

}
