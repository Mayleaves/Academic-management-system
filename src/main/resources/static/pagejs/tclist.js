function deleteCourse(id){
    $('#tcTable').bootstrapTable("destroy");
    if(confirm("是否要删除序号为"+id+"的课程？")){
        $.ajax({
            url:"/webapi/tc/delete/"+id,
            method:'delete'
        }).done(function (){
            loadTable();
        });
    }else{
        loadTable();
    }
}
function searchCourseName(){
    $('#tcTable').bootstrapTable("destroy");
    loadTable();
}
function loadTable(){
    $('#tcTable').bootstrapTable({
        url:'/webapi/tc/getbypage',
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
                sort:"tcid",
                direct:"desc",

                cname:$("#searchName").val(),
                tno:localStorage.getItem('username')
            };
            return paraObj;
        },
        columns: [{
            field: 'tcid',
            title: '序号'
        }, {
            field: 'tno',
            title: '教师号'
        }, {
            field: 'tname',
            title: '教师名'
        }, {
            field: 'cname',
            title: '课程名',
        }, {
            field: 'eva',
            title: '评价'
        },  {
            field: 'tcid',
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