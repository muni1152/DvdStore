<!DOCTYPE html> 
<html>  

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<head> 

<style>

.input {
    padding: 5px;
padding-right: 50px;
    border:none;
}
.text {
 margin-right: 10px;
 }
 </style> 
<meta  content="text/html; charset=ISO-8859-1">  
<title>Customer create</title>  
<h1>Enter Customer details</h1>
</head>  


<body>  


 <form:form method="post" action="addAddressForm" modelAttribute="address" >   

     <table>
<form:hidden path="id"/>

     <tr>    
         <td>Address Line: </td>   
         <td><form:input path="addressLine" maxlength="15" pattern="[a-zA-Z0-9/,-.&\s]+" required= "required" /></td>  
     </tr>

      <tr>    
         <td>City: </td>   
         <td><form:input path="city"  maxlength="30"  pattern="[a-zA-Z]+" required= "required" /></td>  
     </tr>

     

     <tr>    
         <td>State: </td>   
         <td><form:input path="state"  maxlength="30"  pattern="[a-zA-Z]+"  required= "required" /></td>  
     </tr>

     <tr>    
         <td>Country: </td>   
         <td><form:input path="country"  maxlength="30"  pattern="[a-zA-Z]+"  required= "required" /></td>  
     </tr>

    <tr>    
         <td>PinCode: </td>   
         <td><form:input path="pinCode"  maxlength="30"  pattern="[0-9]{6}" required= "required" /></td>  
     </tr>

    

 </table><br>

<c:if test="${action eq 'updateAddress'}">

     <button type="submit" formaction="updateAddressForm" formmethod="post" onclick="return confirm('Are you sure to update address')"  >Update Address</button><br><br>
      
</c:if>

<c:if test="${action ne 'updateAddress'}">

 <button type="submit" onclick="return confirm('Are you sure to Create address')"  >Save</button><br><br>

</c:if>

</form:form>  

<form action="displayCustomer" method="POST">
<button  type="submit" >Cancel</button><br><br></form>

</body>  
</html>  
