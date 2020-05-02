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
    <input class="form-control" name="id" type="hidden"
           value="${document.id}">
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label class="control-label">Title *</label> <input
                    class="form-control" name="title" placeholder="Title" type="text"
                    value="${document.title}">
            </div>
            <p class="form-error">${documentError.title}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label class="control-label">File *</label> <input
                    class="form-control" name="file" type="file">
            </div>
            <p class="form-error">${documentError.file}</p>
        </div>
    </div>

    <div class="row">

        <c:if test="${document.id > 0}">
            <div class="col-md-6">
                <div class="form-group has-feedback">
                    <label class="control-label">Status *</label> <select name="status"
                                                                          class="form-control select2 select2-hidden-accessible"
                                                                          data-placeholder="Select Status"
                                                                          style="width: 100%;"
                                                                          tabindex="-1" aria-hidden="true" required>
                    <c:forEach items="${statusList}" var="status">
                        <c:choose>
                            <c:when test="${document.status == status}">
                                <option value="${status}" selected>${status}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${status}">${status}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </div>
                <p class="form-error">${documentError.status}</p>
            </div>
        </c:if>
    </div>
</div>
<div class="box-footer">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">
        <i class="fa fa-save"></i> Save Changes
    </button>
</div>