<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% String URL_AppRelative = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="Portal CFDI">
	    <meta name="author" content="Fernando Robles">
	    <link rel="icon" href="<%= URL_AppRelative %>/resources/img/favicon.ico">		    
	
	    <!-- Bootstrap core CSS -->
	    <link href="<%= URL_AppRelative %>/resources/bootstrap/3.2.0/bootstrap.min.css" rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="<%= URL_AppRelative %>/resources/bootstrap/3.2.0/signin.css" rel="stylesheet">
	
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->  
	    <script src="<%= URL_AppRelative %>/resources/bootstrap/js/jquery.min.js"></script>
	    <script src="<%= URL_AppRelative %>/resources/bootstrap/js/jquery.placeholder.js"></script>
	    <script>
		   // To test the @id toggling on password inputs in browsers that don’t support changing an input’s @type dynamically (e.g. Firefox 3.6 or IE), uncomment this:
		   // $.fn.hide = function() { return this; }
		   // Then uncomment the last rule in the <style> element (in the <head>).
		   $(function() {
		    // Invoke the plugin
		    $('input, textarea').placeholder();
		    // That’s it, really.
		    // Now display a message if the browser supports placeholder natively
		   });
		   
		   function redirectToLogin(){
			   document.location = "/Login";
		   }
	    </script>
		<title>Inactive</title>
	</head>
	<body>
		
		<!--login modal-->
		<div class="container">	      
          	<h1 class="row text-center">
          		<img src="<%= URL_AppRelative %>/resources/img/mciTop.png" />
          	</h1>
	        <div class="alert alert-danger"><spring:message code="label.sessionalert"/> <a href="<%= URL_AppRelative %>/Login" class="alert-link"><spring:message code="label.login"/> </a></div>	        	        	      

    	</div> <!-- /container -->
</body>

</html>