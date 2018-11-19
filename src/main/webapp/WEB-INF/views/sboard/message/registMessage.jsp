<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="/WEB-INF/views/include/analytics.jsp"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link rel="stylesheet" href="/thearc/resources/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="/thearc/resources/bootstrap/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/thearc/resources/bootstrap/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

    <sec:authentication property="principal" var="pinfo" />
<!-- Main content -->
    <section class="content">
      <div class="row">
      
        <div class="col-md-9">
          
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">쪽지 보내기</h3>
            </div>
            <!-- /.box-header -->
           <form id='mailregister' role='form' action="/thearc/sboard/message/registMessage" method="post">
            <div class="box-body">
            <input type=hidden name='sender' value='${pinfo.member.uid }'>
              <div class="form-group">
                <input class="form-control" type="text" name='targetid' placeholder="받는사람 ID">
              </div>
              <div class="form-group">
                <input class="form-control" type="text" name='title' placeholder="제목:">
              </div>
              <div class="form-group">
                    <textarea id="compose-textarea" name='message' placeholder="내용을 입력하세요" class="form-control" style="height: 300px">
                     
                    </textarea>
              </div>
              
            </div>	
            <!-- /.box-body -->
            <div class="box-footer">
              <div class="pull-right">
                  <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> 보내기</button>

                <button type="button" class="btn btn-default" id="goListBtn"><i class="glyphicon glyphicon-arrow-left"></i> 취소</button>
            
              </div>

              <button type="reset" class="btn btn-default"><i class="fa fa-times"></i> 리셋</button>
            </div>
           </form>
              <!-- /.box-footer -->
          </div>
          <!-- /. box -->
          
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
    <!-- jQuery 2.2.3 -->
    <!-- 에디터와 연관  -->
<script src="/thearc/resources/bootstrap/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/thearc/resources/bootstrap/js/bootstrap.min.js"></script>

<script src="/thearc/resources/bootstrap/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Page Script -->
<script>
  $(function () {
    //Add text editor
    $("#compose-textarea").wysihtml5();
  });
</script>

<script>
    
    
	$(document).ready(function() {
	
				$('#goListBtn').on("click", function() {
				    console.log("goListBtntest");
                    $(location).attr("href","listMessage?uid="+<sec:authentication property="principal.member.uid"/>);
				});
			});
</script>


