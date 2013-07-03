<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript' src="/mvctest/dwr/engine.js"></script>
<script type='text/javascript' src="/mvctest/dwr/util.js"></script>
<script type="text/javascript" src="/mvctest/dwr/interface/dwrService.js"></script>

<title>Home Page</title>
</head>
<body>
<h1>Persons</h1>
 
<c:url var="addUrl" value="/persons/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
 <thead style="background:#fcf">
  <tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Balance</th>
   <th colspan="3"></th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${persons}" var="person">
   <c:url var="editUrl" value="/persons/edit?id=${person.id}" />
   <c:url var="deleteUrl" value="/persons/delete?id=${person.id}" />
  <tr>
   <td><c:out value="${person.firstName}" /></td>
   <td><c:out value="${person.lastName}" /></td>
   <td><c:out value="${person.balance}" /></td>
   <td><a href="${editUrl}">Edit</a></td>
   <td><a href="${deleteUrl}">Delete</a></td>
  </tr>
 </c:forEach>
 </tbody>
</table>


<c:choose>
  <c:when test="${empty persons}">
     There are currently no persons in the list. <a href="${addUrl}">Add</a> a person.
  </c:when> 
  <c:otherwise>
  <p>
	<table border="0">
	  <tr>
	    <td><input type="button" value="Add Person" onclick="window.location='${addUrl}'"/></td>
	    <td><input type="button" value="Delete all" onclick="deleteAll()"/></td>
	  </tr>
    </table>
  </p>
  </c:otherwise>  
</c:choose>

<script type="text/javascript">

	// Delegates to the dwrService
	function deleteAll() {
		dwrService.deleteAll({
			callback : handleAddSuccess,
			errorHandler : handleAddError
		});
	}

	// data contains the returned value
	function handleAddSuccess(result) {
		// Assigns data to result id		
		console.log(result);
		// refresh page
		document.location.reload(true);
	}

	function handleAddError(error) {
		// Show a popup message
		console.log(error);
		alert("Unable to add new person!");
	}
</script>
</body>
</html>