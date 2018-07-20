<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<t:layout title="手机详情">
	<div>
		<h2>品牌:${cellphones.brand}</h2>
		<div><ul>  
          <li>型号：${cellphones.model}</li>
          <li>操作系统：${cellphones.os}</li>
          <li>CPU品牌：${cellphones.cpubrand}</li>
          <li>内存：${cellphones.ram}</li>
          <li>存储容量：${cellphones.storage}</li>
          <li>颜色：${cellphones.color}</li>
          <li>描述：${cellphones.description}</li>
      	</ul></div>
		<div>价格：${cellphones.price}</div>
	</div>


</t:layout>