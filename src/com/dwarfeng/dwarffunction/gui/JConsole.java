package com.dwarfeng.dwarffunction.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.dwarfeng.dwarffunction.cna.ArrayPackFunction;
import com.dwarfeng.dwarffunction.io.CT;
import com.dwarfeng.dwarffunction.numerical.NumberTransformer;
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
	
	public static void main(String[] args) throws IOException{
		JFrame jf = new JFrame();
		jf.getContentPane().setLayout(new BorderLayout());
		JConsole jc = new JConsole();
		jf.getContentPane().add(jc,BorderLayout.CENTER);
		jf.setSize(400, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		System.setOut(jc.getOut());
		long l = -  System.currentTimeMillis();
		for(int i = 0 ; i < 100 ; i ++){
			CT.trace("GUI 控制台");
		}
		l += System.currentTimeMillis();
		CT.trace(l);
		
	}
	
	//---静态常量
	private final static int BUFFER_CAPACITY = 3000;
	
	//---与界面有关的变量
	private JTextArea textArea;
	
	//---其它变量
	private final CycledSlsBuffer csb;
	
	
	public JConsole() {
		this(BUFFER_CAPACITY);
	}
	
	public JConsole(int maxLine){
		
		//初始化常量
		this.csb = new CycledSlsBuffer(maxLine);
		
		//初始化界面
		init();
	}
	
	/**
	 * 获取该控制台的输出流。
	 * @return 该控制台的输出流。
	 */
	public PrintStream getOut(){
		return printStream;
	}
	private final OutputStream outputStream = 
	/*
	 * 匿名输出流类。
	 * 该匿名类与控制台的输出面板相关联，向这个输出流写入数据会改变控制台的显示内容。
	 */
	new OutputStream() {
		
		
		private final CycledSlsBuffer csb = new CycledSlsBuffer(BUFFER_CAPACITY);
		private final ArrayList<Byte> listSource = new ArrayList<Byte>();
		private final List<Byte> byteList = Collections.synchronizedList(listSource);
		
		/*
		 * (non-Javadoc)
		 * @see java.io.OutputStream#flush()
		 */
		@Override
		public void flush(){
			String str = new String(ArrayPackFunction.unpack(byteList.toArray(new Byte[0])));
			int lastIndex = str.lastIndexOf("\n");
			if(lastIndex == -1){
				textArea.setText(csb.toString() + str);
			}else{
				byteList.clear();
				String str0 = str.substring(0, lastIndex);
				String str1 = "";
				if(lastIndex < str.length() - 1){
					str1 = str.substring(lastIndex + 1, str.length());
				}
				csb.add(str0);
				textArea.setText(csb.toString() + str1);
				byte[] bs = str1.getBytes();
				for(byte b : bs){
					byteList.add(b);
				}
			}
			textArea.setCaretPosition(textArea.getText().length());
			//最后，让列表释放多余的空间。
			synchronized (listSource) {
				listSource.trimToSize();
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.io.OutputStream#write(int)
		 */
		@Override
		public void write(int b) throws IOException {
			byteList.add(NumberTransformer.cutInt2Byte(b));
		}
	};
	private final PrintStream printStream = new PrintStream(outputStream,true);
	
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
		this.csb.add(str);
		textArea.setText(this.csb.toString());
	}
	
	
	/**
	 * 提供界面的初始化。
	 */
	private void init(){
		setLayout(new BorderLayout(0, 0));
		
		JTextField inputField = new JTextField();
		add(inputField, BorderLayout.SOUTH);
		inputField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

}
