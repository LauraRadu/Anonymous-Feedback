<html>
<head>
    <title>PostIt</title>

    <link rel="stylesheet" href="postit.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div id="upperBar">
    <p id="logo">Just say it!</p>
    <form action="login" method="POST" class="credentials">
        <input type="text" name="u" placeholder="username">
        <input type="password" name="p" placeholder="password">
        <input type="submit">
    </form>
    <a href="register.html">Register</a>
</div>



<div id="sendSomething">
    <h3 id="something">Something on your mind?</h3>
    <input type="text" id="namePerson" name="namePerson" placeholder="Who?" list="suggestions" onmouseover="getUsers()"/>
    <datalist id="suggestions">
        <option>
    </datalist>
    <br>
    <br>
    <textarea id="opinion" cols="30" rows="10" name="opinion" placeholder="Just say it!"> </textarea>
    <br>
    <br>
    <input type="button" id="add" value="Send your feedback" onClick="addOpinion()"/>
</div>

<script>
    function addOpinion() {          //trimitem parerea catre servlet
        var namePerson = document.getElementById('namePerson').value;
        var opinion = document.getElementById('opinion').value;
        if (namePerson.trim().length > 0 && opinion.trim().length > 0) {
            $.ajax({
                url: 'postIt?action=write&namePerson=' + namePerson + '&opinion=' + opinion
            }).done(function (response) {
                location.href = "index.jsp";
            });
        }
        else {
            var alertDiv = document.createElement("p");
            alertDiv.setAttribute("id", "alertMessage")
            var alertContent = document.createTextNode("You must write your opinion!");
            alertDiv.appendChild(alertContent);
            var fieldsDiv = document.getElementById("sendSomething");
            fieldsDiv.appendChild(alertDiv);
        }
    }

    function getUsers() {
        $.ajax({
            url: 'getUsers?'
        }).done(function (response) {
            putUsers(response.users);
        });
    }
    function putUsers(users) {
        var listUsers = document.getElementById('suggestions')
        listUsers.innerHTML = '';
        for (var i = 0; i < users.length; i++) {

            var option = document.createElement("option");
            option.value = users[i];
            option.text = users[i];
            listUsers.appendChild(option);
        }

    }
</script>
</body>

</html>
