<!DOCTYPE html> 

 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  

<html>  
<head> 
<style>

       table {
                width : 50%;
            }
            
            th {
                text-align: center;
                font-size: 15px;
                color: black;
                height: 5px;
                width: 400;
                padding:3px;
            }
            td {
                text-align: center;
                padding:3px;
            }

 </style> 

<meta  content="text/html; charset=ISO-8859-1">  

</head>  

<body onload="result()">

     <h1>Available categories</h1>
          <table>  
              <tr><th>CategoryId</th><th>Category</th><th>Action</th></tr>  


                   <c:forEach  var="category" items="${categories}"> 
              <tr>
                <td>
                   ${category.id}
              </td>
                  <td>
                     ${category.category}
                 </td>
           

 <c:if test="${operation eq 'Restore'}">

       <form action="restoreCategory" method="POST" ><td>
         <input type="hidden" name="id" value="${category.id}">
            <button type="submit" name="label" value ="Restore" onclick="return confirm('Are u sure to Restore Category for Id - ' + ${category.id})" >Restore</button><tr>
             </form>
 </c:if>
      
 <c:if test="${operation eq 'Display'}">

         <form action="deleteCategory" method="POST" >
<td>
          <input type="hidden" name="id" value="${category.id}">

            <button type="submit" formaction="viewDvdsByCategory" formmethod="POST" >view</button></td>
     
              <td> <button type="submit" name="label" value="Delete" onclick="return confirm('Are u sure to Delete Category for ID -' + ${category.id}) ">Delete 
                  </button></form><td>

     <td>  <form action="updateCategory" method="POST" >
                <input type="hidden" name="id" value="${category.id}">
           <button type="submit" name="label" value ="Update">Edit</button> 
                 </form></td>
            
 </c:if>
    </tr>

  </c:forEach> 

</table>

 <form action="admin" method="POST">
  <button  type="submit" formaction="category" formmethod="POST">Back</button><br><br>
  <button  type="submit">Back to Home PAge</button><br><br>
  <button type="submit" formaction="logOut" formmethod="POST" >Logout </button></form>

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
