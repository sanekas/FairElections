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
        <#if electionsState??>
            <div>
                <span class="label label-info">
                ${electionsState}
                </span>
            </div>
        </#if>
        <div>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary" style="width:150px">Log out</button>
            </form>
        </div>
        <div><a href="/user/${currentUser.id}" class="btn btn-primary" style="width:150px">View myself</a></div>

        <#if currentUser.role == "ADMIN">
            <div><a href="/user/create" class="btn btn-primary" style="width:150px">Create a new user</a></div>
            <div><a href="/users" class="btn btn-primary" style="width:150px">View all users</a></div>
            <div><a href="/voters" class="btn btn-primary" style="width:150px">View voters</a></div>
            <#if electionsButton??>
                <div>
                    <form action="/updateElectionsState" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary" style="width:150px">${electionsButton}</button>
                    </form>
                </div>
            </#if>
        </#if>
        <#if currentUser.role == "VOTER">
            <div><a href="/vote" class="btn btn-primary" style="width:150px">Vote</a></div>
        </#if>
    </#if>
    </ul>
</nav>
</body>
</html>