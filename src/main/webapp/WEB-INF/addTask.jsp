<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/11/2023
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body class="d-flex flex-column w-100 h-100">
<jsp:include page="../header.jsp"></jsp:include>
<% String message=(String) request.getAttribute("message");%>
<main>

    <form action="addTask" method="post" class="w-50">
        <div class="">
            <%=message==null?"":message %>
        </div>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">text</label>
            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   name="title">
        </div>
        <button  class="btn btn-primary" type="submit">Add</button>
    </form>
</main>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
