<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String URL_AppRelative = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">	    
	    <meta name="author" content="Fernando Robles">
	    <link rel="shortcut icon" href="<%= URL_AppRelative %>/resources/img/favicon.ico">		    
	
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
	    </script>
		<title>Portal</title>
	</head>
	<body>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">			
					<strong>Error: </strong> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
		<!--login modal-->
		<div class="container">

	      <form class="form-signin" role="form" action="<%= URL_AppRelative %>/j_spring_security_check" method="POST">
          	<h1 class="row text-center">
          		<img src="<%= URL_AppRelative %>/resources/img/mciTop.png" />
          	</h1>
	        <input type="text" class="form-control" placeholder="Usuario / User" required autofocus  name="j_username">	        
	        <input type="password" class="form-control" placeholder="Contraseña / Password" required  name="j_password">
	        <input type="text" class="form-control" placeholder="Provedor / Provider" required autofocus  name="j_emp">	        
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesión</button>
	      </form>

    	</div> <!-- /container -->
</body>

</html>