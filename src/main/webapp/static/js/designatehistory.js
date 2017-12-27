$(function () {
    $("#dh_datagrid").datagrid({
        fit: true,
        url: "/designatehistory/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        shadow: true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'assigntime', title: '移交时间', width: 100},
            {field: 'classId', title: '意向班级', width: 100,formatter:function (value,row,index) {
                return row.classId ? value.className : '' ;
            }},
            {field: 'intentiondegree', title: '意向程度', width: 100,formatter:function (value,row,index) {
                return row.intentiondegree ? value.name : '' ;
            }},
            {field: 'source', title: '原拥有者', width: 100,formatter:function (value,row,index) {
                return row.source ? value.realname : '' ;
            }},
            {field: 'target', title: '现拥有者', width: 100,formatter:function (value,row,index) {
                return row.target ? value.realname : '' ;
            }},
            {field: 'minute', title: '详情信息', width: 100},
        ]]
    });
    $("#srcId").combobox({
        url:'/employee/selectByRoleSn.do?sn=SALE',
        width:120,
        textField:'realname',
        valueField:'id',
        panelHeight:'auto',
        onSelect:function (record) {
            $("#dh_datagrid").datagrid("load",{
                srcId:record.id
            })
        }
    })
    $("#tarId").combobox({
        url:'/employee/selectByRoleSn.do?sn=SALE',
        width:120,
        textField:'realname',
        valueField:'id',
        panelHeight:'auto',
        onSelect:function (record) {
            $("#dh_datagrid").datagrid("load",{
                tarId:record.id
            })
        }
    })
});
function reload() {
    $("#srcId").combobox("clear");
    $("#tarId").combobox("clear");
    $("#dh_datagrid").datagrid("load",[]);
}
function exportXls() {
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
            //发送请求修改状态
            window.location.href = '/designatehistory/exportXls.do'
            swal("导出成功!", "", "success")
        } else {
            swal("已取消", "您已取消操作！", "error")
        }
    })

}