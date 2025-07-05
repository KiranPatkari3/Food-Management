<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();  // Invalidate session
    response.sendRedirect("login.jsp");  // Redirect to login after logout
%>
