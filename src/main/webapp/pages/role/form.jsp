<%--
  Created by IntelliJ IDEA.
  User: bidhee
  Date: 2/22/18
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="box-body">

    <div class="row">
        <div class="col-md-12">
            <div class="form-group">
                <label class="control-label">Title *</label>
                <input class="form-control" name="title" placeholder="Role Title" type="text" value="${role.title}">
            </div>
            <p class="form-error">${roleError.title}</p>
        </div>
    </div>


    <div class="row">
        <div class="col-md-12">

            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Service &nbsp;<input type="checkbox" class="all" id="all"/></th>
                    <th>Create</th>
                    <th>View</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="counter" value="${1}"></c:set>
                <c:forEach var="permission" items="${permissionList}">
                    <c:choose>
                        <c:when test="${counter eq 1}">
                            <tr>
                            <td>${fn:split(permission,"_")[0]}&nbsp;<input type="checkbox" class="anyOne"/></td>
                            <td>
                                <input type="checkbox" name="permissionList" value="${permission}"/>
                            </td>
                            <c:set var="counter" value="${counter + 1}"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${counter eq 2 or counter eq 3}">
                                    <td>
                                        <input type="checkbox" name="permissionList" value="${permission}"/>
                                    </td>
                                    <c:set var="counter" value="${counter + 1}"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <input type="checkbox" name="permissionList" value="${permission}"/>
                                    </td>
                                    </tr>
                                    <c:set var="counter" value="${1}"></c:set>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
            <p class="form-error">${roleError.permission}</p>
        </div>
    </div>

</div>
<div class="box-footer">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save Changes</button>
</div>