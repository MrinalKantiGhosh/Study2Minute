package com.study2minute.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study2minute.database.Database;
import com.study2minute.utils.Constants;

/**
 * Servlet implementation class InitDBServ
 */
//@WebServlet(name="InitDBServ",urlPatterns="/InitDBServ",loadOnStartup=1)
public class InitDBServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDBServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		boolean catTable = false;
		boolean subCatTable = false;
		boolean artTable = false;
		boolean authTable = false;
		System.out.println("Servlet is initialized");
		Vector<String> tables = Database.getCurrentTables();
		for(int i = 0; i<tables.size();i++)
		{
			if(tables.get(i).equals("CATAGORY_TBL"))
				catTable = true;
			if(tables.get(i).equals("SUBCATAGORY_TBL"))
				subCatTable = true;
			if(tables.get(i).equals("ARTICLE_TBL"))
				artTable = true;
			if(tables.get(i).equals("AUTHOR_TBL"))
				authTable = true;
		}
		if(!catTable)
			Database.createTable(Constants.CREATE_CATAGORY_TBL);
		if(!subCatTable)
			Database.createTable(Constants.CREATE_SUBCATAGORY_TBL);
		if(!artTable)
			Database.createTable(Constants.CREATE_AUTHOR_TBL);
		if(!authTable)
			Database.createTable(Constants.CREATE_ARTICLE_TBL);
		
	}
}
