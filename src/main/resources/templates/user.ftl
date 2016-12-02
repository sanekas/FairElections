<!DOCTYPE html>
<html lang="en">
<#assign title = "User details">
<#include "common/head.ftl">
<body>

<div>
    <h1 align="center"><span class="label label-info">User details</span></h1>
</div >


<div align="center" >
    <nav role="navigation">
        <a href="/" class="btn btn-primary">Home</a>
    </nav>
</div>


<div align="center">
    <p><span class="label label-default"> Username: ${user.username} </span></p>
    <p><span class="label label-default"> Role: ${user.userRole} </span></p>
    <#if currentUser.role != "ADMIN" && twin??>
        <p><span class="label label-default"> Twin link: ${twin} </span></p>
    </#if>
</div>
</body>
</html>