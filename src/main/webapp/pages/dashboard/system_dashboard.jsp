<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/24/19
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
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
                            <span class="info-box-text">Total Property</span>
                            <span class="info-box-number">430</span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline margin-t-5"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">Sold Property</span>
                            <span class="info-box-number">360</span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->

                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>

                <!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-user-circle margin-t-5"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">Agent</span>
                            <span class="info-box-number">99</span>
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
                            <span class="info-box-text">Client</span>
                            <span class="info-box-number">800</span>
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
                            <h3 class="box-title text-center">Agent List</h3>

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
                                    <table id="table3" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Image</th>
                                            <th>Name</th>
                                            <th>MobileNum</th>
                                            <th>City</th>
                                            <th>Sold Property</th>
                                            <th>Perperty For Sale</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <tr>
                                            <td>1</td>
                                            <td><img src="${pageContext.request.contextPath}/resources/img/user-512.png" class="img-circle" style="width: 100px;  height: 100px;" alt="Roshan Shrestha"></td>
                                            <td><a href="#">Roshan Shrestha</a></td>
                                            <td>9849640373</td>
                                            <td>Kathmandu</td>
                                            <td>39</td>
                                            <td>70</td>
                                            <td><a href="#" class="btn btn-default btn-sm"><i class="fa fa-eye"></i> show</a></td>
                                        </tr>

                                        <tr>
                                            <td>2</td>
                                            <td><img src="${pageContext.request.contextPath}/resources/img/user-512.png" style="width: 100px;  height: 100px;" class="img-circle" alt="Rijan Shrestha"></td>
                                            <td><a href="#">Rijan Shrestha</a></td>
                                            <td>9849640374</td>
                                            <td>Kathmandu</td>
                                            <td>20</td>
                                            <td>37</td>
                                            <td><a href="#" class="btn btn-default btn-sm"><i class="fa fa-eye"></i> show</a></td>
                                        </tr>

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
                            <h3 class="box-title text-center">User List</h3>

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