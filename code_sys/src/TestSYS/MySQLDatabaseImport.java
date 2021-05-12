package TestSYS;

import java.io.File;
import java.io.IOException;

/** 
 * MySQL数据库导入 
 *  
 * @author GaoHuanjie 
 */
public class MySQLDatabaseImport {

	/** 
	 * Java实现MySQL数据库导入 
	 *  
	 * @author GaoHuanjie 
	 * @param hostIP MySQL数据库所在服务器地址IP 
	 * @param userName 数据库用户名 
	 * @param password 进入数据库所需要的密码 
	 * @param importFilePath 数据库文件路径 
	 * @param sqlFileName 数据库文件名 
	 * @param databaseName 要导入的数据库名 
	 * @return 返回true表示导入成功，否则返回false。 
	 */
	public static boolean importDatabase(String hostIP, String userName, String password, String importFilePath,
			String sqlFileName, String databaseName) {
		File saveFile = new File(importFilePath);
		if (!saveFile.exists()) {// 如果目录不存在  
			saveFile.mkdirs();// 创建文件夹  
		}
		if (!importFilePath.endsWith(File.separator)) {
			importFilePath = importFilePath + File.separator;
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("mysql").append(" -h").append(hostIP);
		stringBuilder.append(" -P3308").append(" -u").append(userName).append(" -p").append(password);
		stringBuilder.append(" ").append(databaseName);
		stringBuilder.append(" <").append(importFilePath).append(sqlFileName);
		
		System.out.println(stringBuilder.toString());
		try {
			Process process = Runtime.getRuntime().exec("cmd /c " + stringBuilder.toString());//必须要有“cmd /c ”  
			if (process.waitFor() == 0) {// 0 表示线程正常终止。  
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws InterruptedException {
		if (importDatabase("localhost", "root", "1234", "F:\\itemp\\autoTest", "MySQL5.1.40_bpm_uiex_20181011.sql", "alibao_test")) {
			System.out.println("数据库导入成功！！！");
		} else {
			System.out.println("数据库导入失败！！！");
		}
	}
}