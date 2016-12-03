<!DOCTYPE html>
<html lang="en">

<#assign title = "List of Voters">
<#include "common/head.ftl">

<body>
<div align="center">
    <h1><span class="label label-info"> List of Voters </span></h1>

    <table class="table table-hover">
        <thead>
        <tr class="text-primary">
            <th>Username</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <tr class="text-info">
            <td><a href="/user/${user.id}">${user.username}</a></td>
            <td>${user.userRole}</td>
        </tr>
        </#list>
        </tbody>
    </table>

    <nav role="navigation">
        <a class="btn btn-primary" type="button" href="/">Home</a>
        <a class="btn btn-success" href="/user/create">Create a new user</a>
    </nav>
</div>
</body>
</html>