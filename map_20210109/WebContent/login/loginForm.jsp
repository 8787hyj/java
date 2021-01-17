<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=9" > -->

 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 <meta name="description" content="">
 <meta name="author" content="">


<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Insert title here</title>
<style type="text/css">
	
	@import url("http://fonts.googleapis.com/earlyaccess/nanumgothic.css");
	
	html {
		height: 100%;
		
	}
	
	body {
	    width:100%;
	    height:100%;
	    margin: 0;
  		padding-top: 80px;
  		padding-bottom: 40px;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		/* background:linear-gradient(to bottom right, #0098FF, #6BA8D1); */
	}
	
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	}
	
	.form-signin .form-control {
  		position: relative;
  		height: auto;
  		-webkit-box-sizing: border-box;
     	-moz-box-sizing: border-box;
        	 box-sizing: border-box;
  		padding: 10px;
  		font-size: 16px;
	}
	 
	
	
</style>
<title>Insert title here</title>
<script>
	var sessionId = '<%= session.getId() %>';

	
	function AuthResult() {
		
		var verNumber = get_version_of_IE();

		demo1.innerHTML = verNumber;
		
		if (verNumber == -1) {
			demo2.innerHTML = "인터넷 익스플로러가 아닌 다른 브라우저를 사용중이십니다.";
			
			var firstName = document.getElementById('firstName').value;
			document.getElementById("result").innerText = firstName;
			
			/* var url = location.href = "KRCMFVein://!korCen0850*airForce&"+sessionId+"&" +firstName; */ 
			
			document.form.method = "post";
			document.form.submit();
		
		} else {
			demo2.innerHTML = "인터넷 익스플로러를 사용중이십니다.";
			
			var firstName = document.getElementById('firstName').value;
			document.getElementById("result").innerText = firstName; 
			
			/* window.location.href = location.href = "KRCMFVein://!korCen0850*airForce&"+sessionId+"&" +firstName; */
			 
			setTimeout(function(){
			
				var form = document.createElement('form');
				form.setAttribute('method', 'post');
				/* form.setAttribute('action', 'http://192.168.0.117:8080/KORECEN_WebLogin/' );	 */	
				//form.setAttribute('action', 'http://localhost:8080/KORECEN_WebLogin/' ); 
				document.charset = "utf-8";
					
				var hiddenField = document.createElement('input');
				hiddenField.setAttribute('type', 'hidden');
				hiddenField.setAttribute('name', 'firstName');
				hiddenField.setAttribute('value', firstName);
				form.appendChild(hiddenField);
			
			
				document.body.appendChild(form);
				form.submit(); 
				
		  }, 3000);
		
		} /* 버전확인 */
	} /* AuthResult() */
	
	
	function get_version_of_IE() {

		var word;

		var agent = navigator.userAgent.toLowerCase();

		// IE old version ( IE 10 or Lower ) 
		if (navigator.appName == "Microsoft Internet Explorer")
			word = "msie ";

		// IE 11 
		else if (agent.search("trident") > -1)
			word = "trident/.*rv:";

		// Microsoft Edge  
		else if (agent.search("edge/") > -1)
			word = "edge/";

		// 그외, IE가 아니라면 ( If it's not IE or Edge )  
		else
			return -1;

		var reg = new RegExp(word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})");

		if (reg.exec(agent) != null)
			return parseFloat(RegExp.$1 + RegExp.$2);

		return -1;
	}
	
	

</script>
	
</head>
<body cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">

	<div class="card align-middle" style="width:20rem; border-radius:20px;" align="center">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">로그인</h2>
		</div>
		<div class="card-body">
		
		      <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/login" name="form">
		        
		        <label for="inputEmail" class="sr-only">Your ID</label>
		        <input type="text" id="firstName" name="firstName" class="form-control" placeholder="firstName" required autofocus><BR>
		        <input type="text" id="lastName" name="lastName" class="form-control" placeholder="lastName" required autofocus><BR>
		        <input type="button" id="btn-Yes" class="btn btn-lg btn-primary btn-block" onclick="AuthResult(); " value="확인">
		        
        <div id='result' style="display:none;"></div>
		<p style="font-size: 12px"><%= session.getId() %></p> 
		<p id="demo1"></p>
		<p id="demo2"></p>
		      </form>
      
		</div>
	</div>

	<div class="modal">
	
	</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
  </body>
</html>