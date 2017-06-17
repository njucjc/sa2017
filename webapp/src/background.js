$(function() {  
    var stu_id = $("#stu-id"),
    name = $("#name"),  
    major = $("#major"), 
    stu_type = $("#stu-type"), 
    total_grade = $("#total-grade"), 
    usual_grade = $("#usual-grade"),
    design_grade = $("#design-grade"),
    exam_grade = $( "#exam-grade"), 
    rowindex = $("#rowindex"),
    student_list = [],  
    allFields = $([]).add(stu_id).add(name).add(major).add(stu_type).add(usual_grade).add(design_grade).add(exam_grade).add(rowindex);

    var pageInfo = new Object();
    pageInfo.pageNum = 1;
    pageInfo.pageSize = 10; 

        function getTotalGrade(usual_grade, design_grade, exam_grade) {
        var total = usual_grade + design_grade + exam_grade;
        return total.toString();
    }

    function getAllStudents(){
        $.ajax({
            url: 'http://localhost:8080/students',
            type: 'GET',
            dataType:'json',               
        }).done(function(res) {
            removeData();
            student_list = res.list;
            createData(student_list);
            var student = new Object();

        }).fail(function(res) {
            console.log(res);
        });
    } 

    function findStudent(id) {
        $.ajax({
            url: 'http://localhost:8080/students/find',
            type: 'GET',
            data: id,
            dataType:'json',               
        }).done(function(res) {
           stu_id.val(res.list[0].id);
            name.val(res.list[0].name);
            major.val(res.list[0].major);
            stu_type.val(res.list[0].type);
            usual_grade.val(res.list[0].usual);
            design_grade.val(res.list[0].design);
            exam_grade.val(res.list[0].exam); 
            total_grade.val(getTotalGrade(Number(usual_grade.val()), Number(design_grade.val()), Number(exam_grade.val())));
            //return res.list[0];
        }).fail(function(res) {
            console.log(res);
        });
    }

    function addStudent(student) {
        $.ajax({
            url: 'http://localhost:8080/students/add',
            type: 'POST',
            cache: false,
            dataType:'json',
            data: student
        }).done(function(res) {
            removeData();
            student_list = res.list;
            createData(student_list);
        }).fail(function(res) {
            console.log(res);
        });
    }

    function deleteStudent(id) {
        $.ajax({
            url: 'http://localhost:8080/students/delete',
            type: 'POST',
            data: id,
            cache: false,
            dataType:'json'
        }).done(function(res) {
            removeData();
            student_list = res.list;
            createData(student_list);
        }).fail(function(res) {
            console.log(res);
        });  
    }

    function updateStudent(student) {
        $.ajax({
            url: 'http://localhost:8080/students/update',
            type: 'POST',
            cache: false,
            dataType:'json',
            data: student,
        }).done(function(res) {
            removeData();
            student_list = res.list;
            createData(student_list);
        }).fail(function(res) {
            console.log(res);
        });
    }

    function updatePageInfo() {
        $.ajax({
            url: 'http://localhost:8080/students/pageInfo',
            type: 'POST',
            data: pageInfo,
            cache: false,
            dataType:'json'
        }).done(function(res) {
            removeData();
            student_list = res.list;
            createData(student_list);
        }).fail(function(res) {
            console.log(res);
        }); 
    }

    getAllStudents();

    $("#dialog-form").dialog({  
        autoOpen: false,  
        height: 300,  
        width: 500,  
        modal: true,  
        buttons: {  
            "确认": function(){ 
                var student = new Object();
                student.id = stu_id.val();
                student.name = name.val();
                student.major = major.val();
                student.type = stu_type.val();
                student.usual = usual_grade.val();
                student.design = design_grade.val();
                student.exam = exam_grade.val();
 
                if (rowindex.val()==""){//add
                    addStudent(student);
                    bindEvent();  
                }  
                else{//update 
                    var idx = rowindex.val();  
                    var tr = $("#users>tbody>tr").eq(idx);
                    tr.children().eq(0).text(stu_id.val());  
                    tr.children().eq(1).text(name.val());  
                    tr.children().eq(2).text(major.val());
                    tr.children().eq(3).text(stu_type.val());
                    
                    updateStudent(student);

                    bindEvent();  
                    $(this).dialog( "close" );    
                }  
                $(this).dialog( "close" );  
            },  
            "取消": function() {  
                $(this).dialog( "close" );  
            }  
        },  
        close: function(){  
            ;  
        }  
    });

    $("#dialog-import").dialog({
        autoOpen: false,
        height: 200,
        width: 320,
        modal: true,
        buttons: {
            "导入": function(){
                $.ajax({
                    url: 'http://localhost:8080/batchimport',
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#uploadForm')[0]),
                    processData: false,
                    contentType: false
                }).done(function(res) {
                    removeData();
                    student_list = res.list;
                    createData(student_list);
                }).fail(function(res) {

                });
                $(this).dialog("close");
            },
            "取消": function(){
                $(this).dialog("close");
            }
        },
        close: function(){
            ;
        }
    });

    $("#dialog-grade").dialog({
        autoOpen: false,
        height: 300,
        width: 480,
        modal: true,
        buttons: {
            "确认":function(){
                var student = new Object();
                student.id = stu_id.val();
                student.name = name.val();
                student.major = major.val();
                student.type = stu_type.val();
                student.usual = usual_grade.val();
                student.design = design_grade.val();
                student.exam = exam_grade.val();

                updateStudent(student);

                $(this).dialog("close");
            }
        },
        close:function(){
            ;
        }
    });

    function bindEvent(){    
        $(".editButton").click(function(){  
            var b = $(this);  
            var tr = b.parents("tr"); 
            var tds = tr.children();
            var temp = new Object();
            stu_id.val(tds.eq(0).text());
            temp.id = stu_id.val();
            findStudent(temp);
                
            var trs = b.parents("tbody").children(); 
            rowindex.val(trs.index(tr));   
            $( "#dialog-form" ).dialog( "open" );  

            bindEvent();
        });  
 
        $(".deleteButton").click(function(){ 
            var b = $(this);  
            var tr = b.parents("tr");  
            var tds = tr.children();   
            var temp = new Object();
            stu_id.val(tds.eq(0).text());
            temp.id = stu_id.val();
            deleteStudent(temp);
            var tr = $(this).parents("tr");  
            tr.remove();  
            bindEvent();
        }); 

        $(".gradeButton").click(function(){
            var b = $(this);  
            var tr = b.parents("tr");  
            var tds = tr.children();
            stu_id.val(tds.eq(0).text());
            var temp = new Object();
            temp.id = stu_id.val(); 

            findStudent(temp);

            $("#dialog-grade").dialog("open");
            bindEvent();
        });
    };  

    $(".lastPage")
    .button()
    .click(function(){
        if(pageInfo.pageNum > 1)
        pageInfo.pageNum--;
        updatePageInfo(); 
    });

    $(".nextPage")
    .button()
    .click(function(){
        pageInfo.pageNum++;
        updatePageInfo();  
    });

    bindEvent();  

    $( "#create-user" )  
    .button()  
    .click(function(){  
        //clean form  
        allFields.each(function(idx){  
            this.value="";  
        });  
        $( "#dialog-form" ).dialog( "open" );  
    });  

    $("#import")
    .button()
    .click(function(){
        //import local file  
        $( "#dialog-import" ).dialog( "open" );  
    })

    function removeData(){
        var body = document.getElementById("students");
        while(body.hasChildNodes()) {//repeat remove div sub-node
            body.removeChild(body.firstChild);
        }
    }

    function createData(res){
        for (var i = 0;i < student_list.length; i++){
            $( "#users tbody" ).append( "<tr>" +  
                    "<td>" + res[i].id + "</td>" +   
                    "<td>" + res[i].name + "</td>" +   
                    "<td>" + res[i].major + "</td>" +  
                    "<td>" + res[i].type + "</td>" + 
                    '<td><button class="deleteButton">删除</button><button class="editButton">编辑</button><button class="gradeButton">成绩</button></td>'+  
                    "</tr>" );
            }
            bindEvent();
        }



});