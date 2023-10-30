<%-- 
    Document   : manageSubject
    Created on : Sep 26, 2023, 1:48:42 AM
    Author     : phuc2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- Boxicons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>

        <!-- My CSS -->
        <link rel="stylesheet" href="admin/style.css">

        <title>Manage Subject</title>
        <script type ="text/javascript">
            function doDelete(id) {
                if (confirm("ARE YOU SURE YOU WANT TO DELETE SUBJECT WITH ID=" + id)) {
                    window.location = "subjectList?id=" + id+"&action=delete";
                }
            }
        </script>
    </head>
    <body>

        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <span class="text"></span>
            </a>
            <ul class="side-menu top">

                <li>
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
                <div class="head-title">
                    <div class="left">
                        <h1>Subject</h1>
                    </div>
                </div>

                <div class="table-data">
                    <div class="order">
                        <div class="head">                                     
                        </div>
                        <table class="table align-middle">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Subject Code</th>
                                    <th>Subject Name</th>
                                    <th>Subject Description</th>
                                    <th>Status</th>
                                    <th>Action</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.data2}" var="s">
                                    <c:set var="id" value="${s.subjectId}" />
                                    <tr>
                                        <td>${id} </td>
                                        <td>${s.subjectCode}</td>
                                        <td>${s.subjectName} &nbsp;</td>
                                        <td>${s.subjectDescription} </td>
                                        <td>${s.isActive} &nbsp;</td>
                                        <td> 
                                            <i class="material-icons"><a href="subjectList?id=${id}&action=update">build</a></i>
                                            <i class="material-icons"><a href="#" onclick="doDelete('${id}')">delete</a></i>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="pagination">
                    <span class="pagination-button"><</span>

                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                        <a class="${i == page?"active":""}" class="is-active"  href="subjectList2?page=${i}">${i}</a>
                    </c:forEach>
                    <span class="pagination-button">></span>
                </div>


            </main>
            <!-- MAIN -->
        </section>



        <script src="admin/script.js"></script>
    </body>
</html>
