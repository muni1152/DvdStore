<!DOCTYPE html> 
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<html>  
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

</head>  
<body onload="result()">
  

<c:if test="${action eq 'add'}">

  <form:form method="post" action="addCategory" modelAttribute="category" >   


      <table >  
  
         <tr>    
          <td>Enter Category : </td>   
          <td><form:input path="category" maxlength="20"  pattern="[a-zA-Z]+" required= "required"/></td>  
         </tr> 
     </table>

          <button type="submit" name="label" value="Create" onclick="return confirm('Are you sure to Create Category')">Create Category </button><br><br>

             <button  type="submit" formaction="category" formmethod="post">Cancel</button><br><br>
               <button  type="submit" formaction="admin" formmethod="post" >Back to Home PAge</button><br><br>
</form>


 </form:form>  

</c:if>


<c:if test="${action eq 'Update'}">

  <form:form method="post" action="updateCategoryForm" modelAttribute="category" >   

     <form:hidden path="id"/>
     <form:hidden path="status"/>
      <table >  
  
         <tr>    
          <td>Enter new name for Category : </td>   

          <td><form:input path="category" maxlength="20"  pattern="[a-zA-Z]+" required= "required"/></td>  
         </tr> 
     </table>

          <button type="submit" name="label" value="Create" onclick="return confirm('Are u sure to update Category for ID -' + ${category.id})">Update Category </button>

            <button  type="submit"  formaction="displayCategory" formmethod="POST">Cancel</button><br><br>
 </form:form>  

</c:if>

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
