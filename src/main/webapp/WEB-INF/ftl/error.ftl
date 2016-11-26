<!DOCTYPE html>
<html lang="en">

<#assign title = "Error">
<#include "common/head.ftl">

<body>
<h1 align="center"><span class="label label-info">
<#if clientError.title>
    ${clientError.title}
<#else>
    An error occurred
</#if>
</span></h1>

<div align="center">
<#if clientError.message>
${clientError.message}
<#else>
    Don't worry, we already know about it.
</#if>
</div>

<div align="center">
    <nav role="navigation">
        <a class="btn btn-primary" href="/">Home</a>
    </nav>
</div>

</body>
</html>