jQuery(function($) {

    var materialForm = $("#material_form").Validform({
        tiptype : 4,
        btnReset : ".btnReset",
        ajaxPost : false
    });

    tableInit();

    /*$("#test").on("click",function(){

        if (creditorContractDown.check(false)) {
            var data =  $("#creditor_contract_down").serialize();
            $(this).addClass("disabled");
            $.ajax({
                type: "GET",
                url:  config.urlMap.getCreditorContractDownUrl,
                data: data,
                dataType: "json",
                success: function(data){
                    if(data.success) {
                        window.location.href=data.result
                    } else {
                        bootbox.alert("合同下载失败");
                    }
                    $("#creditor_contract_down_btn").removeClass("disabled");
                }
            });
        }
    });*/



    //新增
    $('#new_material').on('click', function() {


        materialForm.resetForm();

        $('#material-modal-table').modal({
            'show': true
        });
    });

    $("#save_material").on('click', function() {


        $("#save_material").addClass("disabled");
        if (materialForm.check(false)) {
            $('#material_form').xform('post', '/material/save', function(data) {
                if (!data.success) {
                    if (!!data.resultCodeEum) {
                        bootbox.alert(data.resultCodeEum[0].msg,function(){
                        });
                    } else {
                        bootbox.alert("保存失败!",function(){
                            $("#save_material").removeClass("disabled");
                        });
                    }

                } else {
                    bootbox.alert("保存成功！", function() {
                        $('#material-modal-table').modal('toggle');
                        $("#save_material").removeClass("disabled");
                        materialForm.resetForm();
                        materialTable.fnDraw();
                    });
                }
            });
        }
        $("#save_material").removeClass("disabled");
    });

});



function tableInit(){
    materialTable = $('#material-table').dataTable({
        'bFilter' : false,
        'bProcessing' : true,
        'bSort' : true,
        'aaSorting':[[1,"desc"]],
        'bServerSide' : true,
        'fnServerParams' : function(aoData) {
            getSearchValue("#customMsgSearchForm",aoData);
        },
        'fnInitComplete':function(){
        },
        'sAjaxSource' : '/material/init' ,
        'aoColumns' : [
            {
                'mDataProp' : 'id',
                'bSortable' : false,
                'mRender' : function(data, type, row) {
                    return "<input type='checkbox' value=" + row.id + ">";
                }
            }, {
                'mDataProp' : 'name',
                'bSortable' : false
            },{
                'mDataProp' : 'info',
                'bSortable' : false
            }]

    });//dataTable
}