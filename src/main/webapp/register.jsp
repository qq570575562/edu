<%--
  Created by IntelliJ IDEA.
  User: zsjlo
  Date: 2017/12/26
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>员工注册注册</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="/static/bootstrap/css/bootstrap.min.css?v=3.3.6"
                                                        rel="stylesheet">
    <link href="/static/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/static/bootstrap/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/static/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/static/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
      <%--SweetAlert弹窗css依赖--%>
<link href="/static/assets/plugins/sweetalert/dist/sweetalert.css" rel="stylesheet">
      <!-- 全局js -->
    <script src="/static/bootstrap/js/jquery.min.js?v=2.1.4"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
      <!-- iCheck -->
    <script src="/static/bootstrap/js/plugins/iCheck/icheck.min.js"></script>
      <!--表单验证-->
    <script src="/static/plugin/validate/jquery.validate.js"></script>
      
      <%--SweetAlert弹窗js依赖--%>
<script type="text/javascript" src="/static/assets/plugins/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }
    </script>
      
      <script type="text/javascript">
          $(function () {
              $("#register").validate({
                  rules: {
                      username: {
                          required: true,//必填
                          minlength: 3, //最少6个字符
                          maxlength: 32,//最多20个字符
                      },
                      password: {
                          required: true,
                          minlength: 3,
                          maxlength: 32,
                      },
                      confirm_password: {
                          required: true,
                          minlength: 3,
                          equalTo: '.password'
                      }
                  },
                  //错误信息提示
                  messages: {
                      username: {
                          required: "必须填写用户名",
                          minlength: "用户名至少为3个字符",
                          maxlength: "用户名至多为32个字符",
                          /*remote: "用户名已存在",*/
                      },
                      password: {
                          required: "必须填写密码",
                          minlength: "密码至少为3个字符",
                          maxlength: "密码至多为32个字符",
                      },

                      confirm_password: {
                          required: "请再次输入密码",
                          minlength: "确认密码不能少于3个字符",
                          equalTo: "两次输入密码不一致",//与另一个元素相同
                      },
                  },
              });
          })
      </script>
    <script>
		function register() {
            //使用ajax来进行登录
            $.post("/employee/register.do", $("form").serialize(), function (data) {
                if (data.success) {
                    swal("注册成功", "点击返回登陆界面", "success");
                    window.parent.location.href = "/login.jsp";
                } else {
                    alert(data.message);
                }
            }, "json");
        }
	</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">GE</h1>
            </div>
            <h3>欢迎注册GE</h3>
            <p>创建一个新账户</p>
           <form id="register" method="post">
		<div class="form-group">
			<input type="text" name="username" class="form-control" placeholder="登陆用户名" autocomplete="off"/>
		</div>
		<div class="form-group">
			<input type="password"
                   name="password"
                   class="form-control password loginForm"
                   placeholder="输入密码"
                   oncontextmenu="return false"
                   onpaste="return false"/>
		    </div>
		    <div class="form-group">
			        <input type="password"
                           name="confirm_password"
                           class="confirm_password form-control "
                           placeholder="再次输入密码"
                           oncontextmenu="return false"
                           onpaste="return false"/>
		    </div>
		    <div class="form-group">
			<input type="text" name="realname" class="form-control" placeholder="真实姓名" autocomplete="off"/>
		    </div>
           <div class="form-group text-left">
                    <div class="checkbox i-checks">
                        <label class="no-padding">
                            <input type="checkbox"><i></i> 我同意注册协议</label>
                    </div>
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="register()">注 册</button>

                <p class="text-muted text-center"><small>已经有账户了？</small><a href="login.jsp">点此登录</a>
                </p>
            </form>


        </div>
    </div>

    
</body>

</html>