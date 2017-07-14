package com.study2minute.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study2minute.database.Database;
import com.study2minute.utils.Constants;

/**
 * Servlet implementation class Author_Save_Serv
 */
@WebServlet("/Author_Save_Serv")
public class Author_Save_Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Author_Save_Serv() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServarg1letRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String author_name = request.getParameter("author_name");
		String phone_number = request.getParameter("phone_number");
		String email_id = request.getParameter("email_id");
		String bank_name = request.getParameter("bank_name");
		String branch_name = request.getParameter("branch_name");
		String account_number = request.getParameter("account_number");
		String account_type = request.getParameter("account_type");
		String IFSC_number = request.getParameter("IFSC_number");
		
		if(Database.insert_AUTHOR_TBL(Constants.INSERT_AUTHOR_TBL, author_name, phone_number, email_id, bank_name, branch_name, account_number, account_type, IFSC_number))
		{
			out.println("<i>New Author is added Successfully</i>");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		
		else
		{
			out.println("<i>Author is Not Inserted</i>");
			request.getRequestDispatcher("index.html").include(request, response);
		}
	}

}
