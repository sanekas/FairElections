<!DOCTYPE html>
<html lang="en">

<#assign title = "Log in">
<#include "common/head.ftl">

<body>
<style scoped>
    body {
        background: url(https://pbs.twimg.com/media/CrCOIgAXgAEg9DD.jpg);
        background-size: cover;
    }
</style>
<h1 align="center"><span class="label label-info">Log in</span></h1>

<h4 align="center"><span class="label label-warning"> You can use: sysadm / demo </span></h4>

<form role="form" action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div align="center">
        <label for="username"><span class="label label-info"> Username </span></label>
        <input placeholder="Введите имя" class="form-control" style="width:200px" type="text" name="username"
               id="username" required autofocus/>
    </div>
    <div align="center">
        <label for="password"><span class="label label-info"> Password </span></label>
        <input placeholder="Введите пароль" class="form-control" style="width:200px" type="password" name="password"
               id="password" required/>
    </div>
    <div align="center" class="row">
        <button class="btn btn-success" type="submit">Sign in</button>
        <a href="/" class="btn btn-primary">Home</a>
    </div>
</form>

<#if error.isPresent()>
<p align="center"><span
        class="label label-danger">The username or password you have entered is invalid, try again.</span></p>
</#if>
</body>
</html>