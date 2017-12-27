<%--
  Created by IntelliJ IDEA.
  User: zsjlo
  Date: 2017/12/26
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>计划</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="/static/bootstrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="/static/bootstrap/css/plugins/iCheck/custom.css" rel="stylesheet">

    <link href="/static/bootstrap/css/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
    <link href="/static/bootstrap/css/plugins/fullcalendar/fullcalendar.print.css" rel="stylesheet">

    <link href="/static/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/static/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">

  </head>
  <body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInDown">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>待办事项</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="calendar.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="calendar.html#">选项1</a>
                                </li>
                                <li><a href="calendar.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div id='external-events'>
                            <p>可拖动的活动</p>
                            <div class='external-event navy-bg'>确定活动目标</div>
                            <div class='external-event navy-bg'>各部门职责及分工</div>
                            <div class='external-event navy-bg'>案例分享</div>
                            <div class='external-event navy-bg'>部分们会议</div>
                            <p class="m-t">
                                <input type='checkbox' id='drop-remove' class="i-checks" checked/>
                                <label for='drop-remove'>移动后删除</label>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="ibox float-e-margins">
                  
                </div>
            </div>
            <div class="col-sm-9">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="calendar.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="calendar.html#">选项1</a>
                                </li>
                                <li><a href="calendar.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div id="calendar"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 全局js -->
    <script src="/static/bootstrap/js/jquery.min.js?v=2.1.4"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>


    <!-- 自定义js -->
    <script src="/static/bootstrap/js/content.js?v=1.0.0"></script>


    <script src="/static/bootstrap/js/jquery-ui.custom.min.js"></script>

    <!-- iCheck -->
    <script src="/static/bootstrap/js/plugins/iCheck/icheck.min.js"></script>

    <!-- Full Calendar -->
    <script src="/static/bootstrap/js/plugins/fullcalendar/fullcalendar.min.js"></script>

    <script>
        $(document).ready(function () {

            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });


            $('#external-events div.external-event').each(function () {

                // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                // it doesn't need to have a start or end
                var eventObject = {
                    title: $.trim($(this).text()) // use the element's text as the event title
                };

                // store the Event Object in the DOM element so we can get to it later
                $(this).data('eventObject', eventObject);

                // make the event draggable using jQuery UI
                $(this).draggable({
                    zIndex: 999,
                    revert: true, // will cause the event to go back to its
                    revertDuration: 0 //  original position after the drag
                });

            });


            /* initialize the calendar
             -----------------------------------------------------------------*/
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
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

                    // render the event on the calendar
                    // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                    $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
                    }

                },
                events: [
                    {
                        title: '健身',
                        start: new Date(y, m, 2)
                    },
                    {
                        title: '本月的报表统计',
                        start: new Date(y, m, d - 5),
                        end: new Date(y, m, d - 2),
                    },
                    {
                        id: 999,
                        title: '客户回访',
                        start: new Date(y, m, d + 4, 16, 0),
                        allDay: false
                    },
                    {
                        title: '会议',
                        start: new Date(y, m, d, 10, 30),
                        allDay: false
                    },
                    {
                        title: '午餐',
                        start: new Date(y, m, d, 12, 0),
                        end: new Date(y, m, d, 14, 0),
                        allDay: false
                    },
                    {
                        title: '儿子生日',
                        start: new Date(y, m, d + 1, 19, 0),
                        end: new Date(y, m, d + 1, 22, 30),
                        allDay: false
                    }
                ],
            });


        });
    </script>
  
  </body>
</html>
