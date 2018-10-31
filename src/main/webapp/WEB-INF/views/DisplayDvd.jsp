<!DOCTYPE html>  
  
<html>  
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<head>  
 <meta charset="utf-8">
   
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Dvds list</title>  
<style>

       table {
                width : 60%;
            }
            
            th {
                text-align: center;
                font-size: 15px;
                color: black;
                height: 35px;
                width: auto;
                padding:10px;
            }
            td {
                text-align: center;
                padding:10px;
            }
     </style>

</head>  


<body onload="result()">


<h1>Available Dvds--></h1>  

<form action="search" method="POST">
<br>

<input type="text" name="dvdId" placeholder="Enter Dvd Id">
 <input type="submit" name="label" value ="Search By Id" >
<input type="hidden" name="status" value="Y">
</form>
<table style=width:60% > 
 
<tr><th>Id</th><th>Name</th><th>Language</th><th>Price</th>  
<th>Rating</th><th>Release Date</th>
<th>Category</th>
</tr> 


<c:forEach var="dvd" items="${dvds}" >  
     <tr>

	<td>${dvd.id}</td>
	<td>${dvd.name}</td>
	<td>${dvd.language}</td>  
	<td>${dvd.price}</td>
	<td>${dvd.rating}</td>
	<td>${dvd.releaseDate}</td>

        
    
         <td> 
               <c:forEach  var="category" items="${dvd.categories}"> 
                ${category.category}
                </c:forEach>
         </td> 
 
          <c:if test="${action ne 'category'}">

               <form action="delete" method="POST">
              <td>
            <input type="hidden" name="dvdId" value="${dvd.id}"/>

               <button type="submit" name="label" value ="delete">Delete</button> </form></td>

          <td>  <form action="updateDvd" method="POST">
	  <input type="hidden" name="dvdId" value="${dvd.id}"/>
 	   <button type="submit" name="label" value ="Edit" ">Edit</button></td>
           </form>
         </td> 
     </tr>
      </c:if>


    <c:if test="${action eq 'category'}"><td>

    <form action="removeCategoryToDvd" method="POST">
      <td>
        <input type="hidden" name="id" value="${dvd.id}">
          <input type="hidden" name="categoryId" value="${category.id}">
            <button type="submit" onclick="return confirm('Are you sure to Delete Category for this Dvd')">Remove</button></td> </tr>
               </form></td>
              </c:if>
       

</c:forEach>  
</table> 
      
<form action="admin" method="POST">
 <button  type="submit" formaction="dvd" formmethod="post" >Back</button><br><br>
<button  type="submit">Back to Home Page</button><br><br>

</form>

<form action="logOut" method="POST">
<button type="submit" >Logout </button>
</form>
    
<script type="text/javascript">  
    function result() {
     var message = "${message}";
    if (message.length != 0) {
        alert(message);
     }
 } 

</script> 

</body>  
</html>  
