function preAdd(){
    $("#sid").val(0);//提示新增，为saveStudent()做准备:将sid设置为0
}
function saveStudent(){
    $('#stuTable').bootstrapTable("destroy");//需要摧毁表格，ajax会自动造一个表
    var data=$("#formStudent").serialize();
    var id=$("#sid").val();
    if(id < 1){//增加学生
        $.ajax({
            url: "/webapi/student/insert",
            method:"post",
            data:data
        }).done(function (){
            loadTable();
            $('#myModal').modal('hide')
        })
    }else{//修改学生
        $.ajax({
            url: "/webapi/student/update",
            method:"put",
            data:data
        }).done(function (){
            loadTable();
            $('#myModal').modal('hide')
        })
    }
}
function deleteStudent(id){
    $('#stuTable').bootstrapTable("destroy");
    if(confirm("是否要删除序号为"+id+"的学生？")){
        $.ajax({
            url:"/webapi/student/delete/"+id,
            method:'delete'
        }).done(function (){
            loadTable();//一旦删除完毕需要更新表格，使用loadTable
        });
    }else{//需要添加else，否则将没有表格出现
        loadTable();//加载，创建一个表格
    }
}
function editStudent(id){
    $('#myModal').modal('show');
    $.ajax({
        url:'/webapi/student/get/'+id
    }).done(function (rs){
        $("#sid").val(rs.sid);
        $("#sname").val(rs.sname);
        $("#sno").val(rs.sno);
        $("#ssex").val(rs.ssex);
        $("#sage").val(rs.sage);
        $("#sscore").val(rs.sscore);
        $("#spassword").val("");//通过编辑点开弹窗，密码处为空白
    })
}
function searchStudentName(){
    $('#stuTable').bootstrapTable("destroy");
    loadTable();
}
function loadTable(){//加载表格+查询
    $('#stuTable').bootstrapTable({
        url:'/webapi/student/getbypage',
        striped:true,
        pagination:true,
        singleSelect: false,
        pageSize:5,//起始容量
        pageNumber:1,
        sidePagination: "server",
        pageList: [5, 10, 15],
        queryParams:function (params){
            var paraObj;
            if($("#option").val()=="sname"){
                paraObj={
                    size: params.limit,
                    page:params.offset/params.limit,
                    sort:"sid",
                    order:"desc",

                    sname:$("#searchName").val(),
                    sno:""
                }
            }else{
                paraObj={
                    size: params.limit,
                    page:params.offset/params.limit,
                    sort:"sid",
                    order:"desc",

                    sname:"",
                    sno:$("#searchName").val()
                }
            }
            return paraObj;
        },
        columns: [{
            field: 'sid',
            title: '序号',
            width: 70
        }, {
            field: 'sname',
            title: '姓名'
        }, {
            field: 'sno',
            title: '学号'
        }, {
            field: 'ssex',
            title: '性别',
            //法一：
            formatter: formatSex
            //法二：1男，2女
            //formatter: function(name) {return name == 1 ? "男" : "女";}
        }, {
            field: 'sage',
            title: '年龄'
        }, {
            field: 'sscore',
            title: '绩点'
        }, {
            field: 'spassword',
            title: '密码'
        }, {
            field: 'sid',
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
    // 法一：1男，2女
    function formatSex(value, row, index) {
        return value == 1 ? "男" : "女";
    }
    function option(value, row, index) {
        var htm = "";
        htm += '<button class="btn btn-primary" onclick="editStudent('+value+')">编辑</button>'
            +' '
            +'<button class="btn btn-primary" onclick="deleteStudent('+value+')">删除</button>'
        return htm;
    }
}
$(function (){//二传手
    loadTable();
})
