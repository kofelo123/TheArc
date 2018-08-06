<%--
  Created by IntelliJ IDEA.
  User: 허정원
  Date: 2018-08-05
  Time: 오전 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


    <style>
        #test{
            position: absolute;
            left:50%;
            top:20%;
        }
    </style>


    <script
            src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>

</head>
<body>

    <div id="upcase">
        <button id="test">Test</button>
    </div>
</body>


<script>

    $(function(){

        $("#upcase").on("click","#test",function(){

            console.log("test");

            $(this).attr("id","test2");

        });

        $("#upcase").on("click","#test2",function(){

            console.log("test2");

        });

    });


</script>
</html>

