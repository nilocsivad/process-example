<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${URL }/static_r/component/AmazeUI-2.4.2/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${URL }/static_r/component/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="${URL }/static_r/component/AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>

<script type="text/javascript">
	$("#header-items").children("li").each(function() {
		var dataKey = $(this).attr("data-key");
		if (dataKey == CUR_DATA_KEY) {
			$(this).addClass("am-active");
			return false;
		}
	});
</script>
