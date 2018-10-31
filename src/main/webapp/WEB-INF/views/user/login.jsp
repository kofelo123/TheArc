<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/userbackground.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'	name='viewport'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<link href="/thearc/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"	type="text/css" />
<link href="/thearc/resources/bootstrap/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />

<!-- admin연결   -->
<link href="/thearc/resources/bootstrap/css/11st.css" rel="stylesheet" type="text/css" media='all' />
<link href="/thearc/resources/bootstrap/css/11st2.css" rel="stylesheet"
	type="text/css" media='all' />
	
<style id='_style-inline-css' type='text/css'>
.preloader {
	background-color: #111111
}

.site-title, .preloader i, .login-form, .login-form a.lost-pass,
	.btn-open-login-form, .site-content, .user-content-wrapper,
	.user-content, footer, .maintenance a {
	color: #ffffff;
}

/* 로그인상태유지 체크박스  */
#useCookie{
	margin-left:20px;
}

</style>
<script type='text/javascript'	src='http://11st.com/wp-includes/js/jquery/jquery.js?ver=1.12.4'></script>
<script type='text/javascript'	src='http://11st.com/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.4.1'></script>
<script type='text/javascript'	src='http://11st.com/wp-content/plugins/maintenance/load/js/jquery.frontend.min.js?ver=4.6.1'></script>

<script>
	var result = '${msg}';
	
	if(result == 'SUCCESS')
		alert("가입완료 되었습니다.로그인해주세요");
</script>

</head>
<body class="login-page">
	<div class="main-container">
		<div class="preloader">
			<i></i>
		</div>
		<div id="wrapper">
			<div class="login-box">
				<div class="login-logo">
					<a href="/thearc/user/login"><b>The Arc</b></a>
				</div>
				<!-- /.login-logo -->
				<div class="login-box-body">
					<p class="login-box-msg"></p>

					<form action="/thearc/login" method="post">
						<div class="form-group has-feedback">
							<input type="text" id="uid" name="username" class="form-control"	placeholder="아이디입력" />
							<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
							<span id="successFail"></span>
						</div>
						<div class="form-group has-feedback">
							<input type="password" id="upw" name="password" class="form-control"	placeholder="패스워드입력" />
							<span class="glyphicon glyphicon-lock form-control-feedback"></span>
							<span id="successFail"></span>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<div class="checkbox icheck">
									<%--<label id="useCookie"> <input type="checkbox" name="useCookie">
										로그인 상태 유지
									</label>--%>
								</div>
								<div class="checkbox">
									<label> <input name="remember-me" type="checkbox">Remember
										Me
									</label>
								</div>
							</div>
							<div class="col-xs-4" style="float: right">
								<button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							   value="${_csrf.token}" />

					</form>
					<a href="/thearc/user/idfind">아이디-비밀번호 찾기</a><br> 
					<a href="/thearc/user/join" class="text-center">회원가입</a>
				</div>
			</div>
		</div>
	</div>
	
	<!--  로그인폼  -->
	<div class="login-form-container">
		<form name="login-form" id="login-form" class="login-form" method="post" action="/thearc/admin/admLogPost">
			<label>관리자 로그인</label>
			<span class="licon user-icon">
				<input type="text" name="uid" id="log" value="" size="20" class="input username" placeholder="관리자아이디" />
			</span>
			<span class="picon pass-icon">
				<input type="password" name="upw" id="login_password" value="" size="20" class="input password"	placeholder="관리자비밀번호" /></span><a class="lost-pass" href="#">관리자 외 접근금지</a>
				<input type="submit" class="button" name="submit" id="submit" value="로그인" tabindex="4" />
				<input type="hidden" name="is_custom_login" value="1" />
		</form>
		<div id="btn-open-login-form" class="btn-open-login-form">
			<i class="foundicon-lock"></i>
		</div>
	</div>
</body>
</html>

	<script>

			$(function(){
				
				$("#uid").on('blur',function(){
					var uid=$(this).val().trim();
					var id=$(this).attr("id");
					
					$.ajax({
						url:'/thearc/user/idcheck2',
						type:'post',
						data:{uid:uid},
						dataType:'text',
						success:function(result){
							console.log("result: "+result);
							// 중복되거나 빈값을 넣었을떄
							if(result=="Empty"){
								successFail(id,"아이디를 입력하세요","red");
							}else if(result=="Success"){
								successFail(id,"해당 아이디는 존재하지 않습니다","red");
							}else if(result="Duplicate"){
								successFail(id,"아이디가 존재합니다.","blue");
							}
						}
					});
				});
				
				
				/** 유효성검사 -html의 각 input 입렵값뒤에 span이 있는데 , 파라미터로 넘겨진 값으로 span을 컨트롤 
				 *  id:해당 input id, message:유효성검사후 띄울메시지, color: true일때 blue, false 일때 red
				*/
			 	function successFail(id,message,color){
			 		
					$("#"+id).next().next().html(message).css("color",color);
					
				} 
				
			});
	</script>
