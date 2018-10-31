<!DOCTYPE html> 
<html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
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
<title>Dvd create</title>  
<h1>Enter DVD details</h1>
</head>  
<body>  

<form:form method="post" action="createDvd" modelAttribute="dvd" >   

      <table >  
  
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

	 <tr> <td> Category : </td><td>
  	      <c:forEach  var="category" items="${categories}"> 
  	     <tr> <td>   <input type="checkbox" name="id" value="${category.id}"/> 
   	         ${category.category}</td></tr>
	    </c:forEach> 
  	  </td></tr>
           
          <td><input type="submit" onclick="return validateCheckBox() " value="Save" /></td>    
       </table><br>
  	
                </form:form>  
      
<form action="admin" method="POST">
<button  type="submit">Cancel</button><br><br>
 <button  type="submit" formaction="dvd" formmethod="post" >Back to Home PAge</button><br><br>
</form>

   

<script>

function validateCheckBox()

{
    var checkboxs=document.getElementsByName("id");
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
      return  (confirm("Are you sure to Create Dvd"));
      
    }  else { 
       alert("Please Select atleast one Category");
       return false;
    }
}

</script>
</body>  
</html>  
