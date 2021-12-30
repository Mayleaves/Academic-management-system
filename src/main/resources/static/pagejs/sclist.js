function deleteCourse(id){
    $('#scTable').bootstrapTable("destroy");
    if(confirm("是否要删除序号为"+id+"的课程？")){
        $.ajax({
            url:"/webapi/sc/delete/"+id,
            method:'delete'
        }).done(function (){
            loadTable();
        });
    }else{
        loadTable();
    }
}
function searchCourseName(){
    $('#scTable').bootstrapTable("destroy");
    loadTable();
}
function loadTable(){
    $('#scTable').bootstrapTable({
        url:'/webapi/sc/getbypage',
        striped:true,
        pagination:true,
        singleSelect: false,
        pageSize:5,
        pageNumber:1,
        sidePagination: "server",
        pageList: [5, 10, 15],
        queryParams:function (params){
            var paraObj={
                size: params.limit,
                page: params.offset/params.limit,
                sort:"scid",
                direct:"desc",

                cname:$("#searchName").val(),
                sno:localStorage.getItem('username')
            };
            return paraObj;
        },
        columns: [{
            field: 'scid',
            title: '序号'
        }, {
            field: 'sno',
            title: '学号'
        }, {
            field: 'sname',
            title: '学生名'
        }, {
            field: 'cname',
            title: '课程名',
        }, {
            field: 'usualScore',
            title: '平时成绩'
        }, {
            field: 'finalScore',
            title: '期末成绩'
        }, {
            field: 'makeupScore',
            title: '补考成绩'
        }, {
            field: 'scid',
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
        htm += '<button class="btn btn-primary" onclick="deleteCourse('+value+')">删除</button>'
        return htm;
    }
}
$(function (){
    loadTable();
})