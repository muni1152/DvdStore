<!DOCTYPE html>  
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
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


<body>


 <table> 

       <tr>
          <th>Order Id</th><th>Customer Id</th><th>Order date</th><th>Address</th><th>View Ordered Dvds</th>
      </tr>
         <c:forEach  var="order" items="${orders}"> 
            <tr>

	    <td> ${order.orderId}</td>
	    <td> ${order.customerId}</td>
	    <td> ${order.orderDate}</td>
            <td>  <c:set var="address" scope="session" value="${order.address}"/>
           ${address.addressLine},${address.city},${address.state},  ${address.country},${address.pinCode} </td>
 
 <td>
<div class="dvd">
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn " data-toggle="modal" data-target="#${order.orderId}">Ordered Dvds</button>

  <!-- Modal -->
  <div class="modal fade" id="${order.orderId}" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Dvds Details</h4>
        </div>
        <div class="modal-body">
         <table>
           <tr>  
              <th>Dvd ID</th><th>Name</th><th>Language</th><th>Price</th><th>Rating</th><th>ReleaseDate</th>
          </tr>
                <c:forEach  var="dvd" items="${order.dvds}"> 
              	 <tr>
			<td>${dvd.id}</td>
			<td>${dvd.name}</td>
			<td>${dvd.language}</td>  
			<td>${dvd.price}</td>
			<td>${dvd.rating}</td>
			<td>${dvd.releaseDate}</td>
              </tr>
                 </c:forEach>
         </table>
        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
</td>

</tr>
</c:forEach>
 </table>
<br>
<form action="admin" method="POST">
<button  type="submit" >Back</button><br><br>
</form>


 <script>
  
</script>
</body>  
</html> 
