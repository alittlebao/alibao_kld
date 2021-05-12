package codeSys.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 
* Copyright © 2018 A Little Bao. All rights reserved.
* 
* Zip压缩解压工具
* <p>导入jar有：apache-ant-zip-1.8.0.jar、commons-io-2.4.jar、dom4j-1.6.1.jar
*
* @version  v1.0.0
* @author Alibao
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月23日     Alibao           v1.0.0               修改原因
 */
public class ZipUtils {

	private static final int BUFFER_SIZE = 2 * 1024;

	/**
	 * 压缩文件
	 * @param src 要压缩的文件或目录
	 * @param zip 输出的压缩文件
	 * @throws IOException
	 */
	public static void zip(File src, File zip) throws IOException {
		List<ZipEntry> list = foreach(src);
		ZipOutputStream out = new ZipOutputStream(zip);
		for (ZipEntry en : list) {
			File fo = new File(src.getParent(), en.getName());
			out.putNextEntry(en);
			FileInputStream in = new FileInputStream(fo);
			byte[] buffer = new byte[BUFFER_SIZE];
			for (int len = 0; (len = in.read(buffer)) != -1;) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.flush();
		}
		out.close();
		System.out.println("压缩完成~");
	}

	/**
	 * 解压缩
	 * @param zip 需要解压的压缩文件
	 * @param out 解压到的目录
	 * @param encode 解压所需的编码，window的压缩工具使用GB18030，ant则使用UTF-8
	 * @throws Exception
	 */
	public static void unzip(File zip, File out, String encode) throws Exception {
		ZipFile zipFile = new ZipFile(zip, encode);
		for (Enumeration<ZipEntry> entries = zipFile.getEntries(); entries.hasMoreElements();) {
			ZipEntry entry = entries.nextElement();
			File file = new File(out, entry.getName());
			if (entry.isDirectory()) {
				file.mkdirs();
			} else {
				File parent = file.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				IOUtils.copy(zipFile.getInputStream(entry), new FileOutputStream(file));
			}
		}
		zipFile.close();
		System.out.println("解压完成~");
	}

	/**
	 * 压缩文件
	 * @param src 要压缩的文件或目录的绝对路径
	 * @param zip 输出的压缩文件的绝对路径
	 * @throws IOException
	 */
	public static void zip(String src, String zip) throws IOException {
		zip(new File(src), new File(zip));
	}

	/**
	 * 压缩文件
	 * @param src 要压缩的文件或目录的绝对路径
	 * @param zip 输出的压缩文件
	 * @throws IOException
	 */
	public static void zip(String src, File zip) throws IOException {
		zip(new File(src), zip);
	}

	/**
	 * 压缩文件
	 * @param src 要压缩的文件或目录
	 * @param zip 输出的压缩文件的绝对路径
	 * @throws IOException
	 */
	public static void zip(File src, String zip) throws IOException {
		zip(src, new File(zip));
	}

	/**
	 * 解压缩
	 * @param zip 需要解压的压缩文件
	 * @param out 解压到的目录
	 * @throws Exception
	 */
	public static void unzip(String zip, String out) throws Exception {
		unzip(new File(zip), new File(out));
	}

	/**
	 * 解压缩
	 * @param zip 需要解压的压缩文件
	 * @param out 解压到的目录
	 * @throws Exception
	 */
	public static void unzip(String zip, File out) throws Exception {
		unzip(new File(zip), out);
	}

	/**
	 * 解压缩
	 * @param zip 需要解压的压缩文件
	 * @param out 解压到的目录
	 * @throws Exception
	 */
	public static void unzip(File zip, String out) throws Exception {
		unzip(zip, new File(out));
	}

	/**
	 * 解压缩,编码默认使用 GB18030
	 * @param zip 需要解压的压缩文件
	 * @param out 解压到的目录
	 * @throws Exception
	 */
	public static void unzip(File zip, File out) throws Exception {
		unzip(zip, out, "GB18030");
	}

	/**
	 * 
	 * 遍历返回压缩文件list
	 *
	 * @param file 输入文件或文件夹
	 * @return 遍历返回压缩文件list
	 */
	private static List<ZipEntry> foreach(File file) {
		return foreach(file, "");
	}

	/**
	 * 
	 * 遍历返回压缩文件list
	 *
	 * @param file 输入文件或文件夹
	 * @param path 指定文件路径
	 * @return 遍历返回压缩文件list
	 */
	private static List<ZipEntry> foreach(File file, String path) {
		List<ZipEntry> list = new ArrayList<ZipEntry>();
		if (file.isDirectory()) {
			path += file.getName() + File.separator;
			for (File fo : file.listFiles()) {
				list.addAll(foreach(fo, path));
			}

		} else if (file.isFile()) {
			list.add(new ZipEntry(path + file.getName()));
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		//upzip("d:/abc", "d:/haha.zip");
		zip("C:/Users/walkw/Desktop/20190214  单选框不选中时设置提交为空", "F:\\itemp\\autoTest\\testzip\\ahah.zip");
	}
}