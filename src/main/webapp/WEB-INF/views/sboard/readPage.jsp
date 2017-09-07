<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   

<%@include file="../include/header.jsp"%>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
    
    
    
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>


<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
<!-- Main content -->
    <style type="text/css">
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
     
     
  	
    </style>
       

    <div class='popup back' style="display:none;"></div>
    <div id="popup_front" class='popup front' style="display:none;">
     <img id="popup_img">
    </div>
     

<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				
				<!-- /.box-header -->

				<form role="form" action="modifyPage" method="post">

					<input type='hidden' name='bno' value="${boardVO.bno}"> <input
						type='hidden' name='page' value="${cri.page}"> <input
						type='hidden' name='perPageNum' value="${cri.perPageNum}">
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">

				</form>

				<div class="box-body">
				<div class="mailbox-read-info">
				 <div class="user-panel">  <!-- user패널좀 수정해야할듯  -->
            <div class="pull-left image">
              <img src="/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
            </div>
            <h4>&nbsp;&nbsp;${boardVO.writer}</h4>
         &nbsp;&nbsp;&nbsp;<fmt:formatDate pattern="MM-dd HH:mm" value="${boardVO.regdate}" />
          </div>
						
						
					</div>
					<div class="mailbox-read-info">
					
						<h2 style="float:left;">${boardVO.title }</h2>
						<div class="optionbox">
						
					
						<!-- <div class="fbshare">
						<a href="#" onClick="window.open('http://www.facebook.com/sharer/sharer.php?u=http://www.smartitcording.com/sboard/readPage?bno=201&uid=kofelo123', '', 'width=475, height=310,left=1000, top=100'); return false;"><i class="fa fa-facebook-official" title="페이스북 공유" ></i></a>
						</div> -->
					
						<div class="fbshare" >
						<a href="#" onClick="window.open('http://www.facebook.com/dialog/share?app_id=966242223397117&display=popup&href=http%3A%2F%2Fwww.smartitcording.com%2Fsboard${sboardNum }%2FreadPage%3Fbno%3D${boardVO.bno}%26uid%3D', '', 'width=475, height=310,left=1000, top=100'); return false;"><i class="fa fa-facebook-official" data-toggle="tooltip" data-placement="left" title="페이스북 공유"></i></a>
						<!-- <a href="http://www.facebook.com/dialog/share?app_id=966242223397117&display=popup&href=http%3A%2F%2Fwww.smartitcording.com%2Fsboard%2FreadPage%3Fbno%3D201%26uid%3Dkofelo123" class="btn-facebook-share"><i class="fa fa-facebook-square fa-fw" data-toggle="tooltip" data-placement="left" title="페이스북 공유"></i></a> -->
                         </div><!--www.facebook.com/dialog/share?app_id=966242223397117&display=popup&href=http%3A%2F%2Fwww.smartitcording.com%2Fsboard%2FreadPage%3Fbno%3D201%26uid%3Dkofelo123  -->
					
						<!-- 	<a href="http://www.facebook.com/sharer/sharer.php?u=http://www.smartitcording.com/sboard/readPage?bno=201&uid=kofelo123" class="btn-facebook-share"><i class="fa fa-facebook-square fa-fw" data-toggle="tooltip" data-placement="left" title="페이스북 공유"></i></a>  -->
						<div class="twitter">
						<a href="#" onClick="window.open('https://twitter.com/intent/tweet?text=스마트IT 코딩단 - ${boardVO.title }&url=http%3A%2F%2Fwww.smartitcording.com%2Fsboard%2FreadPage%3Fbno%3D${boardVO.bno}%26uid%3D', '', 'width=475, height=310,left=1000, top=100'); return false;"><i class="fa fa-twitter-square" data-toggle="tooltip" data-placement="left" title="트위터 공유"></i></a>
						
                         </div>
                         
                        <%--  <div class="bookmark" >
                         <a href="/sboard${sboardNum }/readPage/like?bno=${boardVO.bno}&uid=${login.uid}" style="color:#3c763d"><i class="fa fa-bookmark-o"  data-toggle="tooltip" data-placement="left" title="스크랩"><div id="countvote" class="content-count">${boardVO.countlike }</div></i></a>
                         
                         </div> --%>
                         
						
							<c:if test="${likeVO.likecheck=='n' }">
						<div class="iconlike">
						<a href="/sboard${sboardNum }/readPage/like?bno=${boardVO.bno}&uid=${login.uid}" style="color:gray"><i class="fa fa-thumbs-o-up" data-toggle="tooltip" data-placement="left" title="좋아요"><div id="countvote" class="content-count">${boardVO.countlike }</div></i></a>
						</div>
						</c:if>
						
						<c:if test="${likeVO.likecheck=='y' }">
						<div class="iconlike">
						<a href="/sboard${sboardNum }/readPage/dislike?bno=${boardVO.bno}&uid=${login.uid}" style="color:#64a3f3"><i class="fa fa-thumbs-up" data-toggle="tooltip" data-placement="left" title="좋아요 취소"><div id="countvote" class="content-count">${boardVO.countlike }</div></i></a>
						</div>
							</c:if>
							
						</div>
					</div>
					<%-- <div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content}</textarea>
					</div>
					 --%>
					 <div class="mailbox-read-message">
               	${boardVO.content}
              
                
              </div>
					<%-- <textarea div id="summernote" name="content" readonly="readonly">${boardVO.content }</textarea></div>
  <script>
  $(document).ready(function(){
	  $('#summernote').summernote({
		  height: 'auto',                 
		  minHeight: null,             
		  maxHeight: null,             
		  focus: false,
		  
		  toolbar: [
		 		   
		  		  ],
		  
			
		});
	  $('#summernote').summernote('foreColor', 'white');	 
	  $('#summernote').summernote('disable');
	  
	 
	
  });
  </script> --%>
					
				</div>
				<!-- /.box-body -->
				
  <div class="box-footer">
    
    <div><hr></div>

    <ul class="mailbox-attachments clearfix uploadedList">
    </ul>
 <c:if test="${login.uid == boardVO.writer}">
    <button type="submit" class="btn btn-warning" id="modifyBtn">수정</button>
    <button type="submit" class="btn btn-danger" id="removeBtn">삭제</button>
 </c:if>
    <button type="submit" class="btn btn-primary" id="goListBtn">목록</button>
  </div>

			</div>
			<!-- /.box -->
		
		<!--/.col (left) -->


	<!-- /.row -->




		


<div class="box box-success">
  <div class="box-header">
    <h3 class="box-title">새 댓글달기</h3>
  </div>


  <c:if test="${not empty login}">  
  <div class="box-body">
  <div class="user-panel">  <!-- user패널좀 수정해야할듯  -->
            <div class="pull-left image">
              <img src="/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
            </div>
            <h4>&nbsp;&nbsp;${boardVO.writer}</h4>
      
          </div>
          <input type="hidden" id="newReplyWriter" value="${login.uid }">
     <label for="exampleInputEmail1">내용입력</label> 
    <input class="form-control"  placeholder="내용을 입력하세요" id="newReplyText">
    </div>
  
		<div class="box-footer">
		  <button type="submit" class="btn btn-primary" id="replyAddBtn">댓글 등록</button>
		</div>
  </c:if>
  
  <c:if test="${empty login}">
    <div class="box-body">
      <div><a href="javascript:goLogin();" >로그인 해주세요</a></div>
    </div>
  </c:if>				                 
</div>            


		
		<!-- The time line -->
		<ul class="timeline">
		  <!-- timeline time label -->
		<li class="time-label" id="repliesDiv">
		  <span class="bg-green">
		    댓글 목록 <small id='replycntSmall'> [ ${boardVO.replycnt} ] </small>
		    </span>
		  </li>
		</ul>
		   
			<div class='text-center'>
				<ul id="pagination" class="pagination pagination-sm no-margin ">

				</ul>
			</div>

		</div>
		<!-- /.col -->
			<div class="bannerlink">
	<div class="banner">
		<img src="/resources/bootstrap/image/banner.jpg" />
		
		
	</div>
	<div id="mlink" class="mbx">
			<ul>
			<li class="ico7"><a href="http://sgsso.dhu.ac.kr" target="_blank" title="새창으로 학생종합정보 홈페이지 연결">학생종합정보</a></li>
            <li class="ico5"><a href="http://library.dhu.ac.kr/" target="_blank" title="새창으로 도서관 홈페이지 연결">도서관</a></li>	
            <li class="ico6"><a href="http://bis.gbgs.go.kr/bs/businfo/sub03_03.jsp" target="_blank" title="새창으로 경산버스 홈페이지 연결">경산버스</a></li>					
			<li class="ico9"><a href="https://mail.dhu.ac.kr/" target="_blank" title="새창으로 웹메일 홈페이지 연결">웹메일</a></li>
			<li class="ico11"><a href="http://lifelong.dhu.ac.kr/" target="_blank" title="새창으로 평생교육원 홈페이지 연결">평생교육원</a></li>
			<li class="ico4"><a href="http://www.jaan.co.kr/" target="_blank" title="새창으로 자안쇼핑몰 홈페이지 연결">자안쇼핑몰</a></li>
			</ul>
		</div>
	</div>
	</div>
	<!-- /.row -->


          
<!-- Modal -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body" data-rno>
        <p><input type="text" id="replytext" class="form-control"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn">수정</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>      
	
	
</section>
<!-- /.content -->


<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span>
  </div>
</li>                
</script>  


          
<script id="template" type="text/x-handlebars-template">
				{{#each .}}
	         <li class="replyLi" data-rno={{rno}}>
             <i class="fa fa-comments bg-blue"></i>
             <div class="timeline-item" >
                <span class="time">
                  <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
                </span>
                <h3 class="timeline-header"><strong>{{replyer}}</strong></h3>
                <div class="timeline-body">{{replytext}} </div>
								<div class="timeline-footer">
								{{#eqReplyer replyer }}
                  <a class="btn btn-primary btn-xs" 
									data-toggle="modal" data-target="#modifyModal">Modify</a>
								{{/eqReplyer}}
							  </div>
	            </div>			
           </li>
        {{/each}}
</script>  

<script>

	
	Handlebars.registerHelper("eqReplyer", function(replyer, block) {
		var accum = '';
		if (replyer == '${login.uid}') {
			accum += block.fn();
		}
		return accum;
	});

	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});

	var printData = function(replyArr, target, templateObject) {

		var template = Handlebars.compile(templateObject.html());

		var html = template(replyArr);
		$(".replyLi").remove();
		target.after(html);

	}

	var bno = ${ boardVO.bno};

	var replyPage = 1;

	function getPage(pageInfo) {

		$.getJSON(pageInfo, function(data) {
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));

			$("#modifyModal").modal('hide');
			$("#replycntSmall").html("[ " + data.pageMaker.totalCount + " ]");

		});
	}

	var printPaging = function(pageMaker, target) {

		var str = "";

		if (pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1)
					+ "'> << </a></li>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
		}

		if (pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1)
					+ "'> >> </a></li>";
		}

		target.html(str);
	};

	$("#repliesDiv").on("click", function() {

		if ($(".timeline li").size() > 1) {
			return;
		}
		getPage("/replies${sboardNum}/" + bno + "/1");

	});

	$(".pagination").on("click", "li a", function(event) {

		event.preventDefault();

		replyPage = $(this).attr("href");

		getPage("/replies${sboardNum}/" + bno + "/" + replyPage);

	});

	$("#replyAddBtn").on("click", function() {

		var replyerObj = $("#newReplyWriter");
		var replytextObj = $("#newReplyText");
		var replyer = replyerObj.val();
		var replytext = replytextObj.val();

		$.ajax({
			type : 'post',
			url : '/replies${sboardNum}/',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("등록 되었습니다.");
					replyPage = 1;
					getPage("/replies${sboardNum}/" + bno + "/" + replyPage);
					//replyerObj.val("");
					replytextObj.val("");
				}
			}
		});
	});

	$(".timeline").on("click", ".replyLi", function(event) {

		var reply = $(this);

		$("#replytext").val(reply.find('.timeline-body').text());
		$(".modal-title").html(reply.attr("data-rno"));

	});

	$("#replyModBtn").on("click", function() {

		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();

		$.ajax({
			type : 'put',
			url : '/replies${sboardNum}/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				replytext : replytext
			}),
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("수정 되었습니다.");
					getPage("/replies${sboardNum}/" + bno + "/" + replyPage);
				}
			}
		});
	});

	$("#replyDelBtn").on("click", function() {

		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();

		$.ajax({
			type : 'delete',
			url : '/replies${sboardNum}/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("삭제 되었습니다.");
					getPage("/replies${sboardNum}/" + bno + "/" + replyPage);
				}
			}
		});
	});
</script>


<script>
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$("#modifyBtn").on("click", function(){
		formObj.attr("action", "/sboard${sboardNum}/modifyPage");
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
/* 	$("#removeBtn").on("click", function(){
		formObj.attr("action", "/sboard/removePage");
		formObj.submit();
	}); */

	
	$("#removeBtn").on("click", function(){
		
		var replyCnt =  $("#replycntSmall").html();
		
		if(replyCnt > 0 ){
			alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
			return;
		}	
		
		var arr = [];
		$(".uploadedList li").each(function(index){
			 arr.push($(this).attr("data-src"));
		});
		
		console.log(arr);
	 	if(arr.length > 0){
			$.post("/deleteAllFiles",{files:arr}, function(){
				
				formObj.attr("action", "/sboard${sboardNum}/removePage");
				formObj.submit();
				
			});
		}else{
			
			formObj.attr("action", "/sboard${sboardNum}/removePage");
			formObj.submit();
		}
		
	 	/*
		formObj.attr("action", "/sboard/removePage");
		formObj.submit();
		 */
	});	
	
	$("#goListBtn ").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/sboard${sboardNum}/list");
		formObj.submit();
	});
	
	var bno = ${boardVO.bno};
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/sboard${sboardNum}/getAttach/"+bno,function(list){ ///searchboardcontroller에 파라미터 있음.
		$(list).each(function(){
			
			var fileInfo = getFileInfo(this);
			
			var html = template(fileInfo);
			
			 $(".uploadedList").append(html);
			
		});
	});
	


	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		
		var fileLink = $(this).attr("href");
		
		if(checkImageType(fileLink)){
			
			event.preventDefault();
					
			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);
			
			console.log(imgTag.attr("src"));
					
			$(".popup").show('slow');
			imgTag.addClass("show");		
		}	
	});
	
	$("#popup_img").on("click", function(){
		
		$(".popup").hide('slow');
		
	});	
	
		
	
});


function goLogin(){
	self.location ="/user/login";
}

</script>




<%@include file="../include/footer.jsp"%>
