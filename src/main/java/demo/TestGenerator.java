package main.java.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import randoop.main.Main;
//import org.eclipse.core.resources.IFile;


public class TestGenerator {
	
//	@SuppressWarnings("static-access")
//	public static  void test(String[] strs){
//		Main randoop = new randoop.main.Main();
//		randoop.main(strs);	//System.out.println("fasdfs");
//	}
	
	@SuppressWarnings("static-access")
	public void testGenerate(int[] timeIndexes, String[][] taskGroup){
   	 for(int timeIndex : timeIndexes){
   		 	 int time = Integer.parseInt(taskGroup[timeIndex][3]);
	    	 execCMD("cmd /k cd C:\\Users\\dlydd\\Desktop\\Senior\\ise\\Example\\bin && "
						+ "java -ea -classpath .;%RANDOOP_JAR% randoop.main.Main gentests --classlist=C:\\\\Randoop\\\\MoreTriangle.txt --timelimit="+time);
	    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	 String date=df.format(new Date());
	    	 try{
				    Thread thread = Thread.currentThread();
				    int waitTime = time*1100;
				    thread.sleep(waitTime);
				}catch (InterruptedException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
	    	 System.out.println(time+" seconds test code generated!");
	    	 
	    	 String[] index = {"","0","1","2","3","4","5","6","7","8","9","10"};
	    	 for(int i=0;i<10;i++){
		    	 copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\bin\\\\RegressionTest"+index[i]+".java",
		    			  "C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\MathUtil\\\\test\\\\RegressionTest"+index[i]+".java");
	    	 }
	    	 System.out.println(time+" seconds test code copied!");
//	    	 System.out.println("Jacoco triggered!");
//	    	 try{
//				    Thread thread = Thread.currentThread();
//				    int waitTime = time*1000;
//				    thread.sleep(waitTime);
//				}catch (InterruptedException e) {
//				    // TODO Auto-generated catch block
//				    e.printStackTrace();
//				}
//	    	 System.out.println("Pitest triggered!");
	    	 
	    	 //save test code
	    	 for(int i=0;i<10;i++){
	    		 copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\bin\\\\RegressionTest"+index[i]+".java",
						"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\test-code\\\\"+taskGroup[timeIndex][0]+"\\\\RegressionTest"+index[i]+".java");
	    	 }
	    	 System.out.println("test code saved!");
	    	 
	    	 //parse html and save data
//	    	 String rootDirectoryPath = "C:\\Users\\dlydd\\Desktop\\Senior\\ise\\human-machine\\human-machine-data";
//	    	 HtmlParser.parser(rootDirectoryPath);
   	 }
    }

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
//		execCMD("cmd /k cd C:\\Users\\dlydd\\Desktop\\Senior\\ise\\Example\\bin && "
//				+ "java -ea -classpath .;%RANDOOP_JAR% randoop.main.Main gentests --classlist=C:\\\\Randoop\\\\MoreTriangle.txt --timelimit=10");
//		String[] strs = {"gentests", "--classlist=C:\\\\Randoop\\\\myclasses.txt", "--timelimit=10"};
//		test(strs);
		
//		String[][] taskGroup = {
//				{"22","MoreTriangle","Randoop","10"},
//				{"23","MoreTriangle","Randoop","30"},
//				{"24","MoreTriangle","Randoop","60"},
//				{"25","MoreTriangle","Randoop","120"},
//				{"26","MoreTriangle","Randoop","240"},
//				{"27","MoreTriangle","Randoop","300"},
//				{"28","MoreTriangle","Randoop","480"},
//				{"MoreTriangle_Randoop_20","MoreTriangle","Randoop","20"},
//				{"MoreTriangle_Randoop_40","MoreTriangle","Randoop","40"},
//				{"MoreTriangle_Randoop_50","MoreTriangle","Randoop","50"},
//				{"MoreTriangle_Randoop_180","MoreTriangle","Randoop","180"},
//				{"MoreTriangle_Randoop_1200","MoreTriangle","Randoop","1200"}};
////				{"29","Calculator","Randoop","10"}
////				{"30","Calculator","Randoop","10"}
////				{"10","Calculator","Randoop","10"}
////				{"11","Calculator","Randoop","10"}}
//		
//		for(String[] task:taskGroup){
//			
//			//windows
//			execCMD("cmd /k cd C:\\Users\\dlydd\\Desktop\\Senior\\ise\\Example\\src && "
//					+ "java -ea -classpath .;%RANDOOP_JAR% randoop.main.Main gentests --classlist=C:\\\\Randoop\\\\Calculator.txt --timelimit="+task[3]);
//			
//			//linux
//			//execCMD("cd C:\\Users\\dlydd\\Desktop\\Senior\\ise\\Example\\src && "
//			//		+ "java -ea -classpath $(RANDOOP_JAR) randoop.main.Main gentests --classlist=C:\\\\Randoop\\\\MoreTriangle.txt --timelimit=10");
//			
//			System.out.println("randoop "+task[3]+" exec done!");
//			try{
//			    Thread thread = Thread.currentThread();
//			    int waitTime = Integer.parseInt(task[3])*1500;
//			    thread.sleep(waitTime);
//			}catch (InterruptedException e) {
//			    // TODO Auto-generated catch block
//			    e.printStackTrace();
//			}
	//
	//		copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest.java",
	//				"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\pitest\\\\src\\\\test\\\\java\\\\pitest\\\\pitest\\\\RegressionTest.java");
	//		copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest0.java",
	//				"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\pitest\\\\src\\\\test\\\\java\\\\pitest\\\\pitest\\\\RegressionTest0.java");
	//		insert("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\pitest\\\\src\\\\test\\\\java\\\\pitest\\\\pitest\\\\RegressionTest.java",0
	//				,"package pitest.pitest;");
	//		insert("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\pitest\\\\src\\\\test\\\\java\\\\pitest\\\\pitest\\\\RegressionTest0.java",0
	//				,"package pitest.pitest;");
	//		try{
	//		    Thread thread = Thread.currentThread();
	//		    thread.sleep(5000);//��ͣ5���������ִ��
	//		}catch (InterruptedException e) {
	//		    // TODO Auto-generated catch block
	//		    e.printStackTrace();
	//		}
	//		
	////		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("itart/src/HelloWorld.java"));  
	////		f.refreshLocal(IResource.DEPTH_ZERO, null);
	//		
	//		execCMD("cmd /k cd C:\\Users\\dlydd\\Desktop\\Senior\\ise\\pitest && mvn org.pitest:pitest-maven:mutationCoverage");
			
	//		copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\pitest\\\\src\\\\test\\\\java\\\\pitest\\\\pitest\\\\RegressionTest.java",
	//				"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\1\\\\Randoop\\\\RegressionTest.java");
	//		copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\pitest\\\\src\\\\test\\\\java\\\\pitest\\\\pitest\\\\RegressionTest0.java",
	//				"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\1\\\\Randoop\\\\RegressionTest0.java");
			
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest0.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest0.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest1.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest1.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest2.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest2.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest3.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest3.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest4.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest4.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest5.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest5.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest6.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest6.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest7.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest7.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest8.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest8.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest9.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest9.java");
//			copyFile("C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\Example\\\\src\\\\RegressionTest10.java",
//					"C:\\\\Users\\\\dlydd\\\\Desktop\\\\Senior\\\\ise\\\\human-machine\\\\1\\\\"+task[0]+"\\\\Randoop\\\\RegressionTest10.java");
			
	//		ArrayList<String> lines=readFileByLines("C:\\Users\\dlydd\\Desktop\\Senior\\ise\\pitest\\target\\pit-reports\\201710121342\\index.html");
	//		
	//		String temp1 =lines.get(75);
	//		//System.out.println(temp);
	//		String bcStr = temp1.substring(16, 18);
	//		int bc = Integer.parseInt(bcStr);
	//		System.out.println(bc);
	//		
	//		String temp2 = lines.get(76);
	//		String mcStr = temp2.substring(16,18);
	//		int mc = Integer.parseInt(mcStr);
	//		System.out.println(mc);
	//	}
	}
	
	public static void execEvosuite(){
		execCMD("cmd /k cd C:\\Users\\dlydd\\Desktop\\Senior\\ise\\Example && java -jar evosuite-1.0.3.jar -target target/classes �CprojectCP target/classes �CDsearch_budget=20 �CDminimize=false �CDassertion_strategy=all");
	}
	
	public static void saveData(int task_id,int group_id,String subject,String tool,int time_budget,double BC,double MC,double total){
		try { //�����������ȡ�쳣��
		     
		    //�����������������ΪIP��ַ
		    String server="localhost";
		    //�������ݿ���
		    String dbname= "human_machine";
		    //�������ݿ��û���
		    String user="root";
		    //�������ݿ�����
		    String pass="afd701221cy";
		    //�������ݿ�˿�
		    String port="3306";    
		    //������������
		    String sDBDriver = "org.gjt.mm.mysql.Driver";
		    //�����������
		    String sConnStr ="jdbc:mysql://"+server+":"+port+"/"+dbname+"?user="+user+"&password="+pass;
		    //�������ӱ���
		    Connection conn = null;
		    //��������
		    Statement stmt=null;
		    //��������
		    ResultSet rs = null;
		     
		    //��������
		    Class.forName(sDBDriver);
		    //��������
		    conn = DriverManager.getConnection(sConnStr);
		    //��������
		    stmt = conn.createStatement();
		 
		    
		    
		    //for(int i =0;i  <a.length;i++) { 
		        //�������ݲ�ѯ���
		        String sql = "inset into table human_machine task_id ='"+task_id+"', group_id ='"+group_id+"', subject='"+subject+"' "
		        		+" tool='"+tool+"' time_budget='"+time_budget+"' BC='"+BC+"' MC='"+MC+"' total='"+total+"'"; 
		        //stmt�����������ݿ���õ�Statement,��Щ�����������������ݿ��,�������ύ�����ݿ��� 
		        stmt.executeUpdate(sql);  
		    //}
		 
		         
		    //�رս����
		    rs.close();
		    //�ر�����
		    stmt.close();
		    //���ر�����
		    conn.close();
		     
		}
		catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
	public static ArrayList<String> readFileByLines(String fileName) {
		ArrayList<String> tasks = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("read file line by line!");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
               // System.out.println("line " + line + ": " + tempString);
                line++;
                tasks.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return tasks;
    }
	
	public static void execCMD(String str){
        Runtime runtime=Runtime.getRuntime();
        System.out.println("exec!");
        //String exec
        try {
            runtime.exec(str);
        } catch (IOException e) {        
            e.printStackTrace();
        }        
    }
	
	public static void copyFile(String oldPath,String newPath){
		try { 
			int bytesum = 0; 
			int byteread = 0; 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) { 
				System.out.println("oldfile exits!");
			InputStream inStream = new FileInputStream(oldPath); 
			FileOutputStream fs = new FileOutputStream(newPath); 
			byte[] buffer = new byte[1444]; 
			int length; 
			while ( (byteread = inStream.read(buffer)) != -1) { 
			bytesum += byteread; 
			//System.out.println(bytesum); 
			fs.write(buffer, 0, byteread); 
			} 
			inStream.close(); 
			} 
			} 
			catch (Exception e) { 
			System.out.println("copy file error!"); 
			e.printStackTrace(); 

			} 
	}
	
	static void insert(String filename,int pos,String insertContent){
	    File tmp;
		try {
			tmp = File.createTempFile("tmp",null);
			tmp.deleteOnExit();

	    RandomAccessFile raf = new RandomAccessFile(filename,"rw");
	    FileOutputStream tmpOut = new FileOutputStream(tmp);
	    FileInputStream tmpIn = new FileInputStream(tmp);
	    raf.seek(pos);
	    byte[] buf = new byte[64];
	    int hasRead = 0;
	    while((hasRead = raf.read(buf))>0){
	    
	    tmpOut.write(buf,0,hasRead);
	    
	    }
	    raf.seek(pos);
	    raf.write(insertContent.getBytes());

	    while((hasRead = tmpIn.read(buf))>0){
	    raf.write(buf,0,hasRead);
	    }
	    tmpOut.close();
	    tmpIn.close();
	    }catch(Exception e){
	    	
	    }
	}
	
}
