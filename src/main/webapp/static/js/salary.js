$(function () {

    //获取表格中的对象
    var salary_form = $("#salary_form");
    var salary_dialog = $("#salary_dialog");
    var salary_datagrid = $("#salary_datagrid");


    salary_dialog.dialog({
        width: 300,
        height: 350,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        singleSelect: true,
        closed: true,
        buttons: "#salary_btns",

    });

    //初始化数据表格
    salary_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/salary/query.do',
        rownumbers: true,
        pagination: true,
        pageSize: 15,
        pageList: [15, 20, 25, 30, 35],
        singleSelect: true,
        columns: [[
            {
                field: 'eid',
                title: '员工工号',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return row.employee ? (row.employee.id ? row.employee.id : "") : "";
                }
            },
            {
                field: 'employee',
                title: '员工姓名',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return row.employee ? (row.employee.realname ? row.employee.realname : "") : "";
                }
            },
            {field: 'month', title: '月  份 ', width: 100, align: 'center'},
            {field: 'salary', title: '基本薪资', width: 100, align: 'center'},
            {field: 'workDay', title: '工作天数', width: 100, align: 'center'},
            {field: 'afterNumber', title: '迟到次数', width: 100, align: 'center'},
            {field: 'beforeNumber', title: '早退次数', width: 100, align: 'center'},
            {field: 'resignNumber', title: '补签次数', width: 100, align: 'center'},
            {field: 'nowSalary', title: '实发薪资', width: 100, align: 'center'},
            {field: 'paytime', title: '结算时间', width: 100, align: 'center'}
        ]],
        toolbar: "#salary_toolbar"
    });
    //定义方法
    var methodObj = {
        add: function () {

            //清空表单
            salary_form.form("clear");
            //设置标题
            salary_dialog.dialog("setTitle", "角色新增");
            //弹窗
            salary_dialog.dialog("open");
        },
        edit: function () {
            //获取选中行
            var row = salary_datagrid.datagrid("getSelected");
            if (row == null) {

                swal({
                    title: "请选择一条数据",
                    //text: "删除后将无法恢复，请谨慎操作！",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }

            //设置标题
            salary_dialog.dialog("setTitle", "薪资编辑");

            if (row.employee) {
                row["employee.id"] = row.employee.id;
                row["employee.realname"] = row.employee.realname;
            }
            $("#realname_id").html(row.employee.realname);

            // 清空
            salary_form.form("clear");
            //设置值 回显表单
            salary_form.form("load", row);

            //弹窗
            salary_dialog.dialog("open");
        },
        save: function () {

            var salary = $("#salary_id").textbox("getValue");

            if (!salary) {
                swal({
                    title: "请输入员工薪资",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            salary_form.form("submit", {
                url: "/salary/updateSalary.do",
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "操作成功", "info", function () {
                            salary_dialog.dialog("close");
                            salary_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }
            })
        },
        //核算
        adjust: function () {
            var row = salary_datagrid.datagrid("getSelected");
            if (row == null) {
                swal({
                    title: "请选择一条数据",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            if (row.paytime) {
                swal({
                    title: "工资已发放,不能再次核算",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            if (row.employee) {
                row["employee.id"] = row.employee.id;
                row["employee.realname"] = row.employee.realname;
            }
            delete row.employee;
            $.post("/salary/updateForRow.do", row, function (data) {
                if (data.success) {
                    $.post("/salary/adjust.do", row, function (data) {
                        swal("操作成功！", "信息更新成功!点击确认返回", "success");
                        salary_datagrid.datagrid("reload");
                    });
                } else {
                    $.messager.alert("温馨提示", data.msg, "info");
                }
            })
        },
        //加载全部
        loadAll: function () {
            swal({
                title: "您确定要执行该操作吗?",
                text: "加载可能需要一段时间！",
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
                    $.get("/salary/reloadAll.do", function (data) {
                        if (data.success) {
                            salary_datagrid.datagrid("reload");
                            swal("操作成功！", "信息已更新~", "success");
                        }
                    });
                } else {
                    swal("已取消", "您已取消操作！", "error");
                }
            });
        },

        payOff: function () {

            var row = salary_datagrid.datagrid("getSelected");
            if (row == null) {
                swal({
                    title: "请选择一条数据",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            if (row.paytime) {
                swal({
                    title: "工资已发放,不能再次结算",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            if (row.employee) {
                row["employee.id"] = row.employee.id;
                row["employee.realname"] = row.employee.realname;
            }
            delete row.employee;

            $.post("/salary/payOff.do", row, function (data) {
                if (data.success) {

                    swal("操作成功！", "结算成功!点击确认返回~", "success");
                    salary_datagrid.datagrid("reload");

                } else {
                    //swal("温馨提示！", data.msg, "error");
                    swal("删除失败~", "请确认操作是否有误~", "error");
                }
            })
        },

        //删除操作
        remove: function () {
            //获取选中行
            var row = salary_datagrid.datagrid("getSelected");
            if (row == null) {
                swal({
                    title: "请选择一条数据",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }

            //Sweet Alert弹窗效果
            swal({
                title: "您确定要执行删除操作吗?",
                text: "请谨慎操作！",
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
                    $.get("/salary/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("删除成功！", "", "success");
                            salary_datagrid.datagrid("reload");
                        }
                    });
                } else {
                    swal("已取消", "", "error");
                }
            });
        },
        cancel: function () {
            salary_dialog.dialog("close");
        },
        reload: function () {
            salary_datagrid.datagrid("load");
        },
        //查询操作
        search: function () {
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").datebox("getValue");
            var endDate = $("#endDate").datebox("getValue");
            salary_datagrid.datagrid("load", {
                    keyword: keyword,
                    beginDate: beginDate,
                    endDate: endDate
                }
            );
        },

        //xls导出
        exportXls: function () {

            //Sweet Alert弹窗效果
            swal({
                title: "您确定要执行该导出操作吗?",
                text: "请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    window.location.href = "/salary/exportXls.do";
                    swal("导出成功!", "", "success")
                } else {
                    swal("已取消", "", "error")
                }
            })
        },



        importXls: function () {
            //xls导入
            $("#salary_import").dialog("open");
        },
        download: function () {
            //下载
            window.location.href = "/salary/download.do";
        },
        upload: function () {//上传
            var files = $("#salaryes").filebox("getValue");
            if (!files) {
                swal({
                    title: "请选择上传文件",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            $("#import_form").form("submit", {
                url: "/salary/importXls.do",
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        swal("导入成功", "点击确认返回管理界面", "success");
                        $("#salary_import").dialog("close");
                        salary_datagrid.datagrid("load");
                    } else {
                        swal("导入失败", data.msg, "error");
                    }
                }
            })
        }
    };
    //绑定方法
    $("[data-cmd]").click(function () {
        //获取方法名
        var methodName = $(this).data("cmd");
        //执行方法
        methodObj[methodName]();
    });

    /* parent.layer.open({
         type: 1,
         skin: 'layui-layer-rim', //加上边框
         area: ['420px', '240px'], //宽高
         content: 'html内容'
     });*/


    salary_dialog.dialog({
        width: 300,
        height: 230,
        buttons: "#salary_btns",
        closed: true
    });

    $("#salary_import").dialog({
        width: 400,
        height: 200,
        closed: true,
        title: "文件导入"
    });
});
