<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>UserList</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
	<div class="row">
		<div class="col-lg-offset-2 col-lg-8 form-group">
			<form action="servlet/QueryServlet" method="get">
			<input type="text" value="${pageBean.currentPage }" name="currentPage" maxlength="3" style="display:none">
				<input class="form-control" style="width：60%" type="text"
					placeholder="模糊查询" name="condition" value="${condition }"> 
					<input type="submit" class="btn btn-primary form-control" style="width：40%" value="查询 ">
			</form>
		</div>
		<div class="col-lg-2"></div>

	</div>
	<div class="row">
		<div class="col-lg-offset-2 col-lg-8">
			<div class="table">
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr class="info">
							<td>ID</td>
							<td>用户名</td>
							<td>邮箱</td>
							<td>年级</td>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty pageBean.pageData }">
								<c:forEach var="user" items="${pageBean.pageData }"
									varStatus="vs">
									<tr>
										<td>${user.id }</td>
										<td>${user.username }</td>
										<td>${user.email }</td>
										<td>${user.grade }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4" align="center">暂无数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4" align="center">共 ${pageBean.totalCount }
								条纪录&nbsp;当前 ${pageBean.currentPage } / ${pageBean.totalPage }页&nbsp;&nbsp;
								<a
								href="servlet/QueryServlet?currentPage=1&condition=${condition }">首页</a>
								<!-- 上一页判断 --> <c:choose>
									<c:when test="${pageBean.currentPage == 1 }">
										<a disabled="disabled">上一页</a>
									</c:when>
									<c:otherwise>
										<a
											href="servlet/QueryServlet?currentPage=${pageBean.currentPage-1 }&condition=${condition }">上一页</a>
									</c:otherwise>
								</c:choose> <!-- 下一页判断 --> <c:choose>
									<c:when test="${pageBean.currentPage == pageBean.totalPage }">
										<a disabled="disabled">下一页</a>
									</c:when>
									<c:otherwise>
										<a
											href="servlet/QueryServlet?currentPage=${pageBean.currentPage+1 }&condition=${condition }">下一页</a>
									</c:otherwise>
								</c:choose> <a
								href="servlet/QueryServlet?currentPage=${pageBean.totalPage }&condition=${condition }">末页</a>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<form action="servlet/QueryServlet">
									<input type="text" value="${pageBean.currentPage }"
										name="currentPage" maxlength="3"> 
										<input type="text"
										value="${condition }" name="condition" style="display:none"> <input
										type="submit" value="go">
								</form>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</body>
</html>
