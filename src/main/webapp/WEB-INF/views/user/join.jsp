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
    <link href="/thearc/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/thearc/resources/bootstrap/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	
    	<!-- 배경관련  -->
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
      </div>
      <div class="login-box-body">
        <p class="login-box-msg"></p>

    <div class="joinForm" >
  
    <form:form id="join" action="/thearc/user/joinPost" method="post" name="form" modelAttribute="uvo">

      <fieldset>
        <legend>기본정보</legend><br>
        
        <form:input path="uid" name="uid"  id="uid" size="25"  placeholder="아이디" />
        <form:errors path="uid" class="error" />
        <br/>
        <span id="successFail" ></span>
        
        <form:input path="upw" type="password" id="upw" name="upw" size="25" placeholder="비밀번호" />
        <form:errors path="upw" class="error" /> <br/>
         <span id="successFail" ></span>
        
        <input type="password"  id="upwCheck" name="upwCheck" size="25" placeholder="비밀번호 재확인" ><br>
        <span id="successFail" ></span>
        
         
        <form:input path="uname" name="uname" size="25" placeholder="이름"  /><br>
        <form:errors path="uname" class="error" />
 		<span id="successFail" ></span>
 		
        <input type="text" id="email" name="email" size="12"  placeholder="이메일"  >&nbsp;@ <input type="text" name="email2" id="email2" size="12">
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
		<br>
 		<span id="successFail" ></span>
        <br><br>					
        
      </fieldset>
      <fieldset>
        <legend>추가정보</legend><br>
        <input type="button"     value="주소록"  onclick="goPopup();"> <br>
        <div id="callBackDiv">
        <input type="text"     id="roadAddrPart1"   name="roadAddrPart1"   size="30" placeholder="주소(주소록버튼을 눌러주세요.)"><br>
        <input type="text"     id="addrDetail"   name="addrDetail"   size="30" placeholder="상세주소" > <br>
        </div>
        <input type="phone" name="phone" class="phone-number-check" size="30" placeholder="휴대전화"> 
      </fieldset>
 
		<!--  onclick="go_save()" -->
        <input type="submit"    value="회원가입"   > 
        <input type="reset"      value="리셋" style="margin-left:10px"   >
    </form:form>
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
		
	<script>
	
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
		
		//아이디 중복체크
		$('#uid').on('blur', function(){
			var uid= $(this).val();
			var id = $(this).attr("id");

			$.ajax({
				url:'/thearc/user/idcheck2',
				type:'post',
				data:{uid:uid},
				dataType:'text',
				success:function(result){
					console.log("result: "+result);
					// 중복 되거나 빈값을 넣었을떄 
					if(result =='Duplicate'|| result == "Empty"){

						if(result =="Empty"){
							console.log(id);
							successFail(id,"아이디를 입력하세요","red");
						}else if(result=="Duplicate")
							successFail(id,"중복된 아이디입니다.","red");
						}else{
							successFail(id,"사용가능한 아이디입니다.","blue");
					}
				}
			})
		});
		
        /* 비밀번호값 입력여부 유효성검사 */
		$("#upw").on('blur',function(){
			var upw = $(this).val();
			console.log(upw);
			//비어있으면 에러처리
			if(jQuery.isEmptyObject(upw)){
				successFail($(this).attr("id"),"비밀번호를 입력해주세요","red"); 
			}else if($("#upw ~ #successFail:first").text()=="비밀번호를 입력해주세요"){
        	 	$("#upw ~ #successFail:first").text("");
			} 
		});
		
		/** 유효성검사 -html의 각 input 입렵값뒤에 span이 있는데 , 파라미터로 넘겨진 값으로 span을 컨트롤 
		 *  id:해당 input id, message:유효성검사후 띄울메시지, color: true일때 blue, false 일때 red
		*/
	 	function successFail(id,message,color){
	 		
			$("#"+id).next().next().html(message+"<br/>").css("color",color);
			
		} 
		
		$('#upwCheck').on('blur',function(){
			
			var upwCheck = $(this).val();
			var upw = $('#upw').val();
			if(upw == upwCheck && upw != ""){
				successFail($(this).attr("id"),"비밀번호가 일치합니다","blue");
			}else if(upw != upwCheck){
				successFail($(this).attr("id"),"비밀번호가 일치하지 않습니다","red");
			}else if(upw == upwCheck && upw == ""){
				successFail("upw","비밀번호를 입력해주세요","red");
			}
		}); 
		
		$("#uname").on('blur',function(){
			var uname = $(this).val();
			
			if(uname == "")
				successFail($(this).attr("id"),"이름을 입력해주세요","red");
			else if($("#uname ~ #successFail:first").text()=="이름을 입력해주세요")
				$("#uname ~ #successFail:first").text("");
		});
		
		$("#email, #email2").on('blur',function(){
			var email = $("#email").val();
			var email2 = $("#email2").val();
			
			if(email == "" || email2 == "")
				$("#email ~ #successFail:first").text("이메일을 입력해주세요").css("color","red");
			else if($("#email ~ #successFail:first").text()=="이메일을 입력해주세요")
				$("#email ~#successFail:first").text("");
		});
	}); 
	</script>