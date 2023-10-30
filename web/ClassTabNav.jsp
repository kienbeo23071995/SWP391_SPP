<%-- 
    Document   : ClassTabNav
    Created on : Oct 24, 2023, 5:14:39 AM
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul class="nav nav-pills mb-3 shadow-sm" id="pills-tab" role="tablist">
            <li class="nav-item ">
                <a class="nav-link ${requestScope.active == 1 ? 'active' : ''}" href="${pageContext.request.contextPath}/classDetail?classID=${requestScope.classID}">General</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${requestScope.active == 2 ? 'active' : ''}" href="${pageContext.request.contextPath}/ClassAssignment?classID=${requestScope.classID}">Class Assignment</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${requestScope.active == 3 ? 'active' : ''}" href="${pageContext.request.contextPath}/classIssuseSetting?classID=${requestScope.classID}">Class Issue Setting</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${requestScope.active == 4 ? 'active' : ''}" href="${pageContext.request.contextPath}/classStudent?classID=${requestScope.classID}">Class Students</a>
            </li>
        </ul>
    </body>
</html>
