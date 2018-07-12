<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>메일발송</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="/thearc/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/thearc/resources/bootstrap/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />

        <!-- ///배경관련  -->
	<link rel="stylesheet" href="/thearc/resources/bootstrap/css/vegas.min.css">
	<script src="/thearc/resources/bootstrap/js/jquery-2.1.3.min.js"></script>
	<script src="/thearc/resources/bootstrap/js/vegas.min.js"></script>
		
  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="/thearc/user/login"><b>The Arc</b></a>
      </div>
      <div class="login-box-body">
        <p class="login-box-msg"></p>


		<div id='expression' style="padding-bottom:20px">
			<b>입력하신 메일로 요청하신 계정정보를 보냈습니다.<br></b>
			<b>해당 이메일을 확인 하시고, 비밀번호 변경이<br></b>
			<b>필요하신 경우 해당 이메일을 통해 변경 가능합니다.<br></b>
		</div>
	
		  <div class="row" style="padding-bottom:10px">
		   <div class="col-xs-4 col-xs-offset-4">
		      <button class="btn btn-primary btn-block btn-flat"><a href="/thearc/user/login">로그인</a></button>
		    </div>
		  </div>
      </div>
    </div>

  </body>
</html>

<script>
	$(function(){
		$('body').vegas({
			slides:[
				{
					video : {
						src: ['/thearc/resources/bootstrap/dew.webm'],
						loop:false,
						mute:true
					}
				}
			]
		});
	});
</script>