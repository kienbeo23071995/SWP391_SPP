<%-- 
    Document   : AddAssignment
    Created on : Oct 15, 2023, 1:54:58 AM
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="admin/style.css">
        <link rel="stylesheet" href="admin/adduser.css">
        <title>Add Assignment</title>
    </head>
    <body>

        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel"  style="padding-bottom:40px;padding-top:10px;">
                    <div class="pull-left image">
                        <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p>Alexander Pierce</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <ul class="sidebar-menu">
                    <li class="header">MAIN NAVIGATION</li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-table"></i> <span>Manager Dashboard</span>
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
                        <a href="${pageContext.request.contextPath}/SubjectManager">
                            <i class="fa fa-calendar"></i> <span>Subject Manager</span>
                        </a>
                    </li>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
        <!-- SIDEBAR -->



        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <form action="#">
                    <div class="form-input">
                        <!--                        <input type="search" placeholder="Search...">
                                                <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>-->
                    </div>
                </form>
                <a href="#" class="profile">
                    <img src="img/people.png">
                </a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>

                <div class="container">
                    <div class="title">Add New Subject Setting</div>
                    <div class="content">
                        <form action="${pageContext.request.contextPath}/AddSubjectSetting" method="post">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Type</span>
                                    <input type="text" placeholder="Enter your type" name="type" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Level</span>
                                    <input type="text" placeholder="Enter your level" name="level" required>
                                </div>
                                <div class="input-box form-control">
                                    <span style="margin-left: 25px;" class="details">Status</span>                            
                                    <select name="status" required="">
                                        <option value="1" >Active</option>
                                        <option value="0" >Deactive</option>                          
                                    </select>
                                </div>
                                <div style="color: green"> ${nofi} </div>
                            </div>
                            <input type="hidden" name="action" value="add">
                            <div class="button">
                                <input type="submit" value="Add">
                            </div>
                        </form>
                    </div>
                </div>

            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="admin/script.js"></script>
    </body>
</html>
