<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/component.tld" prefix="component"%>
<%@ page isELIgnored="false" %>
<%
   response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
   response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance 
   response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
   response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%> 

