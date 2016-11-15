<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <title>Create a new user</title>
    <style>
        body { background: url(https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Donald_Trump_and_Hillary_Clinton_during_United_States_presidential_election_2016.jpg/1280px-Donald_Trump_and_Hillary_Clinton_during_United_States_presidential_election_2016.jpg);
            background-size: cover;
        }
    </style>
</head>
<body>
    <h1 align="center"><span class="label label-info"> Create a new user </span></h1>

    <form role="form" name="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div align="center">
            <label for="username"><span class="label label-info"> Username </span></label>
            <input class="form-control" style="width:200px" placeholder="Введите имя" type="text" name="username" id="username" value="${form.username}" required autofocus/>
        </div>
        <div align="center">
            <label for="password"><span class="label label-info"> Password </span></label>
            <input class="form-control" style="width:200px" placeholder="Введите пароль" type="password" name="password" id="password" required/>
        </div>
        <div align="center">
            <label for="passwordRepeated"><span class="label label-info"> Repeat </span></label>
            <input class="form-control" style="width:200px" placeholder="Повторите пароль" type="password" name="passwordRepeated" id="passwordRepeated" required/>
        </div>
        <div align="center">
            <label for="userRole"><span class="label label-info"> Role </span></label>
            <select  name="userRole" id="userRole" required>
                <ul>
                    <li><option <#if form.userRole == 'VOTER'>selected</#if>>VOTER</option></li>
                    <li><option <#if form.userRole == 'ADMIN'>selected</#if>>ADMIN</option></li>
                </ul>
            </select>
        </div>
        <div align="center">
            <button align="center" type="submit" class="btn btn-success">Create</button>
            <nav class="btn btn-primary" role="navigation">
                <a href="/" class="label">Home</a>
            </nav>
        </div>
    </form>

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