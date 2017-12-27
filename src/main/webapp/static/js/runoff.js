$(function () {
    var objMethod = {
        add:function () {
            $("#rf_form").form("clear");
            $("#rf_dialog").dialog("setTitle", "添加流失");
            $("#com1").combobox("setValue",0);
            $("#com2").combobox("setValue",0);
            $("#com3").combobox("setValue",0);
            $("#rf_dialog").dialog("open");
        },
        update:function () {
            var row = $("#rf_datagrid").datagrid("getSelected");
            $("#rf_dialog").dialog("setTitle", "编辑流失");
            $("#rf_form").form("clear");
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
            //console.log(row)
            if (row.sale!=null){
                row["sale.id"] = row.sale.id;
            }
            if (row.auditor!=null){
                row["auditor.id"] = row.auditor.id;
            }
            console.log(row);
            $("#rf_form").form("load",row);
            $("#com1").combobox("setValue",0);
            $("#com2").combobox("setValue",0);
            $("#rf_dialog").dialog("open");
        },
        reload:function () {
            $("#rf_datagrid").datagrid("load",[]);
        },
        save:function(){
            $("#rf_form").form("submit",{
                url: "/runoff/saveOrUpdate.do",
                success: function (data) {
                    console.log(data);
                    data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal("保存成功!", "", "success")
                        $("#rf_dialog").dialog("close");
                        $("#rf_datagrid").datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败", "请检查输入数据是否有误！", "error")
                    }
                }
            })
        },
        search: function () {
            var row = $("#rf_datagrid").datagrid("getSelected");
            $("#rf_form").form("clear");
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
             //console.log(row)
             if (row.sale!=null){
                 row["sale.id"] = row.sale.id;
             }
             if (row.auditor!=null){
                 row["auditor.id"] = row.auditor.id;
             }
            $("#rf_form").form("load",row);
            $("#rf_dialog").dialog("open");
        },
        cancel:function () {
            $("#rf_dialog").dialog("close");
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    $("#rf_dialog").dialog({
        width: 240,
        height: 360,
        closed: true,
        buttons: "#rf_buttons"
    })
    $("#rf_datagrid").datagrid({
        fit: true,
        url: "/runoff/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers : true,
        singleSelect: true,
        shadow:true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'backMoney', title: '流失金额', width: 100},
            {field: 'livedate', title: '流失时间', width: 100},
            {
                field: 'state', title: '是否退款', width: 100, formatter: function (value, row, index) {
                return value ? '<font color="red">已退款</font>': '未退款'
            }} ,
            {field: 'sale', title: '营销人员', width: 100, formatter: function (value, row, index) {
                return row.sale ? value.realname : ''
            }},
            {field: 'classId', title: '流失班级', width: 100,formatter:function (value,row,index) {
                return value ? '基础班' : '大神班' ;
            }},
            {
                field: 'audit', title: '审核状态', width: 100, formatter: function (value, row, index) {
                return value ?  '<font color="#00ff7f">已审核</font>': '待审核'
            }} ,
            {field: 'cause', title: '流失原因', width: 100}
        ]]
    });
})