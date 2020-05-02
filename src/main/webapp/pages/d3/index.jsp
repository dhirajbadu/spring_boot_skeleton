<%--
  Created by IntelliJ IDEA.
  User: dhiraj
  Date: 4/22/20
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/d3/d3.chart.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">

<div class="container">
    <div class="content">

        <div class="row">
            <div class="col-lg-4">
                <h3>vertical bar chart</h3>
                <svg id="chart1" width="300" height="300" style="background: #F5F5F5">

                </svg>
            </div>

            <div class="col-lg-8">
                <h3>horizontal bar chart</h3>
                <svg id="chart" width="600" height="300" style="background: #F5F5F5">

                </svg>
            </div>
        </div>


        <div class="row">

        </div>





    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="module"  src="${pageContext.request.contextPath}/resources/js/d3/d3.5.16.0.min.js"></script>
<script type="module"  src="${pageContext.request.contextPath}/resources/js/d3/d3.horizontal.bar.chart.js"></script>
<script type="module"  src="${pageContext.request.contextPath}/resources/js/d3/d3.vertical.bar.chart.js"></script>
