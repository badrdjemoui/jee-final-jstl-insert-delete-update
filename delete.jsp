<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>delete</title>
    </head>
    
    <body>
     <%@ include file="menu.jsp" %>
       <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
	<form method="post" action="delete">
    	<p>
	    	<label for="id">id : </label>
	    	<input type="text" name="id" id="id" />
    	</p>
    	
    	
    	<input type="submit" />
    </form>
    
    <table>
     <tr>
        <th>id</th>
        <th>nom</th>
        <th>prenom</th>
      </tr>
      </table>
                  <c:forEach var="utilisateur" items="${ utilisateurs }">
	    	<table>
	    	 <tr><c:out value="${ utilisateur.id }" /></tr>
	    	 <tr> <c:out value="${ utilisateur.prenom }" />  </tr>
	    	 <tr> <c:out value="${ utilisateur.nom }" /> </tr>
	        </table>
	    </c:forEach>
	 
    </body>
    
</html>