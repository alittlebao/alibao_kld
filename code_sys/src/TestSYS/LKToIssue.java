package TestSYS;

import java.text.SimpleDateFormat;
import java.util.Date;

import codeSys.java.utils.AlibaoTools;

public class LKToIssue {

	private static final String ISSUE = "LINKEYBPM_12.2"; //将要发布的版本号
	private static final String FILE_PATH = "F:/itemp/autoTest"; //将要导出的文件夹位置

	private static final String MYSQL_DATABASE = "alibao_test"; //MySQL 要导出的数据库名称

	private static final String MYSQL = "MySQL5.1.40";     //MySQL的版本号
	private static final String MYSQL_ACCOUNT = "root";   //MySQL 账号
	private static final String MYSQL_PASS = "1234";      //MySQL 密码
	private static final String MYSQL_HOST = "localhost"; //MySQL 主机地址
	private static final String MYSQL_PORT = "3308";      //MySQL 端口
	private static final String MYSQL_ENCODE = "utf8";    //MySQL 编码

	private static final String ORACLE = ""; //Oracle的版本号
	private static final String MSSQL = ""; //SQL Server的版本号

	public static void main(String[] args) {

		//导出MySQL数据库
		outMySQL();

	}

	/**
	 * 导出MySQL数据到指定目录
	 */
	private static void outMySQL() {

		String fileName = ISSUE + "_" + MYSQL + "_" + formatDate(new Date(), "yyyyMMddHHmmss") + ".sql";
		String filePath = FILE_PATH + "/" + fileName;

		String returnMsg = AlibaoTools.outMySQLDatabase(MYSQL_HOST, MYSQL_PORT, MYSQL_ACCOUNT, MYSQL_PASS,
				MYSQL_DATABASE, MYSQL_ENCODE, filePath);

		System.out.println("outMySQL================================ " + returnMsg);

	}

	/**
	 * 格式化时间为指定格式的字符串
	 * 
	 * @param date 时间对像
	 * @param formate 格式化字符串如：yyyy-MM-dd HH:mm:ss
	 * @return 返回字符串
	 */
	private static String formatDate(Date date, String formate) {
		try {
			SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
			return simpleDateFormate.format(date);
		} catch (Exception e) {
		}
		return "";
	}
}
