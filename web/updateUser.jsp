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
        <script src="https://kit.fontawesome.com/85b9ea1328.js" crossorigin="anonymous"></script>
        <!-- My CSS -->
        <link rel="stylesheet" href="admin/style.css">
        <link rel="stylesheet" href="admin/adduser.css">
        <title>Admin</title>
    </head>
    <style>
        .avatar-container {
            position: relative;
            display: inline-block;
            text-align: center;
        }

        .avatar-frame {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            overflow: hidden;
            position: relative;
        }

        .user-avatar {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
        }

        .change-avatar-btn {
            position: absolute;
            bottom: 10px;
            right: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            width: 40px;
            height: 40px;
            background-color: #fff;
            border: none;
            border-radius: 50%;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .change-avatar-btn i {
            color: #555;
            font-size: 20px;
        }

        .change-avatar-btn:hover {
            background-color: #ddd;
        }

        .change-avatar-btn:focus {
            outline: none;
        }

        #avatar-input {
            display: none;
        }
    </style>
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
                </li >
                <li class="active">
                    <a href="">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Update User</span>
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
                    <div class="title">Update User</div>
                    <div class="content">
                        <form action="UserList" method="post">
                            <div class="user-details">
                                <div class="input-box">
                                    <span class="details">Full Name</span>
                                    <input type="text" value="${user.fullname}" placeholder="Enter your name" name="name" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Phone Number</span>
                                    <input type="text" value="${user.getPhone()}" placeholder="Enter your phone number"
                                           pattern="{10}"
                                           name="phone" value="${user.getPhone()}" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Email</span>
                                    <input type="text" placeholder="Enter your email" value="${user.getEmail()}" name="email" required>
                                </div>
                                <div class="input-box">
                                    <span class="details">Role User</span>                            
                                    <select style="height: 45px;
                                            width: 100%;
                                            outline: none;
                                            font-size: 16px;
                                            border-radius: 5px;
                                            padding-left: 15px;
                                            border: 1px solid #ccc;
                                            border-bottom-width: 2px;
                                            transition: all 0.3s ease;"name="role_id" value="${user.getRole_id()}">
                                        <option value="6">student</option>
                                        <option value="2">project mentor</option>
                                        <option value="4">Class Manager</option>
                                        <option value="3">Subject Manager</option>
                                        <option value="5">Team Leader</option>    
                                    </select>
                                </div>
                                <div class="input-box">
                                    <span class="details">Status</span>                            
                                    <select style="height: 45px;
                                            width: 100%;
                                            outline: none;
                                            font-size: 16px;
                                            border-radius: 5px;
                                            padding-left: 15px;
                                            border: 1px solid #ccc;
                                            border-bottom-width: 2px;
                                            transition: all 0.3s ease;" name="status" >
                                        <option value="1">Active</option>
                                        <option value="0">Deactive</option> 
                                    </select>
                                </div>

                                <div style="color: green"> ${mess}</div>
                            </div>
                            <input type="hidden" name="uid" value="${user.user_id}">
                            <input type="hidden" name="action" value="update">
                            <div class="button">
                                <input type="submit" value="Update User">
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
