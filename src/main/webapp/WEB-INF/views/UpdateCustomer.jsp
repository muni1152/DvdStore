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


 <form:form method="post" action="updateCustomerForm" modelAttribute="customer" >   


   <table>
<form:hidden path="id"/>

    <tr>    
         <td>Name : </td>   
         <td><form:input path="name"  required= "required"/></td>  
     </tr>  

     <tr>    
         <td>Mobile Number: </td>   
         <td><form:input path="mobileNumber" pattern="[6789]{1}[0-9]{9}" required= "required" /></td>  
     </tr>


    <tr>    
         <td>Mail Id : </td>   
         <td><form:input path="mailId" maxlength="50" pattern="[a-zA-Z0-9]+[@]{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z]{2,4}" required= "required"/></td>  
     </tr>

   
 
 </table>




<button type="submit" onclick="return confirm('Are you sure to update Customer')" value="Save" >Save</button><br><br>
      <button type="reset">Reset</button><br><br>
       <button  type="submit" formaction="displayCustomer" formmethod="POST">Cancel</button><br><br>

          </form:form>  


</body>  
</html>  
