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

<t:mainlayout title="City Add">
    <jsp:body>
        <section class="content-header">
            <h1>
                Add City
                <small>New City</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/city/list"><i class="fa fa-user-secret"></i> City List</a>
                </li>
                <li class="active"><i class="fa fa-plus-circle"></i> Add City</li>
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
                               href="${pageContext.request.contextPath}/city/list"><i class="fa fa-list"></i> City List</a>
                        </div>
                        <form action="${pageContext.request.contextPath}/city/save" method="post" modelAttribute="city" autocomplete="off">
                            <%@include file="/pages/city/form.jsp" %>
                        </form>

                    </div>
                    <!-- /.box -->
                </div>
            </div>


        </section>
    </jsp:body>

</t:mainlayout>
