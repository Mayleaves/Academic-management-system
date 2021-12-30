function preAdd(){
    $("#cid").val(0);
}
function saveCourse(){
    $('#couTable').bootstrapTable("destroy");
    var data=$("#formCourse").serialize();
    var id=$("#cid").val();
    if(id < 1){//增加
        $.ajax({
            url: "/webapi/course/insert",
            method:"post",
            data:data
        }).done(function (){
            loadTable();
            $('#myModal').modal('hide')
        })
    }else{//修改
        $.ajax({
            url: "/webapi/course/update",
            method:"put",
            data:data
        }).done(function (){
            loadTable();
            $('#myModal').modal('hide')
        })
    }
}
function deleteCourse(id){
    $('#couTable').bootstrapTable("destroy");
    if(confirm("是否要删除序号为"+id+"的课程？")){
        $.ajax({
            url:"/webapi/course/delete/"+id,
            method:'delete'
        }).done(function (){
            loadTable();
        });
    }else{
        loadTable();
    }
}
function editCourse(id){
    $('#myModal').modal('show');
    $.ajax({
        url:'/webapi/course/get/'+id
    }).done(function (rs){
        $("#cid").val(rs.cid);
        $("#cname").val(rs.cname);
        $("#cno").val(rs.cno);
        $("#ctime").val(rs.ctime);
        $("#ccredit").val(rs.ccredit);
        $("#tno").val(rs.tno);
        $("#esite").val(rs.esite);
    })
}
function searchCourseName(){
    $('#couTable').bootstrapTable("destroy");
    loadTable();
}
function loadTable(){
    $('#couTable').bootstrapTable({
        url:'/webapi/course/getbypage',
        striped:true,
        pagination:true,
        singleSelect: false,
        pageSize:5,
        pageNumber:1,
        sidePagination: "server",
        pageList: [5, 10, 15],
        queryParams:function (params){
             var paraObj;
            if($("#option").val()=="cname"){
                paraObj={
                    size: params.limit,
                    page:params.offset/params.limit,
                    sort:"cid",
                    order:"desc",

                    cname:$("#searchName").val(),
                    cno:""
                }
            }else{
                paraObj={
                    size: params.limit,
                    page:params.offset/params.limit,
                    sort:"cid",
                    order:"desc",

                    cname:"",
                    cno:$("#searchName").val()
                }
            }
            return paraObj;
        },
        columns: [{
            field: 'cid',
            title: '序号',
            width: 70
        }, {
            field: 'cname',
            title: '课程名'
        }, {
            field: 'cno',
            title: '课程号'
        }, {
            field: 'ctime',
            title: '课时',
        }, {
            field: 'ccredit',
            title: '学分'
        }, {
            field: 'onsite',
            title: '上课地点'
        }, {
            field: 'esite',
            title: '考试地点'
        }, {
            field: 'cid',
            title: '操作',
            width: 200,
            formatter: option
        },],
        responseHandler: function(res){
            return{
                "total":res.data.total,//总数
                "rows":res.data.rows//数据
            };
        }
    });
    function option(value, row, index) {
        var htm = "";
        htm += '<button class="btn btn-primary" onclick="editCourse('+value+')">编辑</button>'
            +' '
            +'<button class="btn btn-primary" onclick="deleteCourse('+value+')">删除</button>'
        return htm;
    }
}
$(function (){
    loadTable();
})