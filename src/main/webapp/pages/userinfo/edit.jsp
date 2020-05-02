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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainlayout title="user edit">
    <jsp:body>
        <section class="content-header">
            <h1>
                Edit User
                <small>update User Details for <b>${userinfo.email}</b></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/user/list">User List</a>
                <li><a href="${pageContext.request.contextPath}/user/show/${userinfo.userId}">User Show</a></li>
                <li class="active">Edit User</li>
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
                        <form action="${pageContext.request.contextPath}/user/update" method="post" modelAttribute="user" autocomplete="off">

                            <div class="box-body">

                                <input type="hidden" name="userId" value="${userinfo.userId}"/>
                                <input type="hidden" name="version" value="${userinfo.version}"/>

                                <%@include file="/pages/userinfo/form.jsp" %>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="control-label">Account Expire Date </label><br>
                                            <input class="form-control datepicker"
                                                    value="<fmt:formatDate pattern="MM/dd/yyyy" value="${userinfo.expire}"/>"
                                                    onkeypress="return false;" onkeyup="return false;" type="text" name="expire" placeholder="Expire Date" readonly/>
                                        </div>
                                        <p class="form-error">${userError.expire}</p>
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