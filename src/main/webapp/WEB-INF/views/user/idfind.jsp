<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>아이디 비밀번호 찾기</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="/user/login"><b>The Arc</b>Team</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg"></p>

<form action="/user/idfindmail" method="get">
<div id='expression' style="padding-bottom:20px">
<b>회원 가입시 입력하신 이메일 주소를 입력하시면,<br></b>
<b>아이디 및 비밀번호 변경 링크를 보내드립니다.<br></b>
</div>
  <div class="form-group has-feedback">
    <input type="text" name="email3" class="form-control" placeholder="이메일을 입력해주세요"/>
    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
  </div>
 <!--  <div class="form-group has-feedback">
    <input type="password" name="upw" class="form-control" placeholder=""/>
    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
  </div> -->
  <div class="row" style="padding-bottom:10px">
    
    <center><div class="col-xs-4">
      <button type="submit" class="btn btn-primary btn-block btn-flat">메일발송</button>
      
    </div><!-- /.col --></center>
  </div>
</form>


        

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
    
				
  </body>
</html>