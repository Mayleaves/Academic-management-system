function preAdd(){
    $("#tid").val(0);
}
function saveTeacher(){
    $('#teaTable').bootstrapTable("destroy");
    var data=$("#formTeacher").serialize();
    var id=$("#tid").val();
    if(id < 1){
        $.ajax({
            url: "/webapi/teacher/insert",
            method:"post",
            data:data
        }).done(function (){
            loadTable();
            $('#myModal').modal('hide')
        })
    }else{
        $.ajax({
            url: "/webapi/teacher/update",
            method:"put",
            data:data
        }).done(function (){
            loadTable();
            $('#myModal').modal('hide')
        })
    }
}
function deleteTeacher(id){
    $('#teaTable').bootstrapTable("destroy");
    if(confirm("是否要删除序号为"+id+"的教师？")){
        $.ajax({
            url:"/webapi/teacher/delete/"+id,
            method:'delete'
        }).done(function (){
            loadTable();
        });
    }else{
        loadTable();
    }
}
function editTeacher(id){
    $('#myModal').modal('show');
    $.ajax({
        url:'/webapi/teacher/get/'+id
    }).done(function (rs){
        $("#tid").val(rs.tid);
        $("#tname").val(rs.tname);
        $("#tno").val(rs.tno);
        $("#tsex").val(rs.tsex);
        $("#tage").val(rs.tage);
        $("#tsalary").val(rs.tsalary);
        $("#tpassword").val("");
    })
}
function searchTeacherName(){
    $('#teaTable').bootstrapTable("destroy");
    loadTable();
}
function loadTable(){
    $('#teaTable').bootstrapTable({
        url:'/webapi/teacher/getbypage',
        striped:true,
        pagination:true,
        singleSelect: false,
        pageNumber:1,
        sidePagination: "server",
        pageList: [5, 10, 15],
        queryParams:function (params){
            var paraObj;
            if($("#option").val()=="tname"){
                paraObj={
                    size: params.limit,
                    page:params.offset/params.limit,
                    sort:"tid",
                    order:"desc",

                    tname:$("#searchName").val(),
                    tno:""
                }
            }else{
                paraObj={
                    size: params.limit,
                    page:params.offset/params.limit,
                    sort:"tid",
                    order:"desc",

                    tname:"",
                    tno:$("#searchName").val()
                }
            }
            return paraObj;
        },
        columns: [{
            field: 'tid',
            title: '序号',
            width: 70
        }, {
            field: 'tname',
            title: '姓名'
        }, {
            field: 'tno',
            title: '教师号'
        }, {
            field: 'tsex',
            title: '性别',
            formatter: function(name) {return name == 1 ? "男" : "女";}
        }, {
            field: 'tage',
            title: '年龄'
        }, {
            field: 'tsalary',
            title: '工资'
        }, {
            field: 'tpassword',
            title: '密码'
        }, {
            field: 'tid',
            title: '操作',
            width: 250,
            formatter: option
        },],
        responseHandler: function(res){
            return{
                "total":res.data.total,
                "rows":res.data.rows
            };
        }
    });
    function option(value, row, index) {
        var htm = "";
        htm += '<button class="btn btn-primary" onclick="editTeacher('+value+')">编辑</button>'
            +' '
            +'<button class="btn btn-primary" onclick="deleteTeacher('+value+')">删除</button>'
        return htm;
    }
}
$(function (){
    loadTable();
})