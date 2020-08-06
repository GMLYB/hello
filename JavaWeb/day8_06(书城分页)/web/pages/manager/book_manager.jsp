<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
	<%--静态包含样式和地址--%>
	<%@include file="/pages/common/head.jsp"%>
	<script>
		$(function () {
			$("a.deleteClass").click(function () {
			    return confirm("确定要删除《"+$(this).parent().parent().find("td:first").text()+"》吗？")
            });
        });
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%--静态包含 管理菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getbook&id=${book.id}&pageNow=${requestScope.page.pageNow}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=deletebook&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		<br>
		<div id="page_nav">
			<%--当前页数大于1的时候，才需要显示首页和上一页--%>
			<c:if test="${requestScope.page.pageNow > 1}">
				<a href="manager/bookServlet?action=page&pageNow=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNow=${requestScope.page.pageNow-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
			<c:choose>
				<%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
				</c:when>
				<%--情况2：总页码大于5的情况--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
						<c:when test="${requestScope.page.pageNow <= 3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>
						<%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
						<c:when test="${requestScope.page.pageNow > requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>
						<%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNow-2}"/>
							<c:set var="end" value="${requestScope.page.pageNow+2}"/>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>

			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i == requestScope.page.pageNow}">
					【${i}】
				</c:if>
				<c:if test="${i != requestScope.page.pageNow}">
					<a href="manager/bookServlet?action=page&pageNow=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--页码输出的结束--%>

			<%--如果已经是最后一页，则不显示下一页，末页--%>
			<c:if test="${requestScope.page.pageNow < requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNow=${requestScope.page.pageNow+1}">下一页</a>
				<a href="manager/bookServlet?action=page&pageNow=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
			到第<input value="${param.pageNow}"  name="pn" id="pn_input"/>页
			<input  id="searchPageBtn" type="button" value="确定">
			<script>
				$(function () {
				    //跳转到指定页码
					$("#searchPageBtn").click(function () {
						var pageNow = $("#pn_input").val();
                        // javaScript 语言中提供了一个 location 地址栏对象
                        //它有一个属性叫 href 它可以获取浏览器地址栏中的地址
						location.href = "${pageScope.basePath}manager/bookServlet?action=page&pageNow=" + pageNow;

                    });
                });
			</script>

		</div>
	</div>

	<%--替换掉页脚商标--%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>