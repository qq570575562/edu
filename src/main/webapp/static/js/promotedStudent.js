$(function () {

    //提取出的选择
    var promoted_form = $("#promoted_form");
    var promoted_dialog = $("#promoted_dialog");
    var promoted_datagrid = $("#promoted_datagrid");
    var btn_audit = $("#btn_audit");

    //方法加载
    var methodObject = {
        check: function () {
            var promoted = promoted_datagrid.datagrid("getSelected");
            if (!promoted) {
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
            promoted_form.form("clear");
            promoted_dialog.dialog("setTitle", "查看升班留级");
            //改到这里了
            if (promoted.clientType) {
                promoted["clientType.id"] = promoted.clientType.id;
            }
            if (promoted.saleman) {
                promoted["saleman.id"] = promoted.saleman.id;
            }
            if (promoted.classBefore) {
                promoted["classBefore.id"] = promoted.classBefore.id;
            }
            if (promoted.classAfter) {
                promoted["classAfter.id"] = promoted.classAfter.id;
            }
            promoted_form.form("load", promoted);

            promoted_dialog.dialog("open");

        },
        save: function () {
            promoted_form.form("submit", {
                url: "/promoted/saveOrUpdate.do",
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert("提示信息", data.msg, "info", function () {
                        swal("操作成功！", data.msg, "success");
                        promoted_dialog.dialog("close");
                        promoted_datagrid.datagrid("load");
                        //});
                    } else {
                        swal("操作失败！", data.msg, "error");
                    }
                }
            })
        },
        cancel: function () {
            promoted_dialog.dialog("close");
        },
        reload: function () {
            promoted_datagrid.datagrid("reload");
        },
        audit: function () { // 审核
            var promoted = promoted_datagrid.datagrid("getSelected");
            if (!promoted) {

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
            //console.debug(promoted.auditState);
            var flag = promoted.auditState ? "反审核" : "审核";
            /* $.messager.confirm("提示", "请问你要" + flag + "该操作吗", function (r) {
                 if (r) {
                     var id = promoted_datagrid.datagrid("getSelected").id;
                     $.get("/promotedStudent/changeAuditState.do", {id: id}, function (data) {
                         if (data.success) {
                             $.messager.alert("提示信息", data.msg, "info", function () {
                                 promoted_datagrid.datagrid("reload");
                             })
                         } else {
                             $.messager.alert("提示信息", data.msg, "info");
                         }
                     })
                 }
             })*/
            //Sweet Alert弹窗效果
            swal({
                title: "温馨提示",
                text: "请问你要" + flag + "该操作吗",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    var id = promoted_datagrid.datagrid("getSelected").id;
                    //发送请求修改状态
                    var id = promoted_datagrid.datagrid("getSelected").id;
                    $.get("/promotedStudent/changeAuditState.do", {id: id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", data.msg, "success");
                            promoted_datagrid.datagrid("reload");
                        } else {
                            swal("操作失败！", data.msg, "error");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })

        },
        searchForEmp: function () {
            var afterClzId = $("input[name = 'afterClzId']").val();
            var beginDate = $("input[name = 'beginDate']").val();
            var endDate = $("input[name = 'endDate']").val();
            var keyword = $("input[name = 'keyword']").val();
            promoted_datagrid.datagrid("load", {
                afterClzId: afterClzId,
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword
            });
        },
        exportXls: function () {
            //xls导出
            window.location.href = "/promotedStudent/exportXls.do";
        }
    }

    //快查
    $($('#promoted_mb').menubutton('options').menu).menu({
        onClick: function (item) {
            console.debug(item.text);
            var auditState = {};
            if (item.text == "已审核") {
                auditState = 1;
            } else if (item.text == "未审核") {
                auditState = 0;
            } else if (item.text == "所有") {
            }
            $("#promoted_datagrid").datagrid("load", {
                auditState: auditState
            });
        }
    })

    //统一绑定事件
    $("[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        methodObject[cmd]();
    });

    //设置表的显示数据
    promoted_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: "/promotedStudent/query.do",
        pagination: true,
        singleSelect: true,
        toolbar: "#promoted_toolbar",
        rownumbers: true,
        nowrap: false,
        columns: [[
            // {field: 'cb', checkbox: true, checkOnSelect: true},
            // {field: 'id', title: '缂栧彿', width: 100, align: 'center'},
            {field: 'name', title: '姓名', width: 100, align: 'center'},
            {field: 'totalTuition', title: '总学费', width: 100, align: 'center'},
            {field: 'toPaidTuition', title: '需缴学费', width: 100, align: 'center'},
            {field: 'paidTuition', title: '已交学费', width: 100, align: 'center'},
            {field: 'promoteOrRepeatDate', title: '升班留级时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 130, align: 'center'},
            {
                field: 'classBefore',
                title: '之前班级',
                width: 150,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.className : "";
                }
            },
            {
                field: 'classAfter',
                title: '之后班级',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.className : "";
                }
            },
            {
                field: 'saleman', title: '营销人员', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.realname : "";
                }
            },
            {
                field: 'auditState',
                title: '审核状态',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? '<font color="green">已审核</font>' : '<font color="red">未审核</font>';
                }
            }
        ]],

        onSelect: function (index, data) {
            //点击事件 判定是怎样的审核状态
            var row = promoted_datagrid.datagrid("getSelected");
            if (row.auditState) {
                $("#btn_audit").linkbutton('disable');
                $("#btn_antiAudit").linkbutton('enable');
            } else {
                $('#btn_audit').linkbutton('enable');
                $("#btn_antiAudit").linkbutton('disable');
            }
            /* $.get("/formalStudent/selectByPrimaryKey.do", {id: data.formalStudent.id}, function (formalStu) {
                 if (formalStu.clz.id!=data.classAfter.id){
                     
                     $("#btn_antiAudit").linkbutton('disable');
                 }else {
                     console.debug(123);
                     $('#btn_antiAudit').linkbutton('enable');
                 }
             })
 
             if (data.auditState){
                 $("#btn_audit").linkbutton('disable');
             }else {
                 $('#btn_audit').linkbutton('enable');
             }
             
             var formalId = data.formalStudent.id;
             $.get("/promotedStudent/selectLeaStusByForId.do", {id: formalId}, function (data) {
                 $.each(data, function (index, value) {
                     if (!value.auditState) { //
                         $("#btn_antiAudit").linkbutton('disable');
                         return;
                     }
                 })
             })
             $('#btn_antiAudit').linkbutton('enable');
         */
        }
    });

    promoted_dialog.dialog({
        width: 260,
        height: 280,
        buttons: "#promoted_btns",
        closed: true
    });

    /*   $("#promoted_import").dialog({
           width: 400,
           height: 200,
           closed: true,
           title: ""
       });*/

    /*$('.fancybox').fancybox();*/

});

//体验方法
function search(value) {
    var deptId = $("input[name = 'deptId']").val();
    var beginDate = $("input[name = 'beginDate']").val();
    var endDate = $("input[name = 'endDate']").val();

    $("#promoted_datagrid").datagrid("load", {
        keyword: value,
        deptId: deptId,
        beginDate: beginDate,
        endDate: endDate
    });

}

