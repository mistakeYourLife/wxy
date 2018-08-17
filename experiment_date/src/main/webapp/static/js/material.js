jQuery(function($) {

    testTableInit();

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
});



function testTableInit(){
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
                'bSortable' : true
            },{
                'mDataProp' : 'info',
                'bSortable' : false
            }]

    });//dataTable
}