package codeSys.java.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.IOUtils;

/**
 * 
 * alibao工具类 
 * 
 * Copyright © 2018 A Little Bao. All rights reserved.
 * 
 * <p> * formatDate     格式化时间为指定字符串
 * <p> * getDistance	根据两点经纬度计算距离  
 * <p> * getServerIP	获取服务器IP地址 
 * <p> * isEmpty  判断字符串是否为null或"null"或长度为0
 * <p> * isBlank  判断字符串是否为空值或去掉前后空格长度为0. 相对方法 isNotBlank
 * <p> * intSet	  判断set集合中是否包含某一个元素,可以不分区大小写
 * <p> * importMySQLDatabase  通过CMD命令导入MySQL数据库，相对导出方法 outMySQLDatabase
 * <p> * runCMD	  调用CMD命令，返回控制台输出信息
 * <p> * out      输出信息到控制台
 * @author alibao
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2019年1月29日     alibao           v1.0.0               修改原因
 */
public class AlibaoTools {

	// 是否输出打印信息到控制台 
	private static final boolean IS_SHOW = true;

	// 地球平均半径
	private static final double EARTH_RADIUS = 6378137;

	/**
	 * 
	 * 通过CMD命令导入MySQL数据库
	 *
	 * @param host 主机地址，未指定时默认为localhost
	 * @param port 端口号，未指定时默认为3306
	 * @param account 用户账号
	 * @param pwd     密码
	 * @param databasename  数据库名称
	 * @param encode        数据库编码，默认utf8
	 * @param filePath      导入的数据文件地址（含文件名）
	 * @return 返回运行结果
	 */
	public static String importMySQLDatabase(String host, String port, String account, String pwd, String databasename,
			String encode, String filePath) {

		//主机地址，未指定时默认为localhost
		if (isBlank(host)) {
			host = "localhost";
		}
		//端口号，未指定时默认为3306
		if (isBlank(port)) {
			port = "3306";
		}

		//未指定编码类型时，默认为utf8
		if (isBlank(encode)) {
			encode = "utf8";
		}

		//返回执行的结果
		String returnMess = "";

		StringBuilder runStrB = new StringBuilder();
		runStrB.append("mysql").append(" -h").append(host);
		runStrB.append(" -P").append(port).append(" -u").append(account).append(" -p").append(pwd);
		runStrB.append(" ").append(databasename);
		runStrB.append(" --default-character-set=").append(encode);
		runStrB.append(" <").append(filePath);

		returnMess = runCMD("cmd /c " + runStrB.toString());

		return returnMess;
	}

	/**
	 * 
	 * 通过CMD命令导出MySQL数据库
	 *
	 * @param host 主机地址，未指定时默认为localhost
	 * @param port 端口号，未指定时默认为3306
	 * @param account 用户账号
	 * @param pwd     密码
	 * @param databasename  数据库名称
	 * @param encode        数据库编码，默认utf8
	 * @param filePath      导入的数据文件地址（含文件名）
	 * @return 返回运行结果
	 */
	public static String outMySQLDatabase(String host, String port, String account, String pwd, String databasename,
			String encode, String filePath) {

		//主机地址，未指定时默认为localhost
		if (isBlank(host)) {
			host = "localhost";
		}
		//端口号，未指定时默认为3306
		if (isBlank(port)) {
			port = "3306";
		}

		//未指定编码类型时，默认为utf8
		if (isBlank(encode)) {
			encode = "utf8";
		}

		//返回执行的结果
		String returnMess = "";

		StringBuilder runStrB = new StringBuilder();
		runStrB.append("mysqldump").append(" -h").append(host);
		runStrB.append(" -P").append(port).append(" -u").append(account).append(" -p").append(pwd);
		runStrB.append(" ").append(databasename);
		runStrB.append(" --default-character-set=").append(encode);
		runStrB.append(" >").append(filePath);

		returnMess = runCMD("cmd /c " + runStrB.toString());

		return returnMess;
	}

	/**
	 * 
	 * 调用CMD命令
	 *
	 * @param commandStr 执行命令
	 * @return 返回控制台输出的信息
	 */
	public static String runCMD(String commandStr) {
		return runCMD(commandStr, null, null);
	}

	/**
	 * 
	 * 调用CMD命令
	 *
	 * @param commandStr 执行命令
	 * @param executeType 字符串的数组，其中每个元素的环境变量设置格式为name=value，如果子进程继承当前进程的环境，则为null。
	 * @return 返回控制台输出的信息
	 */
	public static String runCMD(String commandStr, String[] executeType) {
		return runCMD(commandStr, executeType, null);
	}

	/**
	 * 调用CMD命令
	 * java的Runtime.getRuntime().exec(X,X,X)可以调用执行cmd指令。
	 * <P> cmd /c dir 是执行完dir命令后关闭命令窗口。
	 * <p> cmd /k dir 是执行完dir命令后不关闭命令窗口。
	 * <p> cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
	 * <p> cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
	 *
	 * @param commandStr 执行命令
	 * @param executeType 字符串的数组，其中每个元素的环境变量设置格式为name=value，如果子进程继承当前进程的环境，则为null。
	 * @param dir 指定目录环境下运行
	 * @return 返回控制台输出的信息
	 */
	public static String runCMD(String commandStr, String[] executeType, File dir) {

		String CMDmessage = ""; //控制台输出信息
		BufferedReader child_in = null;

		try {

			Process child = Runtime.getRuntime().exec(commandStr, executeType, dir);

			// 获得控制台的输出流 
			child_in = new BufferedReader(new InputStreamReader(child.getInputStream(), Charset.forName("GBK")));
			String line = null;
			while ((line = child_in.readLine()) != null) {
				out(line);
				CMDmessage += ("\n" + line);
			}

			if (child.waitFor() == 0) {// 0 表示线程正常终止。  
				CMDmessage += "\n 运行结束，线程正常终止。";
			}
		} catch (IOException e) {
			out(e.toString(), "error", true);
			CMDmessage += e.toString();
		} catch (InterruptedException e) {
			e.printStackTrace();
			CMDmessage += e.toString();
		} finally {
			IOUtils.closeQuietly(child_in);
		}

		return CMDmessage;
	}

	/**
	 * 
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 *
	 * @param lng1 第一点经度
	 * @param lat1 第一点纬度
	 * @param lng2 第二点经度 
	 * @param lat2 第二点纬度
	 * @return 两点间距离
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	// 把经纬度转为度（°）
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 
	 * 获取服务器IP地址
	 *
	 * @return String IP，若返回"-1"表示获取失败
	 * @author alibao
	 */
	public static String getServerIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			System.out.println(e);
			return "-1";
		}
	}

	/**
	 * 
	* 判断set集合中是否包含某一个元素,可以不分区大小写
	*
	* @param keyStr 要判断的字符
	* @param param 需要对比判断的set集合
	* @param ignoreCase true表示不区分大小写；
	* @return true表示含有，false表示不含有
	 */
	public static boolean intSet(String keyStr, Set<String> param, boolean ignoreCase) {
		if (ignoreCase) {
			for (String str : param) {
				if (str.equalsIgnoreCase(keyStr)) {
					return true;
				}
			}
		} else {
			return param.contains(keyStr);
		}
		return false;
	}

	/**
	 * 判断字符串是否为null或"null"或长度为0
	 * 
	 * @param string 要判断的字符串
	 * @return 判断结果，true为空，false为非空
	 */
	private static boolean isEmpty(String string) {
		return (string == null) || (string.equals("null")) || (string.length() == 0);
	}

	/**
	 * 判断字符串是否为空值或去掉前后空格长度为0
	 * 
	 * @param string 要判断的字符串
	 * @return 判断结果，true为空，false为非空
	 */
	public static boolean isBlank(String string) {
		return (isEmpty(string)) || (string.trim().length() == 0);
	}

	/**
	 * 判断字符串是否不为空值
	 * 
	 * @param string 要判断的字符串
	 * @return 判断结果，true为非空，false为空
	 */
	public static boolean isNotBlank(String string) {
		return !isBlank(string);
	}

	/**
	 * 
	 * 输出信息到控制台
	 *
	 * @param 描述1描述
	 * @return 返回结果描述
	 */
	public static void out(String out) {
		if (IS_SHOW) {
			System.out.println(out);
		}
	}

	/**
	 * 
	 * 输出信息到控制台
	 *
	 * @param out 输出控制台信息
	 * @param status normal和error两种类型
	 * @param isShow 是否输出到控制台
	 * @return 返回结果描述
	 */
	public static void out(String out, String status, boolean isShow) {
		if (isShow) {
			if ("normal".equals(status)) {
				System.out.println(out);
			} else {
				System.err.println(out);
			}
		}
	}

	/**
	 * 格式化时间为指定格式的字符串
	 * 
	 * @param date 时间对像
	 * @param formate 格式化字符串如：yyyy-MM-dd HH:mm:ss
	 * @return 返回字符串
	 */
	public static String formatDate(Date date, String formate) {
		try {
			SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
			return simpleDateFormate.format(date);
		} catch (Exception e) {
		}
		return "";
	}
}
