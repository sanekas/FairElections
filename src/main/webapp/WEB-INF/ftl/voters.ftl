<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>List of voters</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>List of voters</h1>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Vote</th>
        <th>Link</th>
    </tr>
    </thead>
    <tbody>
    <#list voters as voter>
    <tr>
        <td>${voter.id}</td>
        <td>${voter.vote}</td>
        <td>${voter.twin_voter_id}</td>
    </tr>
    </#list>
    </tbody>
</table>

</body>
</html>