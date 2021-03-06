<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
  <link href="${contextPath}/assets/css/form.css" rel="stylesheet">
</head>
<body>
<h1>注册</h1>
 <form:form action="" method="post" commandName="shopuserForm">
 <sec:csrfInput />
    <div>
      <label for="username">用户名</label>
      <form:input type="text" path="username" id="username" />
      <form:errors path="username" cssClass="field-error"></form:errors>
    </div>
    <div>
      <label for="password">密码</label>
      <form:input type="password" path="password" id="password"/>
      <form:errors path="password" cssClass="field-error"></form:errors>
    </div>
    <div> 
      <button type="submit">注册</button>
    </div>
  </form:form>

</body>
</html>