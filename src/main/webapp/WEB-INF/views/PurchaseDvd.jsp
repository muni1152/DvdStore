<!DOCTYPE html>  
  
<html>  
<head>  
 <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Dvds list</title>  
<style>
th {
    text-align: left;
}
</style>
</head>  


<body onload="result()">

<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<form action="DvdController" method="POST">
<br>

<input type="text" name="dvdId" placeholder="Enter Dvd Id">
 <input type="submit" name="label" value ="Search By Id" >
</form>
<table style=width:60% > 
<c:set var="action" scope="session" value="${category}"/>  
 
<tr><th>Id</th><th>Name</th><th>Language</th><th>Price</th>  
<th>Rating</th><th>Release Date</th>
<c:if test="${action ne 'category'}">
<th>Category</th></c:if>
<th>Action</th></tr> 


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
 
        <form action="placeOrder" method="POST">
         <td>
           <p> <input type="checkbox" name="dvdId" value="${dvd.id}" /> Buy</p>
        </td>
         </tr>

</c:forEach>  

</table> 

   <input type="hidden" name="customerId" value="${customer.id}">

    <div class="dvd">
       <!-- Trigger the modal with a button -->
         <button type="button" class="btn " data-toggle="modal" data-target="#${customer.id}">Click to purchase</button>

 <!-- Modal -->
  <div class="modal fade" id="${customer.id}" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Choose your Address</h4>
        </div>
        <div class="modal-body">

         <c:forEach var="address" items="${customer.addresses}">
             <input type="radio" name="addressId" value="${address.id}">${address.addressLine},${address.city},${address.state},  ${address.country},${address.pinCode}
                <br><br>
                 </c:forEach>
        
        </div>
        <div class="modal-footer">
          <button type="submit" name="label" value="buy">Submit</button></form>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
</form>
<br><br>

<form action="displayCustomer" method="POST"><br><br>
              <button  type="submit" >Cancel</button><br><br>
                  <button type="submit" formaction="logOut" formmethod="POST" >Logout </button>
                   </form>

><br>

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
