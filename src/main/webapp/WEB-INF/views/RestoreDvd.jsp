<!DOCTYPE html>  
  
<html>  

<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Dvds list</title>  

<style>

       table {
                width : 50%;
            }
            
            th {
                text-align: center;
                font-size: 15px;
                color: black;
                height: 5px;
                width: 400;
                padding:3px;
            }
            td {
                text-align: center;
                padding:3px;
            }

 </style> 

</head>  


<body onload="result()">


<h1>Available Dvds--></h1>  

<form action="search" method="POST">
<br>
<input type="text" placeholder="Enter Dvd Id..">
 <input type="submit" name="label" value ="Search By Id" >
<input type="hidden" name="status" value="N">
</form>
<table style=width:60% >  
<tr><th>Id</th><th>Name</th><th>Language</th><th>Price</th>  
<th>Rating</th><th>Release Date</th><th>Category</th><th>Action</th></tr>  



<c:forEach var="dvd" items="${dvds}" >  
<tr>

<td>${dvd.id}</td>
<td>${dvd.name}</td>
<td>${dvd.language}</td>  
<td>${dvd.price}</td>
<td>${dvd.rating}</td>
<td>${dvd.releaseDate}</td>
<td> <c:forEach  var="category" items="${dvd.categories}"> 
${category.category}

</c:forEach> </td> 
<form action="restore" method="POST">
<td>
<input type="hidden" name="dvdId" value="${dvd.id}">
<button type="submit" name="label" value ="Restore" >Restore</button>
  </td> </tr>                                                
</form>
</c:forEach>  

</table>  

<form action="admin" method="POST">
 <button  type="submit" formaction="dvd" formmethod="post" >Back</button><br><br>
<button  type="submit">Back to Home Page</button><br><br>

<form action="logOut" method="POST">
<button type="submit" >Logout </button>
</form>
 <script>

 function result() {
    var message = "${message}";
    if (message.length != 0) {
        alert(message);
    }
} 
    
</script>


</body>  
</html>  
