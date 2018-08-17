<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 배경 --%>
<%@ include file="/WEB-INF/views/include/userbackground.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="/thearc/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    
    <!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> -->
    <!-- Theme style -->
    <link href="/thearc/resources/bootstrap/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <!-- <link href="/thearc/resources/bootstrap/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" /> -->


  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="/thearc/sboard/list"><b>The Arc</b></a>
      </div>
      <div class="login-box-body">
        <p class="login-box-msg"></p>
	
	<form action="/thearc/user/modifypw" method="POST" name="formm">
		<div id='expression' style="padding-bottom:20px">
			<b>${user.uid }님 변경하실 비밀번호를 입력해 주세요.<br></b>
	<input type="hidden" name="uid" value="${user.uid }">
	
	</div>
	  <div class="form-group has-feedback">
	    <input type="password" name="upw" class="form-control" placeholder="비밀번호"/>
	    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	  </div>
	  <div class="form-group has-feedback">
	    <input type="password" name="upwcheck" class="form-control" placeholder="비밀번호 확인"/>
	    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	  </div>
  
  <div class="row" style="padding-bottom:10px">
    
    <div class="col-xs-5">
      <input type="button" class="btn btn-primary btn-block btn-flat" onclick="go_save()" value="비밀번호 변경" style="margin-left:75%">
      
      
    </div>
  </div>
</form>

      </div>
    </div>
<!-- 
    jQuery 2.1.4
    <script src="/thearc/resources/bootstrap/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    Bootstrap 3.3.2 JS
    <script src="/thearc/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    iCheck
    <script src="/thearc/resources/bootstrap/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script> -->
    <script>
    function go_save() {
 if (document.formm.upw.value == "") {
    alert("비밀번호를 입력해 주세요.");
    document.formm.upw.focus();
  } else if ((document.formm.upw.value != document.formm.upwcheck.value)) {
    alert("비밀번호가 일치하지 않습니다.");
    document.formm.upw.focus();
  } else{
	  document.formm.action = "/thearc/user/modifypw";
	  document.formm.submit();
  }
}
    </script>
				
  </body>
</html>
