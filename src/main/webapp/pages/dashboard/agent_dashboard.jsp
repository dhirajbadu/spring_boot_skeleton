<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/24/19
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@include file="/pages/parts/header_nav.jsp" %>
<h1> well come to dashboard</h1>
<%@include file="/pages/parts/footer.jsp" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainlayout title="dashboard">
    <jsp:body>
        <section class="content-header">
            <h1>
                Dashboard
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> Index</a></li>
                <li class="active"><i class="fa fa-dashboard"></i> Dashboard</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-home margin-t-5"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">Shutter</span>
                            <span class="info-box-number">${shutterCount}</span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-user-circle margin-t-5"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">Owner</span>
                            <span class="info-box-number">${ownerCount}</span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->

                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline margin-t-5"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">Revenue</span>
                            <span class="info-box-number">760</span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-yellow"><i
                                class="ion ion-ios-people-outline margin-t-5"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">User</span>
                            <span class="info-box-number">${userCount}</span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title text-center">Owner List</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <table id="ownerAjaxDataTable"
                                           url="${pageContext.request.contextPath}/api/owner/list"
                                           class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th>Address</th>
                                            <th>Primary Contact</th>
                                            <th>Identification</th>
                                            <th>Type</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title text-center">Shutter List</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
                                        <div class="form-group has-feedback">
                                            <input type="file" name="file" class="form-control" >
                                        </div>
                                        <!-- /.col -->
                                        <div class="row">
                                            <div class="col-xs-4">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                       value="${_csrf.token}"/>
                                                <button type="submit" class="btn btn-primary btn-block btn-flat">Upload</button>
                                            </div>
                                            <!-- /.col -->
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </jsp:body>

</t:mainlayout>
<script>
    $(function () {
        $.fn.dataTable.ext.errMode = 'none';
        $('#ownerAjaxDataTable').on('error.dt', function (e, settings, techNote, message) {
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
            ajax: $('#ownerAjaxDataTable').attr('url'),
            columnDefs: [{
                targets: 1,
                render: function (data, type, row) {
                    var result = "<a href='${pageContext.request.contextPath}/owner/show/" + row[0] + "' >" + row[1] + "</a>";

                    return result
                },
                targets: 6,
                render: function (data, type, row) {
                    var result = "<a href='${pageContext.request.contextPath}/owner/show/" + row[0] + "' class='btn btn-default btn-sm'><i class='fa fa-eye'></i> Show</a>";

                    return result
                }
            }]
        });
    });

    $('#shutterAjaxDataTable').on('error.dt', function (e, settings, techNote, message) {
        console.log('An error has been reported by DataTables: ', message);
    }).DataTable({
        orderCellsTop: true,
        fixedHeader: true,
        processing: true,
        serverSide: true,
        searching: false,
        ordering: true,
        lengthChange: true,
        order: [0, "asc"],
        ajax: $('#shutterAjaxDataTable').attr('url'),
        columnDefs: [{
            targets: 1,
            render: function (data, type, row) {
                var result = "<a href='${pageContext.request.contextPath}/shutter/show/" + row[0] + "' >"+row[1]+"</a>";

                return result
            },
            targets: 5,
            render: function (data, type, row) {
                var result = "<a href='${pageContext.request.contextPath}/shutter/show/" + row[0] + "' class='btn btn-default btn-sm'><i class='fa fa-eye'></i> Show</a>";

                return result
            }
        }]
    });
</script>
<%--
<script src="${pageContext.request.contextPath}/resources/js/dashboard2.js"></script>--%>
