$(function () {
    var st_datagrid = $("#st_datagrid");
    var st_dialog = $("#st_dialog");
    var st_form = $("#st_form");
    var objMethod = {
        add: function () {
            st_form.form("clear");
            st_dialog.dialog("open");
        },
        edit: function () {
            var row = st_datagrid.datagrid("getSelected");
            st_form.form("clear");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");

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
            $("#st_form input").prop("disabled",false);
            $("#saveOrUpdate").linkbutton("enable");
            st_dialog.dialog("setTitle", "编辑学员跟踪");
            /*if (row.dept!=null){
                    row["dept.id"] = row.dept.id;
            }*/
            if (row.sale!=null){
                row["sale.id"] = row.sale.id;
            }
            if (row.university!=null){
                row["university.id"] = row.university.id;
            }
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            if (row.intentiondegree!=null){
                row["intentiondegree.id"] = row.intentiondegree.id;
            }
            if (row.goal!=null){
                row["goal.id"] = row.goal.id;
            }
            if (row.exchange!=null){
                row["exchange.id"] = row.exchange.id;
            }
            st_form.form("load", row);
            st_dialog.dialog("open");
        },
        //审核功能
        changState: function () {
            var row = st_datagrid.datagrid("getSelected");
            if (!row) {
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
            if (row.state) {
                //$.messager.alert('温馨提示', '不能重复审核', "warning");
                swal("操作失败！", '不能重复审核', "error");
                return;
            }
            $("#audio_form").form("clear");
            if (row.exchange!=null){
                row["exchange.id"] = row.exchange.id;
            }
            if (row.sale!=null){
                row["sale.id"] = row.sale.id;
            }
            $("#audio_form").form("load",row);
            $("#audio_dialog").dialog("open");
        },
        reload: function () {
            st_datagrid.datagrid("load",[])
        },
        cancel: function () {
            st_dialog.dialog("close");
        },
        audioCancel:function () {
            $("#audio_dialog").dialog("close");
        },
        check:function () {
            var row = st_datagrid.datagrid("getSelected");
            if (!row) {
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
            st_dialog.dialog("setTitle", "查看潜在学员");
            if (row.sale!=null){
                row["sale.id"] = row.sale.id;
            }
            if (row.university!=null){
                row["university.id"] = row.university.id;
            }
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            if (row.intentiondegree!=null){
                row["intentiondegree.id"] = row.intentiondegree.id;
            }
            if (row.goal!=null){
                row["goal.id"] = row.goal.id;
            }
            if (row.exchange!=null){
                row["exchange.id"] = row.exchange.id;
            }
            st_form.form("load", row);
            $("#st_form input").prop("disabled",true);
            $("#saveOrUpdate").linkbutton("disable");
            st_dialog.dialog("open");
        },
        //确定审核
        audio:function () {
            $("#audio_form").form("submit", {
                url: "/studenttail/changState.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        swal("审核成功！", '保存成功', "success");
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        $("#audio_dialog").dialog("close");
                        st_datagrid.datagrid("reload");
                    } else {
                        swal("审核失败！", data.msg, "error");
                        //$.messager.alert('温馨提示', data.msg, "error");
                    }
                }
            });
        },
        saveOrUpdate: function () {
            st_form.form("submit", {
                url: "/studenttail/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal("保存成功！", "", "success");
                        st_dialog.dialog("close");
                        st_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败", "请检查数据是否有误！", "error")
                    }
                }
            });
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    st_datagrid.datagrid({
        fit: true,
        url: "/studenttail/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers : true,
        singleSelect: true,
        shadow:true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'name', title: '客户名称', width: 100},
            {field: 'tailnum', title: '跟踪次数', width: 100},
            {field: 'sale', title: '营销人员', width: 100, formatter: function (value, row, index) {
                return row.sale ? value.realname : ''
            }},
            {field: 'thistime', title: '本次跟进时间', width: 100},
            {field: 'followtime', title: '下次跟进时间', width: 100},
            {field: 'goal', title: '跟进目的', width: 100,formatter:function (value,row,index) {
                return row.goal ? value.name : '' ;
            }},
            {field: 'exchange', title: '交流方式', width: 100,formatter:function (value,row,index) {
                return row.exchange ? value.name : '' ;
            }},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'intentiondegree', title: '意向程度', width: 100,formatter:function (value,row,index) {
                return row.intentiondegree ? value.name : '' ;
            }},
            {
                field: 'state', title: '审核状态', width: 100, formatter: function (value, row, index) {
                return value ?  "<font color='red'>已审核</font>": "未审核"
            }
            },
            {field: 'audioex', title: '审核说明', width: 100},
            {field: 'appraise', title: '审核评价', width: 100,formatter:function (value,row,index) {
                return row.appraise ? value.name : '' ;
            }}
        ]]
    });
    st_dialog.dialog({
        //初始化弹出框
        title: '编辑客户跟踪',
        width: 400,
        height: 465,
        closed: true,
        buttons: "#st_buttons",

    });
    $("#audio_dialog").dialog({
        title: '审核',
        width: 240,
        height: 280,
        closed: true,
        buttons: "#audio_buttons"
    })
    $("#pid").combobox({
        url:'/potentialstudent/checkstu.do',
        width:120,
        textField:'name',
        valueField:'id',
        panelHeight:'auto',
        onSelect:function (record) {
            console.log(record);
            st_datagrid.datagrid("load",{pid:record.id});
        }
    })
});
function lasttime() {
    $("#st_datagrid").datagrid("load",{
        lasttime:"true"
    })
}