<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <title>Vote form</title>
    <style>
        body { background: url(https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Donald_Trump_and_Hillary_Clinton_during_United_States_presidential_election_2016.jpg/1280px-Donald_Trump_and_Hillary_Clinton_during_United_States_presidential_election_2016.jpg);
            background-size: cover;
        }
    </style>
</head>
<body>
<h1 align="center"><span class="label label-info"> It's time to choose </span></h1><br/>

<#import "/spring.ftl" as spring/>
<@spring.bind "candidates" />

<div align="center">
    <form action="/vote" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <@spring.formSingleSelect "form.vote", candidates, "<br>" />
        <input class="btn btn-success" type="submit" value="submit"/>
    </form>
</div><br/>

<div align="center">
    <nav role="navigation">
        <a class="btn btn-primary" href="/">Home</a>
    </nav>
</div>

<@spring.bind "form" />
<#if spring.status.error>
    <ul align="center">
        <#list spring.status.errorMessages as error>
            <li class="label label-danger">${error}</li>
        </#list>
    </ul>
</#if>
</body>
</html>