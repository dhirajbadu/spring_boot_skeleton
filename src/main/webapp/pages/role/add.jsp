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

<t:mainlayout title="role add">
    <jsp:body>
        <section class="content-header">
            <h1>
                Add Role
                <small>New Role</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/role/list"><i class="fa fa-user-secret"></i> Role List</a>
                </li>
                <li class="active"><i class="fa fa-plus-circle"></i> Add Role</li>
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
                               href="${pageContext.request.contextPath}/role/list"><i class="fa fa-list"></i> Role List</a>
                        </div>
                        <form action="${pageContext.request.contextPath}/role/save" method="post" modelAttribute="role" autocomplete="off">
                            <%@include file="/pages/role/form.jsp" %>
                        </form>

                    </div>
                    <!-- /.box -->
                </div>
            </div>


        </section>
    </jsp:body>

</t:mainlayout>

<script>
    $(document).ready(function () {
        $("#all").on("click" , function () {
            var isCheckedAll = $(this).prop("checked");
            if(isCheckedAll === true){
                $('input[name="permissionList"]').prop("checked" , true);
                $(".anyOne").prop("checked" , true);
            }else {
                $('input[name="permissionList"]').prop("checked" , false);
                $(".anyOne").prop("checked" , false);
            }
        });

        $(".anyOne").on("click" , function () {
            var isCheckedAll = $(this).prop("checked");
            if(isCheckedAll === true){
                $(this).closest('tr').each(function() {
                    $(this).find("td:eq(1) > input[name='permissionList']").prop("checked" , true);
                    $(this).find("td:eq(2) > input[name='permissionList']").prop("checked" , true);
                    $(this).find("td:eq(3) > input[name='permissionList']").prop("checked" , true);
                    $(this).find("td:eq(4) > input[name='permissionList']").prop("checked" , true);
                });

            }else {
                $(this).closest('tr').each(function() {
                    $(this).find("td:eq(1) > input[name='permissionList']").prop("checked" , false);
                    $(this).find("td:eq(2) > input[name='permissionList']").prop("checked" , false);
                    $(this).find("td:eq(3) > input[name='permissionList']").prop("checked" , false);
                    $(this).find("td:eq(4) > input[name='permissionList']").prop("checked" , false);
                })
            }
        })
    })
</script>

<%--
<script>

    $(function () {
        var checkAll = $('input.all');
        var checkboxes = $('input.myiterator');

        $('.blueCheck').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });

        checkAll.on('ifChecked ifUnchecked', function(event) {
            if (event.type == 'ifChecked') {
                checkboxes.iCheck('check');
            } else {
                checkboxes.iCheck('uncheck');
            }
        });

        checkboxes.on('ifChanged', function(event){
            if(checkboxes.filter(':checked').length == checkboxes.length) {
                checkAll.prop('checked', 'checked');
            } else {
                checkAll.removeProp('checked');
            }
            checkAll.iCheck('update');
        });
    });
</script>--%>
