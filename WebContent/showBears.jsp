<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Karud v�lja</title>
</head>
<body>



<c:forEach var="bear" items="${bears}">
  <tr>
          <td>${bear.id}</td>
          <td>${bear.name}</td>
          <td>${bear.weight}</td>
        </tr>

</c:forEach>

</body>
</html>