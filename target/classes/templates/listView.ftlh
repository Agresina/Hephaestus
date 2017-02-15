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

<security:authorize access="permitAll)">
	<div>
		<H5>
			<a href="${class.name?uncap_first}/create.do"> <spring:message
					code="${class.name?uncap_first}.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="${class.name?uncap_first}s" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="${class.name?uncap_first}/edit.do?${class.name?uncap_first}Id=${row.id}"> <spring:message
					code="${class.name?uncap_first}.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	<#if class.attributes??>
		<#list class.attributes as atb>
			<spring:message code="${class.name?uncap_first}.${atb.name?capitalize?replace(" ", "")?uncap_first}" var="${atb.name?capitalize?replace(" ", "")?uncap_first}" />
			<display:column property="${atb.name?capitalize?replace(" ", "")?uncap_first}" title="\$\{${atb.name?capitalize?replace(" ", "")?uncap_first}\}" sortable="true" />
		</#list>
	</#if>

</display:table>