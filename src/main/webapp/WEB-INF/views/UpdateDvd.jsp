<!DOCTYPE html> 
<html>  
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %> 
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
<title>Dvd Update</title>  
<h1>Enter DVD details</h1>
</head>  
<body>  
  
<form:form method="post" action="update" modelAttribute="dvd" >   

      <table >  
         <form:hidden path="id"/>

         <tr>    
          <td>Name : </td>   
          <td><form:input path="name"  maxlength="30" pattern="[a-zA-Z0-9\s]+" required= "required"/></td>  
         </tr>    

         <tr>    
          <td>Language : </td>   
          <td><form:input path="language" maxlength="15" pattern="[a-zA-Z]+" required= "required" /></td>  
         </tr>

        <tr>    
          <td>Rating : </td>   
          <td><form:input type="number" path="rating"  min="0" max="5" step="0.1" required= "required"/></td>  
         </tr>


          <tr>    
          <td>Price : </td>   
          <td><form:input type="number" path="price" min="0" max="1000" step="0.1"  required= "required"/></td>  
         </tr>
    
          <tr>    
          <td>Release Date : </td>   
          <td><form:input type="date" path="releaseDate"  required= "required"/></td>  
         </tr>


        <tr> <td> Category : </td></tr>
  <c:forEach  var="category" items="${categories}"> 

<c:set var="done" value="false" />
   <c:forEach  var="cat" items="${dvd.categories}"> 

<c:if test ="${cat.category eq category.category}">
       <tr> <td><input type="checkbox" name="categoryId"   value="${category.id}" checked/> 
         ${category.category}</td></tr>
         <c:set var="done" value="true" />
         
</c:if>

</c:forEach>

<c:if test="${!done}">   
       <tr> <td>   <input type="checkbox"  name="categoryId" value="${category.id}" /> 
         ${category.category}</td></tr>
      
</c:if>
  
 </c:forEach> 
   
     
        
       </table><br>
  	
              
               
  <button type="submit" name=label onclick="return validateCheckBox()" value="UpdateDvd">Update Dvd</button><br><br>
  <input type="reset"><br><br>
  <button  type="submit" formaction="display" formmethod="post" >Cancel</button><br><br>  </form:form>  


  <script>

function validateCheckBox()

{
    var checkboxs=document.getElementsByName("categoryId");
    var result=false;
    for(var i=0,l=checkboxs.length;i<l;i++)
    {
        if(checkboxs[i].checked)
        {
            result=true;
            break;
        }
    }
    if(result){
      return  (confirm("Are you sure to update Dvd"));
      
    }  else { 
       alert("Please Select atleast one Category");
       return false;
    }
}

</script>

</body>  
</html>  
