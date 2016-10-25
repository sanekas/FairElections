<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>Vote form</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Vote</h1>

<#if !currentUser.vote??>
<form enctype="multipart/form-data" method="post" action="/vote">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label>
        <input type="radio" value="1"/>
        1. Вася
    </label>
    <label>
        <input type="radio" value="2"/>
        2. Петя
    </label>
    <label>
        <input type="radio" value="3"/>
        3. Ваня
    </label>
    <button type="submit">Vote!</button>
</form>
</#if>
<#if currentUser.vote??>
Results!
</#if>

</body>
</html>