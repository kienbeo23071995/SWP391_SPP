<%-- 
    Document   : tabnav
    Created on : Oct 24, 2023, 3:11:06 AM
    
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
                <a class="nav-link ${requestScope.active == 1? 'active' : ''}" href="${pageContext.request.contextPath}/subjectDetail?subID=${requestScope.subID}" >General</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${requestScope.active == 2? 'active' : ''}" href="${pageContext.request.contextPath}/SubjectAssignment?subID=${requestScope.subID}" >Class Assignment</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${requestScope.active == 3? 'active' : ''}" href="${pageContext.request.contextPath}/SubjectSetting?subID=${requestScope.subID}" >Subject Setting</a>
            </li>
        </ul>
    </body>
</html>
