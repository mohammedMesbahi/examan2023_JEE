<%@page import="java.util.List"%>
<%@ page import="com.example.examan2023.beans.Task" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%List<Task> tasks= (List<Task>) request.getAttribute("tasks"); %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<main>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>text</th>
            <th>options</th>
        </tr>
        </thead>
        <tbody>
        <%for(Task task:tasks){ %>
        <tr>
            <td><%=task.getId() %></td>
            <td><%=task.getTitle()%></td>
            <td><a href="deleteTask?id=<%=task.getId() %>">X</a></td>
            <td><a href="moveTaskUp?id=<%=task.getId() %>">up</a></td>
            <td><a href="moveTaskDown?id=<%=task.getId() %>">down</a></td>

        </tr>
        <%} %>
        </tbody>
    </table>
</main>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>