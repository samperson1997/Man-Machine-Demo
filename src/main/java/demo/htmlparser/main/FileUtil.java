package demo.htmlparser.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * File Util(Tool).
 * @author DingDuan
 *
 * 
 */
public class FileUtil {
	
	/**
	 * Traverse root directory.
	 * @param rootDirectoryPath
	 * 
	 * 
	 */
	public static File[] traverseRootDirectory(String rootDirectoryPath) {
		File rootDirectory = new File(rootDirectoryPath);
		if (!rootDirectory.exists()) {
			System.err.println("The directory of \"" + rootDirectoryPath + "\" does not exists!");
			return null;
		} else {
			File[] groupDirectoryArray = rootDirectory.listFiles();
			if (groupDirectoryArray == null) {
				System.err.println("The directory of \"" + rootDirectoryPath + "\" is empty!");
				return null;
			}
			return groupDirectoryArray;
		}
	}
	
	/**
	 * Traverse group directory.
	 * @param groupDirectoryPath
	 * 
	 * 
	 */
	public static File[] traverseGroupDirectory(File groupDirectoryPath) {
		File[] taskDirectoryArray = groupDirectoryPath.listFiles();
		if (taskDirectoryArray == null) {
			System.err.println("The directory of " + groupDirectoryPath.getAbsolutePath() + " is empty!");
		} 
		return taskDirectoryArray;
	}
	
	/**
	 * Get group id from path.
	 * @param path
	 * @return
	 * 
	 * 
	 */
	public static int getGroupIdFromPath(String path) {
		//System.out.println(path);
		String dir[] = path.split("\\\\");
		String idString = dir[dir.length - 2];
		int id = Integer.parseInt(idString);
		return id;
	}

	/**
	 * Get the last level directory name from path
	 * @param path
	 * @return the last level directory name
	 */
	public static String getLastDirByPath(String path){
		String dir[] = path.split("\\\\");
		return dir[dir.length - 1];
	}
	
	/**
	 * Read content from html file.
	 * @param htmlFilePath
	 * @return
	 * 
	 * 
	 */
	@SuppressWarnings("resource")
	public static String readHtmlFile(String htmlFilePath) {
		File file = new File(htmlFilePath);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		byte[] bytes = new byte[1024];
		try {
			for (int n; (n = input.read(bytes)) != -1;) {
				buffer.append(new String(bytes, 0, n, "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
