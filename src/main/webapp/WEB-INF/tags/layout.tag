<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="css" fragment="true" %> <!-- fragment设为true意味着该参数的值是标记片段 -->
<%@ attribute name="js" fragment="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${title}</title>
  <jsp:invoke fragment="css"/> <!-- 将css标记片段插入此处 -->
</head>
<body>
  <div class="header"> 
    <sec:authorize access="isAuthenticated()">
      <sec:authentication property="principal.username" />
      <form action="${contextPath}/logout" method="post" style="display: inline;">
        <sec:csrfInput />
        <button type="submit">退出</button>
      </form>  
    </sec:authorize>  
    
    <sec:authorize access="isAnonymous()">
      <a href="${contextPath}/login">登录</a>
      <a href="${contextPath}/shopuser/add">注册</a>
    </sec:authorize>
  </div>
  
  <div class="content">
    <h1>${title}</h1>
    <jsp:doBody />
  </div>
  
  <div class="footer">copyright 2018</div>
   
  <jsp:invoke fragment="js"/>
</body>
</html>