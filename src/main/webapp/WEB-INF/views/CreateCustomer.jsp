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

 <form:form method="post" action="createCustomer" modelAttribute="customer" >   

   <table>

    <tr>    
         <td>Name : </td>   
         <td><form:input path="name"  required= "required"/></td>  
     </tr>  

    <tr>    
         <td>User Name(which is your valid mobile number) : : </td>   
         <td><form:input path="user.userName" pattern="[6789]{1}[0-9]{9}" required= "required"  /></td>  
     </tr>

     <tr>    
         <td>Mail Id : </td>   
         <td><form:input path="mailId" maxlength="50" pattern="[a-zA-Z0-9]+[@]{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z]{2,4}" required= "required"/></td>  
     </tr>

     
     <tr>    
         <td>Address Line: </td>   
         <td><form:input path="addresses[0].addressLine" maxlength="15" pattern="[a-zA-Z0-9/,-.&\s]+" required= "required" /></td>  
     </tr>

      <tr>    
         <td>City: </td>   
         <td><form:input path="addresses[0].city"  maxlength="30"  pattern="[a-zA-Z]+" required= "required" /></td>  
     </tr>

      <tr>    
         <td>Mobile Number: </td>   
         <td><form:input path="mobileNumber" pattern="[6789]{1}[0-9]{9}" required= "required" /></td>  
     </tr>

     <tr>    
         <td>State: </td>   
         <td><form:input path="addresses[0].state"  maxlength="30"  pattern="[a-zA-Z]+"  required= "required" /></td>  
     </tr>

     <tr>    
         <td>Country: </td>   
         <td><form:input path="addresses[0].country"  maxlength="30"  pattern="[a-zA-Z]+"  required= "required" /></td>  
     </tr>

    <tr>    
         <td>PinCode: </td>   
         <td><form:input path="addresses[0].pinCode"  maxlength="30"  pattern="[0-9]{6}" required= "required" /></td>  
     </tr>

     <tr>    
         <td>Password: </td>   
         <td><form:input type="password" path="user.password"  maxlength="30"  pattern="[a-zA-Z0-9@./+-*&^%$#!~]+"/></td>  
     </tr>

 </table><br>

 <button type="submit" onclick="return confirm('Are you sure to Create Customer')" value="Save" >

      <input type="reset"><br><br>
       <button  type="submit" formaction="index" formmethod="POST">Cancel</button><br><br>

          </form:form>  

</body>  
</html>  
