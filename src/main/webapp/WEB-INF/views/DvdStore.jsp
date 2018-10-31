<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title> CRUD </title>  
</head>  
<body onload = "result()">
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<c:if test="${action eq 'admin'}">
        <br> <br>
 <form action="dvd" method="POST">
            <button type="submit" name="label" value="Dvd">Dvd</button><br><br>

            <button type="submit" name="label"  formaction="category" formmethod="POST" value="Category">Category</button><br><br>

            <button type="submit" name="label" formaction="viewCustomers" formmethod="POST" >View Customers</button><br><br>

            <button type="submit"  formaction="viewOrders" formmethod="POST">View Orders</button><br><br>
</form>
</c:if>
       
                       
  <c:if test="${action eq 'dvd'}">

       <form action="display" method="GET" >
        <h1>Welcome to DvdMenu</h1> 
          <button type="submit" >Display Dvds</button><br><br>
        
           <button type="submit" formaction="create" formmethod="POST">Create DVD</button><br><br>
             <button type="submit" formaction="restoreDvds" formmethod="POST">Restore Dvds</button><br><br>
                  <button  type="submit" formaction="admin" formmethod="POST">Back</button><br><br>
                     </form>
</c:if>
             

    <c:if test="${action eq 'category'}">

        <form action="CategoryController" method="POST"><br>
         <h1>Welcome to CategoryMenu</h1> 
           <button type="submit"  formaction="createCategory" formmethod="POST">Create Category</button><br><br>
             <button type="submit"  formaction="displayCategory" formmethod="POST" >Display Categoies</button><br><br>
              <button type="submit"  formaction="restoreCategoryForm" formmethod="POST">Restore Category</button><br><br>
                <button  type="submit"  formaction="admin" formmethod="POST" >Back</button><br><br>
                 </form>
      </c:if>

     

<form action="logOut" method="POST">
<button type="submit" >Logout </button>
</form>

 <script>
function result() {
   var message = "${message}";
   if (message.length != 0) {
        alert(message) ;
    }
} 

</script>

</body>  
</html>  
