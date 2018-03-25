<html>
<head>
    <title>PostIt</title>

    <link rel="stylesheet" href="postit.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<header id="upperBar">
    <p id="logo">Just say it!</p>
    <div id="login">
        <form action="login" method="POST" class="credentials">
            <input type="text" name="u" placeholder="Username" class="credential" required>
            <input type="password" name="p" placeholder="Password" class="credential" required>
            <button type="submit">Login</button>
        </form>
        <a href="register.jsp" id="register">Register</a>
        <p id="alertMessage" style="display: none">Something went wrong!</p>
    </div>
</header>

<div id="rules">
    <h1 id="rulesTop">Keep in mind:</h1>
    <h2 class="rule">Positive feedback is eqally as important as critical feedback!</h2>
    <h2 class="rule">Focus on the situation, not the person!</h2>
    <h2 class="rule">Give recommendations on how to improve!</h2>
    <h2 class="rule">Most importantly, DON'T BE RUDE!</h2>
    <img src="http://happyoffice.nl/wp-content/uploads/2017/03/Screen-Shot-2017-03-27-at-14.58.08.png" style="width: 55%">
</div>

<div id="sendSomething">
    <h3 id="something">Something on your mind?</h3>
    <input type="text" id="namePerson" name="namePerson" placeholder="About?" list="suggestions"
    />
    <datalist id="suggestions">
        <option>
    </datalist>
    <br>
    <textarea id="opinion" cols="30" rows="10" name="opinion" placeholder="Drop a line. Max 300 characters"></textarea>
    <br>
    <div style="font-weight: bold">Send this to:</div>

    <input type="text" id="sendTo" name="namePerson" placeholder="Who?" list="suggestions"
    />
    <button id="addSendTo" value="AddPerson" onClick="addTo()">Add</button>

    <datalist id="sendToSuggestions">
        <option>
    </datalist>

    <div id="personsTo"></div>

    <button id="add" value="Send your feedback" onClick="addOpinion()">Send feedback</button>
    <span id="alertMessageInput" style="display: none">You must complete all fields!</span>
</div>

<script>
    function addTo() {
        var user = document.getElementById('sendTo').value;
        var person = document.getElementById('personsTo');

        if (user.trim() !== "") {
            var personSpace = document.createElement("div");
            personSpace.setAttribute("name", user);
            personSpace.setAttribute("value", user);
            personSpace.setAttribute("id", user);
            personSpace.setAttribute("class", "allPersonsTo");
            personSpace.setAttribute("onClick", "deletePerson(\'" + user + "\')");
            personSpace.innerText = user;
            person.appendChild(personSpace);
        }
    }

    function deletePerson(user) {
        var elem = document.getElementById(user);
        elem.remove();
    }

    function addOpinion() {          //trimitem parerea catre servlet
        var namePerson = document.getElementById('namePerson').value;
        var opinion = document.getElementById('opinion').value;

        var personsTo = "";
        var nodes = document.getElementsByClassName("allPersonsTo");
        var checkedValues = [],
            length = nodes.length,
            i = 0;

        for (i; i < length; i++) {
            checkedValues.push(nodes[i].innerText.trim());
        }

        if (checkedValues.length > 0) {
            personsTo = checkedValues[0];
        }
        for (var j = 1; j < checkedValues.length; j++) {
            personsTo += "," + checkedValues[j];
        }
        console.log(personsTo);

        if (namePerson.trim().length > 0 && opinion.trim().length > 0 && opinion.length <= 300 && personsTo.length > 0) {
            $.ajax({
                url: 'postIt?action=write&namePerson=' + namePerson + '&opinion=' + opinion + '&personsTo=' + personsTo
            }).done(function (response) {
                location.href = "index.jsp";
            });
        }
        else {
            var x = document.getElementById("alertMessageInput");
            x.style.display = "block";
        }
    }

    function loginFailed() {
        <%
               Object ob = session.getAttribute("flag");
               if (ob != null && ob.equals(true)) {
           %>
        var x = document.getElementById("alertMessage");
        x.style.display = "block";
        <%}
        session.removeAttribute("flag");%>
    }

    loginFailed();

    function getUsers() {
        $.ajax({
            url: 'getUsers?'
        }).done(function (response) {
            putUsers(response.users);
//            addSendTo(response.users)
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

    //    function addSendTo(users) {
    //        var listUsers = document.getElementById('suggestionsSendTo');
    //        listUsers.innerHTML = '';
    //
    //        for (var i = 0; i < users.length; i++) {
    //            var label = document.createElement('label');
    //            var radio = document.createElement("input");
    //            radio.setAttribute("type", "checkbox");
    //            radio.setAttribute("name", "users"+i);
    //            radio.setAttribute("value", users[i]);
    //            label.innerText=users[i];
    //            listUsers.appendChild(radio);
    //            listUsers.appendChild(label);
    //        }
    //    }
    getUsers();


</script>
</body>

</html>
