<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
<script type="text/javascript">

$(document).ready(function(){

	$("#loginbtn").click(function (e) {
	var username = document.getElementById("userName").value;
    var password = document.getElementById("password").value; 
   
    var url="http://localhost:56120/Service1.svc/login/"+username;
    //alert(url);
	$.ajax({
		 type: 'GET',
        data: '',
        url: url,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success:
  function (data, textStatus, XMLHttpRequest) {
       	 //alert("hello");
       	 if(data.wrngpwd <3){
       	 	if(username==data.userName && password==data.pwd){
       			alert("Login success");
       			window.location.href="dashboard.jsp";
       			<%HttpSession sess = request.getSession(); 
       			sess.setAttribute("log", "Success");%>
       	 		}
       	 	else if(username==data.userName && password!=data.pwd){
       		 	alert("wrong password"); 
       		 	var countWrongpwd = data.wrngpwd;
       		 	//alert(countWrongpwd);
       		 	countWrongpwd=countWrongpwd+1;
       		 	//alert(countWrongpwd);
       		 	//alert(countWrongpwd);
       		 	var secQtn="";
       		 	var urlWrngPwd="http://localhost:56120/Service1.svc/wrongpwd/"+username+","+data.pwd+","+countWrongpwd+","+secQtn;
       		 	alert(urlWrngPwd);
       				 $.ajax({
       			 	type: 'GET',
       	       		 data: '',
       	        	url: urlWrngPwd,
       	        	contentType: 'application/json; charset=utf-8',
       	        	dataType: 'json',
       	        	success:
       	 		 	function (data, textStatus, XMLHttpRequest) {
       	        		
       	        		}
       	       		 }) 
       	       	window.location.href="logout.jsp";
       	        }
       	 		}
       			  else{
       		 		alert("you have entered wrong password for 3 times, user is disabled for login");
       	 			}
       	     	 
        }
	 })
	})
 });
</script>
<title></title>
</head>
<body>
<form action="login" method="post">
<div id="loginDiv">
<p>UserName : <input type="text" id="userName" name="userName"></input></p>
<p>Password : <input type="password" id="password" name="password"/></p>
<p><input id="loginbtn" type="submit" name="action" value="login" /></p>
<p><input id="validateUser" type="hidden" name="isValidUser" value="false"></p>
</div>
</form>
<p></p>
<p></p>
<p></p>
<p></p>
<p><input id="forgotpwd" type="submit" name="action" value="forgot password?" /></p>

</body>
</html>