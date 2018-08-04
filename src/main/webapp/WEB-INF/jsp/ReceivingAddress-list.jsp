<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<t:layout title="收货地址列表">
	<div>
		<a href="${contextPath}/uc/Receiving-address/add">添加</a>
	</div>

	<table>
		<tr><th>姓名</th><th>手机号</th><th>地址</th></tr>
		<c:forEach items="${receivingAddress}" var="receivingAddress">
      <tr>
        <td>${receivingAddress.consigneeName}</td>
        <td>${receivingAddress.cellphoneNumber}</td>
        <td>${receivingAddress.detailedAddress}</td>
        <td><a href="${contextPath}/uc/Receiving-address/${receivingAddress.id}/edit">修改</a></td>
      </tr>
    </c:forEach>
	</table>
</t:layout>