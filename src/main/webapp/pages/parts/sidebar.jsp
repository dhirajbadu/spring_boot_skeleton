<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/26/19
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>

<li class="header">HEADER</li>

<sec:authorize access="hasAuthority('System')">
    <%@include file="/pages/parts/sidebar_system.jsp" %>
</sec:authorize>

<sec:authorize access="hasAuthority('Agent')">
    <%@include file="/pages/parts/sidebar_agent.jsp" %>
</sec:authorize>

<sec:authorize access="hasAuthority('Guest')">
    <%@include file="/pages/parts/sidebar_guest.jsp" %>
</sec:authorize>
