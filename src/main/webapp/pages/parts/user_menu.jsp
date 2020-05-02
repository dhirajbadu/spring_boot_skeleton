<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/26/19
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<li class="user-header">
    <img src="${pageContext.request.contextPath}/resources/img/user-512.png" class="img-circle" alt="User Image">

    <p>
        <sec:authentication property="principal.username" />

        <sec:authorize access="hasAuthority('System')">
            <br>System
        </sec:authorize>

        <sec:authorize access="hasAuthority('Agent')">
            <br>Agent
        </sec:authorize>

        <sec:authorize access="hasAuthority('Guest')">
            <br>Guest
        </sec:authorize>

        <%--<small>Member since Nov. 2012</small>--%>
    </p>
</li>
<!-- Menu Body -->
<%--<li class="user-body">
    <div class="row">
        <div class="col-xs-4 text-center">
            <a href="#">Followers</a>
        </div>
        <div class="col-xs-4 text-center">
            <a href="#">Sales</a>
        </div>
        <div class="col-xs-4 text-center">
            <a href="#">Friends</a>
        </div>
    </div>
    <!-- /.row -->
</li>--%>
<!-- Menu Footer-->
<li class="user-footer">
    <div class="pull-left">
        <a href="${pageContext.request.contextPath}/user/changepassword" class="btn btn-default btn-flat">Change Password</a>
    </div>
    <div class="pull-right">
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
    </div>
</li>
