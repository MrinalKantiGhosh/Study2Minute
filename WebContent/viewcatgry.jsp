<%@page import="java.util.Vector"%>
<%@page import="com.study2minute.utils.Constants"%>
<%@page import="com.study2minute.database.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Category</title>
</head>
<body>
	<table>
		<th>
			<td>Category Id</td>
			<td>Category</td>
		</th>
		<%
		Vector<String[]> results = Database.selectAllCategory(Constants.SELECT_ALL_CATEGORY);
		int length = results.size();
		for(int i = 0; i<length;i++)
		{%>
			<tr>
				<td><%=results.get(i)[0] %> </td>
				
				<td><%=results.get(i)[1] %> </td>
			</tr>
			
		<%
		}
		%>
	</table>

</body>
</html>