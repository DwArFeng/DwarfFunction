package com.dwarfeng.func.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 构造一个具有指定图像的无文字按钮，图像一经指定，不能缩放。
 * @author DwArFeng
 * @since 1.7
 */
public class JIconButton extends JButton {
	
	private static final long serialVersionUID = 1708306634638895381L;
	private Image image;
	
	/**
	 * 生成一个具有默认图像null的图像按钮
	 */
	public JIconButton(){
		this(null);
	}
	/**
	 * 生成一个具有指定图像的按钮。
	 * @param image 指定的图像。
	 */
	public JIconButton(Image image){
		super("");
		this.setImage(image);
	}
	
	/**
	 * 返回按钮的图像。
	 * @return 按钮的图像。
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * 设置按钮的图像。
	 * @param image 指定的图像。
	 */
	public void setImage(Image image) {
		this.image = image;
		if(this.image == null) this.setIcon(null);
		else this.setIcon(new ImageIcon(image));
	}
	@Override
	public void setText(String str){
		super.setText("");
	}

}
