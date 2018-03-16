
<html>
<head>
    <title>PostIt</title>

    <link rel="stylesheet" href="postit.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<header id="upperBar">
    <p id="logo">Just say it!</p>
    <div id="login">
        <form action="login" method="POST" class="credentials">
            <input type="text" name="u" placeholder="username">
            <input type="password" name="p" placeholder="password">
            <input type="submit">
        </form>
        <a href="register.html" id="register">Register</a>
        <span id="alertMessage">
            <%
                Object ob = session.getAttribute("flag");
                if (ob != null && ob.equals(true)) {
            %>
            &emsp;&emsp;Something went wrong! Try again!
            <%}
            session.removeAttribute("flag");%>
        </span>
    </div>
</header>


<div id="sendSomething">
    <h3 id="something">Something on your mind?</h3>
    <input type="text" id="namePerson" name="namePerson" placeholder="Who?" list="suggestions"
          />
    <datalist id="suggestions">
        <option>
    </datalist>
    <br>
    <br>
    <textarea id="opinion" cols="30" rows="10" name="opinion" placeholder="Drop a line. Max 300 characters"></textarea>
    <br>
    <br>
    <div>Send this to: </div>
    <form action="index.jsp" method="post" id="suggestionsSendTo"> </form>
    <input type="button" id="add" value="Send your feedback" onClick="addOpinion()"/>
</div>

<script>
    function addOpinion() {          //trimitem parerea catre servlet
        var namePerson = document.getElementById('namePerson').value;
        var opinion = document.getElementById('opinion').value;

        var personsTo="";
        var nodes = document.getElementById("suggestionsSendTo").children;
        var checkedValues = [],
            length = nodes.length,
            i = 0;

        for(i;i<length;i++){
            if(nodes[i].checked)
            {
                checkedValues.push(nodes[i].value.trim());}
        }

        if(checkedValues.length > 0) {
            personsTo=checkedValues[0];
        }
        for(var j=1 ;j<checkedValues.length;j++) {
            personsTo += "," + checkedValues[j];
        }

        if (namePerson.trim().length > 0 && opinion.trim().length > 0 && personsTo.length>0) {
            $.ajax({
                url: 'postIt?action=write&namePerson=' + namePerson + '&opinion=' + opinion + '&personsTo=' + personsTo
            }).done(function (response) {
                location.href = "index.jsp";
            });
        }
        else {
            var alertDiv = document.createElement("p");
            alertDiv.setAttribute("id", "alertMessage")
            var alertContent = document.createTextNode("You must complete all fields!");
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
            addSendTo(response.users)
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
    
    function addSendTo(users) {
        var listUsers = document.getElementById('suggestionsSendTo')
        listUsers.innerHTML = '';

        for (var i = 0; i < users.length; i++) {
            var label = document.createElement('label');
            var radio = document.createElement("input");
            radio.setAttribute("type", "checkbox");
            radio.setAttribute("name", "users"+i);
            radio.setAttribute("value", users[i]);
            label.innerText=users[i];
            listUsers.appendChild(radio);
            listUsers.appendChild(label);
        }
    }
    getUsers();
</script>
</body>

</html>
