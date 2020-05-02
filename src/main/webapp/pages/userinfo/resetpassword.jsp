<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 5/26/19
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Reset|Password</title>

    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skin-blue.min.css">

</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="${pageContext.request.contextPath}/"><b>Real</b>Estate</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Reset Your Password</p>

        <form action="${pageContext.request.contextPath}/auth/password/reset" method="post">
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="password" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <input type="password" name="confirmPassword" class="form-control" placeholder="repassword" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <div class="g-recaptcha" data-callback="capcha_filled" data-expired-callback="capcha_expired" data-sitekey="6LeG-kMUAAAAACApsSOmYiulgmDIMHn6aMqaKeUM"></div>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-12">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                    <input type="hidden" name="token" value="${token}"/>
                    <button type="submit" id="btnreset" class="btn btn-primary btn-block btn-flat" disabled="disabled">Reset&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>
                </div>
                    <!-- /.col -->
            </div>
        </form>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-default btn-block btn-flat">Login&nbsp;&nbsp;<span class="glyphicon glyphicon-log-in"></span></a>

       <%-- <div class="social-auth-links text-center">
            <p>- OR -</p>
            <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
                Facebook</a>
            <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
                Google+</a>
        </div>--%>
        <!-- /.social-auth-links -->

        <c:if test="${error ne null}">
            <p style="color: red">${error}</p><br>

        </c:if>

        <c:if test="${message ne null}">
            <p style="color: darkgreen">${message}</p><br>

        </c:if>

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

</body>
</html>

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/adminlte.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>

<script>
    function capcha_filled(){
        $('#btnreset').prop("disabled", false);
    }

    function capcha_expired() {
        $('#btnreset').prop("disabled", true);
        alert("captcha expired");
    }
</script>