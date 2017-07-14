<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.study2minute.database.*" %>
    <%@ page import="com.study2minute.utils.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Article Table</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/materialize.css">
</head>
<body>
	<section>
		<table class="highlight bordered">
			<thead>
				<tr>
					<th>Article Id</th>
					<th>Article Title</th>
					<th>Catagory Name</th>
					<th>Sub-Catagory Name</th>
					<th> Author Name</th>
					<th>Upload Time</th>
					<th>Read Count</th>
					<th>Like</th>
					<th>Dislike</th>
					<th>Location</th>
					<th>Show Article</th>
				</tr>
			</thead>
			
			<tbody>
				<%
					String pdf_loc = "";
					Vector<String[]> showArticle = Database.showARTICLE_TBL(Constants.SELECT_ALL_ARTICLE);
					int length = showArticle.size();
					for(int i=0; i<length; i++)
					{
				%>
					<tr>	
						<td><%= showArticle.get(i)[0] %></td>
						<td><%= showArticle.get(i)[3] %></td>
						<td><%= showArticle.get(i)[1] %></td>
						<td><%= showArticle.get(i)[2] %></td>
						<td><%= showArticle.get(i)[4] %></td>
						<td><%= showArticle.get(i)[5] %></td>
						<td><%= showArticle.get(i)[6] %></td>
						<td><%= showArticle.get(i)[7] %></td>
						<td><%= showArticle.get(i)[8] %></td>
						<td><%= showArticle.get(i)[9] %></td>
						<td><a href="#showArticle" class="button">Show</a></td>
					</tr>
				<% 
					if(i==length-1)
						pdf_loc = showArticle.get(i)[9];
					}
				%>
			</tbody>
			
		</table>
	</section>
	<%=pdf_loc %>
	<embed id="showArticle" type='application/pdf'>
	<script>
		var  showArticle = document.getElemenById("showArticle");
		showArticle.setAttribute("src",pdf_loc);
	</script>
	
	

</body>
</html>