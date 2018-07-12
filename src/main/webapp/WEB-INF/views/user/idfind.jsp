<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>아이디 비밀번호 찾기</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="/thearc/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/thearc/resources/bootstrap/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> -->
    <!-- <link href="/thearc/resources/bootstrap/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" /> -->

    <!-- ///배경관련  -->
	<link rel="stylesheet" href="/thearc/resources/bootstrap/css/vegas.min.css">
	<script src="/thearc/resources/bootstrap/js/jquery-2.1.3.min.js"></script>
	<script src="/thearc/resources/bootstrap/js/vegas.min.js"></script>
  </head>

  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="/thearc/user/login"><b>The Arc</b>Team</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg"></p>

	<form name="form" action="/thearc/user/idfindmail" method="get">
		<div id='expression' style="padding-bottom:20px">
			<b>회원 가입시 입력하신 이메일 주소를 입력하시면,<br></b>
			<b>아이디 및 비밀번호 변경 링크를 보내드립니다.<br></b>
		</div>
  	<div class="form-group has-feedback">

    <input type="text"      name="email" size="12"  placeholder="이메일"  >&nbsp;@ 
    <input type="text" name="email2" size="12" >
        	<select name="company" onclick="mailcheck()" style="margin-left:10px">
   				<option value="직접입력" selected="selected">직접입력</option>
    			<option value="naver.com">네이버</option>
    			<option value="daum.net" >다음</option>
    			<option value="nate.com" >네이트</option>
    			<option value="hotmail.com" >핫메일</option>													
    			<option value="yahoo.com" >야후</option>
   				<option value="empas.com">엠파스</option>
   				<option value="dreamwiz.com">드림위즈</option>
   				<option value="gmail.com">지메일</option>
			</select>
    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
  </div>
  
  <div class="row" style="padding-bottom:10px">
    <!-- <center> -->
    <!-- </center> -->
    	<div class="col-xs-4 col-xs-offset-4">
      		<button type="submit" class="btn btn-primary btn-block btn-flat">메일발송</button>
	    </div>
  </div>
</form>

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
		
	function mailcheck(){
		if(document.form.company.value!="직접입력"){
			document.form.email2.value=document.form.company.value;
		}else if(document.form.company.value=="직접입력"){
			document.form.email2.value="";
		}
	}
</script>