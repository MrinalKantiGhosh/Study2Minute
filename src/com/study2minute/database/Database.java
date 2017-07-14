package com.study2minute.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;

import org.apache.derby.tools.sysinfo;

import com.study2minute.utils.Constants;


/*
 import java.util.*;
public class HelloWorld{

     public static void main(String args[]){
        Calendar calendar = Calendar.getInstance();
    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
    System.out.println("TimeStamp = " + ourJavaTimestampObject.toString());
     }
}

 * */

public class Database {
	
	private static int tableCount = 1;
	//-------------------------------------------------------------------------Connection
	@SuppressWarnings("finally")
	private static Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName(Constants.DRIVER_URL).newInstance();
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			return con;
		}
	}
	//------------------------------------------------------------------------------------
	
	//------------------------------------------------DataBase table Check using meta-data
	
	public static Vector<String> getCurrentTables()
	{
		Vector<String> tables = new Vector<String>();
		Connection conn = getConnection();
		try {
			DatabaseMetaData metaData =  conn.getMetaData();
			ResultSet rs =metaData.getTables(null,null,null,new String[]{"TABLE"});
			while(rs.next())
			{
				tables.addElement(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tables;
	}
	
	
	//------------------------------------------------------------------------------------
	
	//-------------------------------------------------------------------------Table Create
	
	public static void createTable(String query) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();
			System.out.println("Table " + tableCount + " has been created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				tableCount++;
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	//-------------------------------------------------------------------------------------
	
	
	// -----------------------------------------------------------------Insertion In Tables
	
	@SuppressWarnings("finally")
	public static boolean insert_CATAGORY_TBL(String query, String catagory_name) {
		Connection con = getConnection();
		boolean status = false;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,catagory_name);
			int rowAffected = ps.executeUpdate();
			if(rowAffected == 1)
				status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return status;
			}
		}
		
	}

	@SuppressWarnings("finally")
	public static boolean insert_SUBCATAGORY_TBL(String query, String subcatagory_name, int catagory_id) {
		Connection con = getConnection();
		boolean status = false;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,subcatagory_name);
			ps.setInt(2,catagory_id);
			int rowAffected = ps.executeUpdate();
			if(rowAffected == 1)
				status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return status;
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean insert_AUTHOR_TBL(String query, String author_name, String phone_number, String email_id, String bank_name, String branch_name, String account_number, String account_type, String iFSC_number) {
		boolean status = false;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,author_name);
			ps.setString(2, phone_number);
			ps.setString(3, email_id);
			ps.setString(4, bank_name);
			ps.setString(5, branch_name);
			ps.setString(6, account_number);
			ps.setString(7, account_type);
			ps.setString(8, iFSC_number);
		
			int rowAffected = ps.executeUpdate();
			if(rowAffected == 1)
				status=true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return status;
			}
		}
	
	}
	
	@SuppressWarnings("finally")
	public static boolean insert_ARTICLE_TBL(String query, String subcatagory_id, String article_title, String author_id, String pdf_loc) {
		boolean status = false;
		Connection con = getConnection();
		 Calendar calendar = Calendar.getInstance();
		 Timestamp upload_time = new Timestamp(calendar.getTime().getTime());
		try {
			//System.out.println(pdf_loc);
			PreparedStatement ps = con.prepareStatement(query);
			//System.out.println("ps = " + ps);
			//System.out.println("Database.java -> " + subcatagory_id + "   " + article_title + "   " + author_id + "   " + pdf_loc);
			ps.setInt(1, Integer.parseInt(subcatagory_id));
			//System.out.println("reached_Database 1");
			ps.setString(2, article_title);
			//System.out.println("reached_Database 2");
			ps.setInt(3, Integer.parseInt(author_id));
			//System.out.println("reached_Database 3");
			ps.setTimestamp(4, upload_time);
			//System.out.println("reached_Database 4");
			ps.setString(5, pdf_loc);
			//System.out.println("reached_Database 5");
			int rowAffected = ps.executeUpdate();
			if(rowAffected ==1)
				status=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return status;
			}
		}
	}
	
	//--------------------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------Show All Columns
	
	@SuppressWarnings("finally")
	public static Vector<String[]> selectAllCategory(String query)
	{
		Connection conn = getConnection();
		Vector<String[]> resultVector = new Vector<String[]>();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String[] arr = new String[2];
				arr[0] = ""+id;
				arr[1] = name;
				resultVector.addElement(arr);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				return resultVector;
			}
		}
		
	}
	
	@SuppressWarnings("finally")
	public static Vector<String[]> show_CATAGORY_TBL(String query)
	{
		Connection con = getConnection();
		Vector<String[]> resultVector = new Vector<String[]>();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int catagory_id = rs.getInt(1);
				String catagory_name = rs.getString(2);
				String showTable[] = new String[2];
				showTable[0] = ""+catagory_id;
				showTable[1] = catagory_name;
				resultVector.addElement(showTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return resultVector;
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static Vector<String[]> show_SUBCATAGORY_TBL(String query)
	{
		Vector<String[]> resultVector = new Vector<String[]>();
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int subcatagory_id = rs.getInt(1);
				String subcatagory_name = rs.getString(2);
				int parent_catagory_id = rs.getInt(3);
				String showTable[] = new String[3];
				showTable[0] = ""+subcatagory_id;
				showTable[1] = subcatagory_name;
				showTable[2] = show_CATAGORY_NAME_GIVEN_CATAGORY_ID(parent_catagory_id);
				resultVector.addElement(showTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return resultVector;
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static Vector<String[]> show_AUTHOR_TBL(String query)
	{
		Vector<String[]> resultVector = new Vector<String[]>();
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int author_id = rs.getInt(1);
				String author_name = rs.getString(2);
				String phone_number = rs.getString(3);
				String email_id = rs.getString(4);
				String bank_name = rs.getString(5);
				String branch_name = rs.getString(6);
				String account_number = rs.getString(7);
				String account_type = rs.getString(8);
				String IFSC_number = rs.getString(9);
				
				String showTable[] = new String[9];
				showTable[0] = ""+author_id;
				showTable[1] = author_name;
				showTable[2] = phone_number;
				showTable[3] = email_id;
				showTable[4] = bank_name;
				showTable[5] = branch_name;
				showTable[6] = account_number;
				showTable[7] = account_type;
				showTable[8] = IFSC_number;
				
				resultVector.addElement(showTable);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return resultVector;
			}
		}
		
	}
	
	@SuppressWarnings("finally")
	public static Vector<String[]> showARTICLE_TBL(String query)
	{
		Vector<String[]> resultVector = new Vector<String[]>();
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int article_id = rs.getInt(1);
				int subcatagory_id = rs.getInt(2);
				String article_title = rs.getString(3);
				int author_id = rs.getInt(4);
				Timestamp upload_time = rs.getTimestamp(5);
				int hitCount = rs.getInt(6);
				int upVote = rs.getInt(7);
				int downVote = rs.getInt(8);
				String pdf_loc = rs.getString(9);
				
				String showTable[] = new String[10];
				
				showTable[0] = ""+article_id;
				showTable[1] = show_CATAGORY_NAME_GIVEN_SUBCATAGORY_ID(subcatagory_id);
				showTable[2] = show_SUBCATAGORY_NAME_GIVEN_SUBCATAGORY_ID(subcatagory_id);
				showTable[3] = article_title;
				showTable[4] = show_AUTHOR_NAME_GIVEN_AUTHOR_ID(author_id);
				showTable[5] = upload_time.toString();
				showTable[6] = ""+hitCount;
				showTable[7] = ""+upVote;
				showTable[8] = ""+downVote;
				showTable[9] = pdf_loc;
				
				resultVector.addElement(showTable);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return resultVector;
			}
		}
	}
	//------------------------------------------------------------------------------------------------
	
	//-------------------------------------------------------------------Specific Selection from Tables
	
	@SuppressWarnings("finally")
	public static String show_CATAGORY_NAME_GIVEN_CATAGORY_ID(int catagory_id)
	{
		String catagory_name = null;
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(Constants.SELECT_CATAGORY_NAME_GIVEN_CATAGORY_ID);
			ps.setInt(1,catagory_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				catagory_name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return catagory_name;
			}
		}
		
	}
	
	
	@SuppressWarnings("finally")
	public static int show_CATAGORY_ID_GIVEN_CATAGORY_NAME(String catagory_name)
	{
		int catagory_id=0;
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(Constants.SELECT_CATAGORY_ID_GIVEN_CATAGORY_NAME);
			ps.setString(1,catagory_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				catagory_id = rs.getInt("CATAGORY_ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return catagory_id;
			}
		}
		
	}
	
	
	@SuppressWarnings("finally")
	public static String show_SUBCATAGORY_NAME_GIVEN_SUBCATAGORY_ID(int subcatagory_id)
	{
		String subcatagory_name = null;
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(Constants.SELECT_SUBCATAGORY_NAME_GIVEN_SUBCATAGORY_ID);
			ps.setInt(1,subcatagory_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				subcatagory_name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return subcatagory_name;
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static String show_CATAGORY_NAME_GIVEN_SUBCATAGORY_ID(int subcatagory_id)
	{
		String catagory_name = null;
		int catagory_id = 0;
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(Constants.SELECT_CATAGORY_ID_GIVEN_SUBCATAGORY_ID);
			ps.setInt(1,subcatagory_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				catagory_id = rs.getInt(1);
			}
			catagory_name = show_CATAGORY_NAME_GIVEN_CATAGORY_ID(catagory_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return catagory_name;
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static String show_AUTHOR_NAME_GIVEN_AUTHOR_ID(int author_id)
	{
		String author_name = null;
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(Constants.SELECT_AUTHOR_NAME_GIVEN_AUTHOR_ID);
			ps.setInt(1,author_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				author_name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return author_name;
			}
		}
	}

	
	@SuppressWarnings("finally")
	public static Vector<String[]> selectAllCategoryName(String query)
	{
		Connection conn = getConnection();
		Vector<String[]> resultVector = new Vector<String[]>();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String name = rs.getString(1);
				String arr[] = new String[1];
				arr[0] = name;
				resultVector.addElement(arr);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				return resultVector;
			}
		}
		
	}
	
	@SuppressWarnings("finally")
	public static Vector<String[]> show_SUBCATAGORY_NAME_GIVEN_PARENT_CATAGORY_ID(String catagory_name)
	{
		Vector<String[]> resultVector = new Vector<String[]>();
		Connection con = getConnection();
		int catagory_id = show_CATAGORY_ID_GIVEN_CATAGORY_NAME(catagory_name);
		try {
			PreparedStatement ps = con.prepareStatement(Constants.SELECT_SUBCATAGORY_NAME_SUBCATAGORY_ID_GIVEN_PARENT_CATAGORY_ID);
			ps.setInt(1, catagory_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String name = rs.getString("SUBCATAGORY_NAME");
				String id = Integer.toString(rs.getInt("SUBCATAGORY_ID"));
				String arr[] = new String[2];
					arr[0] = name;
					arr[1] = id;
				resultVector.addElement(arr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				return resultVector;
			}
		}
	}
	
	
	@SuppressWarnings("finally")
	public static Vector<String[]> show_ARTICLE_LIST_FROM_SUBCATAGORY_ID(int subcatagory_id)
	{
		Vector<String[]> resultVector = new Vector<String[]>();
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(Constants.GET_ARTICLE_LIST_FROM_SUBCATAGORY_ID);
			ps.setInt(1, subcatagory_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String[] table = new String[6];

				table[0] = rs.getString("ARTICLE_TITLE");
				table[1] = Integer.toString(rs.getInt("HITCOUNT"));
				table[2] = Integer.toString(rs.getInt("UPVOTE"));
				table[3] = Integer.toString(rs.getInt("DOWNVOTE"));
				int author_id = rs.getInt("AUTHOR_ID");
				ps = con.prepareStatement(Constants.SELECT_AUTHOR_NAME_GIVEN_AUTHOR_ID);
				ps.setInt(1,author_id);
				ResultSet rs1 = ps.executeQuery();
				String author_name = "";
				while(rs1.next())
				{
					author_name = rs1.getString("AUTHOR_NAME");
				}
				table[4] = author_name;
				table[5] = rs.getString("PDF_LOC");
				resultVector.addElement(table);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return resultVector;
			}
		}
	}
	
	//-------------------------------------------------------------------------------------------------
}
