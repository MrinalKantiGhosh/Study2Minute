<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="com.study2minute.database.*" %>
 <%@ page import="com.study2minute.utils.*" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catagory Table</title>
 <link rel="stylesheet" type="text/css" href="css/style.css">
 <link rel="stylesheet" type="text/css" href="css/materialize.css">
</head>
<body>
	<section class="viewCatagory">
			<table class="highlight bordered">
				<thead>
					<tr>
						<th>Category ID</th>
						<th>Category Name</th>
					</tr>
				</thead>
				<tbody>	
					<%
					Vector<String[]> showCatagory = Database.show_CATAGORY_TBL(Constants.SELECT_ALL_CATEGORY);
					int length = showCatagory.size();
					for(int i=0; i<length; i++)
					{
					%>
					<tr>
						<td><%=  showCatagory.get(i)[0] %></td>
						<td><%= showCatagory.get(i)[1] %></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
	</section>
	
</body>
</html>