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

<security:authorize access="permitAll">
	<div>
		<H5>
			<a href="warehouse/create.do"> <spring:message
					code="warehouse.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="warehouses" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="warehouse/edit.do?warehouseId=${row.id}> <spring:message
					code="warehouse.edit" />
			</a>
		</display:column>
	</security:authorize>
	
			<spring:message code="warehouse.address" var="address" />
			<display:column property="address" title="\$\{address\}" sortable="true" />
			<spring:message code="warehouse.name" var="name" />
			<display:column property="name" title="\$\{name\}" sortable="true" />
			<spring:message code="warehouse.stock" var="stock" />
			<display:column property="stock" title="\$\{stock\}" sortable="true" />

</display:table>