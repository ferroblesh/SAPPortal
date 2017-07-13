<%@page import="com.wise.model.UserDto"%>
<%@page import="com.wise.model.MenusDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Envío de factura con orden de compra - CFD/CFDI</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<% String URL_AppRelative = request.getContextPath(); %>
<% UserDto currentUser = (UserDto) session.getAttribute("UserDetails"); %>
<% MenusDto menusDto = (MenusDto) session.getAttribute("menusDto"); %>
<link href="<%= URL_AppRelative %>/resources/img/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/bootstrap/css/bootstrap-combined.min.css" >
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/xtheme-gray.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/custom.css"/>
<link rel="stylesheet" type="text/css" href="<%= URL_AppRelative %>/resources/sencha/resources/css/ux/fileuploadfile.css"/>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/resources/sencha/ext/ext-all.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ext-lang-es.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/FileUploadField.js"></script>
<script type="text/javascript"> 	
 	var contextrootpath = "<%= URL_AppRelative %>";
 	var listarDivision = <%= menusDto.getDivision() %>; 
 	Ext.Ajax.timeout = 120000;
</script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/utilities.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/PropertyReader.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/ux/Bundle.js"></script>
<script type="text/javascript" src="<%= URL_AppRelative %>/js/add.js"></script>
<style>
.dropdown-menu li > a:hover, .dropdown-menu li > a:focus, .dropdown-submenu:hover > a {
	background-color: #eee;
	background-image: none;
	color: black;	
}
.dropdown-header {
  font-weight: bold;
  text-align: center;
}
.navbar-inner {
	background-image: linear-gradient(to bottom, #CCCCCC, #F2F2F2);
}
.navbar .nav > .active > a, .navbar .nav > .active > a:hover, .navbar .nav > .active > a:focus {
  -moz-text-decoration-color: -moz-use-text-color;
  -moz-text-decoration-line: none;
  -moz-text-decoration-style: solid;
  background-color: #FEFEFE;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.125) inset;
  color: #555555;
}
.navbar {
  margin-bottom: 0;
  overflow-x: visible;
  overflow-y: visible;
}
.navbar.navbar-static {
  z-index: 1000;
}
</style>

</head>
<body>
<img style="padding:10px;" src="<%= URL_AppRelative %>/resources/img/mciTop.png" width="130"/>
<span style="float:right"><div style="float:left; margin-right: 5px;" id="changePass"><a href="#" onclick="windowPassword();">Cambio Contraseña</a><span> </span></div> Idioma : <a href="?language=en">English</a>|<a href="?language=es">Español</a></span>
<span class="pull-right label label-default" style="margin-top: 5px; padding: 5px 60px; font-size: 15px; clear: right; background-color: #3c3c3b;"><spring:message code="label.provider"/> <%= currentUser.getUsername() %> <%= currentUser.getProviderName() %></span>
<div id="navbar-example" class="navbar navbar-static">
  <div class="navbar-inner">  	
    <div class="container" style="width: auto;">     	
      <ul class="nav" role="navigation">
        <li class=""> <a id="fat-menu" href="<%= URL_AppRelative%>/Inicio?language=${pageContext.response.locale}" role="button"><spring:message code="menu.invoicelist"/></a>
      </li>
      <% if(menusDto.getListaOC()) { %>
        <li class=""> <a id="fat-menu" href="<%= URL_AppRelative%>/OCSinFacturar?language=${pageContext.response.locale}" role="button"><spring:message code="menu.pendinginvoices"/></a></li>
      <% } %>
      <% if(menusDto.isOC() || menusDto.isSOC() || menusDto.isInbox()) { %>
        <li class="dropdown"> <a id="drop1" href="#" role="button" class="dropdown-toggle active" data-toggle="dropdown"><spring:message code="menu.sendinvoice"/><b class="caret"></b></a>
        	<ul class="dropdown-menu" role="menu" aria-labelledby="drop2" style="top:35px;">
        		<% if (menusDto.isOC()) {%>
        		<li class="divider"></li>    
        		<li class="dropdown-header"><spring:message code="submenu.purchaseorder"/></li>
        		<li class="divider"></li>
	        		<% if (menusDto.isOCCFDI()) {%>
	        			<li><a tabindex="-1" href="<%= URL_AppRelative%>/oc/cfdi?language=${pageContext.response.locale}">• CFDI</a></li>
	        		<% } %>
	        		<% if (menusDto.isOCCBB()) { %>
          				<li><a tabindex="-1" href="<%= URL_AppRelative%>/oc/cbb?language=${pageContext.response.locale}">• <spring:message code="submenu.wocfdi"/></a></li>
        			<% } %>
          		<% } %>
          		<% if ( menusDto.isSOC()) {%>
          		<li class="divider"></li>          		
          		<li class="dropdown-header"><spring:message code="submenu.wopurchaseorder"/></li>
        		<li class="divider"></li>
        			<% if (menusDto.getSocCFDI()) { %>
	          		<li><a tabindex="-1" href="<%= URL_AppRelative%>/soc/cfdi?language=${pageContext.response.locale}">• CFDI</a></li>
	          		<% } %>
	          		<% if (menusDto.getSocCBB()) { %>
	          		<li><a tabindex="-1" href="<%= URL_AppRelative%>/soc/cbb?language=${pageContext.response.locale}">• <spring:message code="submenu.wocfdi"/></a></li>
	          		<% } %>
          		<% } %>
          		<% if ( menusDto.isInbox()) { %>
          		<li class="divider"></li>
          		<li class="dropdown-header"><spring:message code="submenu.invoicewoentry"/></li>
          		<li class="divider"></li>
          			<% if (menusDto.getBuzonAgregar()) { %>
	          		<li><a tabindex="-1" href="<%= URL_AppRelative%>/fse/add?language=${pageContext.response.locale}">• <spring:message code="submenu.addinbox"/></a></li>
	          		<% } %>
	          		<% if (menusDto.getBuzonConsultar()) { %>
	          		<li><a tabindex="-1" href="<%= URL_AppRelative%>/fse/search?language=${pageContext.response.locale}">• <spring:message code="submenu.queryinbox"/></a></li>
	          		<% } %>
          		<% } %>
          	</ul>
      	</li>
      <% } %>
      <% if(menusDto.getEstatusRecepcion()) { %>
      	<li class=""> <a href="<%= URL_AppRelative %>/EstatusRecepcion?language=${pageContext.response.locale}" id="drop2" role="button" class=""><spring:message code="menu.receptionstatus"/></b></a></li>
      <% } %>
      <% if(menusDto.getEstadoCuenta()) { %>
      	<li class=""><a href="<%= URL_AppRelative %>/EstadoCuenta?language=${pageContext.response.locale}" id="drop2" role="button" class=""><spring:message code="menu.accountstatus"/></a></li>
      <% } %>
      </ul>
      <ul class="nav pull-right">
        <li id="fat-menu"> <a tabindex="-1" href="<%= URL_AppRelative%>/logout" role="button"><spring:message code="label.logout"/></a>
      </li>
      </ul>
    </div>
  </div>
</div>
<div id="formListaFactura"></div>
<div id="gridListaFactura"></div>
<script src="<%= URL_AppRelative %>/resources/bootstrap/js/jquery.min.js"></script>
<script src="<%= URL_AppRelative %>/resources/bootstrap/js/bootstrap303.js"></script>
<script>
(function ($, window, delay) {
	  var theTimer = 0;
	  var theElement = null;
	    var theLastPosition = {x:0,y:0};
	  $('[data-toggle]')
	    .closest('li')
	    .on('mouseenter', function (inEvent) {
	    if (theElement) theElement.removeClass('open');
	    window.clearTimeout(theTimer);
	    theElement = $(this);

	    theTimer = window.setTimeout(function () {
	      theElement.addClass('open');
	    }, delay);
	  })
	    .on('mousemove', function (inEvent) {
	        if(Math.abs(theLastPosition.x - inEvent.ScreenX) > 4 || 
	           Math.abs(theLastPosition.y - inEvent.ScreenY) > 4)
	        {
	            theLastPosition.x = inEvent.ScreenX;
	            theLastPosition.y = inEvent.ScreenY;
	            return;
	        }
	        
	    if (theElement.hasClass('open')) return;
	    window.clearTimeout(theTimer);
	    theTimer = window.setTimeout(function () {
	      theElement.addClass('open');
	    }, delay);
	  })
	    .on('mouseleave', function (inEvent) {
	    window.clearTimeout(theTimer);
	    theElement = $(this);
	    theTimer = window.setTimeout(function () {
	      theElement.removeClass('open');
	    }, delay);
	  });
	})(jQuery, window, 200); // 200 is the delay in milliseconds
</script>
</body>
</html>