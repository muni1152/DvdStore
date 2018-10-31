<!DOCTYPE html>  
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html>  
<head>  
   <meta charset="utf-8">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  

   <style>

       table {
                width : 80%;
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

<title>Dvds list</title>  

</head>  


<body onload="result()">


 <table> 

       <tr>
          <th>Customer Id</th><th>Name</th><th>Mobile No</th><th>Mail Id</th>
      </tr>
         <c:forEach  var="customer" items="${customers}"> 
            <tr>

	    <td> ${customer.id}</td>
	    <td> ${customer.name}</td>
	    <td> ${customer.mobileNumber}</td>
            <td> ${customer.mailId}</td>
 <td>
<div class="address">
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn " data-toggle="modal" data-target="#${customer.id}">view Address</button>

  <!-- Modal -->
  <div class="modal fade" id="${customer.id}" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Address Details</h4>
        </div>
        <div class="modal-body">
         <table>
           <tr>  
              <th>AddressLine</th><th>City</th><th>State</th><th>Country</th><th>PinCode</th>
          </tr>
                <c:forEach  var="address" items="${customer.addresses}"> 
    		 <tr> 
        	    <td> ${address.addressLine} </td>
        	    <td> ${address.city} </td>
        	    <td> ${address.state} </td>
       		    <td> ${address.country} </td>
		    <td> ${address.pinCode} </td>
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

<td>
<div class="orders">
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn " data-toggle="modal" data-target="#${customer.name}">view Orders</button>

  <!-- Modal -->
  <div class="modal fade" id="${customer.name}" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Order Details</h4>
        </div>
        <div class="modal-body">
         <table padding:10px>
<tr>
  <th>OrderId</th><th>Dvd Name</th><th>Dvd Language</th><th>Dvd Price</th><th>Order date</th><th>Shipping-Address</th>
</tr>

   <c:forEach  var="order" items="${customer.orders}"> 
         
     <c:forEach  var="dvd" items="${order.dvds}"> 
       <tr>
            <td> ${order.orderId} </td>
            <td> ${dvd.id} </td>
            <td> ${dvd.name} </td>
            <td> ${dvd.price} </td>
            <td> ${order.orderDate} </td>
            <td>  <c:set var="address" scope="session" value="${order.address}"/>
           ${address.addressLine},${address.city},${address.state},  ${address.country},${address.pinCode} </td>
       </tr>
           
      </c:forEach>
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
<td>

</td></tr>
</c:forEach>
 </table>
<br>

<form action="admin" method="POST">
<button  type="submit" >Back</button><br><br>
</form>
</body>  
</html> 
