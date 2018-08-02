package demo.mysql;

import demo.htmlparser.entity.ResultEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.po.*;
import demo.vo.ResultVO;


/**
 * All operations on the database.
 * @author DingDuan
 *
 * 
 */
public class DatabaseUtil {
	// driver name
	private final static String driver = "com.mysql.jdbc.Driver";
	// url
    private final static String url = "jdbc:mysql://localhost:3306/human_machine?serverTimezone=UTC&characterEncoding=utf-8";
    // username
    private final static String user = "root";
    // password
    private final static String password = "afd212688cy";
    
    /**
     * Write all resultEntity in resultEntityList to database.
     * @param resultEntityList
     * 
     * 
     */
    public static void writeResultEntityListToDatabase(List<ResultEntity> resultEntityList) {
        Connection con = null; 
        PreparedStatement preparedStatement = null;
	    try {
	    	Class.forName(driver);
	        con = DriverManager.getConnection(url,user,password);
	        preparedStatement = con.prepareStatement("insert into human_machine (id,subject,tool,time_budget,BC,MC,total,time_start,time_end) "
                    + "values(?,?,?,?,?,?,?,?,?)");
	        for (ResultEntity resultEntity : resultEntityList) {
	            preparedStatement.setInt(1, resultEntity.getId());
	            preparedStatement.setString(2, resultEntity.getSubject());
	            preparedStatement.setString(3, resultEntity.getTool());              
	            preparedStatement.setInt(4, resultEntity.getTime_budget());    
	            preparedStatement.setDouble(5, resultEntity.getBC());              
	            preparedStatement.setDouble(6, resultEntity.getMC());    
	            preparedStatement.setDouble(7, resultEntity.getTotal());
				preparedStatement.setString(8,resultEntity.getTime_start());
				preparedStatement.setString(9,resultEntity.getTime_end());
	            int count = preparedStatement.executeUpdate();
	            if (count == 1) {
	            	System.out.println("Insert to table 'human_machine' success!");
	            } else {
	            	System.err.println("Failed to insert to table 'human_machine'!");
	            }
	        }
	    } catch (ClassNotFoundException e) {   
	            e.printStackTrace();   
	    } catch(SQLException e) {
	            e.printStackTrace();  
	    } catch (Exception e) {
	            e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	            	preparedStatement.close();
	            }
	        	if (con != null) {
	        		con.close();
	        	}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	     }
    }

    
    /**
     * Write resultEntity to database.
     * @param resultEntity
     * 
     * 
     */
	public static void writeResultEntityToDatabase(ResultEntity resultEntity) {
//		System.out.println(resultEntity.getId());
//		System.out.println(resultEntity.getTime_start());
//		System.out.println(resultEntity.getTime_end());
		// new Connection object
        Connection con = null; 
        PreparedStatement preparedStatement = null;
        try {
            // load driver program
            Class.forName(driver);
            // connect to mySQL
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //pre execute add data,there are two parameters--"?"
            preparedStatement = con.prepareStatement("insert into human_machine (subject,tool,time_budget,BC,MC,total,time_start,time_end) "
                    + "values(?,?,?,?,?,?,?,?)");

            //preparedStatement.setInt(1, resultEntity.getId());
            preparedStatement.setString(1, resultEntity.getSubject());
            preparedStatement.setString(2, resultEntity.getTool());
            preparedStatement.setInt(3, resultEntity.getTime_budget());
            preparedStatement.setDouble(4, resultEntity.getBC());
            preparedStatement.setDouble(5, resultEntity.getMC());
            preparedStatement.setDouble(6, resultEntity.getTotal());
			preparedStatement.setString(7,resultEntity.getTime_start());
			preparedStatement.setString(8,resultEntity.getTime_end());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
            	System.out.println("insert one record successfully!");
            } else {
            	System.out.println("fail to insert one record!");
            }
        } catch(ClassNotFoundException e) {   
            e.printStackTrace();   
        } catch(SQLException e) {
            e.printStackTrace();  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		if (preparedStatement != null) {
            		preparedStatement.close();
            	}
        		if (con != null) {
        			con.close();
        		}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        }
	}
	
	public ArrayList<ResultEntity> getResultEntityFromDatabase(){
		ArrayList<ResultEntity> list = new ArrayList<ResultEntity>();
		Connection con = null;
        try {
            // load driver program
            Class.forName(driver);
            // connect to mySQL
            con = DriverManager.getConnection(url,user,password);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String time_now=df.format(new Date());
            String sql = "select * from human_machine";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
            	ResultEntity re = new ResultEntity();
            	re.setId(rs.getInt("id"));
            	re.setSubject(rs.getString("subject"));
            	re.setTool(rs.getString("tool"));
            	re.setTime_budget(rs.getInt("time_budget"));
            	re.setBC(rs.getDouble("BC"));
            	re.setMC(rs.getDouble("MC"));
            	re.setTotal(rs.getDouble("total"));
            	re.setTime_start(rs.getString("time_start"));
            	re.setTime_end(rs.getString("time_end"));
            	list.add(re);
            }
            rs.close();
            state.close();
            con.close();
        }catch(ClassNotFoundException e) {   
                e.printStackTrace();   
            } catch(SQLException e) {
                e.printStackTrace();  
            } 
            	
		return list;
		
	}

	/*
	 * get scores from database
	 */
	public ArrayList<ResultVO> getScoreFromDatabase(String toolName){
		ArrayList<ResultVO> list = new ArrayList<ResultVO>();
		Connection con = null;
		try {
			// load driver program
			Class.forName(driver);
			// 1.getConnection() methodconnect to mySQL
			con = DriverManager.getConnection(url,user,password);
			String sql = "select * from human_machine where tool='"+toolName+"'";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
//			vo.setBC(0);
//			vo.setMC(0);
//			spo.setTotal(0);
			while(rs.next()){
				ResultVO vo = new ResultVO();
				vo.setId(rs.getInt("id"));
				vo.setTime_budget(rs.getInt("time_budget"));
				vo.setBC(rs.getDouble("BC"));
				vo.setMC(rs.getDouble("MC"));
				vo.setTotal(rs.getDouble("total"));
				vo.setTool(toolName);
				list.add(vo);
			}
			rs.close();
			state.close();
			con.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/*
	return the max id in database
	 */
	public int getMaxIdFromDatabase() {
		int maxId=0;
		Connection con = null;
		try {
			// load driver program
			Class.forName(driver);
			// 1.getConnection() methodconnect to mySQL
			con = DriverManager.getConnection(url, user, password);
			String sql = "select max(id) from human_machine";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				maxId=rs.getInt("max(id)");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}

	public static void main(String[] args){
		DatabaseUtil d = new DatabaseUtil();
		//System.out.println(d.getMaxIdFromDatabase());
		ArrayList<ResultVO> list = new ArrayList<ResultVO>();
		list=d.getScoreFromDatabase("Randoop");
		for(ResultVO vo:list){
			System.out.println(vo.toString());
		}
	}
}
