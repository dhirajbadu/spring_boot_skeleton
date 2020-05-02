<%@ attribute name="title" required="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${title}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skin-blue.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
	<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.min.css">
    <%--select 2--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/select2.min.css">

    <!-- bootstrap wysihtml5 - text editor-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>T</b>B</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Tarkari</b>Bazar</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <jsp:include page="/pages/parts/header_nav.jsp"/>
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- The user image in the navbar-->
                            <img src="${pageContext.request.contextPath}/resources/img/user-512.png" class="user-image" alt="User Image">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs"><sec:authentication property="principal.username" /></span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <jsp:include page="/pages/parts/user_menu.jsp"/>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${pageContext.request.contextPath}/resources/img/user-512.png" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p><sec:authentication property="principal.username" /></p>
                    <!-- Status -->
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>

            <!-- search form (Optional) -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
                </div>
            </form>
            <!-- /.search form -->

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu" data-widget="tree">
                <jsp:include page="/pages/parts/sidebar.jsp"/>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <jsp:doBody/>

    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <jsp:include page="/pages/parts/footer.jsp"/>
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <jsp:include page="/pages/parts/setting.jsp"/>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/adminlte.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.sparkline.min.js"></script>
<--select2 dropdown-->
<script src="${pageContext.request.contextPath}/resources/js/select2.full.min.js"></script>
<!-- bootstrap datepicker -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/icheck.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>


<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>

<script>

    var pageContext = '${pageContext.request.contextPath}';
    $(document).ready(function () {
        //$('.confirmDelete').bootstrap_confirm_delete({});

        //$(".content-wrapper").attr("style" , "style='min-height: 100%;'");

        $(document).on("click" , ".treeview" , function () {
            console.log("you clicked on sidebar");
            $(".scroll-style-4").attr("style" , "height: 100%; overflow-y: scroll ;");
        });

        $('.select2').select2();

        $(function () {
            $.fn.dataTable.ext.errMode = 'none';
            $('#ajaxTable').on('error.dt', function (e, settings, techNote, message) {
                console.log('An error has been reported by DataTables: ', message);
            }).DataTable({
                "processing": true,
                "serverSide": true,
                'searching': false,
                'ordering': true,
                'lengthChange': true,
                "ajax": $('#ajaxTable').attr('url')
            });

            $('#ajaxTableFilter').on('error.dt', function (e, settings, techNote, message) {
                console.log('An error has been reported by DataTables: ', message);
            }).DataTable({
                "processing": true,
                "serverSide": true,
                'searching': true,
                'ordering': true,
                'lengthChange': true,
                "ajax": $('#ajaxTableFilter').attr('url')
            });

            $('.datepicker').datepicker({
                autoclose: true,
                todayHighlight: true
                //format : "yyyy-mm-dd"
            });

            $(".datepickermonth").datepicker( {
                autoclose: true,
                todayHighlight: true,
                minViewMode: "months"
            });

            //bootstrap WYSIHTML5 - text editor
            $('.editor').wysihtml5();
/*
            //iCheck for checkbox and radio inputs
            $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                checkboxClass: 'icheckbox_minimal-blue',
                radioClass: 'iradio_minimal-blue'
            });
            //Red color scheme for iCheck
            $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                checkboxClass: 'icheckbox_minimal-red',
                radioClass: 'iradio_minimal-red'
            });
            //Flat red color scheme for iCheck
            $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });*/
        });

    });
</script>

<%--for sidebar active--%>
<script>
    /** add active class and stay opened when selected */
    var url = window.location;
    console.log('this is url '+url);
    $('ul.sidebar-menu a').each(function(){
        console.log("this is iteration "+this.href);
        if(url==this.href){
            $(this).parent().addClass('active');
        }
    });

    $('ul.treeview-menu a').each(function(){
        if(url==this.href){
            $(this).parent().addClass('active');
            $('ul.treeview-menu').parent('li').addClass('active');
        }
    });
</script>

<script>
    $(function () {
        $('#table1').DataTable();
        $('#table2').DataTable({
            'paging': false,
            'lengthChange': false,
            'searching': true,
            'ordering': true,
            'info': false,
            'autoWidth': true
        });

        $('#table3').DataTable({
            'paging': true,
            'lengthChange': true,
            'searching': true,
            'ordering': true,
            'info': false,
            'autoWidth': true
        });
    })
</script>