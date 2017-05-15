<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<#assign pepe="${" >

<form:form action="${class.name?uncap_first}/edit.do" modelAttribute="${class.name?uncap_first}">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<#if class.attributes??>
		<#list class.attributes as atb>
	<acme:textbox path="${atb.name?capitalize?replace(" ", "")?uncap_first}" code="${class.name?uncap_first}.${atb.name?capitalize?replace(" ", "")?uncap_first}" />
	<br />
		</#list>
	</#if>

	<!---------------------------- BOTONES -------------------------->

	<input type="submit" name="save"
		value="<spring:message code="${class.name?uncap_first}.save" />" />

	<jstl:if test="${pepe}${class.name?uncap_first}.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="${class.name?uncap_first}.delete" />"
			onclick="return confirm('<spring:message code="${class.name?uncap_first}.confirm.delete" />')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="${class.name?uncap_first}.cancel" />"
		onclick="javascript: window.location.replace('${class.name?uncap_first}/list.do')" />

</form:form>