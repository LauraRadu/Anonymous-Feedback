<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="register.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div id="upperBar">
    <p id="logo">Just say it!</p>
</div>


<form action="register" method="POST" class="credentials">
    <table id="register">
        <tr>
            <td>Work e-mail: </td>
            <td><input type="text" name="email" class="data" placeholder="E-mail" required></td>
        </tr>

        <tr>
            <td>Full name: </td>
            <td> <input type="text" name="u" class="data" placeholder="Name" required></td>
        </tr>

        <tr>
            <td>Password: </td>
            <td><input type="password" name="p" class="data" placeholder="Password" id="password1" required></td>
        </tr>

        <tr>
            <td>Retype password: </td>
            <td><input type="password" name="rp" class="data" placeholder="Password" id="password2" required></td>
        </tr>

        <tr>
            <td></td>
            <td><button type="submit" id="submit">Register</button></td>
        </tr>
    </table>
</form>
<div id="alertMessage" style="display: none">Something went wrong! E-mail or name already in use!</div>

<script>
    var password = document.getElementById("password1");
    var confirm_password = document.getElementById("password2");

    function verifyCredentials() {
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = verifyCredentials;
    confirm_password.onkeyup = verifyCredentials;

    function registrationFailed() {
    <%
        Object ob = session.getAttribute("flagRegister");
        if (ob != null && ob.equals(true)) {
        %>
            var x = document.getElementById("alertMessage");
            x.style.display = "block";
        <%}
        session.removeAttribute("flagRegister");%>
    }
    registrationFailed();

</script>
</body>

</html>