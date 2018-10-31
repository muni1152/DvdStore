<!DOCTYPE html>  
  
<html>  
<head>  
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
<h1>Customer details--></h1> 
 <table style=width:60% > 
<br>
	<P>Customer Id = ${customer.id}</p>
	<P>Name = ${customer.name}</p>
	<p>Mobile No  = ${customer.mobileNumber}</p>
        <p>Mail Id =  ${customer.mailId}</p>
<br>
<form action="updateCustomer" method="POST">
    <input type="hidden" name="customerId" value="${customer.id}">
      <button type="submit" >Edit Profile</button>
       <br><br>
                 <button type="submit" formaction="purchaseDvd" formmethod="post" >purchase</button><br>
                  <br>
                    <input type="hidden" name="mobileNumber" value="${customer.mobileNumber}">
                     <input type="hidden" name="customerId" value="${customer.id}">
                       <button type="submit" formaction="addAddress" formmethod="post" >Add Address</button>
                       <br><br>
                         <button type="submit"formaction="viewOrder" formmethod="post">View Orders</button>
    
                            </form>
<tr>
   <th>AddressLine</th><th>City</th><th>State</th><th>Country</th><th>PinCode</th><th>Action<th></tr>
<br><br>
  <c:forEach  var="address" items="${customer.addresses}"> 
     <tr> 
        <td> ${address.addressLine} </td>
        <td> ${address.city} </td>
        <td> ${address.state} </td>
        <td> ${address.country} </td>
        <td> ${address.pinCode} </td>
        <form action="updateAddress" method="POST">
        <input type="hidden" name="addressId" value="${address.id}">
        <td><button type="submit" formaction="removeAddress" formmethod="post">Remove</button>
        <button type="submit">Edit</button></td>
        </form>
     </tr>

  </c:forEach>
</table>


 <c:if test="${action eq 'viewOrder'}">

   <p>Order Detais</p>
     <table style=width:60%>
         <tr>
            <th>OrderId</th><th>Dvd Name</th><th>Dvd Language</th><th>Dvd Price</th><th>Order date</th><th>Address</th><th>Action</th>
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
           <td>
              <form action="cancelOrder" method="POST">
              <input type="hidden" name="orderId" value="${order.orderId}">
             <button type="submit" >Cancel Order</button>
             </form>
            <td>
       </tr>
      </c:forEach>
     </c:forEach>
    </table>

<form action="displayCustomer" method="POST">
       <br><br>  <button type="submit" >Back</button></form><br><br>
    </c:if>


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
