<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<#assign title = "Create new user">
<#include "common/head.ftl">
<body>
<div align="center">

    <h1><span class="label label-info"> Create a new user </span></h1>

    <form role="form" name="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <label for="username"><span class="label label-info"> Username </span></label>
            <input class="form-control" style="width:200px" placeholder="Введите имя" type="text" name="username"
                   id="username" value="${form.username}" required autofocus/>
        </div>
        <div>
            <label for="password"><span class="label label-info"> Password </span></label>
            <input class="form-control" style="width:200px" placeholder="Введите пароль" type="password" name="password"
                   id="password" required/>
        </div>
        <div>
            <label for="passwordRepeated"><span class="label label-info"> Repeat </span></label>
            <input class="form-control" style="width:200px" placeholder="Повторите пароль" type="password"
                   name="passwordRepeated" id="passwordRepeated" required/>
        </div>
        <div>
            <label for="userRole"><span class="label label-info"> Role </span></label>
            <select name="userRole" id="userRole" required>
                <option <#if form.userRole == 'VOTER'>selected</#if>>VOTER</option>
                <option <#if form.userRole == 'ADMIN'>selected</#if>>ADMIN</option>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-success">Create</button>
            <nav class="btn btn-primary" role="navigation">
                <a href="/">Home</a>
            </nav>
        </div>
    </form>

    <@spring.bind "form" />
    <#if spring.status.error>
    <ul>
        <#list spring.status.errorMessages as error>
            <li class="label label-danger">${error}</li>
        </#list>
    </ul>
    </#if>
</div>
</body>
</html>