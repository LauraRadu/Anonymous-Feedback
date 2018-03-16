<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession s = request.getSession();
    session.removeAttribute("flag");
    Object o = s.getAttribute("userid");
    if(o==null)
    {
        response.sendRedirect("index.jsp");
    }
%>

<html>
<head>
    <title>Posts</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="opinions.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div id="upperBar">
    <div id="welcome">
        <p>Welcome, <%=session.getAttribute("username")%>
        </p>
        <a href="index.jsp">Logout</a>
    </div>
    <p id="logo">Just say it!</p>
</div>

<div id="separate"></div>

<div id="posts">
</div>
</body>
</html>
