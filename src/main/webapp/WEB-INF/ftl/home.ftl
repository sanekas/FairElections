<!DOCTYPE html>
<html lang="en">

<#assign title = "Home page">
<#include "common/head.ftl">

<body>
<style scoped>
    body {
        background: url(http://newsl.org/wp-content/uploads/2015/07/donald_trump_108984675_1.jpg);
        background-size: cover;
    }
</style>
<h1 align="center"><span class="label label-info">Welcome to fair elections!</span></h1>
<nav role="navigation">
    <ul>
    <#if !currentUser??>
        <a href="/login" class="btn btn-primary" style="width:150px">Log in</a><br/>
    </#if>
    <#if currentUser??>
        <div>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary" style="width:150px">Log out</button>
            </form>
        </div>
        <br/>
        <div><a href="/user/${currentUser.id}" class="btn btn-primary" style="width:150px">View myself</a></div>
        <br/>
    </#if>
    <#if currentUser?? && currentUser.role == "ADMIN">
        <div><a href="/user/create" class="btn btn-primary" style="width:150px">Create a new user</a></div>
        <br/>
        <div><a href="/users" class="btn btn-primary" style="width:150px">View all users</a></div>
        <br/>
        <div><a href="/voters" class="btn btn-primary" style="width:150px">View voters</a></div>
        <br/>
    </#if>
    <#if currentUser?? && (currentUser.role == "ADMIN" || currentUser.role == "VOTER")>
        <div><a href="/vote" class="btn btn-primary" style="width:150px">Vote</a></div>
    </#if>
    </ul>
</nav>
</body>
</html>