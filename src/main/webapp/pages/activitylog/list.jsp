<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 4/22/19
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainlayout title="activity log">
    <jsp:body>
        <section class="content-header">
            <h1>
                Activity Log List
                <small>All Logs</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li class="active"><i class="fa fa-database"></i> Log</li>
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

                        <div class="box-body">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>SN</th>
                                <th>Activity</th>
                                <th>Url</th>
                                <th>Method</th>
                                <th>RemoteAddrl</th>
                                <th>Username</th>
                                <th>DateTime</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach varStatus="i" items="${logList}" var="log">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td>${log.activity}</td>
                                    <td>${log.uri}</td>
                                    <td>${log.method}</td>
                                    <td>${log.remoteAddrl}</td>
                                    <td>${log.username}</td>
                                    <td>${log.dateTime}</td>
                                </tr>

                            </c:forEach>
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