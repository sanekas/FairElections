<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>User details</title>
        <style>
            body { background: url(http://orensmi.ru/wp-content/uploads/2016/11/trumpvsclinton.jpg);
                background-size: cover;
            }
        </style>
    </head>
    <body>
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