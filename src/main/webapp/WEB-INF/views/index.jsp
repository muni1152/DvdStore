<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title> CRUD </title>  
</head>  
<body onload="result()">  

<center>

   <h1>Welcome to DvdStore</h1> <br><br>


       <select form="login" name="role" >
        <option  name="role"  value="ADMIN">Admin</option>
           <option name="role" value="CUSTOMER">Customer</option>
             </select>
                <br>
                   <br>

 <form action="signIn" method="POST" id="login" >


          User Name :
            <input type="text" name="mobileNumber" placeholder="Enter UserName" pattern="[6789]{1}[0-9]{9}"> 
              <br>
                 <br> 
                   Password :
                     <input type="password" name ="password" >
                       <br>
                         <br>
                           <button type="submit" name="label" value="LogIn">SignIn</button>
                             <br>
                               <br>
                                 <br>
                                   <p>If You are new user...SignUp Here<p>
                                     <br>
                                       <br>
                                         <button type="submit" formaction="signUp" formmethod="POST" >SignUp</button>
                                            <br>
                                              <br>
                                                </form></center>

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
