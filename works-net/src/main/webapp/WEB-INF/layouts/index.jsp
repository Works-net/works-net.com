<%--@elvariable id="title" type="String"--%>
<%--@elvariable id="additionalHeaders" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/main.css" />
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    ${additionalHeaders}
</head>
<body>
<%@ include file="../views/module/menu.jsp"%>
${body}
</body>
</html>