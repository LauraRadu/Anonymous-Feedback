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


<head>
    <meta charset="UTF-8">
    <title>Opinions</title>
    <link rel="stylesheet" href="opinions.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div id="upperBar">
    <div id="welcome">
        <p>Welcome, <%=session.getAttribute("username")%>
        </p>
        <a href="logout">Logout</a>
    </div>
    <p id="logo">Just say it!</p>
</div>

<div id="separate"></div>

<div id="posts">
</div>

<script>
    function getPosts() {          //trimitem parerea catre servlet
        $.ajax({
            url: 'postIt?action=read'
        }).done(function (response) {
            putPostsInHTML(response.posts);
        });
    }

    function putPostsInHTML(posts) {
        var divPost = document.getElementById('posts');
        var ol = document.getElementById('posts');
        var list = '';
        for (var i = 0; i < posts.length; i++) {
            var name = posts[i].name;
            var opinion = posts[i].opinion;
            var date = posts[i].date;
            var hour = posts[i].hour;
            var allOpinions = '<div class="posts"><h3 id="postsForOneUser" style="cursor: pointer" onclick="postsForUser()">' + name + '</h3><p>' + opinion + '</p><p class="date">' + date + '</p><p class="hour">' + hour + '</p></div>';
            list = list + allOpinions;
        }
        ol.innerHTML = list;
    }
    getPosts();

    function postsForUser() {
        var name = document.getElementById("postsForOneUser");
        $.ajax({
            url: 'postsForOneUser' + name
        }).done(function (response) {
            location.href = "postsUser.jsp";
        });
    }
</script>
</body>
</html>