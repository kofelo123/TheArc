<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/analytics.jsp"%>	

 <link rel="stylesheet" href="/resources/bootstrap/plugins/fullcalendar/fullcalendar.min.css">
  <link rel="stylesheet" href="/resources/bootstrap/plugins/fullcalendar/fullcalendar.print.css" media="print">


 

     <!-- Main content -->
    <section class="content">
      <div class="row">
        <!-- <div class="col-md-3">
          <div class="box box-solid">
            <div class="box-header with-border">
              <h4 class="box-title">드래그 아이콘</h4>
            </div>
            <div class="box-body">
              the events
              <div id="external-events">
                <div class="external-event bg-green">수업</div>
                <div class="external-event bg-yellow">팀 회의</div>
                <div class="external-event bg-aqua">과제</div>
                <div class="external-event bg-light-blue">프로젝트작업</div>
                <div class="external-event bg-red">발표일</div>
                <div class="checkbox">
                  <label for="drop-remove">
                    <input type="checkbox" id="drop-remove">
                    	드래그 후 삭제하기 
                  </label>
                </div>
              </div>
            </div>
            /.box-body
          </div>
          /. box
          <div class="box box-solid">
            <div class="box-header with-border">
              <h3 class="box-title">드래그 아이콘 생성</h3>
            </div>
            <div class="box-body">
              <div class="btn-group" style="width: 100%; margin-bottom: 10px;">
                <button type="button" id="color-chooser-btn" class="btn btn-info btn-block dropdown-toggle" data-toggle="dropdown">Color <span class="caret"></span></button>
                <ul class="fc-color-picker" id="color-chooser">
                  <li><a class="text-aqua" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-blue" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-light-blue" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-teal" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-yellow" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-orange" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-green" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-lime" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-red" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-purple" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-fuchsia" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-muted" href="#"><i class="fa fa-square"></i></a></li>
                  <li><a class="text-navy" href="#"><i class="fa fa-square"></i></a></li>
                </ul>
              </div>
              /btn-group
              <div class="input-group">
                <input id="new-event" type="text" class="form-control" placeholder="이벤트 이름">

                <div class="input-group-btn">
                  <button id="add-new-event" type="button" class="btn btn-primary btn-flat">추가</button>
                </div>
                /btn-group
              </div>
              /input-group
            </div>
          </div>
        </div> -->
        <!-- /.col -->
        <div class="col-md-12" style="width:75%;padding-left:7%;">
          <div class="box box-primary">
            <div class="box-body no-padding">
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
              
            </div>
            <!-- /.box-body -->
          
          </div>
          
          <!-- /. box -->
          
        </div>
        <!-- /.col -->
             
      </div>
   
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
    
    <!-- jQuery 2.2.3 -->
<script src="/resources/bootstrap/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../../../resources/bootstrap/js/bootstrap.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Slimscroll -->
<script src="/resources/bootstrap/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/resources/bootstrap/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/resources/bootstrap/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/resources/bootstrap/js/demo.js"></script>
<!-- fullCalendar 2.2.5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="/resources/bootstrap/plugins/fullcalendar/fullcalendar.min.js"></script>
<!-- Page specific script -->




<script>
  $(function () {

    /* initialize the external events
     -----------------------------------------------------------------*/
    function ini_events(ele) {
      ele.each(function () {

        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
          title: $.trim($(this).text()) // use the element's text as the event title
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        $(this).draggable({
          zIndex: 1070,
          revert: true, // will cause the event to go back to its
          revertDuration: 0  //  original position after the drag
        });

      });
    }

    ini_events($('#external-events div.external-event'));

    /* initialize the calendar
     -----------------------------------------------------------------*/
    //Date for the calendar events (dummy data)
    var date = new Date();
    var d = date.getDate(),
        m = date.getMonth(),
        y = date.getFullYear();
    $('#calendar').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      buttonText: {
        today: 'today',
        month: 'month',
        week: 'week',
        day: 'day'
      },
      //Random default events
   events: [
        {
          title: '11월의 시작',
          start: new Date(y, m, 1),
          backgroundColor: "#f56954", //red
          borderColor: "#f56954" //red
        },
        {
            title: '졸업작품 회의',
            start: new Date(y, m, 	3, 10, 0),
            allDay: false,
            backgroundColor: "lightblue", //Success (green)
            borderColor: "pink" //Success (green)
          },
          {
              title: '국가장학금 신청기간',
              start: new Date(y, m, 	17, 10, 0),
              allDay: false,
              backgroundColor: "lightblue", //Success (green)
              borderColor: "pink" //Success (green)
             
            },
          {
              title: 'G-Star 2016',
              start: new Date(y, m,17, 10, 0),
              allDay: false,
              backgroundColor: "purple", //Success (green)
              borderColor: "pink" //Success (green)
            },
            {
                title: 'G-Star 2016',
                start: new Date(y, m,18, 10, 0),
                allDay: false,
                backgroundColor: "purple", //Success (green)
                borderColor: "pink" //Success (green)
              },
              {
                  title: 'G-Star 2016',
                  start: new Date(y, m,19, 10, 0),
                  allDay: false,
                  backgroundColor: "purple", //Success (green)
                  borderColor: "pink" //Success (green)
                },
                {
                    title: 'G-Star 2016',
                    start: new Date(y, m,20, 10, 0),
                    allDay: false,
                    backgroundColor: "purple", //Success (green)
                    borderColor: "pink" //Success (green)
                  },
            
          {
              title: '졸업작품 리허설',
              start: new Date(y, m, 21, 10, 0),
              allDay: false,
              backgroundColor: "gold", //Success (green)
              borderColor: "pink" //Success (green)
              
            },
            {
                title: '작품전시',
                start: new Date(y, m, 23, 10, 0),
                allDay: false,
                backgroundColor: "orange", //Success (green)
                borderColor: "pink" //Success (green)
              },
              {
                  title: '졸업작품 발표회',
                  start: new Date(y, m, 24, 10, 12),
                  allDay: false,
                  backgroundColor: "gold", //Success (green)
                  borderColor: "white" //Success (green)
                
                }, 
                {
                    title: '수업평가(기말평가)기간',
                    start: new Date(y, m, 30, 10, 0),
                    allDay: false,
                    backgroundColor: "pink", //Success (green)
                    borderColor: "pink" //Success (green)
                  }, 
            
        {
          title: 'Click for Google',
          start: new Date(y, m, 28),
          end: new Date(y, m, 29),
          url: 'http://google.com/',
          backgroundColor: "#3c8dbc", //Primary (light-blue)
          borderColor: "#3c8dbc" //Primary (light-blue)
        }
      ],
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function (date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');

        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.backgroundColor = $(this).css("background-color");
        copiedEventObject.borderColor = $(this).css("border-color");

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }

      }
    });

    /* ADDING EVENTS */
    var currColor = "#3c8dbc"; //Red by default
    //Color chooser button
    var colorChooser = $("#color-chooser-btn");
    $("#color-chooser > li > a").click(function (e) {
      e.preventDefault();
      //Save color
      currColor = $(this).css("color");
      //Add color effect to button
      $('#add-new-event').css({"background-color": currColor, "border-color": currColor});
    });
    $("#add-new-event").click(function (e) {
      e.preventDefault();
      //Get value and make sure it is not null
      var val = $("#new-event").val();
      if (val.length == 0) {
        return;
      }

      //Create events
      var event = $("<div />");
      event.css({"background-color": currColor, "border-color": currColor, "color": "#fff"}).addClass("external-event");
      event.html(val);
      $('#external-events').prepend(event);

      //Add draggable funtionality
      ini_events(event);

      //Remove event from text input
      $("#new-event").val("");
    });
  });
</script>


<%@include file="../include/adminfooter.jsp"%>
