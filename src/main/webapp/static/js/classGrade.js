$(function () {
    var classGrade_datagrid = $("#classGrade_datagrid");
    var classGrade_dialog = $("#classGrade_dialog");
    var classGrade_form = $("#classGrade_form");
    var objMethod = {
        add: function () {
            classGrade_dialog.dialog("setTitle", "添加班级");
            classGrade_form.form("clear");
            classGrade_dialog.dialog("open");
        },
        edit: function () {
            var row = classGrade_datagrid.datagrid("getSelected");
            classGrade_form.form("clear");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");

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
          /*  $.get("/classGrade/selectRoleId.do",{id:row.id},function (data) {
               $("#role_id").combobox("setValues",data);
            });*/
            classGrade_dialog.dialog("setTitle", "编辑班级");
            if (row.classroom!=null){
                row["classroom.id"] = row.classroom.id;
            }
            if (row.systemDictionaryItem != null) {
                row["systemDictionaryItem.id"] = row.systemDictionaryItem.id;
            }
            classGrade_form.form("load", row);
            classGrade_dialog.dialog("open");
        },


        openClass:function () {
            var row = classGrade_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一个班级!', "warning");
                swal("请选择一个班级!", "", "warning");

                return;
            }

            /*$.get("/classGrade/openClass.do", {id: row.id}, function (data) {
                if (data.success) {
                    //$.messager.alert('温馨提示', '已开班!', 'info',function () {
                        swal("开班成功!", "", "success");
                        classGrade_datagrid.datagrid("reload");
                   // });
                } else {
                    $.messager.alert('温馨提示', data.msg, "error");
                }
            })*/

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
                    //发送请求修改状态
                    $.get("/classGrade/openClass.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("开班成功!", "", "success");
                            classGrade_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })







        },
        distribute: function () {
            var row = classGrade_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");

                swal("请选择一条数据", "", "warning")
               /* swal({
                    title: "请选择一条数据",
                    //text: "删除后将无法恢复，请谨慎操作！",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });*/

                return;
            }
            $("#distribute_dialog").dialog("open");
        },

        remove: function () {
            var row = classGrade_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");
                swal("请选择一条数据", "", "warning")
                return;
            }

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
                    $.get("/classGrade/remove.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("删除成功！", "", "success");
                                classGrade_datagrid.datagrid("reload");
                            
                        } else {
                            swal('温馨提示', data.msg, "error");
                        }
                    })
                } else {
                    swal("已取消", "", "error");
                }
            });
         /* $.messager.confirm("温馨提示","你确定要删除吗?",function (r) {
              if (r){
                  $.get("/classGrade/remove.do", {id: row.id}, function (data) {
                      if (data.success) {
                          $.messager.alert('温馨提示', '删除成功!', 'info',function () {
                              classGrade_datagrid.datagrid("reload");
                          });
                      } else {
                          $.messager.alert('温馨提示', data.msg, "error");
                      }
                  })
              }
          })*/
        },
        reload: function () {
            $("[data-cmd=openClass]").linkbutton("enable");
            classGrade_datagrid.datagrid("load")
        },
        cancel: function () {
            classGrade_dialog.dialog("close");
        },
        saveOrUpdate: function () {
            var row = classGrade_datagrid.datagrid("getSelected");
            classGrade_form.form("submit", {
                url: "/classGrade/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        swal("保存成功！", "", "success");
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        classGrade_dialog.dialog("close");
                        classGrade_datagrid.datagrid("reload");
                    } else {
                        swal('温馨提示', data.msg, "error");
                        //$.messager.alert('温馨提示', data.msg, "error");
                    }
                },
                onSubmit:function (param) {
                    param["leader.id"]=row.leader.id;
                }
            });
        },
        saveDis:function () {
            $("#saveDisForm").form("submit", {
                url: '/classGrade/saveDis.do',
                onSubmit: function (param) {
                    var row = classGrade_datagrid.datagrid("getSelected");
                    param.classId = row.id;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        swal('温馨提示', 分配成功, "success");
                       // $.messager.alert('温馨提示', '分配成功', "info",function () {

                            $("#distribute_dialog").dialog("close");
                            classGrade_datagrid.datagrid("reload");
                        //});
                    } else {
                        swal('温馨提示', data.msg, "error");
                       // $.messager.alert('温馨提示', data.msg, "error");
                    }
                }
            })
        },
        cancelDis:function () {
            $("#distribute_dialog").dialog("close");
        },
        submitCourse:function () {

          /* $.messager.confirm("温馨提示","您确定要上传吗?",function (r) {
                if(r){
                    $("#import_form").form("submit",{
                        url:"/classGrade/importCourse.do",
                        success:function (data) {
                            data = $.parseJSON(data);
                            if (data.success){
                                //$.messager.alert("温馨提示",'上传成功!',"info",function () {
                                    swal("上传成功！", "", "success");
                                    $("#import_dialog").dialog("close");
                                //});

                            }else {
                                //$.messager.alert("温馨提示",data.msg,"error");
                                swal("上传失败!", "", "error");
                            }
                        }
                    });
                }
            })*/
            swal({
                title: "您确定要执行上传操作吗?",
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
                    $("#import_form").form("submit",{
                        url:"/classGrade/importCourse.do",
                        success:function (data) {
                            data = $.parseJSON(data);
                            if (data.success){
                                //$.messager.alert("温馨提示",'上传成功!',"info",function () {
                                swal("上传成功！", "", "success");
                                $("#import_dialog").dialog("close");
                                //});
                            }else {
                                //$.messager.alert("温馨提示",data.msg,"error");
                                swal("上传失败!", data.msg, "error");
                            }
                        }
                    });
                } else {
                    swal("已取消", "", "error");
                }
            });
        },
        importCourse:function () {
            var row = classGrade_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");
                swal("请选择一行数据！", "", "warning");
                return;
            }
            $("#import_dialog").dialog("open");
        }

    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    classGrade_datagrid.datagrid({
        fit: true,
        url: "/classGrade/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers : true,
        singleSelect: true,
        shadow:true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'className', title: '班级名', width: 100},
            {field: 'studentCount', title: '学生数量', width: 100},
            {
                field: 'state', title: '班级状态', width: 100, formatter: function (value, row, index) {
                return value ? "已开班" : '<font color="red">未开班</font>';
            }
            },
            {
                field: 'systemDictionaryItem', title: '所属学院', width: 100, formatter: function (value, row, index) {
                return value ? value.name : '';
            }
            },
            {field: 'classroom', title: '所在教室', width: 100 ,formatter: function (value, row, index) {
                return value ? value.name : '';
            }},
            {
                field: 'leader', title: '班主任', width: 100, formatter: function (value, row, index) {
                return value ? value.realname : '<font color=\'red\'>尚未分配班主任</font>'
            }
            }
        ]],
        onClickRow:function (index,row) {
            if (row.leader != null){
                $("[data-cmd=distribute]").linkbutton({
                    text:"更换班主任"
                });
            }else {
                $("[data-cmd=distribute]").linkbutton({
                    text:"分配班主任"
                });
            }
            if(row.state){
                $("[data-cmd=openClass]").linkbutton("disable");
            }else {
                $("[data-cmd=openClass]").linkbutton("enable");
            }
        }
    });
    $("#distribute_dialog").dialog({
        title: '分配班主任',
        width: 200,
        height: 100,
        modal:true,
        closed: true,
        modal:true,
        buttons: "#distribute_buttons"
    })
    $("#import_dialog").dialog({
        title: '上传课程表',
        width: 195,
        height: 100,
        modal:true,
        closed: true,
        modal:true,
        buttons: "#import_buttons"
    })
    classGrade_dialog.dialog({
        //初始化弹出框
        title: '添加班级',
        width: 240,
        height: 235,
        modal:true,
        closed: true,
        modal:true,
        buttons: "#classGrade_buttons"
    });
});
