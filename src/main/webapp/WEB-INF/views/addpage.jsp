<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <script type='text/javascript' src="/mvctest/dwr/engine.js"></script>
   <script type='text/javascript' src="/mvctest/dwr/util.js"></script>
   <script type="text/javascript" src="/mvctest/dwr/interface/dwrService.js"></script>
   <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
  
<title>Create person</title>
</head>
<body>
 
<h1>Create New Person</h1>

<div class="content">
	 <table>
	  <tr><td>First Name</td><td><input id="firstName"/></td></tr>
	  <tr><td>Last Name</td><td><input id="lastName"/></td></tr>
	  <tr><td>Balance</td><td><input id="balance"/></td></tr>
	 </table>
	 <input type="button" value="Save" onclick="savePerson()"/>
</div>
  
<div class="resultInfo">
 Result: <b><span id="result"></span></b>
 
 <div id="getBack" style="display:block" > 
    <p>Return to <a href="/mvctest/homepage">Main List</a>
              or <a href="/mvctest/persons/add">add another person</a>
    </p> 
 </div>
</div>

<script type="text/javascript">
    jQuery(document).ready(function() {
    	jQuery(".resultInfo").hide();
    });

	// Retrieves the matching value
	// Delegates to the dwrService
	function savePerson() {
		// Retrieve value of text inputs
		var operand1 = dwr.util.getValue("firstName");
		var operand2 = dwr.util.getValue("lastName");
		var operand3 = dwr.util.getValue("balance");
		
		// Pass two numbers, a callback function, and error function
		dwrService.add(operand1, operand2, operand3, {
			callback : handleAddSuccess,
			errorHandler : handleAddError
		});
	}

	// data contains the returned value
	function handleAddSuccess(result) {
		// Assigns data to result id		
		console.log(arguments);
		// set result
		if (result == "success") {
			var date=new Date();
			var res = result + ". You have added a new person at " + date.getHours()+":"+date.getMinutes();
			dwr.util.setValue("result", res);
		} else {
			dwr.util.setValue("result", result);
		}
		// hide context panel
		jQuery(".content").hide();
		// show resultInfo panel
		jQuery(".resultInfo").show();
	}

	function handleAddError(error) {
		// Show a popup message
		console.log(error);
		dwr.util.setValue("result", "failed");
		alert("Unable to add new person!");
		jQuery(".content").hide();
		jQuery(".resultInfo").show();
	}
</script>
</body>
</html>