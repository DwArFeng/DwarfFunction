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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import com.dwarfeng.dwarffunction.cna.ArrayPackFunction;
import com.dwarfeng.dwarffunction.debugtool.CodeTimer;
import com.dwarfeng.dwarffunction.io.CT;
import com.dwarfeng.dwarffunction.numerical.NumberTransformer;

/**
 * 控制台类。
 * <p>该类的表现行为类似控制台，用于替换重置之前版本的不完善的控制台，而重置之前版本的控制台将被移除。
 * <br> 这次的控制台不会主动覆盖<code>System.in</code>，因此，不必再担心使用控制台之后就会失去原有的系统输出流。
 * 控制台仍然提供一个输出流，当用户想重新定向输出流时，可以将提供的输出流重定向到<code>System.in</code>上。
 * <br> 控制台提供大致的行数保证，它允许文本达到一个最大的行数，当文本超过最大的行数时，控制台会按照一定的比例删掉
 * 最早输出的一部分行数，以控制行数不超过最大值。
 * 被删除。这个行数成为能被显示的最大行数，最大行数在构造器中被指定，一旦被指定就不能更改。
 * 
 * @author DwArFeng
 * @since 1.8
 */
public class JConsole extends JPanel{
	
	public static void main(String[] args) throws IOException{
		JFrame jf = new JFrame();
		jf.getContentPane().setLayout(new BorderLayout());
		JConsole jc = new JConsole(6);
		jf.getContentPane().add(jc,BorderLayout.CENTER);
		jf.setSize(400, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		CodeTimer ct = new CodeTimer();
		
		System.setOut(jc.getOut());
		
		Thread t1,t2,t3;
		t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0 ; i < 200 ; i ++){
					CT.trace("t1---1000");
				}
			}
		});
		t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0 ; i < 200 ; i ++){
					CT.trace("t2---2000");
				}
			}
		});
		t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0 ; i < 200 ; i ++){
					CT.trace("t3---3000");
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		ct.start();
		while(t1.isAlive() || t2.isAlive() || t3.isAlive()){}
		ct.stopAndPrint();
		
	}
	
	//---静态常量
	private final static int DEFAULT_MAX_LINE = 3000;
	private final static int LINE_RATIO = 10;
	
	//---与界面有关的变量
	private JTextArea textArea;
	
	//---其它变量
	private int maxLine = DEFAULT_MAX_LINE;
	private final Lock lock = new ReentrantLock();
	
	/**
	 * 生成一个默认的控制台。
	 */
	public JConsole() {
		this(DEFAULT_MAX_LINE);
	}
	
	/**
	 * 生成一个拥有最大行数的控制台.
	 */
	/**
	 *  生成一个拥有最大行数的控制台。
	 *  <p> 控制台的最大行数为<code>maxLine</code>与1的最大值。
	 * @param maxLine 最大的行数。
	 */
	public JConsole(int maxLine){
		
		//初始化常量
		this.maxLine = Math.max(maxLine, 1);
		
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
		
		private final ArrayList<Byte> listSource = new ArrayList<Byte>();
		private final List<Byte> byteList = Collections.synchronizedList(listSource);
		
		/*
		 * (non-Javadoc)
		 * @see java.io.OutputStream#flush()
		 */
		@Override
		public void flush(){
			String str = new String(ArrayPackFunction.unpack(byteList.toArray(new Byte[0])));
			if(textArea != null){
				append(str);
			}
			byteList.clear();
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
		return maxLine;
	}
	
	/**
	 * 向控制台的输出窗口追加文本。
	 * @param str 指定的追加文本。
	 */
	private void append(String str){
		textArea.append(str);
		ensureMaxLine();
	}
	
	/**
	 * 确保最大行。
	 */
	private void ensureMaxLine(){
		int line = textArea.getLineCount();
		if(line >= this.maxLine){
			try{
				textArea.replaceRange(
						null, textArea.getLineStartOffset(0), 
						textArea.getLineEndOffset(line - maxLine + maxLine/LINE_RATIO)
				);
			}catch(BadLocationException e){
				//DO NOTHING
			}
		}
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
