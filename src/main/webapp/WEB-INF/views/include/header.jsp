<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
	<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>The Ark</title>
    <meta name="description" content="">
 
    <!-- CSS FILES -->
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/bootstrap/css/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/style.css" media="screen" data-name="skins">
</head>

  
<body class="home">

<!--Start Header-->
<div id="top-fix" class="clearfix">
    <header id="header" class="container">
        <div class="row">
            <!-- Logo / Mobile Menu -->
            <div id="logo-bar" class="col-sm-12 ">
                <!-- <div id="logo" > -->
                <div class="col-sm-9 col-xs-12 ">
                	<div class="mainlogo" style="margin-left:30%">
                    	<h1><a href="/sboard/main"><img src="/resources/bootstrap/images/mainlogo.png" alt="theark" /></a></h1>
                    </div>
                </div>
                     <!--  </div> -->
                    <!-- <div class="col-lg-2 col-md-2 col-xs-12"> -->
                    <div class="col-sm-1 " style="margin-right:-20px;margin-top:10px">
                      <button type="button" id="loginbutton" class="btn btn-success btn-block" >로그인</button>
                     </div>
              		<div class="col-sm-1 " style="margin-top:10px">
                      <button type="button" id="joinbutton" class="btn btn-success btn-block">회원가입</button>
                     </div>
              
            </div>
        </div>
        <div class="row">
            <!-- Navigation
            ================================================== -->
            <div class="navbar navbar-default navbar-static-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse ">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.html">디아크소개</a>
                            <ul class="dropdown-menu">
                                <li><a href="index_2.html">디아크란</a></li>
                                <li><a href="index_3.html">관람안내</a></li>
                                <li><a href="index_4.html">디아크풍경</a></li>
                                <li><a href="/sboard/location">오시는길</a></li>
                            </ul>
                        </li>

                        <li><a href="#">관광코스</a>
                            <ul class="dropdown-menu">
                                <li><a href="/sboard/list/thisweek">금주의 디아크</a></li>
                                <li><a href="columns.html">야자수 테라리움</a></li>
                                <li><a href="typography.html">수상레저체험</a></li>
                                <li><a href="pricing-tables.html">바닷속이야기</a></li>
                                <li><a href="icons.html">감성아카데미</a></li>
                            </ul>
                        </li>

                        <li><a href="#">이벤트</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="about.html">임시</a>
                                    <ul class="dropdown-menu">
                                        <li><a href="about.html">임시</a></li>
                                        <li><a href="about-2.html">임시</a></li>
                                    </ul>
                                </li>
                                <li><a href="services.html">임시</a></li>
                                <li><a href="faq.html">임시</a></li>
                                <li><a href="sidebar-right.html">임시</a></li>
                                <li><a href="sidebar-left.html">임시</a></li>
                                <li><a href="404-page.html">임시</a></li>
                            </ul>
                        </li>

                        <li><a href="#">커뮤니티</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="/sboard/list/free">자유게시판</a>
                                </li>
                                <li><a href="/sboard/faq">자주하는질문</a></li>
                                <li><a href="/sboard/list/qna">묻고답하기</a></li>
                                <li><a href="/sboard/list/news">언론보도</a></li>
                                <li><a href="/sboard/list/photo">포토존</a></li>
                                <li><a href="/sboard/list/visit">방문후기</a></li>
                            </ul>
                        </li>

                        <li><a href="#">고객센터</a>
                            <ul class="dropdown-menu">
                                <li><a href="/sboard/list/notice">공지사항</a></li>
                                <li><a href="/sboard/list/supporter">서포터즈</a></li>
                                <li><a href="blog-post.html">임시</a></li>
                            </ul>
                        </li>

                        <li><a href="contact.html">사이트맵</a></li>
                         
                     

                       
                    </ul>
                </div>
            </div>
        </div>
        <!-- Container / End -->
    </header>
    
    <%@ include file="analytics.jsp"%>	