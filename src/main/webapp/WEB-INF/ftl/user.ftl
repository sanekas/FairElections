<!DOCTYPE html>
<html lang="en">
<#assign title = "User details">
<#include "common/head.ftl">
<body>
<style scoped>
    body {
        background: url(http://orensmi.ru/wp-content/uploads/2016/11/trumpvsclinton.jpg);
        background-size: cover;
    }
</style>
<div>
    <h1 align="center"><span class="label label-info">User details</span></h1>
</div>
<nav role="navigation" align="center">
    <a href="/" class="btn btn-primary">Home</a>
</nav>
<div align="center">
    <p><span class="label label-default"> Username: ${user.username} </span></p>
    <p><span class="label label-default"> Role: ${user.userRole} </span></p>
</div>
</body>
</html>