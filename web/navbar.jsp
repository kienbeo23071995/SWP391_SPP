<%-- 
    Document   : navbar.jsp
    Created on : Oct 23, 2023, 2:50:22 AM
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- SIDEBAR -->
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <ul class="sidebar-menu">
                    <li class="header">MAIN NAVIGATION</li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-table"></i> <span>User Manager</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="/G4_Project_SWP391/UserList"><i class="fa fa-circle-o"></i> Users List</a></li>
                            <li><a href="/G4_Project_SWP391/settings"><i class="fa fa-circle-o"></i> Settings List</a></li>
                            <li><a href="/G4_Project_SWP391/subjectList"><i class="fa fa-circle-o"></i> Subjects List</a></li>

                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-table"></i> <span>Subject Manager</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="${pageContext.request.contextPath}/subjectList"><i class="fa fa-circle-o"></i> Subject List</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-table"></i> <span>Class Manager</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="${pageContext.request.contextPath}/classList"><i class="fa fa-circle-o"></i> Class List</a></li>
                        </ul>
                    </li>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
    </body>
</html>
