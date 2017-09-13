<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@include file="../include/header2.jsp"%>
<%@include file="../include/header.jsp"%>

<!-- 썸네일게시판용 -->
<script type="text/javascript" src="/resources/bootstrap/js/upload.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>


<style>
@font-face {
    font-family: 'Typo_DecoVariety';
    src: url(/resources/bootstrap/fonts/HoonWhitecatR.ttf) format('truetype');
}
.content{
	
	 background-image: url(/resources/bootstrap/images/bg2.jpg);
}
</style>

<!-- Main content -->
<section class="content">
	<div class="row" style="margin-left:17%">
		<!-- left column -->

		<div class="col-sm-10 col-md-10 col-lg-10">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title" >게시글 검색</h3>
				</div>


				<div class='box-body'>

					<select name="searchType"  style="background-color:#e8faf1">
							
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							제목</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							내용</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							작성자</option>
						<option value="tc"
							<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
							제목+내용</option>
						<option value="cw"
							<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
							내용+작성자</option>
						<option value="tcw"
							<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							제목+내용+작성자</option>
					</select> <input type="text" name='keyword' id="keywordInput"  style="background-color:#e8faf1"
						value='${cri.keyword }'>
					<button id='searchBtn'  style="background-color:#e8faf1">검색</button>
					

				</div>
			</div>
			

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">${category}게시판</h3>
				</div>
				<!--  게시판 리스트 본문 -->
				<!--  썸네일게시판 -->
				<c:choose>
				
					<c:when test="${category eq 'thisweek' }"> 
						<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<c:forEach items="${list}" var="boardVO">
					<tr>
						<div class="blog_medium">
							<article class="post">
								<div class="post_date">
									<span class="day"><fmt:formatDate pattern="dd" value="${boardVO.regdate}" /></span>
									<span class="month"><fmt:formatDate pattern="M" value="${boardVO.regdate}" />월</span>
								</div>
								<figure class="post_img">
									<div class="image">
									<!-- <a href="#">
										<img src="images/blog/blog_medium_1.png" alt="이미지없음">
									</a> -->
									</div>
								</figure>
								<div class="post_content">
									<div class="post_meta">
										<h2>
											<a href="#">${boardVO.title}</a>
										</h2>
										<div class="metaInfo">
											<span><i class="fa fa-user"></i> By <a href="#">${boardVO.writer}</a> </span>
											<span><i class="fa fa-comments"></i> <a href="#">${boardVO.replycnt}</a></span>
										</div>
									</div>
									<p>${boardVO.content }</p>
									<a class="btn btn-small btn-default" href="#">자세히 보기</a>
								</div>
							</article>
													
						<!-- 	<article class="post no_images">
								<div class="post_date">
									<span class="day">28</span>
									<span class="month">Nov</span>
								</div>
								<div class="post_content">
									<div class="post_meta">
										<h2>
											<a href="#">perferendis dolor asperio</a>
										</h2>
										<div class="metaInfo">
											<span><i class="fa fa-user"></i> By <a href="#">John Doe</a> </span>
											<span><i class="fa fa-comments"></i> <a href="#">12 Comments</a></span>
										</div>
									</div>
									<p>Lorem ipsum dolor sit amet, consectetur adip, sed do eiusmod tempor incididunt  ut aut reiciendise voluptat maiores alias consequaturs aut perferendis doloribus asperiores ut labore.</p>
									<a class="btn btn-small btn-default" href="#">Read More</a>
								</div>
							</article> -->
							
						</div>
					</tr>
					</c:forEach>
					</div>
				</div><!-- /row끝, 썸네일게시판  -->
					</c:when>
				
				<c:otherwise>
 								
				<div class="box-body" >
					<table class="table table-hover table-striped" >
					
						<c:forEach items="${list}" var="boardVO">

							<tr style="font-family:Typo_DecoVariety; font-size:20px">
								<%-- <td>${boardVO.bno}</td> --%>
								<td style="width: 40%;padding-left:20px;"><a
									href='/sboard/readPage/${category}${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${boardVO.bno}&uid=${login.uid}' style="color:#337ab7">
										${boardVO.title} <%-- <strong>[${boardVO.replycnt}  ]</strong> --%>
								</a></td>
								<td style="width:7%">
									<i class="fa fa-comment-o"  title="댓글">&nbsp;&nbsp;&nbsp;&nbsp;${boardVO.replycnt}</i>
								</td>
								<td style="width:7%">								
									<i class="fa fa-thumbs-o-up" title="좋아요" >&nbsp;&nbsp;&nbsp;&nbsp;${boardVO.countlike }</i>
								</td>
								<td style="width:25%">
								<i class="glyphicon glyphicon-eye-open" title="조회수">&nbsp;${boardVO.viewcnt  }</i>
								</td>
								<td class="mailbox-date" style="width:10%"><fmt:formatDate pattern="MM-dd HH:mm"
										value="${boardVO.regdate}" /></td>
								<%-- <td><span class="badge bg-aqua">${boardVO.viewcnt }</span></td> --%>
								<td class="mailbox-subject">${boardVO.writer} </td>
							</tr>

						</c:forEach>

					</table>
				</div>
				</c:otherwise>
				</c:choose>
				<!-- 게시판리스트 본문 end  -->
				<!-- /.box-body -->


				<div class="box-footer">
				<!--  버튼 -->
 				<button id='newBtn' style="background-color:#8ecbff;color:white;outline:0;border:0">새 글 쓰기</button>
					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
							</c:if>

						</ul>
					</div>

				</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<%@include file="../include/footer.jsp"%>
<%@include file="../include/footer2.jsp"%>



<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
		location.replace(self.location);
	}
</script>

<script id="templateAttach" type="text/x-handlebars-template">
	<img src="{{imgsrc}}" alt="Attachment"></span>
</script>  

<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "${category}"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});

				$('#newBtn').on("click", function(evt) {
					
					self.location = "/sboard/register/${category}";

				});
				
				var bno = 3216;
				var template = Handlebars.compile($("#templateAttach").html());
				
				$.getJSON("/sboard/getAttachOne/"+bno,function(list){ ///searchboardcontroller에 파라미터 있음.
					$(list).each(function(){
						
						var fileInfo = getFileInfo(this);
						
						var html = template(fileInfo);
						
						 $(".image").append(html);
						
					});
				});
				
				

			});
</script>