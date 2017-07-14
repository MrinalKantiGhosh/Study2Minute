<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.study2minute.database.*" %>
    <%@ page import = "com.study2minute.utils.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>
	<%System.out.println(request.getParameter("subcatagory_id")); %>
	<div id="myTabContent" class="tab-content">
  		<div class="tab-pane fade active in" id="home">
    		
      	</div>
		  <%
		  
		  int subcatagory_id = Integer.parseInt(request.getParameter("subcatagory_id"));
		  String subcatagory_name = Database.show_SUBCATAGORY_NAME_GIVEN_SUBCATAGORY_ID(subcatagory_id);
		  %>
		  <div class="tab-pane fade" id="dropdown">
		  <center><u><%=subcatagory_name%></u></center>
		  <table class="table table-hover">
		   <thead>
		      <tr>
		        <th>Article Title </th>
		        <th>Author Name </th>
		        <th>Read Count </th>
		        <th>Like </th>
		        <th>Dislike </th>
		        <th>PDF</th>
		      </tr>
			</thead>
			<tbody>
		    
		    <%
		    	Vector<String[]> articleTable = Database.show_ARTICLE_LIST_FROM_SUBCATAGORY_ID(subcatagory_id);
		    	for(int i=0; i<articleTable.size(); i++)
		    	{
		    		%>
		    			<tr>
		    				<td><%= articleTable.get(i)[0]%></td>
		    				<td><%= articleTable.get(i)[4]%></td>
		    				<td><%= articleTable.get(i)[1]%></td>
		    				<td><%= articleTable.get(i)[2]%></td>
		    				<td><%= articleTable.get(i)[3]%></td>
		    				<td><a href="<%= articleTable.get(i)[5]%>">Show</a></td>
		    			</tr>
		    		<% 
		    	}
		    %>
		    </tbody>
		   </table>
		  </div>
	</div>
	
	
		
	
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>