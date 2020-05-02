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

<t:mainlayout title="user details">
    <jsp:body>
        <section class="content-header">
            <h1>
                User Show
                <small>User Details</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/user/list"><i class="fa fa-users"></i> User List</a>
                </li>
                <li class="active"><i class="fa fa-user"></i> User Show</li>
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
                    <div class="box box-info">

                        <div class="box-header with-border">
                            <a class="btn btn-primary btn-sm pull-right"
                               href="${pageContext.request.contextPath}/user/edit/${userinfo.userId}"><i
                                    class="fa fa-edit"></i> Edit User</a>
                        </div>

                        <div class="box-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <strong>User</strong>

                                    <p class="text-muted">
                                            ${userinfo.email}
                                    </p>

                                    <hr>

                                    <strong>User Type</strong>

                                    <p class="text-muted">
                                            ${userinfo.userType}
                                    </p>

                                    <hr>

                                    <strong>isEnabled</strong>

                                    <p class="text-muted">
                                            ${userinfo.enabled}
                                    </p>

                                    <hr>

                                    <strong>isAccountNonExpired</strong>

                                    <p class="text-muted">${userinfo.accountNonExpired}</p>

                                    <hr>

                                    <strong>isAccountNonLocked</strong>

                                    <p class="text-muted">${userinfo.accountNonLocked}</p>

                                    <hr>

                                    <strong>Expired On Date </strong>

                                    <p class="text-muted"><fmt:formatDate pattern="yyyy-MMM-dd HH:mm:ss"
                                                                          value="${userinfo.expire}"/></p>

                                    <hr>
                                    <strong>Roles</strong>

                                    <p class="margin-b-5">

                                    <table class="table">
                                        <tbody >
                                        <c:forEach var="role" items="${userinfo.roleSet}">
                                            <tr>
                                                <td><span class="label label-success">${role.title}</span></td>
                                                <td>
                                                    <c:forEach var="permission" items="${role.permissionSet}">
                                                        <span class="label label-default">${permission.value}</span>&nbsp;
                                                    </c:forEach>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    </p>

                                    <hr>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <c:choose>
                                        <c:when test="${userinfo.enabled eq 'false'}">
                                            <a class="btn btn-success btn-sm "
                                               href="${pageContext.request.contextPath}/user/action/${userinfo.userId}?query=enable"><i
                                                    class="fa fa-eye"></i>&nbsp;Enable</a> &nbsp;&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-danger btn-sm"
                                               href="${pageContext.request.contextPath}/user/action/${userinfo.userId}?query=diseble"><i
                                                    class="fa fa-lock"></i>&nbsp;Disable</a> &nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                    <c:when test="${userinfo.accountNonExpired eq 'false'}">
                                    <a class="btn btn-success btn-sm"
                                       href="${pageContext.request.contextPath}/user/action/${userinfo.userId}?query=unExpire"><i
                                            class="fa fa-eye"></i>&nbsp;UnExpire</a> &nbsp;&nbsp;
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-danger btn-sm"
                                           href="${pageContext.request.contextPath}/user/action/${userinfo.userId}?query=expire"><i
                                                class="fa fa-lock"></i>&nbsp;Expire</a> &nbsp;&nbsp;
                                    </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                    <c:when test="${userinfo.accountNonLocked eq 'false'}">
                                    <a class="btn btn-success btn-sm"
                                       href="${pageContext.request.contextPath}/user/action/${userinfo.userId}?query=unLock"><i
                                            class="fa fa-eye"></i> &nbsp;UnLock</a> &nbsp;&nbsp;
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-danger btn-sm"
                                           href="${pageContext.request.contextPath}/user/action/${userinfo.userId}?query=lock"><i
                                                class="fa fa-lock"></i> &nbsp;Lock</a> &nbsp;&nbsp;
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </section>
    </jsp:body>

</t:mainlayout>