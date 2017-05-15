<%--
 * header.jsp
 *
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<!-- Do not forget to edit the authorities !! -->
		
	<#list classes as class>
	${clas}	
		<security:authorize access="permitAll">
			<li><a class="fNiv"><spring:message code="master.page.${clas.name?uncap_first}s" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="${clas.name?uncap_first}/list.do"><spring:message code="master.page.${clas.name?uncap_first}s.list" /></a></li>
					<li><a href="${clas.name?uncap_first}/create.do"><spring:message code="master.page.${clas.name?uncap_first}s.create" /></a></li>
				</ul>
			</li>
		</security:authorize>
	</#list>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="registrationForm/registration.do"><spring:message	code="master.page.register" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message code="master.page.profile" /> (<security:authentication property="principal.username" />)</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul><
			/li>
		</security:authorize>

	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>