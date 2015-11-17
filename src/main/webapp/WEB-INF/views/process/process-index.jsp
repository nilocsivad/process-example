<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/url.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>index | Amaze UI</title>
<%@include file="./refrence-head.jsp"%>
<script type="text/javascript">
	var CUR_DATA_KEY = "home";
</script>
<link rel="stylesheet" href="${URL }/static_r/style/process/process-index.css.css" />
</head>
<body>

	<%@include file="./header.jsp"%>

	<c:forEach items="${processes }" var="proc">
		<div class="wrap-box">
			<div class="am-container">
				<h2 class="about-title about-color">${proc.name }</h2>
				<p>
					<img class="proc" alt="${proc.name }" src="${URL }/u/home/show/process/image/2/${proc.deploymentId }?diagramResourceName=${proc.diagramResourceName }&t=<%=System.currentTimeMillis() %>">
				</p>
				<p>
					<a class="start-proc" href="${URL }/u/process/one/show/start/view">启动 <b>${proc.name }</b></a> <span class="clear"></span>
				</p>
				<p>&nbsp;</p>
			</div>
		</div>
	</c:forEach>


	<div class="wrap-box">
		<div class="am-container">
			<h2 class="about-title about-color">Generate-JSON 欢迎大家的使用</h2>
		</div>
	</div>


	<%@include file="./footer.jsp"%>

</body>
<%@include file="./refrence-foot.jsp"%>
<script type="text/javascript">
	$( ".wrap-box" ).each( function( i, dom ) {
		if ( i % 2 == 0 ) {
			$( dom ).css( {
				backgroundColor : "#f2f2f2"
			} );
		} else {
			$( dom ).css( {
				backgroundColor : "#fff"
			} );
		}
	} );
</script>
</html>
