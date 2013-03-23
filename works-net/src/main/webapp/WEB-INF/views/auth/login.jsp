<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="body">
    <c:if test="${not empty param.error}">
        <div class=".error"><tags:message code="login.loginerror"/>
            : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
    </c:if>
    <form method="POST" action="<c:url value="/j_spring_security_check" />">
        <input id="j_username" type="text" placeholder="<tags:message code="login.login"/>" name="j_username"/>
        <input id="j_password" type="password" placeholder="<tags:message code="login.password"/>" name="j_password"/>
        <label for="_spring_security_remember_me"><tags:message code="login.remember"/></label>
        <input id="_spring_security_remember_me" type="checkbox" name="_spring_security_remember_me"/>

        <div>
            <input type="submit" value="Login"/>
            <input type="reset" value="Reset"/>
        </div>
    </form>

</c:set>

<%@ include file="../../layouts/index.jsp" %>