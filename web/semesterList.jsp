<%-- 
    Document   : semesterList
    Created on : Sep 21, 2023, 12:55:38 PM
    Author     : asus
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

        <title>Admin</title>
    </head>
    <body>

        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <span class="text"></span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="SemesterList">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">View Semester</span>
                    </a>
                </li>
                <li>
                    <a href="AddSemester">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Add Semester</span>
                    </a>
                </li>
                <li>
                    <a href="UserList">
                        <i class='bx bxs-group' ></i>
                        <span class="text">View User</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu">
                <li>
                    <a href="#" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
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
                <div class="head-title">
                    <div class="left">
                        <h1>Semester</h1>
                    </div>
                </div>

                <div class="table-data">
                    <div class="order">
                        <div class="head">                                     
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Semester Code</th>
                                    <th>Subject Code</th>
                                    <th>Subject Name</th>
                                    <th>Class</th>
                                    <th>Major</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${semester}" var="s">
                                    <tr>
                                        <td>${s.getSetting().getSemesterCode()} </td>
                                        <td>${s.getSubject().getSubjectCode()}</td>
                                        <td>${s.getSubject().getSubjectName()}</td>
                                        <td>${s.getClassd().getClassCode()}</td>
                                        <td>${s.getClassd().getClassDetail()}</td>
                                        <td><span class="status completed">${s.getIsActive()}</span></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="admin/script.js"></script>
    </body>
</html>
