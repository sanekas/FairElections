<!DOCTYPE html>
<html lang="en">

<#assign title = "Vote">
<#include "common/head.ftl">

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