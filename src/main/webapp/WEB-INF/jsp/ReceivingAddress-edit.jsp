<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="title" value="${receivingAddress.id == null ? '添加收货地址' : '修改收货地址'}"></c:set>
<t:layout title="${title}">

 	<jsp:attribute name="css">
    	<link href="${contextPath}/assets/css/form.css" rel="stylesheet">
    </jsp:attribute>


	<jsp:body>
	<form:form action="" method="post" commandName="receivingAddress">
	    <sec:csrfInput />
		<div>
			<label for="consigneeName">收货人姓名</label> 
			<form:input type="text" path="consigneeName" id="consigneeName"/>
			<form:errors path="consigneeName" cssClass="field-error" />
		</div>
		<div>
			<label for="cellphoneNumber">手机号</label> 
			<form:input type="text" path="cellphoneNumber" id="cellphoneNumber"/>
			<form:errors path="cellphoneNumber" cssClass="field-error" />
		</div>
		
		<div>
        	<label for="detailedAddress">详细地址</label>
        	<form:textarea path="detailedAddress" />
        	<form:errors path="detailedAddress" cssClass="field-error" />      
      	</div>

		
		<div>
			<button type="submit">确定</button>
		</div>

	</form:form>
	</jsp:body>
</t:layout>