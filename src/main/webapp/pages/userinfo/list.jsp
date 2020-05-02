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

<t:mainlayout title="user list">
    <jsp:body>
        <section class="content-header">
            <h1>
                User List
                <small>All Users</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li class="active"><i class="fa fa-users"></i> User</li>
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
                            <a class="btn btn-primary btn-sm pull-right" href="${pageContext.request.contextPath}/user/add"><i class="fa fa-plus-circle"></i>  Add User</a>
                        </div>

                        <div class="box-body">

                            <!-- /.box-header -->
                            <table id="ajaxUserTable" url="${pageContext.request.contextPath}/api/user/list" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>isEnabled</th>
                                    <th>isAccountNonLocked</th>
                                    <th>isAccountNonExpired</th>
                                    <th>Expire On</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                            <!-- /.box-body -->
                        </div>

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
            orderCellsTop: true,
            fixedHeader: true,
            processing: true,
            serverSide: true,
            searching: true,
            ordering: true,
            lengthChange: true,
            order: [0, "asc"],
            ajax: $('#ajaxUserTable').attr('url'),
            columnDefs: [{
                targets: 1,
                render: function (data, type, row) {
                    var result = "<a href='${pageContext.request.contextPath}/user/show/" + row[0] + "' >"+row[1]+"</a>";

                    return result
                }
            }]
        });
    })
</script>