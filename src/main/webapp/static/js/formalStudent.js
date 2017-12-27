jQuery.ajax.traditional = false;
$(function () {
    //初始化按钮
    var formalStudent_form = $("#formalStudent_form");
    var formalStudent_transfer_form = $("#formalStudent_transfer_form");
    var formalStudent_leaving_form = $("#formalStudent_leaving_form");
    var formalStudent_check_form = $("#formalStudent_check_form");
    var formalStudent_dialog = $("#formalStudent_dialog");
    var formalStudent_transfer_dialog = $("#formalStudent_transfer_dialog");
    var formalStudent_leaving_dialog = $("#formalStudent_leaving_dialog");
    var formalStudent_check_dialog = $("#formalStudent_check_dialog");
    var formalStudent_datagrid = $("#formalStudent_datagrid");
    var btn_suspend = $("#btn_suspend");
    var btn_transfer = $("#btn_transfer");
    var btn_leave = $("#btn_leave");
    var btn_eidt = $("#btn_eidt");
    var see_save_btn = $("#see_save_btn");


    //方法提供
    var methodObject = {
        edit: function () {

            var formalStudents = formalStudent_datagrid.datagrid("getSelections");

            if (formalStudents.length != 1) {
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
            formalStudent_form.form("clear");
            formalStudent_dialog.dialog("setTitle", "编辑学员");

            //formalStudent_dialog.dialog("open");
            var formalStudent = formalStudents[0];
            //回显数据
            if (formalStudent.clz) {
                formalStudent["clz.id"] = formalStudent.clz.id;
            }
            if (formalStudent.saleman) {
                formalStudent["saleman.id"] = formalStudent.saleman.id;
            }
            if (formalStudent.foreignLangLevel) {
                formalStudent["foreignLangLevel.id"] = formalStudent.foreignLangLevel.id;
            }
            if (formalStudent.education) {
                formalStudent["education.id"] = formalStudent.education.id;
            }
            if (formalStudent.clientType) {
                formalStudent["clientType.id"] = formalStudent.clientType.id;
            }
            if (formalStudent.source) {
                formalStudent["source.id"] = formalStudent.source.id;
            }
            if (formalStudent.campus) {
                formalStudent["campus.id"] = formalStudent.campus.id;
            }
            formalStudent_form.form("load", formalStudent);

            formalStudent_dialog.dialog("open");
        },
        save: function () {
            formalStudent_form.form("submit", {
                url: "/formalStudent/saveOrUpdate.do",
                /*onSubmit: function (param) {
                 var ids = $("#formalStudent_roles").combobox("getValues");
                 $.each(ids, function (index, item) {
                 // console.log(item);
                 param["roles[" + index + "].id"] = item;
                 });
                 console.log(param);
                 return $("#formalStudent_form").form("validate");
                 },*/
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {

                        swal("保存成功！", data.msg, "success");
                        formalStudent_dialog.dialog("close");
                        formalStudent_datagrid.datagrid("load");

                    } else {
                        swal("保存失败", data.msg, "error")
                        // $.messager.alert("提示信息", data.msg, "info");
                    }
                }
            })
        },
        check: function () {
            var formalStudents = formalStudent_datagrid.datagrid("getSelections");

            if (formalStudents.length != 1) {
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
            formalStudent_form.form("clear");
            formalStudent_dialog.dialog("setTitle", "编辑学员");

            var formalStudent = formalStudents[0];
            //回显数据
            if (formalStudent.clz) {
                formalStudent["clz.id"] = formalStudent.clz.id;
            }
            if (formalStudent.saleman) {
                formalStudent["saleman.id"] = formalStudent.saleman.id;
            }
            if (formalStudent.foreignLangLevel) {
                formalStudent["foreignLangLevel.id"] = formalStudent.foreignLangLevel.id;
            }
            if (formalStudent.education) {
                formalStudent["education.id"] = formalStudent.education.id;
            }
            if (formalStudent.clientType) {
                formalStudent["clientType.id"] = formalStudent.clientType.id;
            }
            if (formalStudent.payMode) {
                formalStudent["payMode.id"] = formalStudent.payMode.id;
            }
            if (formalStudent.source) {
                formalStudent["source.id"] = formalStudent.source.id;
            }
            if (formalStudent.campus) {
                formalStudent["campus.id"] = formalStudent.campus.id;
            }
            formalStudent_form.form("load", formalStudent);

            formalStudent_dialog.dialog("open");

            see_save_btn.hide();

        },
        leave: function () { //流失
            var formalStudents = formalStudent_datagrid.datagrid("getSelections");
            if (formalStudents.length != 1) {
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

            var formalStudent = formalStudents[0];
            formalStudent_leaving_form.form("clear");
            formalStudent_leaving_dialog.dialog("setTitle", "流失学员");

            formalStudent_leaving_dialog.form("load", formalStudent);
            formalStudent_leaving_dialog.dialog("open");
            //
        },

        save_leaving: function () { // 流失按钮方法

            var row = formalStudent_datagrid.datagrid("getSelected");

            formalStudent_leaving_form.form("submit", {
                url: '/formalStudent/leavingStudent.do',
                onSubmit: function (param) {

                        //还有2个字段在后台已经封装
                        param["formalStuId"]=row.id;
                        param["fsid"]=row.id;
                        param["tel"]=row.tel;
                        param["name"]=row.name;
                        param["sale.id"]=row.saleman.id;
                        param["sale.realname"]=row.saleman.realname;


                },
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        swal("操作成功！", data.msg, "success");
                        formalStudent_leaving_dialog.dialog("close");
                        formalStudent_datagrid.datagrid("reload");

                    } else {
                        //$.messager.alert("提示信息", data.msg, "info");
                        swal("操作失败", data.msg, "error");
                    }
                }
            });
        },
        cancel_leaving: function () {
            formalStudent_leaving_dialog.dialog("close");
        },
        cancel: function () {
            formalStudent_dialog.dialog("close");
        },
        cancel_transfer: function () {
            formalStudent_transfer_dialog.dialog("close");
        },
        save_transfer: function () { //转班
            var rows = formalStudent_datagrid.datagrid("getSelections");
            //获取所有id
            var ids = $.map(rows,function (item) {
                return item.id;
            })
            console.log(ids);

            formalStudent_transfer_form.form("submit", {
                url: '/promotedStudent/transStudent.do',
                onSubmit: function (param) {
                    /*var stuids = $("#transfer_stuId").val();
                     var stuids_arr = stuids.split(',');
                     console.debug(stuids_arr);
                     param["ids_arr"] = stuids_arr;*/
                    //param["ids"] = ids;
                    for (var i = 0; i < ids.length; i++) {
                     param["ids["+i+"]"]=ids[i];
                    }
                },
                success: function (data) {
                    var data = $.parseJSON(data);
                    //alert(data.msg);
                    if (data.success) {
                        swal("操作成功！", data.msg, "success");
                        formalStudent_transfer_dialog.dialog("close");
                        formalStudent_datagrid.datagrid("reload");
                    } else {
                        swal("操作失败", data.msg, "error");
                    }
                }
            });

        },
        reload: function () {
            formalStudent_datagrid.datagrid("reload");
        },
        delete: function () {

            var formalStudent = formalStudent_datagrid.datagrid("getSelections");
            if (formalStudent.length != 1) {
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

            $.messager.confirm("提示信息", "确认执行该操作吗??", function (r) {
                if (r) {
                    var id = formalStudent_datagrid.datagrid("getSelected").id;
                    $.get("/formalStudent/delete.do", {id: formalStudent.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", data.msg, "success");
                            formalStudent_datagrid.datagrid("reload");

                        } else {
                            swal("操作失败", data.msg, "error");
                            //$.messager.alert("提示信息", data.msg, "info");
                        }
                    })
                }
            })
        },

        suspend: function () { //休学
            var formalStudents = formalStudent_datagrid.datagrid("getSelections");

            if (formalStudents.length != 1) {
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
            //由于是多选,所用fomalStudent获得第一个
            var formalStudent = formalStudents[0];
            var flag = formalStudent.state == 0 ? "复学" : "休学";
            var statVal = flag == "复学" ? 1 : 0;

            /* $.messager.confirm("提示信息", "是否对该学员做" + flag + "处理?", function (r) {
                 if (r) {
                     var id = formalStudent_datagrid.datagrid("getSelected").id;
                     $.get("/formalStudent/changeState.do", {id: id, statVal: statVal}, function (data) {
                         if (data.success) {
                             $.messager.alert("提示信息", data.msg, "info", function () {
                                 formalStudent_datagrid.datagrid("reload");
                             })
                         } else {
                             $.messager.alert("提示信息", data.msg, "info");
                         }
                     })
                 }
             })*/

            swal({
                title: "您确定要执行该操作吗?",
                text: "是否对该学员做" + flag + "处理?",
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
                    var id = formalStudent_datagrid.datagrid("getSelected").id;
                    $.get("/formalStudent/changeState.do", {id: id, statVal: statVal}, function (data) {
                        if (data.success) {
                            swal("操作成功！", data.msg, "success");
                            formalStudent_datagrid.datagrid("reload");
                        } else {
                            swal("操作失败", data.msg, "error");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })
        },

        transfer: function () { //转班
            var formalStudents = formalStudent_datagrid.datagrid("getSelections");
            if (!formalStudents) {
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
            var selectStuIds = $.map(formalStudents, function (item, index) {
                return item.id;
            })
            //集体转班
            if (formalStudents.length > 1) {
                // 获得学生id
                var selectClzIds = $.map(formalStudents, function (item, index) {
                    return item.clz.id;
                })
                for (var index in selectClzIds) {
                    if (index > 0) {
                        if (selectClzIds[index] != selectClzIds[index - 1]) { //
                            // $.messager.alert('提示信息', '请不要批量对不同班级学生做转班', 'info');
                            swal("操作失败", '请不要批量对不同班级学生做转班', "error");
                            return;
                        }
                    }
                }
                for (var i in formalStudents) {
                    // console.debug(formalStudents[i].state);
                    if (formalStudents[i].state != 1) {
                        //$.messager.alert('提示信息', '请确认所有学生都在上学状态', 'info');
                        swal("操作失败", '请确认所有学生都在上学状态', "error");
                        return;
                    }
                }
            }
            formalStudent_transfer_form.form("clear");
            formalStudent_transfer_dialog.dialog("setTitle", "转班");

            var formalStudent = formalStudents[0];

            //回显
            formalStudent_transfer_form.form("load", {
                'clz.id': formalStudent.clz.id,
                'ids': selectStuIds
            });
            formalStudent_transfer_dialog.dialog("open");
        },
        exportXls: function () {
            //xls导出
            window.location.href = "/formalStudent/exportXls.do";
           /* //$.messager.alert('提示信息', '三四十个字段,还要加判断,疯狂粘贴的,手抽筋了,这个功能割了', 'info');
            swal("吐槽信息", '三四十个字段,还要加判断,疯狂粘贴的,手抽筋了,这个功能割了', "warning");*/
        },
        searchForKey: function () {
            // 查询
            var clzId = $("input[name = 'clzId']").val();
            var beginDate = $("input[name = 'beginDate']").val();
            var endDate = $("input[name = 'endDate']").val();
            var keyword = $("input[name = 'keyword']").val();

            $("#formalStudent_datagrid").datagrid("load", {
                keyword: keyword,
                clzId: clzId,
                beginDate: beginDate,
                endDate: endDate
            });
        }
    };

    //绑定事件
    $("a[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        methodObject[cmd]();
    });

    //快查
    $($('#mb').menubutton('options').menu).menu({
        onClick: function (item) {
            //console.debug(item.text);
            var paidState = {};
            var stateVal = {};
            var clientType = {};
            if (item.text == "未付") {
                paidState = 0;
            } else if (item.text == "已付") {
                paidState = 1;
            } else if (item.text == "休学") {
                stateVal = 0;
            } else if (item.text == "在学中") {
                stateVal = 1;
            } else if (item.text == "线上") {
                clientType = 43;
            } else if (item.text == "线下") {
                clientType = 44;
            } else if (item.text == "所有") {
                //什么参数都不加
            }
            $("#formalStudent_datagrid").datagrid("load", {//load带参数
                paidState: paidState,
                state: stateVal,
                clientType: clientType
            });
        }
    })

    //初始化
    formalStudent_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: "/formalStudent/query.do",
        pagination: true,
        // singleSelect: true,
        rownumbers: true,
        toolbar: "#formalStudent_toolbar",
        columns: [[
            {field: 'cb', checkbox: true, checkOnSelect: true},
            {field: 'name', title: '姓名', width: 100, align: 'center'},
            {
                field: 'saleman', title: '销售人员', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.realname : "";
            }
            },
            {field: 'totalTuition', title: '总学费', width: 100, align: 'center'},
            {field: 'ownTuition', title: '需缴学费', width: 100, align: 'center'},
            {field: 'paidTuition', title: '已缴学费', width: 100, align: 'center'},
            {
                field: 'paidState',
                title: '是否已付',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? '<font color=green>是</font>' : '<font color=red>否</font>';
                }
            },
            {field: 'enrollDate', title: '入学时间', width: 150, align: 'center'},
            {field: 'school', title: '学校', width: 150, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {
                field: 'clz',
                title: '入学班级',
                width: 150,
                align: 'center',
                formatter: function (value, row, index) {
                    return row.clz ? value.className : "";
                }
            },
            {
                field: 'payMode',
                title: '付款方式',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {
                field: 'clientType',
                title: '客户类型',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                if (value == 0) {
                    return '<font color=red>休学</font>';
                } else if (value == 1) {
                    return '<font color=green>正常</font>';
                } else if (value == 2) {
                    return '<font color=#ff1493>转班</font>';
                } else if (value == 3) {
                    return '<font color=orange>流失中</font>';
                }
            }
            }
        ]],
        onSelect: function (index, data) {

            var row = formalStudent_datagrid.datagrid("getSelected");
            //判断该按钮是否可用
            if (row.state != 1) {
                btn_leave.linkbutton("disable");
                btn_transfer.linkbutton("disable");
                btn_suspend.linkbutton("disable");
                btn_eidt.linkbutton("disable");
            } else {
                btn_transfer.linkbutton("enable");
                btn_leave.linkbutton("enable");
                btn_suspend.linkbutton("enable");
                btn_eidt.linkbutton("enable");
            }
            //启用
            if (row.state == 0) {
                btn_suspend.linkbutton("enable");
            }

            var selections = formalStudent_datagrid.datagrid("getSelections");

            if (selections.length > 1) {

                //处理bug
                var stateIds = $.map(selections, function (item) {
                    return item.state;
                })
                for (var i = 0; i < stateIds.length; i++) {
                    if (stateIds[i] != 1) {
                        $.messager.alert('提示信息', '请确认所有学生都在上学状态', 'info');
                        return;
                    }
                }
                btn_transfer.linkbutton("enable");
                btn_transfer.linkbutton({
                    text: "批量转班"
                });
            }
            if (data.state == 1) {
                btn_suspend.linkbutton({
                    text: "休学"
                });
            } else if (data.state == 0) {
                //btn_changeState.linkbutton("enable");
                btn_suspend.linkbutton({
                    text: "复学"
                });
            }
            // 批量记录班级id
            /*$("#afterClass").combobox({
                url: '/classGrade/selectClzsExceptId.do?classId=' + data.clz.id,
                valueField: 'id',
                textField: 'name',
                panelHeight: 'auto'
            })*/
        },
        onUnselect: function (index, data) { // 未选中是的状态
            var selections = formalStudent_datagrid.datagrid("getSelections");
            if (selections.length = 1) {
                btn_transfer.linkbutton("enable");
                btn_eidt.linkbutton("enable");
                btn_transfer.linkbutton({
                    text: "转班"
                });
            }
        }
    });

    //弹出框大小
    formalStudent_dialog.dialog({
        width: '700',
        height: '500',
        buttons: "#formalStudent_btns",
        closed: true,
        onBeforeClose: function () {
            //按钮设为可看
            see_save_btn.show();
        }
    });
    formalStudent_transfer_dialog.dialog({
        width: 240,
        height: 170,
        buttons: "#formalStudent_transfer_btns",
        closed: true
    });
    formalStudent_leaving_dialog.dialog({
        width: 300,
        height: 300,
        buttons: "#formalStudent_leaving_btns",
        closed: true
    });

    /*   $("#formalStudent_import").dialog({
           width: 400,
           height: 200,
           closed: true,
           title: "鏂囦欢涓婁紶"
       })*/

    $("[name='isMoneyBack']").change(function () {
        if ($(this).val() == 0) {
            $('#drawBackAmount_tr').hide();
        } else {
            $('#drawBackAmount_tr').show();
        }
    });

});





