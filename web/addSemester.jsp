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
        <link rel="stylesheet" href="admin/addsemester.css">
        <title>Admin</title>
    </head>
    <body>

        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <span class="text"></span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="SemesterList">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">View Semester</span>
                    </a>
                </li>
                <li class="active">
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
                </li >
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

                <div class="container">
                    <div class="title">Add New Semester</div>
                    <div class="content">
                        <form action="AddSemester" method="post">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Semester</span>
                                    <select name="semester">
                                        <c:forEach items="${setting}" var="s">
                                            <option value="${s.getSettingId()}">${s.getSemesterCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="input-box">
                                    <span class="details">Class</span>
                                    <select name="clas">
                                        <c:forEach items="${clas}" var="s">
                                            <option value="${s.getClassId()}">${s.getClassCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="input-box">
                                    <span class="details">Subject</span>
                                    <select name="subject">
                                        <c:forEach items="${subject}" var="s">
                                            <option value="${s.getSubjectId()}">${s.getSubjectCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div style="color: green"> ${mess} </div>
                            <div class="button">
                                <input style="height: 100%;
                                       width: 34%;
                                       border-radius: 5px;
                                       border: none;
                                       color: #fff;
                                       font-size: 18px;
                                       font-weight: 500;
                                       letter-spacing: 1px;
                                       cursor: pointer;
                                       transition: all 0.3s ease;
                                       background: linear-gradient(135deg, #71b7e6, #9b59b6);
                                       margin-left: 22rem;" type="submit" value="Create Semester">
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
