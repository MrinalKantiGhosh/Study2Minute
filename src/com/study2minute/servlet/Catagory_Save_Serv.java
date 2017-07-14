package com.study2minute.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study2minute.database.Database;
import com.study2minute.utils.Constants;

/**
 * Servlet implementation class Catagory_Save
 */
@WebServlet("/Catagory_Save_Serv")
public class Catagory_Save_Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catagory_Save_Serv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		String catagory_name = request.getParameter("catagory_name");
		if(Database.insert_CATAGORY_TBL(Constants.INSERT_CATAGORY_TBL, catagory_name))
		{
			out.println("<i>New Catagory is inserted Successfully</i>");
			rd.include(request, response);
		}
		
		else
		{
			out.println("<i>Catagory is not inserted</i>");
			rd.include(request, response);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
