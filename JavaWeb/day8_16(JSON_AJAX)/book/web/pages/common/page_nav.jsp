<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页开始--%>
<div id="page_nav">
    <%--当前页数大于1的时候，才需要显示首页和上一页--%>
    <c:if test="${requestScope.page.pageNow > 1}">
        <a href="${requestScope.page.url}&pageNow=1">首页</a>
        <a href="${requestScope.page.url}&pageNow=${requestScope.page.pageNow-1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageNow=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>

    <%--如果已经是最后一页，则不显示下一页，末页--%>
    <c:if test="${requestScope.page.pageNow < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNow=${requestScope.page.pageNow+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNow=${requestScope.page.pageTotal}">末页</a>
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
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNow=" + pageNow;

            });
        });
    </script>

</div>
<%--分页结束--%>