<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/26/19
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainlayout title="user add">
    <jsp:body>
        <section class="content-header">
            <h1>
                Add User
                <small>New User</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/user/list"><i class="fa fa-users"></i> User List</a>
                </li>
                <li class="active"><i class="fa fa-plus-circle"></i> Add User</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success alert-dismissable">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">x</a>
                            <strong>${message}</strong>
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissable">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">x</a>
                            <strong>${error}</strong>
                        </div>
                    </c:if>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <a class="btn btn-primary btn-sm pull-right"
                               href="${pageContext.request.contextPath}/user/list"><i class="fa fa-list"></i> User List</a>
                        </div>
                        <form action="${pageContext.request.contextPath}/user/save" method="post" modelAttribute="user" autocomplete="off">

                            <div class="box-body">

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group has-feedback">
                                            <label class="control-label">Email *</label>
                                            <input class="form-control" name="email" value="${user.username}"
                                                   placeholder="email" type="email" required>
                                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                                        </div>
                                        <p class="form-error">${userError.username}</p>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group has-feedback">
                                            <label class="control-label">Password *</label>
                                            <input class="form-control" name="password" placeholder="password"
                                                   type="password" required>
                                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                                        </div>
                                        <p class="form-error">${userError.password}</p>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group has-feedback">
                                            <label class="control-label">Confirm Password *</label>
                                            <input class="form-control" name="confirmPassword" placeholder="retype password"
                                                   type="password" required>
                                            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                                        </div>
                                        <p class="form-error">${userError.repassword}</p>
                                    </div>
                                </div>

                                <%@include file="/pages/userinfo/form.jsp" %>


                            </div>
                            <div class="box-footer">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save changes</button>
                            </div>

                        </form>

                    </div>
                    <!-- /.box -->
                </div>
            </div>


        </section>
    </jsp:body>

</t:mainlayout>

<script>
    $(function () {
        $.fn.dataTable.ext.errMode = 'none';
        $('#ajaxUserTable').on('error.dt', function (e, settings, techNote, message) {
            console.log('An error has been reported by DataTables: ', message);
        }).DataTable({
            "processing": true,
            "serverSide": true,
            'searching': true,
            'ordering': true,
            'lengthChange': true,
            "ajax": $('#ajaxUserTable').attr('url')
        })
    })
</script>