<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
<%@ page import="com.study2minute.database.*" %> 
<%@ page import="com.study2minute.utils.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sub-Catagory Table</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
 <link rel="stylesheet" type="text/css" href="css/materialize.css">
</head>
<body>
	<section>
		<table class="highlight bordered">
			<thead>
				<tr>
					<th>Sub-Catagory Id</th>
					<th>Sub-Catagory Name</th>
					<th>Parent Catgory Name</th>
				</tr>
			</thead>
			<tbody>
			<%
				Vector<String[]> showSubcatagory = Database.show_SUBCATAGORY_TBL(Constants.SELECT_ALL_SUBCATAGORY);
				int length = showSubcatagory.size();
				for(int i=0; i<length; i++)
				{
				%>
					<tr>
						<td><%=showSubcatagory.get(i)[0] %></td>
						<td><%=showSubcatagory.get(i)[1] %></td>
						<td><%=showSubcatagory.get(i)[2] %></td>
					</tr>
				<% 
				}
			%>
			</tbody>
		</table>
	</section>
</body>
</html>