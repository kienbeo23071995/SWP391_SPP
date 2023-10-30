<%-- 
    Document   : updateSubject
    Created on : Sep 26, 2023, 1:48:22 AM
    Author     : phuc2
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
        <title>Admin</title>
    </head>
    <body>

        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <span class="text"></span>
            </a>
            <ul class="side-menu top">

                <li >
                    <a href="addNewSubject.jsp">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Add Subject</span>
                    </a>
                </li>
                <li  class="active">
                    <a href="subjectList">
                        <i class='bx bxs-group' ></i>
                        <span class="text">View Subject</span>
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
                        <input type="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
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
                    <div class="title">Update Subject</div>
                    <div class="content">
                        <c:set var="s" value="${requestScope.data3}"/>
                        <c:set var="id" value="${s.subjectId}" />
                        <c:set var="code" value="${s.subjectCode}" />
                        <c:set var="name" value="${s.subjectName}" />
                        <c:set var="des" value="${s.subjectDescription}" />
                        <c:set var="status" value="${s.isActive}" />
                        <form action="subjectList" method="post">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Subject ID</span>
                                    <input type="number" readonly name="id" value="${id}">
                                </div>
                                <div class="input-box">
                                    <span class="details">Subject Code</span>
                                    <input type="text"  name="code" value="${code}">
                                </div>
                                <div class="input-box">
                                    <span class="details">Subject Name</span>
                                    <input type="text" 
                                           name="name" value="${name}"> 
                                </div>

                                <div class="input-box">
                                    <span class="details">Status</span>                            
                                    <select style="    height: 45px;
                                            width: 100%;
                                            outline: none;
                                            font-size: 16px;
                                            border-radius: 5px;
                                            padding-left: 15px;
                                            border: 1px solid #ccc;
                                            border-bottom-width: 2px;
                                            transition: all 0.3s ease;" name="status" value="${status}">

                                        <option value="Activated">Activate</option>
                                        <option value="Deactivated">Deactivate</option>

                                    </select> 

                                </div>
                                <div class="input-box">
                                    <span class="details" row ="3">Subject Description</span>
                                    <input type="text" 
                                           name="des" value="${des}" row ="3"> 
                                </div>
                                <div style="color: green"> ${mess} </div>
                            </div>
                            <div class="button">
                                <input type="submit" name="action" value="update">
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



