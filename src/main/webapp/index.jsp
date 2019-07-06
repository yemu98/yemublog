<%--
  Created by IntelliJ IDEA.
  User: main.yemu
  Date: 19-6-10
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%=request.getContextPath()%>
<%response.sendRedirect("html/index.html");%>
</body>
</html>
