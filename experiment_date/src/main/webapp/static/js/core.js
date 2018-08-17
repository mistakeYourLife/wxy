/*NAME:通用表单处理;REQUIRE:jquery,validform*/
(function($) {
	
    // form处理
    $.fn.xform = function(action, url, callback, async) {
        var form = this;
        if (typeof action !== "undefined") {
            switch (action) {
                case "load" :
                    load();
                    break;
                case "post" :
                    post();
                    break;
                case "reset" :
                    reset();
                    break;
                default :
                    throw new Error("xform:action is not defined;");
                    break;
            }
        };
      //callback,默认无
        if (typeof callback === "undefined") {
            callback=function(data){return;};
        }
        function load() { 
            // 数据处理
            var inputArray=[],
            textareaArray = [],
            dateArray = [];
            form.find("select,input[type='checkbox'],input[type='radio'],input[type='date'],input[type='date2'],.switchInput").each(function(){
                var name=$(this).attr("name");
                inputArray.push(name);
            });
            form.find("textarea").each(function(){
                var name=$(this).attr("name");
                textareaArray.push(name);
            });
            form.find(".dateInput").each(function(){
                var name=$(this).attr("name");
                dateArray.push(name);
            });
            //$("input[type='password']").parents(".form-group").hide();
            function dataHandle(json) {
                $.each(json,function(name, value) {
                     if(value!==null&&value!==""){
                        if($.inArray(name,inputArray)!==-1){
                            var select=$("select[name="+name+"]");
                            var checkbox=$("input[type='checkbox'][name="+name+"]");
                            var switchBox=$(".switchInput[name="+name+"]");
                            var datebox=$("input[type='date'][name="+name+"]");//这种格式默认是年/月/日，暂时未找到怎么自定义其它格式
                            var customDate=$("input[type='date2'][name="+name+"]");//解决上面标签不支持的问题
                            if(select.length){
                                //select
                                select.find("option[value='"+value+"']").attr("selected","selected");
                            }
                            else if(checkbox.length){
                                if($.isArray(value)){
                                    //普通checkbox
                                    for(i in value){
                                        $(form).find('input[value='+value[i]+']').each(function(){
                                            $(this).attr("checked","checked");
                                        });
                                    }
                                }
                                else{
                                    checkbox.attr("checked","checked");
                                }
                            }
                            else if(switchBox.length){
                                //switch开关
                                var switchNumber=Number(value);
                                if(switchNumber){
                                    switchBox.val(1);
                                    switchBox.siblings(".ace-switch").click();
                                }
                                else{
                                    switchBox.val(0).siblings(".ace-switch").removeAttr("checked");
                                }
                            }
                            else if(datebox.length){
                            	var format = datebox.attr("format");
                            	if(format !== "undefined"){
                            		datebox.val(formatDate(Number(value),format));
                            	}else{
                            		datebox.val(formatDate(Number(value)));
                            	}
                            }else if(customDate.length){
                            	var format = customDate.attr("format");
                            	if(format !== "undefined"){
                            		customDate.val(formatDate(Number(value),format));
                            	}else{
                            		customDate.val(formatDate(Number(value)));
                            	}
                            }
                        }else if($.inArray(name,textareaArray)!==-1){
                        	$("textarea[name='"+name+"']").html(value);//textarea的显示
                        }else if($.inArray(name,dateArray)!==-1){
                        	var format = $("input[name='"+name+"']").attr("dateFormat");
                        	if(!!format){
                        		$("input[name='"+name+"']").val(formatDate(Number(value),format));//时间控件的时间显示class为dateInput
                        	}else{
                        		$("input[name='"+name+"']").val(formatDate(Number(value)));//时间控件的时间显示class为dateInput
                        	}
                        }else{
                            switch (name) {
//                                case "password":
//                                    $('input[name=password]', form).remove();
//                                    break;
                                case "image":
                                    break;
                                case "content":
//                                    callback(value);
                                    break;
                                default :
                                    $('*[name="' + name + '"]', form).val(value);
                                    break;
                            }         
                        }
                    }
                });
            }
            // 获取JSON数据
            if (typeof url !== "undefined") {
                $.getJSON(url, function(data) {
                    dataHandle(data);
                    callback(data);
                });
            } else {
                throw new Error("loadFormData(url):url is not defined!");
            }
            //禁用输入框
        };
        // post
        function post() {
            //async，默认异步
            if (typeof async === "undefined") {
                async = true;
            }
            if (typeof url !== "undefined") {
                var array = form.serializeArray();
                $.ajax({
                    type: "POST",
                    url: url,
                    data: array,
                    dataType: "json",
                    async: async,
                    success: function(data){
                        callback(data);
                        $('#modal-table,.j-modal').modal('hide');
                    },
                    error:function(e){
                        bootbox.alert("请求异常:"+e.responseText);
                    }
                });
            } else {
                throw new Error("postFormJson(url,async):url is not defined!");
            }
        };
        // reset
        function reset() {
            form.append('<input type="reset" class="btnReset hidden">');
            $(".btnReset", form).click();
            $("input[type=hidden]").removeAttr("value");//修复hidden不能清除的问题
            $("input[type='password']").parents(".form-group").show();
            $(".ace-switch").removeAttr("checked");//清除switch选中
            var labelBox=form.find(".labelBox");
            if(labelBox.length>0){//保持初始选中的checkbox
                labelBox.children("input[type='checkbox']").each(function(){
                    var isChecked=$(this).parent().attr("checked");
                    if(typeof isChecked !=="undefined"||isChecked !== "false"){
                        $(this).val($(this).parent().attr("value"))
                            .attr("checked",isChecked);
                    };
                });
            }
        }
    };
    // 设置分类目录
    $.fn.setCategory = function(url, obj) {
        // config
        var _this = this, json = {};
        var config = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: changeCategory
            }
        };
        // 没有参数时的示例
        if (typeof obj === "undefined") {
            obj = $("#categoryTree");
        }
        // 返回数据
        function changeCategory(treeId, treeNode, clickFlag) {
            var id=treeNode.id;
            var cid=_this.siblings("input[type='hidden']").val();
            if(id!==Number(cid)){
                _this.val(treeNode.name).siblings("input[type='hidden']").val(id);
                $("#modal-categoryTree").modal("hide");
            }
        };
        //点击时初始化
        _this.click(function() {
            if (typeof url !== "undefined") {
                $.getJSON(url, function(data) {
                    json = data;
                    $.each(data,function(index,node){
                        node["pId"]=node["parentId"];
                    });
                    //初始化列表树
                    $.fn.zTree.init(obj, config, json);
                    $("#modal-categoryTree").modal("show");  
                });
            } else {
                throw new Error("setCategory():url is not defined");
            };         
        });        
    };
})(jQuery);

(function() {
    // 关闭对话框时重置表单
    $(".labelBox>input").on("click",function(){
        var value=$(this).parent().attr("value");
        $(this).val(value);
    });
    $("#modal-table").on("hide.bs.modal", function() {
        var form = $(this).find("form");
        if (form.length !== 0) {
            form.xform("reset");
        }
    });
    //switch值转换
    $(".ace-switch").on("click",function(){
       if($(this).is(":checked")){
           $(this).siblings(".switchInput").val(1);
       }else{
           $(this).siblings(".switchInput").val(0);
       }
    });
    //datatable行选中与高亮
    $(".table").delegate("tbody>tr","click",function(){  
        $(this).parents("tbody").find("input[type=checkbox]").removeAttr("checked");
        $(this).find("input[type=checkbox]").click();
    });
    $(".table").delegate("input[type=checkbox]","click",function(e){
        e.stopPropagation();
    });
    //
    // 修复日期控件和弹出层冲突的问题
    // $('.date-picker').on({
    // focus: function() {
    // setTimeout('$(".datepicker").css("z-index", "1060")', 100);
    // },
    // click: function() {
    // $(".datepicker").css('z-index', '1060');
    // }
    // });
})();
/* Set the defaults for DataTables initialisation */
$.extend(true, $.fn.dataTable.defaults, {
	"dom": 'T<"clear">lfrtip',
	"oTableTools": {//excel导出
        "aButtons": []
	},
    "aLengthMenu":[10,25,50,100,1000],
    "sPaginationType": "full_numbers",
    "bPaginate": true,
    "oLanguage": {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    }
});


$.extend(true, $.fn.modal.Constructor.DEFAULTS, {
	"backdrop":'static'
});
//
//$((function($){
//    $.datepicker.regional['zh-CN'] = {
//        clearText: '清除',
//        clearStatus: '清除已选日期',
//        closeText: '关闭',
//        closeStatus: '不改变当前选择',
//        prevText: '<上月',
//        prevStatus: '显示上月',
//        prevBigText: '<<',
//        prevBigStatus: '显示上一年',
//        nextText: '下月>',
//        nextStatus: '显示下月',
//        nextBigText: '>>',
//        nextBigStatus: '显示下一年',
//        currentText: '今天',
//        currentStatus: '显示本月',
//        monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],
//        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],
//        monthStatus: '选择月份',
//        yearStatus: '选择年份',
//        weekHeader: '周',
//        weekStatus: '年内周次',
//        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
//        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
//        dayNamesMin: ['日','一','二','三','四','五','六'],
//        dayStatus: '设置 DD 为一周起始',
//        dateStatus: '选择 m月 d日, DD',
//        dateFormat: 'yy-mm-dd',
//        firstDay: 1,
//        initStatus: '请选择日期',
//        isRTL: false};
//    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
//})(jQuery));

/**
 * dataTable 插件
 */
jQuery.fn.dataTableExt.oApi.fnProcessingIndicator = function ( oSettings, onoff )
{
	if ( onoff === undefined ) {
		onoff = true;
	}
	this.oApi._fnProcessingDisplay( oSettings, onoff );
};

function getAllSearchValue(aoData) {
    $("thead input,thead select").each(function(i) {
        if (this.value != null && this.value != "") {
            aoData.push({
                "name": this.name,
                "value": trimStr(this.value)
            });
        }
    });
} 
//时间格式化 useage:formatDate("1408609262000","yyyy-mm-dd");
function formatDate(timestamp,format){
    if(typeof timestamp!=="undefined"){
        var date = new Date(Number(timestamp)),
            oDate="", 
            year = date.getFullYear(), 
            month = date.getMonth()+1, 
            day = date.getDate(),
            hours = date.getHours(),
            minutes = date.getMinutes(),
            seconds = date.getSeconds();
        var time = [month, day, hours, minutes, seconds];
        for(i in time){
            if(time[i]<10){
                time[i]="0"+time[i];
            }
        }
        if(typeof format==="undefined"){
            oDate=year + "-" + time[0] + "-" + time[1];
            return oDate;
        }else{
            switch (format) {
                case "yyyy-mm-dd HH:mm:ss" :
                    oDate = year + "-" + time[0] + "-" + time[1] + " " + time[2] + ":"
                            + time[3] + ":" + time[4];
                    break;
                case "yyyy-mm-dd HH:mm" :
                    oDate = year + "-" + time[0] + "-" + time[1] + " " + time[2] + ":"
                    + time[3];
                    break;
                case "yyyy-mm-dd" :
                    oDate= year + "-" + time[0] + "-" + time[1];
                    break;
                case "yyyy-mm" :
                    oDate= year +  "-" + time[0];
                    break;
                case "HH:mm:ss" :
                    oDate = time[2] + ":" + time[3] + ":" + time[4];
                    break;
                case "HH:mm" :
                    oDate = time[2] + ":"+ time[3];
                    break;
                default :
                    break;
            }
            return oDate;
        }
        return;
    }else{
        throw new Error("formatDate():timestamp is not defined");
    }
}
//根据字典值获取字典label
function getDictLabel(dictObj, value){
	for(var i=0;i<dictObj.length;i++){
		if(dictObj[i].value == value){
			return dictObj[i].label;
		}
	}
	return value;
}

//获取债权类型Name
function getGuarantyTypeName(dictObj,instal,value){
	for(var i=0;i<dictObj.length;i++){
		if(dictObj[i].value == value){
			label = dictObj[i].label;
			if(value == 'car' && instal==1){
				label = "购车融项目";
			}
			return label;
		}
	}
	return value;
}

//清除缓存
function clearCacheByKey(key){
	 bootbox.confirm("你确定要清空Redis数据缓存吗?", function(result) {
         if (result) {
             $.post(config.baseURL+"/myCache/clearCacheByKey",{"key":key},function(data){
            	 bootbox.alert("缓存已成功清除");
             })
         }
     });
}

//获取选中的区域编码 select,input[type='checkbox'],input[type='radio']

function getAreaCode(selector){
	var code=0,
		url = config.baseURL+"/sysArea/areaSelect?code=";
	code=selector.find("option:selected").val();
	selector.xform("post", url+code, function(data){
		var optionList="";
		$.each(data, function(name,value){
			optionList+="<option value='"+value.code+"'>"+value.name+"</option>";
		});
		selector.parent().next().find("select").html(optionList);
	});
}

/**
 * 获得搜索键值
 * @param formID 搜索表单
 * @param dataTableData  dataTable 数组对象值
 */
function getSearchValue(formID,dataTableData){
	 var paramArray = $(formID).serializeArray();
	 paramArray.forEach(function(obj){
		 dataTableData.push(obj);
	 })
}

/**
 * 重置表单
 */
$(".resetButton").on("click",function(){
	var form = $(this).closest("form");
    if (form.length !== 0) {
        form.xform("reset");
    }
})

/**
 * 修复拖动宽度
 * @param e
 * @param ui
 * @returns
 */
function fixHelper(e, ui) {
	ui.children().each(function() {
 		$(this).width($(this).width());
	});
	return ui;  
}

/**
 * 保留2为小数
 * @param len
 * @returns {Number}
 */
Number.prototype.toFixed=function(len){
    var temp = Math.pow(10,len);
    var s = Math.ceil(this * temp)
    return s/temp;
}
//遮罩层
function xShade(action) {
    var shade = $(".u-shade");
    if (typeof action === "undefined" || action === "show") {
        if (shade.length === 0) {
            var html = "<div class='u-shade' style='text-align:center;padding-top:140px;color:blue;font-weight:bold;font-size:16px;'><img src=" + config.baseURL + "/static/img/loading.gif/>&nbsp;&nbsp;请稍候...</div>";
            $("body").append(html);
        } else {
            shade.show();
        }
    } else if (action === "hide") {
        shade.remove();
    }
}

/*比较两个时间的大小
 * 1:end>start
 * 0:end==start
 * -1:start>end
 * */
function compareTwoDate(start,end){
	if(start==""){
		return 1;
	}else if(end==""){
		return -1;
	}else if(start=="" && end ==""){
		return 0;
	}else{
		var dayNum = new Date(end) - new Date(start);
		if(dayNum>0){
			return 1;
		}else if(dayNum<0){
			return -1;
		}else{
			return 0;
		}
	}
}

/**
 * trim
 * @param str
 * @returns
 */
function trimStr(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}
//户籍地校验
function checkAreaSelected(type) {
	if(type == "option") {
		//可选项
		if(($("#area_prov").val() != '' && $("#area_city").val() != '' && $("#area_dict").val() != '') || ($("#area_prov").val() == '' && $("#area_city").val() == '' && $("#area_dict").val() == ''))
			return true;
		else 
			return false;
	} else {
		//必选项
		if($("#area_prov").val() == '' && $("#area_city").val() == '' && $("#area_dict").val() == '')
			return true;
		else
			return false;
	}
}

Number.prototype.toFixed = function(s) {
	changenum = (parseInt(this * Math.pow(10, s) + 0.5) / Math.pow(10, s))
			.toString();
	index = changenum.indexOf(".");
	if (index < 0 && s > 0) {
		changenum = changenum + ".";
		for (i = 0; i < s; i++) {
			changenum = changenum + "0";
		}

	} else {
		index = changenum.length - index;
		for (i = 0; i < (s - index) + 1; i++) {
			changenum = changenum + "0";
		}

	}

	return changenum;
}


/**
 * 动态添加html
 * srcHtml:需要加载的html
 * directHtmlId:设置到指定id之后
 */
function loadHtmlAfterDirectHtml(srcHtml,directHtmlId){
	$("#"+directHtmlId).after(srcHtml);
}