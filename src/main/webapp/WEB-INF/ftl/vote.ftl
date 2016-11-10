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

<#import "/spring.ftl" as spring/>
<@spring.bind "candidates" />

<form action="/vote" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <@spring.formSingleSelect "form.vote", candidates, "<br>" />
    <input type="submit" value="submit"/>
</form>

<@spring.bind "form" />
<#if spring.status.error>
    <ul>
        <#list spring.status.errorMessages as error>
            <li>${error}</li>
        </#list>
    </ul>
</#if>
</body>
</html>