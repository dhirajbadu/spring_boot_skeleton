<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/26/19
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>

<li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
<li><a href="${pageContext.request.contextPath}/user/list"><i class="fa fa-users"></i> <span>User</span></a></li>
<li><a href="${pageContext.request.contextPath}/role/list"><i class="fa fa-user-circle"></i> <span>Role</span></a></li>
<li><a href="${pageContext.request.contextPath}/country/list"><i class="fa fa-user-circle"></i> <span>Country</span></a></li>
<li><a href="${pageContext.request.contextPath}/city/list"><i class="fa fa-user-circle"></i> <span>City</span></a></li>
<li><a href="${pageContext.request.contextPath}/document/list"><i class="fa fa-user-circle"></i> <span>Document</span></a></li>
<li><a href="${pageContext.request.contextPath}/agentinfo/list"><i class="fa fa-user-circle"></i> <span>Agent Info</span></a></li>
<li><a href="${pageContext.request.contextPath}/activity/log/list"><i class="fa fa-database"></i> <span>Log</span></a></li>
<li><a href="${pageContext.request.contextPath}/token/list"><i class="fa fa-user-secret"></i> <span>Tokens</span></a></li>
<%--<li class="treeview">
    <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
    </a>
    <ul class="treeview-menu">
        <li><a href="#8">Link in level 2</a></li>
        <li><a href="#9">Link in level 2</a></li>
    </ul>
</li>--%>
