<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/4/29
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            //发送ajax请求
            $("#btn").click(function () {
                //alert("haha");
                /* $.post("user/testAjax",{"username":"hehe","password":"123","age":12},function (data) {
                      //应该获取服务器响应的json数据  然后进行解析
                      alert(data.username);
                      alert(data.age);
                  },"application/json");*/
            $.ajax({
                    type:"post",
                    url:"user/testJson",
                    contentType:"application/json;charset=utf-8",
                    data:'{"username":"hehe","password":"123"}',
                    dataType:"json",
                    success:function (data) {
                        //应该获取服务器响应的json数据  然后进行解析
                        //alert(JSON.parse(data));
                        alert(data.username);
                        alert(data.password);
                    }
                });
            });
        });
    </script>
</head>
<body>
   <a href="user/testReturnString">测试响应结果类型为String</a>
   <a href="user/testReturnVoid">测试响应结果类型为Void</a>
   <a href="user/testModelAndView?username=zs&password=123">测试响应结果类型为ModelAndView</a>

   <a href="user/testForward">请求转发</a>
   <a href="user/testSendRedirect">重定向</a>
   <hr color="red" size="5" width="100%">
   <input type="button" id="btn" value="发送"/>
</body>
</html>
