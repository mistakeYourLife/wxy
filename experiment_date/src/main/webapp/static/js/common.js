/*global $,window*/
/*module:iframe标签页管理;require:jquery,bootstrap*/
//iframe大小适应

jQuery(function($){
	setIframeSize();
})

function setIframeSize() {
    var fullHeight = $(window).height();
    $('body').height(fullHeight);
    $('.contentFrame').height(fullHeight - 95);
}

$(window).resize(function () {
    setIframeSize();
});

//iframe容器处理
function setIframeBox(targetId, url, title) {
    var currentTab = $("#tabTitle-" + targetId);
    if (!currentTab.length) {
        $("#iframeTabs").append('<li><a href="#tab-' + targetId + '" id="tabTitle-' +
            targetId + '" tid="'+targetId+'" role="tab" data-toggle="tab">' + title +
            '<button type="button" class="close">&times;</button></a></li>');
        var tabContent = '<div class="tab-pane" id="tab-' + targetId + '"><iframe src="' + url + '" ' +
            ' width="100%" scrolling="auto" name="contentFrame" class="contentFrame" frameborder="0" ' +
            ' id="contentFrame-' + targetId + '"></iframe></div>';
        $("#tab-content").append(tabContent);
        $('#contentFrame-' + targetId).attr("src", url);
        setIframeSize();
        $("#tabTitle-" + targetId).tab("show");
    } else {
        currentTab.tab("show");
    }
}

//左侧菜单高亮及TAB联动
$("#sidebar .submenu a").click(function (e) {
    e.preventDefault();
    var url = $(this).attr("href"),
        title = $(this).text(),
        tid = $(this).attr("target-id");
    setIframeBox(tid, url, title);
    $(this).parents("#sidebar").find("li").removeClass("active");
    $(this).parent("li").addClass("active");
});

//获取上一个Tab
var relatedTab = {};
$("#iframeTabs").delegate('a[data-toggle="tab"]', 'shown.bs.tab', function (e) {
    relatedTab = e.relatedTarget;
    var tid=$(this).attr('tid');
    $('#sidebar .submenu li').removeClass('active');
   $('#sidebar .submenu a[target-id='+tid+']').parent('li').addClass('active');
});

//TAB关闭切换
$("#iframeTabs").delegate("button", "click", function (e) {
    e = e || window.event;
    e.stopPropagation();
    $(this).parents(".active").remove();
    if (relatedTab) {
        $(relatedTab).tab("show");
    } else {
        $("#tabTitle-00").tab("show");
    }
});

$("#j-update-sysuser-url").click(function (e) {
    var url = $(this).data("url");
    var title = $(this).text();
    var   tid ="15899555555555";
    console.log(url);
    setIframeBox(tid, url, title);
});



/**
 * 关闭激活的Tab
 * @param newTargetName 新目标名称
 */
function closeActiveIframe(newTargetName){
	$("#iframeTabs li.active").remove();
	if(typeof newTargetName !== "undefined" ){
		openNewIframe(newTargetName);
	}else{
		if (relatedTab) {
			$(relatedTab).tab("show");
		} else {
			$("#tabTitle-00").tab("show");
		}
	}
}

/**
 * 手动开打新的Iframe窗口
 * @param targetName 目标名称
 */
function openNewIframe(targetName){
	$("#sidebar .submenu a").each(function(){
		if($(this).attr("name") == targetName){
			var url = $(this).attr("href"),title = $(this).text(),tid = $(this).attr("target-id");
			//保证打开的页面值是最新的
			var currentTab = $("#tabTitle-" + tid);
			if (currentTab.length > 0) {
				currentTab.remove();
			}
		    setIframeBox(tid, url, title);
		    $(this).parents("#sidebar").find("li").removeClass("active");
		    $(this).parent("li").addClass("active");
		    return false;
		}
	});
}

/**
 * 动态添加html
 * srcHtml:需要加载的html
 * directHtmlId:设置到指定id之后
 */
function loadHtmlAfterDirectHtml(srcHtml,directHtmlId){
	$("#"+directHtmlId).after(srcHtml);
}
