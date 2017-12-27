<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE>
<html>

<head>
    <meta charset="utf-8">
    <%@include file="/static/commons/commons.jsp" %>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="/static/bootstrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/static/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/static/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
    
    <script type="text/javascript" src="/static/plugin/fancyBox/jquery.fancybox.js"></script>

    <script type="text/javascript">
            $(function () {
                var head_form = $("#head_form");
                var objMethod = {
                    upload: function () {
                        head_form.form("submit", {
                            url: "/employee/editHead.do",
                            success: function (data) {
                                data = $.parseJSON(data);
                                if (data.success) {
                                    swal("保存头像成功！", "重新登录后才能正确显示", "success")
                                } else {
                                    swal("保存失败", "请检查输入数据是否有误！", "error")
                                }
                            }
                        })
                    }
                };

                $("[data-cmd]").click(function () {
                    var method = $(this).data("cmd");
                    objMethod[method]();
                });
            })
	</script>


</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>头像上传</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_editors.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div>
                        <ul class="nav nav-tabs" id="avatar-tab">
                            <li class="active" id="upload"><a href="javascript:;">本地上传</a>
                            </li>
                            <li id="albums"><a href="javascript:;">相册选取</a>
                            </li>
                        </ul>
                    </div>
                        <div>
                            <a class="fancybox list_img"
                               href="<shiro:principal property='imagePath'/>"
                               data-fancybox-group="gallery">
							<img src="<shiro:principal property='imageSmall'/>" alt="头像" class="list_img"/>
							</a>
							<input type="hidden" name="imagePath" value="<shiro:principal property='imagePath'/>"/>
                        </div>
                        <br/>
                        <br/>
                    <div>
                        <form action="#" id="head_form" method="post" enctype="multipart/form-data">
                                 <table align='center' style="margin-top:20px">
                                    <input type="hidden" name="id" value="<shiro:principal property='id'/>">
                                    <input class="easyui-filebox" name="picture" data-options="required:true,prompt:'请选择一张图片'" style="width:200px" buttonText="选择图片">
                                </table>
                        </form>
                    </div>
                 <%--<div id="emp_buttons">
                    <a class="easyui-linkbutton" data-cmd="upload" data-options="plain:true,iconCls:'icon-save'">保存</a>
                    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
                 </div>--%>
                 <div id="editorPanelButtons">
                     <a href="javascript:;"
                        class="btn btn-w-m btn-primary button_upload" data-cmd="upload"><i class="fa fa-upload"></i> 上传</a>
                 </div>
                        
                    <div id="userAlbums" style="display:none">
                                <a href="/static/bootstrap/img/a1.jpg" class="fancybox" title="选取该照片">
                                    <img src="/static/bootstrap/img/a1.jpg" class="list_img" alt="示例图片1"/>
                                </a>
                                <a href="/static/bootstrap/img/a2.jpg" class="fancybox" title="选取该照片">
                                    <img src="/static/bootstrap/img/a2.jpg" alt="示例图片2"/>
                                </a>
                                <a href="/static/bootstrap/img/a3.jpg" class="fancybox" title="选取该照片">
                                    <img src="/static/bootstrap/img/a3.jpg" alt="示例图片2"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
    </div>
    </div>

    </div>

    <!-- 全局js -->
    <script src="/static/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>


    <!-- 自定义js -->
   <%-- <script src="/static/bootstrap/js/content.js?v=1.0.0"></script>--%>


    <!-- fullavatareditor -->
    <script type="text/javascript" src="/static/bootstrap/plugins/fullavatareditor/scripts/swfobject.js"></script>
    <script type="text/javascript"
            src="/static/bootstrap/plugins/fullavatareditor/scripts/fullAvatarEditor.js"></script>
    <script type="text/javascript" src="/static/bootstrap/plugins/fullavatareditor/scripts/jQuery.Cookie.js"></script>
 <%--   <script type="text/javascript" src="/static/bootstrap/plugins/fullavatareditor/scripts/test.js"></script>--%>

</body>

</html>
</html>