<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
	<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <title>The Arc</title>
    <meta name="description" content="">
 
    <!-- CSS FILES -->
    <link rel="stylesheet" href="/thearc/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/thearc/resources/bootstrap/css/style.css">
    <link rel="stylesheet" type="text/css" href="/thearc/resources/bootstrap/css/style.css" media="screen" data-name="skins">
    
 	<meta property="og:title"              content="${boardVO.title }" />
	<%-- <meta property="og:description"        content="${boardVO.content }" />  이부분때문에 summernote 에러난다.--%>
	<meta property="og:image"              content="https://encrypt	ed-tbn0.gstatic.com/images?q=tbn:ANd9GcSnFa8hrulJs73ktd-t_ST_Bgjqq9Hu3LIWUyEx0F2vQGmtX7ou" />


</head>

  
<body class="home">

<!--Start Header-->
<div id="top-fix" class="clearfix">
    <header id="header" class="container">
        <div class="row">
            <!-- Logo / Mobile Menu -->
            <div id="logo-bar">
                <!-- <div id="logo" > -->
                <div class="col-lg-5 col-lg-offset-3 col-md-6 col-md-offset-1 col-sm-6 ">
                	<div class="mainlogo" >
                    	<h1><a href="/thearc/sboard/main"><img src="/thearc/resources/bootstrap/images/mainlogo.png" alt="theark" /></a></h1>
                    </div>
                </div>


                       <%-- 개발시 로그인 편하게 하기위해--%>
                <sec:authorize access="isAnonymous()">
                    <c:if test="${pageContext.request.getServerName() eq 'localhost'}">
                        <script type="text/javascript" src="/thearc/resources/bootstrap/js/jquery-1.10.2.min.js"></script>
                        <script></script>
                        <button id="devlogin" style="width:40px;height:30px; margin-top:10px;"></button>
                        <script>
                            $(function(){
                                $("#devlogin").click(function(){

                                    var form = $('<form></form>');

                                    form.attr({action:"/thearc/login" , method:"post"});
                                    form.appendTo('body');

                                    $("<input></input>").attr({type:"hidden",name:"username",value:"admin2"}).appendTo(form);
                                    $("<input></input>").attr({type:"hidden",name:"password",value:"admin2"}).appendTo(form);
                                    $("<input></input>").attr({type:"hidden",name:"${_csrf.parameterName}",value: "${_csrf.token}"}).appendTo(form);
                                    form.submit();

                                });
                            })

                        </script>
                    </c:if>
                </sec:authorize>


                <sec:authentication property="principal" var="pinfo"/>

                    <sec:authorize access="isAuthenticated()">

                        <div class="col-lg-2 col-lg-offset-0 col-md-2 col-md-offset-1 col-sm-2 col-sm-offset-2">

                            <sec:authorize access="hasRole('ADMIN')">
                                <img src="/thearc/resources/bootstrap/img/king.jpg"  style="width:25px;height:25px;float:left;margin-top:9px;margin-left:50%;"/><h3 style="line-height:0px">&nbsp;&nbsp;${pinfo.member.uid}</h3><h4>&nbsp;&nbsp;(관리자)</h4>
                            </sec:authorize>

                            <sec:authorize access="hasRole('SUPPORTER')">
                                <img src="/thearc/resources/bootstrap/img/supporter.png" style="width:25px;height:25px;float:left;margin-top:9px;margin-left:50%"/><h3 style="line-height:0px;">&nbsp;&nbsp;${pinfo.member.uid}</h3><h4>&nbsp;&nbsp;(서포터즈)</h4>
                            </sec:authorize>

                            <sec:authorize access="hasRole('MEMBER')">
                                <img src="/thearc/resources/bootstrap/img/user.png" style="width:25px;height:25px;float:left;margin-top:9px;margin-left:50%"/><h3 style="line-height:0px">&nbsp;&nbsp;${pinfo.member.uid}</h3><h4>&nbsp;&nbsp;(일반회원)</h4>
                            </sec:authorize>

                        </div>


                        <!--  아래 버튼들의 id를 이용해서 js컨틀로하는부분은 footer에 있다 -->
                        <div class="col-sm-1">
                            <button type="button" id="message" onClick="window.open('/thearc/sboard/message/listMessage?uid=${pinfo.member.uid }', '', 'width=475, height=490,left=1000, top=100'); return false;" class="btn btn-success btn-blok" style="margin-top:10px;margin-left:-15px;">메세지함</button>
                        </div>
                        <div class="col-sm-1">
                            <button type="button" id="logout" class="btn btn-success btn-blok" style="margin-top:10px;margin-left:-60%;">로그아웃</button>
                        <sec:authorize access="hasRole('ADMIN')">

                            <a href="#"><img src="/thearc/resources/bootstrap/image/lock3.png" style="width:40px; height:40px; margin-top:10px; float:right" onclick="adminLogin()"></a>

                        </sec:authorize>
                        </div>

                    </sec:authorize>






              <%--      <c:if test="${not empty login }">
                  <div class="col-lg-1 col-lg-offset-0 col-md-2 col-md-offset-1 col-sm-2 col-sm-offset-2">
                    	<c:choose>
                    		<c:when test="${login.authority=='user' }">
                    	<img src="/thearc/resources/bootstrap/img/user.png" style="width:25px;height:25px;float:left;margin-top:9px;margin-right:5px;"/><h3 style="line-height:0px">&nbsp;&nbsp;${login.uid}</h3><h4>&nbsp;&nbsp;(일반회원)</h4>
                    		</c:when>
                    		<c:when test="${login.authority=='supporter' }">
                    	<img src="/thearc/resources/bootstrap/img/supporter.png" style="width:25px;height:25px;float:left;margin-top:9px;margin-right:5px;"/><h3 style="line-height:0px">&nbsp;&nbsp;${login.uid}</h3><h4>&nbsp;&nbsp;(서포터즈)</h4>
                    		</c:when>
                    		<c:when test="${login.authority=='admin' }">
                    	<img src="/thearc/resources/bootstrap/img/king.jpg"  style="width:25px;height:25px;float:left;margin-top:9px;margin-right:5px;"/><h3 style="line-height:0px">&nbsp;&nbsp;${login.uid}</h3><h4>&nbsp;&nbsp;(관리자)</h4>
                    		</c:when>
                    	</c:choose>
                    </div>
                    <div class="col-sm-1">
                    	<button type="button" id="message" onClick="window.open('/thearc/sboard/message/listMessage?uid=${login.uid }', '', 'width=475, height=490,left=1000, top=100'); return false;" class="btn btn-success btn-blok" style="margin-top:10px;margin-left:-15px;">메세지함</button>
                    </div>
                    <div class="col-sm-1">
                    	<button type="button" id="logout" class="btn btn-success btn-blok" style="margin-top:10px;margin-left:-60%;">로그아웃</button>
                    </div>
                    </c:if>--%>
                <sec:authorize access="isAnonymous()">
                    <div class="col-lg-1"></div>

                    <div class="col-md-2 col-md-offset-1 col-lg-1 col-lg-offset-0 col-sm-2 col-sm-offset-2" style="margin-top:10px">

                        <!--  아래 버튼들의 id를 이용해서 js컨틀로하는부분은 footer에 있다 -->
                        <button type="button" id="loginbutton" class="btn btn-success btn-block" >로그인</button>
                    </div>
                    <div class="col-md-2 col-lg-1 col-sm-2" style=" margin-top:10px">
                        <button type="button" id="joinbutton" class="btn btn-success btn-block">회원가입</button>
                    </div>
                </sec:authorize>
<%--
                <c:if test="${empty login }">
                    <div class="col-md-2 col-md-offset-1 col-lg-1 col-lg-offset-0 col-sm-2 col-sm-offset-2" style="/* margin-right:% ;*/margin-top:10px">

                        <!--  아래 버튼들의 id를 이용해서 js컨틀로하는부분은 footer에 있다 -->
                        <button type="button" id="loginbutton" class="btn btn-success btn-block" >로그인</button>
                    </div>
                    <div class="col-md-2 col-lg-1 col-sm-2" style="margin-top:10px">
                        <button type="button" id="joinbutton" class="btn btn-success btn-block">회원가입</button>
                    </div>
                </c:if>--%>
              
            </div>
        </div>
        <div class="row">
            <!-- Navigation
            ================================================== -->
            <div class="navbar navbar-default navbar-static-top" role="navigation">
                <div class="navbar-header">
                	<div class="navbar-responsive">
                	<a href="/thearc/sboard/main">
                		<img src="/thearc/resources/bootstrap/images/thearc.PNG" style="width:100px;height:40px;margin-left:35%;margin-top:10px;" />
                	</a>
                	</div>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    
                </div>
                <div class="navbar-collapse collapse ">
                    <ul class="nav navbar-nav">
                        <li><a href="#">디아크소개</a>
                            <ul class="dropdown-menu">
                                <li><a href="/thearc/sboard/thearc">디아크란</a></li>
                                <li><a href="/thearc/sboard/exhibit">관람안내</a></li>
                               <!--  <li><a href="/thearcindex_4.html">디아크풍경</a></li> -->
                                <li><a href="/thearc/sboard/location">오시는길</a></li>
                            </ul>
                        </li>

                        <li><a href="#">관광코스</a>
                            <ul class="dropdown-menu">
                                <li><a href="/thearc/sboard/list/terarium">야자수 테라리움</a></li>
                                <li><a href="/thearc/sboard/list/leisure">수상레저체험</a></li>
                                <li><a href="/thearc/sboard/list/seastory">바닷속이야기</a></li>
                                <li><a href="/thearc/sboard/list/academy">감성아카데미</a></li>
                                <li><a href="/thearc/sboard/list/thisweek">금주의 디아크</a></li>
                            </ul>
                        </li>

                        <li><a href="#">이벤트</a>
                            <ul class="dropdown-menu">
                                <li><a href="/thearc/sboard/list/race">포토레이싱대회</a></li>
                                <li><a href="/thearc/sboard/list/mythearc">내가 담은 디아크</a></li>
                                <li><a href="/thearc/sboard/list/musicvideo">뮤직비디오</a></li>
                            </ul>
                        </li>

                        <li><a href="#">커뮤니티</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="/thearc/sboard/list/free">자유게시판</a>
                                </li>
                                <li><a href="/thearc/sboard/list/news">언론보도</a></li>
                                <li><a href="/thearc/sboard/list/photo">포토존</a></li>
                                <li><a href="/thearc/sboard/list/visit">방문후기</a></li>
                            </ul>
                        </li>

                        <li><a href="#">고객센터</a>
                            <ul class="dropdown-menu">
                                <li><a href="/thearc/sboard/list/notice">공지사항</a></li>
                                 <li><a href="/thearc/sboard/faq">자주하는질문</a></li>
                                <li><a href="/thearc/sboard/list/qna">묻고답하기</a></li>
                                <li><a href="/thearc/sboard/list/supporter">서포터즈</a></li>
                            </ul>
                        </li>

                        <li><a href="#">기타</a>
                         <ul class="dropdown-menu">
                                <li><a href="#" onClick="window.open('/thearc/music', '', 'width=680, height=420,left=830, top=330'); return false;"><i class="glyphicon glyphicon-music" style="color:#25c1ef"></i> <span>뮤직플레이어</span></a></li>
                                 <li><a href="https://github.com/kofelo123/TheArc" target="_blank"><i class="fa fa-github "></i> <span>깃허브</span></a></li>
                            </ul>
                    	 </li>
                       
                    </ul>
                </div>
            </div>
        </div>
        <!-- Container / End -->
    </header>
    
    <%@ include file="analytics.jsp"%>








<%--

<c:if test="${empty login }">
    <div class="col-md-2 col-md-offset-1 col-lg-1 col-lg-offset-0 col-sm-2 col-sm-offset-2" style="/* margin-right:% ;*/margin-top:10px">

        <!--  아래 버튼들의 id를 이용해서 js컨틀로하는부분은 footer에 있다 -->
        <button type="button" id="loginbutton" class="btn btn-success btn-block" >로그인</button>
    </div>
    <div class="col-md-2 col-lg-1 col-sm-2" style="margin-top:10px">
        <button type="button" id="joinbutton" class="btn btn-success btn-block">회원가입</button>
    </div>
</c:if>

<c:if test="${not empty login }">
    <div class="col-lg-1 col-lg-offset-0 col-md-2 col-md-offset-1 col-sm-2 col-sm-offset-2">
        <c:choose>
            <c:when test="${login.authority=='user' }">
                <img src="/thearc/resources/bootstrap/img/user.png" style="width:25px;height:25px;float:left;margin-top:9px;margin-right:5px;"/><h3 style="line-height:0px">&nbsp;&nbsp;${login.uid}</h3><h4>&nbsp;&nbsp;(일반회원)</h4>
            </c:when>
            <c:when test="${login.authority=='supporter' }">
                <img src="/thearc/resources/bootstrap/img/supporter.png" style="width:25px;height:25px;float:left;margin-top:9px;margin-right:5px;"/><h3 style="line-height:0px">&nbsp;&nbsp;${login.uid}</h3><h4>&nbsp;&nbsp;(서포터즈)</h4>
            </c:when>
            <c:when test="${login.authority=='admin' }">
                <img src="/thearc/resources/bootstrap/img/king.jpg"  style="width:25px;height:25px;float:left;margin-top:9px;margin-right:5px;"/><h3 style="line-height:0px">&nbsp;&nbsp;${login.uid}</h3><h4>&nbsp;&nbsp;(관리자)</h4>
            </c:when>
        </c:choose>
    </div>
    <div class="col-sm-1">
        <button type="button" id="message" onClick="window.open('/thearc/sboard/message/listMessage?uid=${login.uid }', '', 'width=475, height=490,left=1000, top=100'); return false;" class="btn btn-success btn-blok" style="margin-top:10px;margin-left:-15px;">메세지함</button>
    </div>
    <div class="col-sm-1">
        <button type="button" id="logout" class="btn btn-success btn-blok" style="margin-top:10px;margin-left:-60%;">로그아웃</button>
    </div>
</c:if>--%>
<script>
    function adminLogin(){
        $(location).attr("href","/thearc/admin/userlist");
    }
</script>