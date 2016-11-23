<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <title>List of Voters</title>
</head>
<body>

<h1 align="center"><span class="label label-info"> List of Voters </span></h1>

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
<div align="center">
    <nav role="navigation">
        <a class="btn btn-primary" type="button" class="label" href="/">Home</a>
        <a class="btn btn-success" href="/user/create">Create a new user</a>
    </nav>
</div>
</body>
</html>