<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap 3.3.4 -->
    <link href="/thearc/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/thearc/resources/bootstrap/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="/thearc/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
     <script src="/thearc/resources/bootstrap/js/member.js"></script>
	
    	<!-- ///배경관련  -->
		<link rel="stylesheet" href="/thearc/resources/bootstrap/css/vegas.min.css">
		<script src="/thearc/resources/bootstrap/js/jquery-2.1.3.min.js"></script>
		<script src="/thearc/resources/bootstrap/js/vegas.min.js"></script>
    <!-- 아래부터 도로명주소   -->
    <link rel="stylesheet" href="/thearc/resources/bootstrap/css/addrlinkSample.css">
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/thearc/user/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1,addrDetail){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.addrDetail.value = addrDetail;
}

</script>
  </head>
  <body class="login-page">
    <div class="login-box2">
      <div class="login-logo">
        <a href="/thearc/user/login"><b>회원가입</b></a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg"></p>


    <div class="joinForm" >
  
    <form:form id="join" action="/thearc/user/joinPost" method="post" name="form" modelAttribute="uvo">

      <fieldset>
        <legend>기본정보</legend><br>
        
<!--         <input type="text"      name="uid"  id="uid"      size="25"  placeholder="아이디" ><br> -->
        <form:input path="uid" name="uid"  id="uid" size="25"  placeholder="아이디" />
        <form:errors path="uid" class="error" />
        <br>
        <span id="successId" style="color:blue;display:none">멋진 아이디네요!<br/></span>	
        <span id="failId" style="color:red;display:none">이미 사용중인 아이디입니다.<br/></span>	
        <input type="hidden"    name="reid" >
        <!-- <input type="button"    value="중복 체크"   onclick="idcheck()" ><br> -->
         
<!--         <input type="password"  name="upw" size="25" placeholder="비밀번호 " ><br> -->
        <form:input path="upw" name="upw" size="25" placeholder="비밀번호" />
        <form:errors path="upw" class="error" /> <br/>
         
        <input type="password"  name="upwCheck" size="25" placeholder="비밀번호 재확인" ><br>
        
        <span id="successUpw" style="color:blue;display:none;">비밀번호가 일치합니다.<br/></span>
        <span id="failUpw" style="color:red;display:none;">비밀번호가 일치하지 않습니다.<br/></span>
        
        <!-- <input type="text"      name="uname" size="25" placeholder="이름" ><br> -->
        <form:input path="uname" name="uname" size="25" placeholder="이름"  /><br>
        <form:errors path="uname" class="error" />
        
        
        <input type="text"      name="email" size="12"  placeholder="이메일"  >&nbsp;@ <input type="text"      name="email2" size="12">
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
        <br><br><br>					
        
      </fieldset>
      <fieldset>
        <legend>추가정보</legend><br>
        <!-- <label>주소록</label> 						
        <input type="text"       name="zipNum"   size="10" placeholder="--------------">  -->     
        <input type="button"     value="주소록"  onclick="goPopup();"> <br>
        <div id="callBackDiv">
        <!-- <label>주소</label>  -->
        <input type="text"     id="roadAddrPart1"   name="roadAddrPart1"   size="30" placeholder="주소(주소록버튼을 눌러주세요.)"><br>
        <!-- <label>상세주소</label> -->
        <input type="text"     id="addrDetail"   name="addrDetail"   size="30" placeholder="상세주소" > <br>
        </div>
        <!-- <label>휴대전화</label> -->
        <input type="phone" name="phone" class="phone-number-check" size="30" placeholder="휴대전화"> 
   <!--      <select name="phone">
   				<option value="010" selected="selected">010</option>
    			<option value="011">011</option>
    			<option value="016" >016</option>
    			<option value="017" >017</option>
    			<option value="018" >018</option>
    			<option value="019" >019</option>
			</select>
        -&nbsp;<input  type="text"       name="phone2" size="6">&nbsp;-&nbsp;<input  type="text"  name="phone3" size="6"><br><br> -->
      </fieldset>
      <div class="clear"></div>
    
 
			<!--  onclick="go_save()" -->
        <input type="submit"    value="회원가입"   > 
        <input type="reset"      value="취소" style="margin-left:10px"   >
    
          
         
        
   
   
   
    </form:form>
   </article>
  </div>
 <!--  <div class="form-group has-feedback">
    <input type="password" name="upw" class="form-control" placeholder=""/>
    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
  </div> -->
  
  

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
								loop:true,
								mute:true
							}
						}
					]
				});
			});
		</script>
<!-- 	<script>
	$(function(){

		$(".phone-number-check").on('keydown', function(e){
		   // 숫자만 입력받기(하이픈입력시 내부에서는 알아서 변환)
		    var trans_num = $(this).val().replace(/-/gi,'');
		var k = e.keyCode;
		            //타이핑 제한          숫자 + 영어(영어는왜있는지..?) (한글자음,모음)               (스페이스)   (한글 가-힣)
		if(trans_num.length >= 11 && ((k >= 48 && k <=126) || (k >= 12592 && k <= 12687 || k==32 || k==229 || (k>=45032 && k<=55203)) ))
		{   
		      e.preventDefault();//타이핑 더이상 못받게
		}
		}).on('blur', function(){ // 포커스를 잃었을때 실행합니다.
		    if($(this).val() == '') return;

		    // 기존 번호에서 - 를 삭제합니다.
		    var trans_num = $(this).val().replace(/-/gi,'');
		  
		    // 입력값이 있을때만 실행합니다.
		    if(trans_num != null && trans_num != '')
		    {
		        // 총 핸드폰 자리수는 11글자이거나, 10자여야 합니다.
		        if(trans_num.length==11 || trans_num.length==10) 
		        {   
		            // 유효성 체크
		            var regExp_ctn = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
		            if(regExp_ctn.test(trans_num))
		            {
		                // 유효성 체크에 성공하면 하이픈을 넣고 값을 바꿔줍니다.
		                trans_num = trans_num.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");                  
		                $(this).val(trans_num);
		            }
		            else
		            {
		                alert("유효하지 않은 전화번호 입니다.");
		                $(this).val("");
		                $(this).focus();
		            }
		        }
		        else 
		        {
		            alert("유효하지 않은 전화번호 입니다.");
		            $(this).val("");
		            $(this).focus();
		        }
		  }
		});  
		
		$('#uid').on('blur', function(){
			var uid= $(this).val();
			
			$.ajax({
				url:'/thearc/user/idcheck2',
				type:'post',
				data:{uid:uid},
				dataType:'text',
				success:function(result){
					
					console.log("result: "+result);
					if(result =='SUCCESS'){
						$('#failId').css("display","inline");
						$('#successId').css("display","none");
					}else{
						$('#successId').css("display","inline");
						$('#failId').css("display","none");
					}
				}
			})
		});
		
		$('[name=upwCheck]').on('blur',function(){
			console.log("testtesttest");
			var upwCheck = $(this).val();
			var upw = $('[name=upw]').val();
			console.log("upwCheck"+upwCheck);
			console.log("upw"+upw);
			if(upw == upwCheck){
				$('#successUpw').css("display","inline");
				$('#failUpw').css("display","none");
			}else{
				$('#failUpw').css("display","inline");
				$('#successUpw').css("display","none");
			}
		});b
	});
	</script> -->