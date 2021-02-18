<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of bands</title>
    <link rel="stylesheet" href="<c:url value="/static/concerttours-webapp.css"/>" type="text/css"
          media="screen, projection"/>
</head>
<div class="container">

    <h2>List of bands</h2>
    <h3>Token: ${token}</h3>

    <div>
        <table>
            <thead>
            <tr>
                <th><b>Code</b></th>
                <th><b>Name</b></th>
                <th><b>History</b></th>
                <th><b>Album sales</b></th>
            </tr>
            </thead>
            <c:forEach var="band" items="${bands}">
                <tr>
                        <td>${band.id}</td>
                        <td>${band.name}"</td>
                        <td>${band.description}$</td>
                        <td>${band.albumsSold}$</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</html>