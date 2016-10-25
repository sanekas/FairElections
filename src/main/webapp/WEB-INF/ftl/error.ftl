<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Error page</title>
</head>
<body>
<h1 align="center">Sorry, an error occurred! We already know about it.</h1>
<nav role="navigation">
    <ul>
    <#if !currentUser??>
        <li><a href="/login">Log in</a></li>
    </#if>
    <#if currentUser??>
        <li>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Log out</button>
            </form>
        </li>
        <li><a href="/user/${currentUser.id}">View myself</a></li>
    </#if>
    <#if currentUser?? && currentUser.role == "ADMIN">
        <li><a href="/user/create">Create a new user</a></li>
        <li><a href="/users">View all users</a></li>
    </#if>
    </ul>
</nav>
</body>
</html>