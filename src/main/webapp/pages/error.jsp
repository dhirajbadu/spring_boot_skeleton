<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 8/25/19
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ERROR ${code}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminLTE.min.css">
</head>
<body style="background-color: #ecf0f5">
<div class="error-page">
    <div class="login-box-body">

        <c:if test="${message ne null}">
            <h2>${message}</h2>
        </c:if>

        <c:if test="${code ne null}">

            <p>Code :
            <h2 class="headline text-red">${code}</h2></p>
        </c:if>

        <c:if test="${status ne null}">
            <p>Status :
            <h2 class="headline text-red">${status}</h2></p>
        </c:if>
        <c:if test="${path ne null}">
            <p>Path :
            <h2 class="headline text-red">${path}</h2></p>
        </c:if>

        <div class="error-content">
            <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>

            <p>
                ${error}
            </p>
            <br>
            <a href="${pageContext.request.contextPath}/" class="btn btn-info btn-sm"><i class="fa fa-home"></i>
                HOMEPAGE</a>.
        </div>
    </div>
    <!-- /.error-page -->

</div>
<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/js/adminlte.min.js"></script>

</body>
</html>
