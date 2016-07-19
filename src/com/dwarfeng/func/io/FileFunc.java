package com.dwarfeng.func.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 实现文件操作的类。
 * <p>实现的文件操作目前为复制。
 * @author DwArFeng
 * @since 1.8
 */
public class FileFunc {

	
	/**
	 * 将一个文件复制到另一个文件。
	 * @param source 需要复制的源文件。
	 * @param target 需要复制到的目标文件。
	 * @throws IOException 复制过程中IO发生异常是抛出的异常。
	 */
	public static void FileCopy(File source,File target) throws IOException{
		//如果target不存在，则创建target以及其目录（有必要的话）。
		createFileIfNotExists(target);
		//定义2MB的缓冲大小
		int bufferSize = 2097152;
		int length = 0;
		//创建输入输出流
		FileInputStream in = new FileInputStream(source);
		FileOutputStream out = new FileOutputStream(target);
		//获取通道
		FileChannel inC = in.getChannel();
		FileChannel outC = out.getChannel();
		//定义字节缓冲
		ByteBuffer buffer = null;
		while(true){
			//判断完成
			if(inC.position() == inC.size()){
				in.close();
				out.close();
				inC.close();
				outC.close();
				return;
			}
			//开辟字节缓冲
			length = (int) (inC.size() - inC.position() < bufferSize ? inC.size() - inC.position():bufferSize);
			buffer = ByteBuffer.allocateDirect(length);
			//复制数据
			inC.read(buffer);
			buffer.flip();
			outC.write(buffer);
		}
	}
	/**
	 * 文件/文件夹的删除方法。
	 * <p> 该方法会对目标文件进行删除，如果目标文件是标准文件的话，则删除该文件。
	 * <br> 如果目标文件是文件夹的话，则会删除该文件夹（包括文件夹中的所有文件、子文件夹、子文件夹中的文件等等）。
	 * @param file 目标文件或文件夹。
	 * @return 文件或文件夹是否删除。
	 */
	public static boolean deleteFile(File file){
		if(file.isDirectory()){
			String[] children = file.list();
			//递归删除该目录的子目录文件
			for(int i = 0 ; i < children.length ; i ++){
				boolean success = deleteFile(new File(file,children[i]));
				if(!success) return false;
			}
		}
		//目录此时为空，可以删除
		return file.delete();
	}
	/**
	 * 如果指定的文件不存在，则尝试新建文件的方法。
	 * <p> 该方法在建立文件时，会将其根目录一同创建（如果具有根目录的话）。
	 * @param file 指定的文件。
	 * @throws IOException 文件无法创建或者通信错误时抛出的异常。
	 */
	public static void createFileIfNotExists(File file) throws IOException{
		//如果文件存在，则什么事也不做。
		if(file.exists()) return;
		File parentFile = file.getParentFile();
		if(parentFile != null && !parentFile.exists()) parentFile.mkdirs();
		file.createNewFile();
	}
	
	//不允许实例化
	private FileFunc() {}

}
