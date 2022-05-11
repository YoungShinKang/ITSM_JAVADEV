<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Web Login Test</title>
		<!-- jQuery --> 
		<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
		
		<script>
			$(document).ready(function() {
				$('#btnLogin').click(function() {
					var action = $('#frmLogin').attr("action");			
					/*
					var params = {
							"username" : $('#user_id').val(),
							"password" : $('#user_pw').val()
					}
					*/
					var params = {"username":"user_id","password":"asdfsf"}
					$.ajax({
						type:'POST',
						contentType: 'application/json',
						dataType :'json',
						url: action,
						data: JSON.stringify(params),
						success: function(data) {
							console.log(data.reponseMessage);
							$('#msg').html("<p style='color:green; font-weight:bold'>"+data.reponseMessage+"</p>");
						}
					});
				});
			});
		</script>
	
	</head>
	<body>
		<h2>Login Ajax(jQuery)</h2><hr/>
		<form id="frmLogin" name="frmLogin" action="/auth/login" method="post">
			<input type="text" id="user_id" name="user_id" placeholder="아이디" /><br/>
			<input type="password" id="user_pw" name="user_pw" placeholder="패스워드" /><br/>
			<input type="button" id="btnLogin" value="로그인" />
		</form>
		<div id="msg"></div>
	</body>
</html>
