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

                <li>
                    <a href="UserList">
                        <i class='bx bxs-group' ></i>
                        <span class="text">View User</span>
                    </a>
                </li >
                <li class="active">
                    <a href="AddUser.jsp">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Add New User</span>
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

                <div class="container">
                    <div class="title">Add New User</div>
                    <div class="content">
                        <form action="UserList" method="post" enctype="multipart/form-data">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Full Name</span>
                                    <input type="text" placeholder="Enter your name" name="name" value="${name}" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Avatar</span>
                                    <input type="file" name="file" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Email</span>
                                    <input type="text" placeholder="Enter your email" name="email" value="${email}" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Phone Number</span>
                                    <input type="text" placeholder="Enter your phone number"
                                           pattern="{10}"
                                           name="phone" value="${phone}" required>
                                </div>
                                <div class="input-box">
                                    <span style="margin-left: 25px;" class="details">Role User</span>                            
                                    <select style="    height: 45px;
                                            width: 100%;
                                            outline: none;
                                            font-size: 16px;
                                            border-radius: 5px;
                                            padding-left: 15px;
                                            border: 1px solid #ccc;
                                            border-bottom-width: 2px;
                                            transition: all 0.3s ease;
                                            margin-left: 29px;" name="role_id">
                                        <option value="6">student</option>
                                        <option value="2">project mentor</option>
                                        <option value="4">Class Manager</option>
                                        <option value="3">Subject Manager</option>
                                        <option value="5">Team Leader</option>                                  
                                    </select>
                                </div>
                                <div style="color: red"> ${mess} </div>
                                <div style="color: green"> ${nofi} </div>
                            </div>
                            <input type="hidden" name="action" value="add">
                            <div class="button">
                                <input type="submit" value="Add User">
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
