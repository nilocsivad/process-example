<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/url.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>Process One | Amaze UI</title>
<%@include file="../refrence-head.jsp"%>
<script type="text/javascript">
	var CUR_DATA_KEY = "process-1";
</script>
<link rel="stylesheet" href="${URL }/static_r/style/process/index.css.css" />
<script type="text/javascript">
	function startProcess() {
		document.getElementById( "form-start-process" ).submit();
	}
</script>
</head>
<body>

	<%@include file="../header.jsp"%>


	<div class="wrap-box">
		<div class="am-container">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>
				<a href="${URL }/u/process/one/do/redeploy">重新部署流程一</a>
			</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<form id="form-start-process" method="post" action="${URL }/u/process/one/2/start">
				<p>
					<label>流程发起人:</label><input name="onlineUName" />
				</p>
				<p>
					<a href="javascript:startProcess()">启动流程一</a>
				</p>
			</form>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>


	<%@include file="../footer.jsp"%>

</body>
<%@include file="../refrence-foot.jsp"%>
</html>
