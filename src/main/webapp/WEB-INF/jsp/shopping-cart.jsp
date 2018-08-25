<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<t:layout title="购物车详情">

  <c:choose>
    <c:when test="${shoppingCart.items.isEmpty()}">
      <div>空空如也，<a href="${contextPath}/">去逛一逛</a></div>
    </c:when>
    <c:otherwise>
	
		<table>
			<tr>
				<th>商品</th>
				<th>单价</th>
				<th>数量</th>
			</tr>
			<c:forEach items="${shoppingCart.items}" var="item">
				<tr>
					<td>${item.cellphone.brand}${item.cellphone.model}
						${item.cellphone.color}</td>
					<td>￥${item.cellphone.price / 100}</td>

				<td>
					<form action="${contextPath}/uc/shopping-cart/item-dec"
						method="post" class="inline">
						<sec:csrfInput />
						<input type="hidden" name="cellphoneId"
							value="${item.cellphone.id}">
						<button type="submit">-</button>
					</form> ${item.quantity}
					<form action="${contextPath}/uc/shopping-cart/item-inc"
						method="post" class="inline">
						<sec:csrfInput />
						<input type="hidden" name="cellphoneId"
							value="${item.cellphone.id}">
						<button type="submit">+</button>
					</form>
				</td>

				<td>
					<form
						action="${contextPath}/uc/shopping-cart/delete"
						method="post">
						<sec:csrfInput />
						<input type="hidden" name="cellphoneId" value="${item.cellphone.id}">
						<input type="hidden" name="shopuserId" value="${item.shopuser.id}">
						<button type="submit">删除</button>
					</form>
				</td>
				</tr>
				
			</c:forEach>
		</table>
	<div>总金额: ￥${shoppingCart.totalCost() / 100}</div>
	<a href="${contextPath}/uc/orders/add">马上结算</a>
</c:otherwise>
</c:choose>
</t:layout>
