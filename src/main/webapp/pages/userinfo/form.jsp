<%--
  Created by IntelliJ IDEA.
  User: bidhee
  Date: 2/22/18
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="control-label">User Type *</label>
            <select name="userType" id="userType" class="form-control select2 select2-hidden-accessible"
                    data-placeholder="Select User Type" style="width: 100%;" tabindex="-1" aria-hidden="true" required>
                <c:forEach var="userType" items="${userTypeList}">
                    <c:choose>
                        <c:when test="${userType eq userinfo.userType}">
                            <option selected="selected">${userType}</option>
                        </c:when>
                        <c:otherwise>
                            <option>${userType}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <p class="form-error">${userError.userType}</p>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="control-label">Role *</label>
            <select name="roleIdSet" class="form-control select2 select2-hidden-accessible" multiple="" data-placeholder="Select Permissions" style="width: 100%;" tabindex="-1" aria-hidden="true" required>
                <c:forEach items="${roleList}" var="role">
                    <c:choose>
                        <c:when test="${fn:length(userinfo.roleIdSet) gt 0}">
                            <spring:eval var="containsValue" expression="userinfo.roleIdSet.contains(role.id )" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="containsValue" value="${false}"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${containsValue eq true}">
                            <option value="${role.id}" selected="selected">${role.title}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${role.id}">${role.title}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <p class="form-error">${userError.role}</p>
    </div>
</div>

<div class="row">
    <div class="col-md-3">
        <div class="form-group">
            <label class="control-label">Active </label><br>
            <label class="">
                <div class="icheckbox_minimal-blue" style="position: relative;" aria-checked="false"
                     aria-disabled="false">
                    <c:choose>
                        <c:when test="${userinfo.enabled}">
                            <input name="enabled" type="checkbox" checked="checked">
                        </c:when>
                        <c:otherwise>
                            <input name="enabled" type="checkbox">
                        </c:otherwise>
                    </c:choose>
                </div>
            </label>
        </div>
        <p class="form-error">${userError.enable}</p>
    </div>
</div>

<div class="row">
    <div class="col-md-3">
        <div class="form-group">
            <label class="control-label">Account Non Expired </label><br>
            <label class="">
                <div class="icheckbox_minimal-blue" style="position: relative;" aria-checked="false"
                     aria-disabled="false">
                    <c:choose>
                        <c:when test="${userinfo.accountNonExpired}">
                            <input name="accountNonExpired" type="checkbox" checked="checked">
                        </c:when>
                        <c:otherwise>
                            <input name="accountNonExpired" type="checkbox">
                        </c:otherwise>
                    </c:choose>
                </div>
            </label>
        </div>
        <p class="form-error">${userError.accountNonExpired}</p>
    </div>
</div>

<div class="row">
    <div class="col-md-3">
        <div class="form-group">
            <label class="control-label">Account Non Locked </label><br>
            <label class="">
                <div class="icheckbox_minimal-blue" style="position: relative;" aria-checked="false"
                     aria-disabled="false">
                    <c:choose>
                        <c:when test="${userinfo.accountNonLocked}">
                            <input name="accountNonLocked" type="checkbox" checked="checked">
                        </c:when>
                        <c:otherwise>
                            <input name="accountNonLocked" type="checkbox">
                        </c:otherwise>
                    </c:choose>
                </div>
            </label>
        </div>
        <p class="form-error">${userError.accountNonLocked}</p>
    </div>
</div>

