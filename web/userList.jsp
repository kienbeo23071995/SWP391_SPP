<%-- 
    Document   : semesterList
    Created on : Sep 21, 2023, 12:55:38 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Boxicons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- My CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="plugins/morris/morris.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.min.css">

    <!-- SweetAlert2 JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Css -->
    <link href="../../assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/85b9ea1328.js" crossorigin="anonymous"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="admin/style.css">
    <style>
        .category-bar {
            position: relative;
            display: inline-block;
            margin-left: 10px;
        }

        .category-bar label {
            margin-right: 5px;
        }

        .category-bar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            width: 150px; /* Adjust the width as needed */
            background-color: #f2f2f2;
            border-radius: 5px;
            cursor: pointer;
        }

        .category-bar ul li {
            padding: 8px 12px;
            transition: background-color 0.3s ease;
        }

        .category-bar ul li:hover {
            background-color: #eaeaea;
        }

        .category-bar ul li a {
            text-decoration: none;
            color: #333;
        }

        .category-bar .select-arrow {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            pointer-events: none;
        }
        .table {
            width: 100%;
            border-collapse: collapse;
            font-family: Arial, sans-serif;
        }

        .table th {
            background-color: #f5f5f5;
            color: #333;
            font-weight: bold;
            padding: 8px;
            text-align: center;
        }

        .table td {
            padding: 8px;
            text-align: center;
        }

        .table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .table tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">

    <jsp:include page="navbar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header" style="margin-bottom: 10px;margin-top:20px;">
                        <h3 class="box-title" style="font-size: 35px; font-weight: bold;"><a href="/G4_Project_SWP391/UserList">User List</a></h3>
                    </div>

                    <div class="box-tools" style="display: flex; justify-content: flex-start; align-items: center; margin-bottom: 20px; margin-left: 70px;">
                        <!-- Search form -->
                        <form action="UserList" method="GET">
                            <div class="input-group input-group-sm" style="width: 200px; margin-right: 10px;">
                                <input name="search" type="search" class="form-control" placeholder="Search...">
                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-default" style="padding: 6px 12px;">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" name="action" value="search">
                        </form>

                        <!-- Role dropdown -->
                        <div class="dropdown" style="margin-right: 10px;">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                                Role <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="UserList">All Roles</a></li>
                                    <c:forEach items="${rolename}" var="r">
                                    <li><a class="dropdown-item" href="UserList?rid=${r.rid}&action=searchRole">${r.rname}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>

                        <!-- Status dropdown -->
                        <div class="dropdown" style="margin-right: 10px;">
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                                Status <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <li><a class="dropdown-item" href="UserList">All</a></li>
                                <li><a class="dropdown-item" href="UserList?action=searchStatus&status=1">Active</a></li>
                                <li><a class="dropdown-item" href="UserList?action=searchStatus&status=0">DeActive</a></li>
                            </ul>
                        </div>

                        <!-- Add new users button -->
                        <div class="input-group-btn" style="margin-left: auto; margin-right: 160px;">
                            <button type="submit" class="btn btn-default" style="background-color: yellowgreen; border-radius: 4px;">
                                <a style="color: white" href="AddUser.jsp">
                                    <i class="fa fa-plus"></i>New Users
                                </a>
                            </button>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover" style="margin: 0 auto; width: 90%;">
                            <tr>
                                <th style="width: 5%;text-align: center">ID</th>
                                <th style="width: 20%;text-align: center">Full Name</th>
                                <th style="width: 20%;text-align: center">Email</th>
                                <th style="width: 20%;text-align: center">Phone Number</th>
                                <th style="width: 15%;text-align: center">Role</th>
                                <th style="width: 10%;text-align: center">Status</th>
                                <th style="width: 25%;text-align: center">Actions</th>
                            </tr>
                            <c:forEach items="${user}" var="u">
                                <tr>
                                    <td style="width: 5%;text-align: center">${u.user_id}</td>
                                    <td style="width: 20%;text-align: center">${u.fullname}</td>
                                    <td style="width: 20%;text-align: center">${u.getEmail()}</td>
                                    <td style="width: 20%;text-align: center">${u.getPhone()}</td>
                                    <td style="width: 15%;text-align: center">${u.role_id.rname}</td>
                                    <td style="width: 10%;text-align: center">
                                        ${u.getStatus() eq '1' ? '<span style="color: green;">Active</span>' : '<span style="color: red;">Deactive</span>'}
                                    </td>
                                    <td style="width: 25%;text-align: center">
                                        <a href="UserList?uid=${u.user_id}&action=update">
                                            <span style="font-size: 18px;" class="status process">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </span>
                                        </a>
                                        <a href="UserList?uid=${u.user_id}&action=delete" onclick="return confirm('Bạn có chắc chắn muốn xóa ${u.fullname} không?')">
                                            <span style="font-size: 18px;" class="status pending">
                                                <i class="fa-solid fa-trash-can"></i>
                                            </span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="box-footer clearfix" style="margin-left: 44%">
                        <ul class="pagination pagination-sm no-margin pull-left text-center">
                            <c:if test="${requestScope.pagenum > 1}">
                                <li class="page-item"><a style="font-size: larger;" class="page-link" href="UserList?pagenum=${requestScope.pagenum - 1}">Previous</a></li>
                                </c:if>
                                <c:forEach begin="${requestScope.pagenum}" end="${requestScope.totalPage > requestScope.pagenum + 2 ? (requestScope.pagenum + 2) : requestScope.totalPage}" var="i">
                                <li class="page-item"><a style="font-size: larger;" class="page-link" href="UserList?pagenum=${i}">${i}</a></li>
                                </c:forEach>
                                <c:if test="${requestScope.pagenum < requestScope.totalPage}">
                                <li class="page-item"><a style="font-size: larger;" class="page-link" href="UserList?pagenum=${requestScope.pagenum + 1}">Next</a></li>
                                </c:if>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- ./wrapper -->

    <!-- jQuery 2.2.3 -->
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script>
                                                function updateSelectedStatus(status) {
                                                    document.getElementById("selectedStatus").textContent = status;
                                                }
    </script>

    <!-- jQuery UI 1.11.4 -->
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
                                                $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.6 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- Morris.js charts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="plugins/morris/morris.min.js"></script>
    <!-- Sparkline -->
    <script src="plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="plugins/knob/jquery.knob.js"></script>
    <!-- daterangepicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
    <script src="plugins/daterangepicker/daterangepicker.js"></script>
    <!-- datepicker -->
    <script src="plugins/datepicker/bootstrap-datepicker.js"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <!-- Slimscroll -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="dist/js/pages/dashboard.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>
</body>
</html>

