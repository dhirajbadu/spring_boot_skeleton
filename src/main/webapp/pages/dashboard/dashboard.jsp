<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 6/24/19
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasAuthority('System')">
    <%@include file="/pages/dashboard/system_dashboard.jsp" %>
</sec:authorize>

<sec:authorize access="hasAuthority('Agent')">
    <%@include file="/pages/dashboard/agent_dashboard.jsp" %>
</sec:authorize>

<sec:authorize access="hasAuthority('ROLE_USER')">
    <%@include file="/pages/dashboard/user.jsp" %>
</sec:authorize>