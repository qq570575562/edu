$(function () {

    //获取表格中的对象
    var systemlog_datagrid = $("#systemlog_datagrid");

    //初始化数据表格
    systemlog_datagrid.datagrid({
        fit: true,
        
        url: '/systemLog/query.do',
        rownumbers: true,
        pagination: true,
        pageSize: 15,
        pageList: [15, 20, 25, 30, 35],
        singleSelect: true,
        columns: [[
            {
                field: 'opuser', title: '操作员工', width: 130, formatter: function (value, row, index) {
                    return value ? value.username : '登陆操作'
                }
            },
            {field: 'optime', title: '操作时间', width: 130},
            {field: 'opip', title: '操作IP地址', width: 130},
            {field: 'function', title: '操作的内容', width: 500},
            {field: 'parmas', title: '参数', width: 200}
        ]],
        toolbar: "#mytoolbar"
    });
    //定义方法
    var methodObj = {

        cancel: function () {
            systemlog_dialog.dialog("close");
        },
        reload: function () {
            systemlog_datagrid.datagrid("load");
        },
        removeAll: function () {
            //获取选中行
            var row = systemlog_datagrid.datagrid("getSelected");
            //Sweet Alert弹窗效果
            swal({
                title: "您确定要执行该操作吗?",
                text: "该操作不可恢复,请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    //发送请求修改状态
                    $.get("/systemLog/deleteAll.do", function (data) {
                        if (data.success) {
                            swal("操作成功！", "日志清除成功", "success");
                            systemlog_datagrid.datagrid("load");
                        }
                    });
                } else {
                    swal("已取消", "您已取消了操作！", "error");
                }
            });


        },

    };

    //绑定方法
    $("[data-cmd]").click(function () {

        //获取方法名
        var methodName = $(this).data("cmd");
        //执行方法
        methodObj[methodName]();
    });


});