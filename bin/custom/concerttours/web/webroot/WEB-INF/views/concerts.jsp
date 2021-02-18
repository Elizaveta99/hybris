<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>List of bands</title>
    <link rel="stylesheet" href="<c:url value="/static/concerttours-webapp.css"/>" type="text/css"
          media="screen, projection"/>
</head>
<div class="container">

    <h2>List of concerts</h2>

    <div>
        <table>
            <thead>
            <tr>
                <th><b>Name</b></th>
                <th><b>Date</b></th>
                <th><b>Venue</b></th>
                <th><b>Producer</b></th>
            </tr>
            </thead>
            <c:forEach var="concert" items="${concerts}">
                <tr>
                        <td>${concert.name}</td>
                        <td>
                            <fmt:formatDate pattern="dd:MM:yyyy" value="${concert.date}" />
                        </td>
                        <td>${concert.venue}</td>
                        <td>${concert.producer.firstName} ${concert.producer.lastName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</html>