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
            preparedStatement = con.prepareStatement("insert into human_machine (id,subject,tool,time_budget,BC,MC,total,time_start,time_end) "
                    + "values(?,?,?,?,?,?,?,?,?)");

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
            String sql = "select * from human_machine where time_end";
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
	public ArrayList<ScorePO> getScoreFromDatabase(String toolName){
		ArrayList<ScorePO> list = new ArrayList<ScorePO>();
		Connection con = null;
		try {
			// load driver program
			Class.forName(driver);
			// 1.getConnection() methodconnect to mySQL
			con = DriverManager.getConnection(url,user,password);
			String sql = "select * from human_machine where tool='"+toolName+"'";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			ScorePO spo = new ScorePO();
			spo.setBC(0);
			spo.setMC(0);
			spo.setTotal(0);
			while(rs.next()){
				spo.setBC(rs.getDouble("BC"));
				spo.setMC(rs.getDouble("MC"));
				spo.setTotal(rs.getDouble("total"));
			}
			list.add(spo);
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
}
