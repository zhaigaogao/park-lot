<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="./taglibs.jsp"%>

<c:set var="position" value="${pageContext.request.getParameter(\"position\").split(\">\")}" />

<ol class="breadcrumbs">
    <c:forEach items="${position}" var="result" varStatus="row">
        <c:choose>
            <c:when test="${row.last}">
                <li class="active">${result}</li>
            </c:when>
            <c:otherwise>
                <li>${result}</li><li class="sep">&gt;</li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ol>
