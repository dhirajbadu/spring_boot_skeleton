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

<t:mainlayout title="change password">
    <jsp:body>
        <section class="content-header">
            <h1>
                Change Password
                <small>change your password</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                <li class="active">Password Change</li>
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

                        <form action="${pageContext.request.contextPath}/user/updatepassword" method="post" autocomplete="off">

                            <div class="box-body">

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group has-feedback">
                                            <label class="control-label">Current Password *</label>
                                            <input class="form-control" name="oldpassword"
                                                   placeholder="old password" type="password" required>
                                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group has-feedback">
                                            <label class="control-label">New Password *</label>
                                            <input class="form-control" name="newpassword"
                                                   placeholder="new password" type="password" required>
                                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group has-feedback">
                                            <label class="control-label">Confirm Password *</label>
                                            <input class="form-control" name="repassword"
                                                   placeholder="confirm password" type="password" required>
                                            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                                        </div>
                                    </div>
                                </div>

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