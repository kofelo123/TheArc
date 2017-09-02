function go_save() {
  if (document.formm.uid.value == "") {
    alert("아이디를 입력하여 주세요.");
    document.formm.uid.focus();
  } else if (document.formm.uid.value != document.formm.reid.value) {
    alert("중복확인을 클릭하여 주세요.");//아이디 중복확인 실시 여부 확인 위해서 input type hidden 하나따로뒀다,
    document.formm.uid.focus();
  } else if (document.formm.upw.value == "") {
    alert("비밀번호를 입력해 주세요.");
    document.formm.upw.focus();
  } else if ((document.formm.upw.value != document.formm.upwCheck.value)) {
    alert("비밀번호가 일치하지 않습니다.");
    document.formm.upw.focus();
  } else if (document.formm.uname.value == "") {
    alert("이름을 입력해 주세요.");
    document.formm.uname.focus();
  } else if (document.formm.email.value == "") {
    alert("이메일을 입력해 주세요.");
    document.formm.email.focus();
  } else {
    document.formm.action = "/user/joinPost";
    document.formm.submit();
  }
}

function idcheck() {
  if (document.formm.uid.value == "") {
    alert('아이디를 입력하여 주십시오.');
    document.formm.uid.focus();
    return;
  }
 var url = "/user/idcheck?uid=" 
+ document.formm.uid.value;//? 로 바꾸니 controller requestmapping이 인식됨-> 동시에 파라미터가 잘전달됨.
 /* var url = "/user/idcheck&uid="   ///뒤늦게 보니 웃기다. 당연한건데..
+ document.formm.uid.value;*/
  window.open( url, "_blank_1",
"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=400, height=250, left=1100, top=340");
  
 
}/**/

function post_zip() {
  var url = "/user/findZipNum";
  window.open( url, "_blank_1",
"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=400, top=400, left=1100 ");
}

/*function go_next() {
  if (document.formm.okon1[0].checked == true) {
    document.formm.action = "NonageServlet?command=join_form";
    document.formm.submit();
  } else if (document.formm.okon1[1].checked == true) {
    alert('약관에 동의하셔야만 합니다.');
  
}*/

function mailcheck(){
	
	if(document.formm.company.value!="직접입력"){
		document.formm.email2.value=document.formm.company.value;
	}else if(document.formm.company.value=="직접입력"){
		document.formm.email2.value="";
	}
	
	
}