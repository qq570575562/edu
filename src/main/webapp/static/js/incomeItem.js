$(function () {

    //提取出的选择
    var incomeItem_form = $("#incomeItem_form");
    var incomeItem_dialog = $("#incomeItem_dialog");
    var incomeItem_datagrid = $("#incomeItem_datagrid");
    var btn_audit = $("#btn_audit");
    //方法加载
    var methodObject = {
        add: function () {

            //清空表单
            incomeItem_form.form("clear");
            //设置标题
            incomeItem_dialog.dialog("setTitle", "新增收款");
            //设置审核人的行隐藏
            $("#auditor_tr").hide();
            //打开弹出框
            incomeItem_dialog.dialog("open");


        },
        edit: function () {
            var rows = incomeItem_datagrid.datagrid("getSelected");
            if (!rows) {
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
            incomeItem_form.form("clear");
            incomeItem_dialog.dialog("setTitle", "编辑收款");

            //改到这里了,回显数据
            if (rows["payMode"]) {
                rows["payMode.id"] = rows.payMode.id;
            }
            //console.log(rows);
            incomeItem_form.form("load", rows);
            incomeItem_dialog.dialog("open");
        },
        save: function () {
            incomeItem_form.form("submit", {
                url: "/incomeItem/saveOrUpdate.do",
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert("提示信息", data.msg, "info", function () {
                        swal("操作成功！", data.msg, "success");
                        incomeItem_dialog.dialog("close");
                        incomeItem_datagrid.datagrid("load");
                        // });
                    } else {
                        //$.messager.alert("提示信息", data.msg, "info");
                        swal("操作失败", data.msg, "error");
                    }
                }
            })
        },
        cancel: function () {
            incomeItem_dialog.dialog("close");
        },
        reload: function () {
            incomeItem_datagrid.datagrid("reload");
        },
        audit: function () { // 审核
            var incomeItem = incomeItem_datagrid.datagrid("getSelected");
            if (!incomeItem) {
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

           /* $.messager.confirm("提示", "请问你要执行审核操作吗?", function (r) {
                if (r) {
                    var id = incomeItem_datagrid.datagrid("getSelected").id;
                    $.get("/incomeItem/changeState.do", {id: id}, function (data) {
                        if (data.success) {
                            $.messager.alert("提示信息", data.msg, "info", function () {
                                incomeItem_datagrid.datagrid("reload");
                            })
                        } else {
                            $.messager.alert("提示信息", data.msg, "info");
                        }
                    })
                }
            })*/
            //Sweet Alert弹窗效果
            swal({
                title: "您确定要执行该操作吗?",
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
                    var id = incomeItem_datagrid.datagrid("getSelected").id;
                    //发送请求修改状态
                    $.get("/incomeItem/changeState.do", {id: id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", data.msg, "success");
                            incomeItem_datagrid.datagrid("reload");
                        } else {
                            swal("操作失败", data.msg, "error");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })


        },
        searchForEmp: function () {
            var className = $("input[name = 'className']").val();
            var beginDate = $("input[name = 'beginDate']").val();
            var endDate = $("input[name = 'endDate']").val();
            var keyword = $("input[name = 'keyword']").val();
            incomeItem_datagrid.datagrid("load", {
                className: className,
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword
            });
        },
        exportXls: function () {
            //xls导出
            window.location.href = "/incomeIte/exportXls.do";
        }
    }

    //统一绑定事件
    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        methodObject[cmd]();
    });

    //设置表的显示数据
    incomeItem_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: "/incomeItem/query.do",
        pagination: true,
        singleSelect: true,
        toolbar: "#incomeItem_toolbar",
        rownumbers: true,
        nowrap: false,
        columns: [[

            {field: 'name', title: '姓名', width: 100, align: 'center'},
            {field: 'className', title: '班级', width: 100, align: 'center'},
            {field: 'inputTime', title: '收款时间', width: 100, align: 'center'},
            {field: 'inMoney', title: '收款金额', width: 100, align: 'center'},
            {field: 'ownTuition', title: '欠款金额', width: 100, align: 'center'},
            {field: 'payMode',
                title: '支付方式',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {field: 'paee', title: '收款人', width: 100, align: 'center'},
            {field: 'info', title: '备注', width: 100, align: 'center'},
            {field: 'saleman', title: '营销人员', width: 100, align: 'center'},
            {
                field: 'status',
                title: '审核状态',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? '<font color="green">已审核</font>' : '<font color="red">未审核</font>';
                }
            },
            {field: 'auditor', title: '审核人', width: 100, align: 'center'},

        ]],

        onSelect: function (index, data) {
            //点击事件 判定是怎样的审核状态
            var data = incomeItem_datagrid.datagrid("getSelected");
            if (data.status){
                $("#btn_audit").linkbutton('disable');
            }else {
                $('#btn_audit').linkbutton('enable');
            }

        }
    });

    incomeItem_dialog.dialog({
        width: 250,
        height: 360,
        buttons: "#incomeItem_btns",
        closed: true
    });


});

//体验方法
function search(value) {
    var deptId = $("input[name = 'deptId']").val();
    var beginDate = $("input[name = 'beginDate']").val();
    var endDate = $("input[name = 'endDate']").val();

    $("#incomeItem_datagrid").datagrid("load", {
        keyword: value,
        deptId: deptId,
        beginDate: beginDate,
        endDate: endDate
    });

}

function setOwntuition() {
    //获得id

    var formalstudentId = $("input[name = 'formalstudentId']").val();
    if (formalstudentId) {
        //get获得
        $.get("/formalStudent/selectOwnTuitionByid.do?id=" + formalstudentId, function (data) {
            //data = parseInt(data);
            $("#item_ownTuition").numberbox('setValue', data);

        })
    }

}

