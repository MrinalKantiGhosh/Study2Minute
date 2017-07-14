package com.study2minute.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.study2minute.database.Database;
import com.study2minute.utils.Constants;

/**
 * Servlet implementation class Article_Save_Serv
 */
@WebServlet("/Article_Save_Serv")
public class Article_Save_Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Article_Save_Serv() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 4 * 1024 * 1024;
    private int maxMemSize = 4 * 1024;
    String servFilePath = null;
    
    /*StringBuffer subcatagory_id = new StringBuffer();
    StringBuffer article_title = new StringBuffer();
    StringBuffer author_id = new StringBuffer();*/
    
    String subcatagory_id = new String();
	String article_title = new String();
	String author_id = new String();
    
    public void init()
    {
    	filePath = Constants.UPLOAD_PATH;
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(!isMultipart)
		{
			//System.out.println("reached1");
			response.sendRedirect("errorPage.html");
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("/home/mrinal/tempUploadFile"));
		
		//System.out.println("reached2");
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> i = fileItems.iterator();
			
		    //System.out.println("reached3");
		    while(i.hasNext())
		    {
		    	FileItem fi = i.next();
		    	if(!fi.isFormField())
		    	{
		    		//System.out.println("reached4");
		    		String fileName = fi.getName();
		    		
		    		if(fileName.lastIndexOf("/") >= 0)
		    		{
		    			 servFilePath = filePath+ "/" + fileName.substring(fileName.lastIndexOf("/"));
		    			 //System.out.println("reached5");
		    		}
		    		else
		    		{
		    			servFilePath = filePath +  "/" + fileName.substring(fileName.lastIndexOf("/") + 1);
		    			//System.out.println("reached6");
		    		}
		    		File file = new File(servFilePath);
		    		try {
		    			//System.out.println("reached7");
						fi.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		//System.out.println("reached8");
		    		out.println("Uploaded File Path = "+ servFilePath +"<br>");
		    	}
		    	
		    	else{
		    		String fieldName = fi.getFieldName();
		    		System.out.println(fieldName);
		    		System.out.println("reached9");
		    		if(fieldName.equals("subcatagory_id"))
		    			subcatagory_id = fi.getString();
		    		
		    		if(fieldName.equals("article_title"))
		    			article_title = fi.getString();
		    		
		    		if(fieldName.equals("author_id"))
		    			author_id = fi.getString();
		    		
		    		//System.out.println(" Article Save->" + subcatagory_id + "   " + article_title + "   " + author_id + "   " + servFilePath);
		    		//System.out.println("value = " + fi.getString());
		    	}
		   
		    }
		    
		    //System.out.println("reached10");
		    
    		if(Database.insert_ARTICLE_TBL(Constants.INSERT_ARTICLE_TBL, subcatagory_id, article_title, author_id, servFilePath))
    		{
    			out.println("<i>New Article Inserted Successfully</i>");
    			request.getRequestDispatcher("index.html").include(request, response);
    		}

    		else
    		{
    			out.println("Article is not inserted");
    			request.getRequestDispatcher("index.html").include(request, response);
    		}
		    
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}

}



