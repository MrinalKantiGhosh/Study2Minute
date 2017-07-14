
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.study2minute.database.*" %>
    <%@ page import = "com.study2minute.utils.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show</title>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
      			<a class=" navbar-brand" href="#">Study2minuTe</a>
    		</div>
			
			      <%
			  		int subcatagory_id = 0;
			    	Vector<String[]> catagory_names = Database.selectAllCategoryName(Constants.SELECT_CATAGORY);
			    	for(int i=0; i<catagory_names.size(); i++)
			    	{
			      %>
			      	<ul class="nav navbar-nav">
				      	<ul class="nav nav-tabs">
				  			<li class="dropdown">
				    			<a class="dropdown-toggle" data-toggle="dropdown" href="#"><%=catagory_names.get(i)[0]%></a>
						    	
						    	<%
						    		Vector<String[]> subcatagory = Database.show_SUBCATAGORY_NAME_GIVEN_PARENT_CATAGORY_ID(catagory_names.get(i)[0]);	
						    			if(subcatagory.isEmpty())
						    			{}
						    			else
						    			{
						    				%>
						    					<ul class="dropdown-menu">
						    				<% 
						    				for(int j=0; j<subcatagory.size(); j++)
							    			{
							    			%>
							    					
							    					<li><a href="showSubCatagoryContent.jsp?subcatagory_id=<%=subcatagory.get(j)[1]%>" data-toggle="tab"><%=subcatagory.get(j)[0] %></a></li> 
							      					<!--  <li><a href="#subcatagory_content?subcatagory_id=<%=subcatagory.get(j)[1]%>" data-toggle="tab"><%=subcatagory.get(j)[0] %></a></li> -->
							      					<li class="divider"></li>
							      				
							    			<% 
							    			}
						    				%>
						    					</ul>
						    				<% 
						    				
						    			}		
						    	%>
				  			</li>
						</ul>
					</ul>
			      <%
			    	}
			      %>
			    </a>
		</div>
	</nav>
	
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>
