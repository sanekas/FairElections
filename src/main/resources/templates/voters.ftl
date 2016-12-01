<!DOCTYPE html>
<html lang="en">

<#assign title = "List of voters">
<#include "common/head.ftl">

<body>

<h1 align="center"><span class="label label-info">List of voters</span></h1>

<table class="table table-hover">
    <thead>
    <tr class="text-primary">
        <th>Id</th>
        <th>Vote</th>
        <th>Link</th>
    </tr>
    </thead>
    <tbody>
    <#list voters as voter>
    <tr class="text-info">
        <td>${voter.id}</td>
        <td>${voter.vote}</td>
        <td>${voter.twinVoterId}</td>
    </tr>
    </#list>
    </tbody>
</table>

<div align="center">
    <nav role="navigation">
        <a type="button" href="/" class="btn btn-primary">Home</a>
    </nav>
</div>

</body>
</html>