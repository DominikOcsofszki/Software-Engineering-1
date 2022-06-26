<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>Parkhaus Kasse</title>
</head>
<body>
<h1><%= "Parkhaus Kasse" %></h1>
<%--<form action="pay-servlet" method="get" id="payMe">--%>
<form target="_blank" action="./main-servlet" method="get" >
<%--<form action="./index.jsp" method="get" >--%>


    <p>Choose your Subscription:</p>

    <form>

        <input type="radio" id="week" name="cmd" value="week">
        <label for="week">week</label><br>
        <input type="radio" id="month" name="cmd" value="month">
        <label for="month">month</label><br>
        <input type="radio" id="year" name="cmd" value="year">
        <label for="year">year</label>
        <input type="text" name="carNr">
        <input action="./index.jsp" type="submit">
<%--        <form action="./index.jsp" method="get" >--%>


    </form>


    <%--    <input type="text" name="cmd">--%>
    <%--    <input type="text" name="carNr">--%>
    <%--    <input type="submit">--%>
</form>
<a href="index.jsp">zurück zum Parkhaus</a>
</body>
</html>
