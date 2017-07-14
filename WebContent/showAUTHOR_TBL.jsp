<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.study2minute.database.*" %>
    <%@ page import = "com.study2minute.utils.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Author Table</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/materialize.css">
</head>
<body>
	<section>
		<table class="highlight bordered">
			<thead>
				<tr>
					<th>Author Id</th>
					<th>Author Name</th>
					<th>Phone Number</th>
					<th>Email-id</th>
					<th>Bank Name</th>
					<th>Branch name</th>
					<th> Account Number</th>
					<th>Account Type</th>
					<th>IFSC Number</th>
				</tr>
			</thead>
			<tbody>
				<%
					Vector<String[]> showAuthor = Database.show_AUTHOR_TBL(Constants.SELECT_ALL_AUTHOR);
					int length = showAuthor.size();
					for(int i=0; i<length; i++)
					{
						%>
							<tr>
								<td><%= showAuthor.get(i)[0] %></td>
								<td><%= showAuthor.get(i)[1] %></td>
								<td><%= showAuthor.get(i)[2] %></td>
								<td><%= showAuthor.get(i)[3] %></td>
								<td><%= showAuthor.get(i)[4] %></td>
								<td><%= showAuthor.get(i)[5] %></td>
								<td><%= showAuthor.get(i)[6] %></td>
								<td><%= showAuthor.get(i)[7] %></td>
								<td><%= showAuthor.get(i)[8] %></td>
							</tr>
						<%
					}
				%>
			</tbody>
		</table>
	</section>
</body>
</html>