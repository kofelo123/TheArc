<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@include file="../include/header2.jsp"%>
<%@include file="../include/header.jsp"%>



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
	<div class="row" style="margin-left:15%">
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
					<h3 class="box-title">리스트</h3>
				</div>
				<div class="box-body" >
					<table class="table table-hover table-striped" >
					
						<c:forEach items="${list}" var="boardVO">

							<tr style="font-family:Typo_DecoVariety; font-size:20px">
								<%-- <td>${boardVO.bno}</td> --%>
								<td style="width: 40%;padding-left:20px;"><a
									href='/sboard${sboardNum }/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${boardVO.bno}&uid=${login.uid}' style="color:#337ab7">
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
				<!-- /.box-body -->


				<div class="box-footer">
				<!--  버튼 -->
 				<button id='newBtn' style="background-color:#8ecbff;color:white;outline:0;border:0">새 글 쓰기</button>
					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
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

<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "list"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});

				$('#newBtn').on("click", function(evt) {

					self.location = "register";

				});

			});
</script>