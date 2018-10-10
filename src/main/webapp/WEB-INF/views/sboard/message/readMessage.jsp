<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../../include/analytics.jsp"%>	

<meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link rel="stylesheet" href="/thearc/resources/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="/thearc/resources/bootstrap/css/AdminLTE.min.css">

 <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-9">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">쪽지 수신함</h3>
              <div class="box-tools pull-right">
                <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Previous"><i class="fa fa-chevron-left"></i></a>
                <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Next"><i class="fa fa-chevron-right"></i></a>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <div class="mailbox-read-info">
                <h3>${messageVO.title }</h3>
                <h5>From: ${messageVO.sender }
                  <span class="mailbox-read-time pull-right"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
										value="${messageVO.sendate}" /></span></h5>
              </div>
             
              <!-- /.mailbox-controls -->
              <div class="mailbox-read-message">
               	${messageVO.message}
              </div>
              <!-- /.mailbox-read-message -->
            </div>
            <!-- /.box-footer -->
            <div class="box-footer">
              <div class="pull-right">
                <button type="button" class="btn btn-default"><i class="fa fa-reply"></i> 답장</button>
                <button type="button" class="btn btn-default"><i class="fa fa-share"></i> 전달</button>
              </div>
              <button type="button" class="btn btn-default"><i class="fa fa-trash-o"></i> 삭제</button>
              <button id="goListBtn" class="btn btn-default"><i class="fa fa-print" ></i> 목록</button>
            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->

<script type="text/javascript" src="/thearc/resources/bootstrap/js/jquery-1.10.2.min.js"></script>
<script>
	$(document).ready(function() {
				$('#goListBtn').on("click", function() {
				    $(location).attr("href","listMessage?uid=${login.uid }");
					 <%--self.location = "listMessage?uid= ${login.uid }"; --%>
				});
			});
</script>