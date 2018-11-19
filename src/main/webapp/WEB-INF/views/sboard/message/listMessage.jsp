<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../../include/analytics.jsp"%>	

<meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link rel="stylesheet" href="/thearc/resources/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="/thearc/resources/bootstrap/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/thearc/resources/bootstrap/plugins/iCheck/flat/blue.css">
  
  <!-- Main content -->
    <section class="content">
      <div class="row">
       
        <!-- /.col -->
        <div class="col-md-9" style="width:100%">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">쪽지함</h3>

              <div class="box-tools pull-right">
                <div class="has-feedback">
                  <input type="text" class="form-control input-sm" placeholder="검색">
                  <span button id='searchBtn' class="glyphicon glyphicon-search form-control-feedback" />
                </div>
              </div>
              <!-- /.box-tools -->
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <div class="mailbox-controls">
                <!-- Check all button -->
                <!-- /.btn-group -->
                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
                <div class="pull-right">
                
                <button id='newBtn'>쪽지 보내기</button>
                 <!--  1-50/200  버튼 있는 부분
                  <div class="btn-group">
                    <button type="button" class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
                    <button type="button" class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
                  </div>
                  /.btn-group -->
                </div>
                <!-- /.pull-right -->
              </div>
              <div class="table-responsive mailbox-messages">
                <table class="table table-hover table-striped">
                  <tbody>
                  <c:forEach items="${list}" var="MessageVO">
							<tr>
								<td><input type="checkbox"></td>
								<td class="mailbox-star"><a href="#"><i class="fa fa-star-o text-yellow"></i></a></td>
								<td class="mailbox-name">${MessageVO.sender}</td>
								<td class="mailbox-subject"><a
									href='/thearc/sboard/message/readMessage${pageMaker.makeSearch(pageMaker.cri.page) }&mid=${MessageVO.mid}'>
										${MessageVO.title} 
								</a></td>
								<c:if test="${MessageVO.readcheck=='y' }">
								 <td class="mailbox-attachment"><i class="glyphicon glyphicon-ok"></i></td>
								</c:if>
								<c:if test="${MessageVO.readcheck=='n' }">
								 <td class="mailbox-attachment"></td>
								</c:if>
								<td class="mailbox-date"><fmt:formatDate pattern="MM-dd HH:mm"
										value="${MessageVO.sendate}" /></td>
						</c:forEach>
                  </tbody>
                </table>
                <!-- /.table -->
              </div>
              <!-- /.mail-box-messages -->
            </div>
            <!-- /.box-body -->
            <div class="box-footer no-padding">
              <div class="mailbox-controls">
                <!-- Check all button -->
               <div class="text-center">
                 	<ul class="pagination">

                        <sec:authentication property="principal" var="pinfo"/>

							<c:if test="${pageMaker.prev}">
								<li><a
									href="/thearc/listMessage${pageMaker.makeSearch(pageMaker.startPage - 1) }&uid=${pinfo.member.uid }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="/thearc/listMessage${pageMaker.makeSearch(idx)}&uid=${pinfo.member.uid }">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="/thearc/listMessage${pageMaker.makeSearch(pageMaker.endPage +1) }&uid=${pinfo.member.uid }">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
                  <!-- /.btn-group -->
                </div>
                <!-- /.pull-right -->
              </div>
            </div>
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
    
    <!-- jQuery 2.2.3 -->
<script src="/thearc/resources/bootstrap/plugins/jQuery/jquery-2.2.3.min.js"></script>


<script>
	$(document).ready(function() {			
				$('#newBtn').on("click", function(evt) {
					self.location = "registMessage";
				});
			});
</script>

<script>
	var result = '${msg}';
	if (result == 'SUCCESS') {
		alert("전송이 완료되었습니다.");
		location.replace(self.location);
	}
</script>