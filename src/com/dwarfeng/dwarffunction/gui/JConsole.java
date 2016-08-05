package com.dwarfeng.dwarffunction.gui;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dwarfeng.dwarffunction.str.CycledSlsBuffer;

/**
 * 控制台类。
 * <p>该类的表现行为类似控制台，用于替换重置之前版本的不完善的控制台，而重置之前版本的控制台将被移除。
 * <br> 这次的控制台不会主动覆盖<code>System.in</code>，因此，不必再担心使用控制台之后就会失去原有的系统输出流。
 * 控制台仍然提供一个输出流，当用户想重新定向输出流时，可以将提供的输出流重定向到<code>System.in</code>上。
 * <br> 该控制台可以显示一定行数的文本，如果显示的行数已经到达了指定的行数，那么如果再向其中追加新的文本，会导致最旧的文本行
 * 被删除。这个行数成为能被显示的最大行数，最大行数在构造器中被指定，一旦被指定就不能更改。
 * 
 * @author DwArFeng
 * @since 1.8
 */
public class JConsole extends JPanel{
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(new BorderLayout());
		JConsole jc = new JConsole();
		jf.add(jc,BorderLayout.CENTER);
		jf.setSize(400, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	//---静态常量
	private final static int BUFFER_CAPACITY = 3000;
	
	//---与界面有关的变量
	private JTextArea textArea;
	private JAdjustableBorderPanel adjust;
	
	//---其它变量
	private final CycledSlsBuffer csb;
	private final ConsoleOutPutStream cops;
	
	
	public JConsole() {
		this(BUFFER_CAPACITY);
	}
	
	public JConsole(int maxLine){
		
		//初始化常量
		this.csb = new CycledSlsBuffer(maxLine);
		
		//初始化界面
		init();
	}
	
	public PrintStream getOut(){
		// TODO 待完善
		return null;
	}
	
	private final class ConsoleOutPutStream extends OutputStream{

		
		@Override
		public void write(int b) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private final OutputStream outputStream = new OutputStream() {
		
		/*
		 * (non-Javadoc)
		 * @see java.io.OutputStream#flush()
		 */
		@Override
		public void flush(){
			
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.io.OutputStream#write(int)
		 */
		@Override
		public void write(int b) throws IOException {
			// TODO Auto-generated method stub
			
		}
	};
	
	public InputStream getIn(){
		// TODO 带完善
		return null;
	}
	
	public Font getFont(){
		//TODO 待完善
		return null;
	}
	
	public void setFont(Font font){
		//TODO 待完善
	}
	
	/**
	 * 返回控制台显示的最大行数。
	 * @return 控制台显示的最大行数。
	 */
	public int getMaxLine(){
		return this.csb.getCapacity();
	}
	
	private void append(String str){
		this.csb.append(str);
		textArea.setText(this.csb.toString());
	}
	
	
	/**
	 * 提供界面的初始化。
	 */
	private void init(){
		setLayout(new BorderLayout(0, 0));
		
		adjust = new JAdjustableBorderPanel();
		adjust.setSeperatorThickness(5);
		adjust.setSouthEnabled(true);
		add(adjust);
		
		JTextField inputField = new JTextField();
		adjust.add(inputField, BorderLayout.SOUTH);
		inputField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		adjust.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
