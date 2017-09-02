<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<link href="CSS/subpage.css" rel="stylesheet">
<style type="text/css">
body{   
  background-color:#44576f;
  font-family: Verdana;
}
#wrap{     
  margin: 0 20px;
}
h1 {
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  color: #CCC;
  font-weight: normal;
}
input[type=button], input[type=submit] {
  float: right;
}
</style>
<script type="text/javascript">
function idok(){ ///부모페이지로 값전달
  opener.formm.uid.value="${user.uid}"; 
  opener.formm.reid.value="${user.uid}";
  self.close();
}
</script>
</head>
<body>
<div id="wrap">
  <h1>ID 중복확인</h1>
  <form method=get name=formm style="margin-right:0"  > <!-- 그냥 get함수로 바꿧다 post굳이 안하고(파라미터 관련)   -->
	
    아이디  <input type=text name="uid" value=""> 
            <input type=submit value="검색" class="submit"><br>     
    <div style="margin-top: 20px">   
      <c:if test="${answer == -1}">
        <script type="text/javascript">
          opener.document.formm.uid.value="";
        </script>
        ${user.uid}는 이미 사용중인 아이디입니다.
      </c:if>
      <c:if test="${answer == 1}">
        ${user.uid}는 사용 가능한 ID입니다.
        <input type="button" value="사용" class="cancel" onclick="idok()">
      </c:if>
    </div>
  </form>
</div>  
</body>
</html>