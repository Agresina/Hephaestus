<?xml version="1.0" encoding="UTF-8"?>

<!-- * PopulateDatabase.xml * * Copyright (C) 2014 Universidad de Sevilla 
	* * The use of this project is hereby constrained to the conditions of the 
	* TDG Licence, a copy of which you may download from * http://www.tdg-seville.info/License.html -->

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	">

	<!-- UserAccounts -->
	
	<#list classes as class>
		<#if class.superclass?lower_case == "actor">
			<#list 1..NUM_POPULATE as num>
				<bean id="userAccount${class.name?lower_case}${num}" class="security.UserAccount">
					<property name="username" value="${class.name?lower_case}${num}" />
					<#assign my_variable = class.name?lower_case+num>
					<property name="password" value="${my_variable?eval}" />
					<property name="authorities">
						<list>
							<bean class="security.Authority">
								<property name="authority" value="${class.name?upper_case}" />
							</bean>
						</list>
					</property>
				</bean>
				
			</#list>
		</#if>
	</#list>
	
	<!-- /UserAccounts -->
	
	<!-- Actors -->
	
	<#list classes as class>
		<#if class.superclass?lower_case == "actor">
			<!-- ${class.name} -->
			
			<#list 1..NUM_POPULATE as num>
				<bean id="${class.name?uncap_first}${num}" class="domain.${class.name}">
				<#list ACTOR.attributes as atb>
					<#if atb.name?lower_case != "useraccount">
					<#if atb.association == true>
						<#if atb.multiplicity != "OneToMany">
							<property name="${atb.name?uncap_first}" ref="${atb.name?uncap_first+num}" />
						</#if>
					<#else>
						<property name="${atb.name?uncap_first}" value="${class.name+num+atb.name?cap_first}" />
					</#if>
					</#if>
				</#list>
				<#list class.attributes as atb>
					<#if atb.association == true>
						<#if atb.multiplicity != "OneToMany">
							<property name="${atb.name?uncap_first}" ref="${atb.name?uncap_first+num}" />
						</#if>
					<#else>
						<property name="${atb.name?uncap_first}" value="${class.name+num+atb.name?cap_first}" />
					</#if>
				</#list>
				<property name="userAccount" ref="userAccount${class.name+num}" />
				</bean>
			</#list>
			
			<!-- /${class.name}-->
		</#if>
	</#list>
	
	<!-- /Actors -->
	
	<#list classes as class>
		<#if !(class.superclass?lower_case == "actor") && class.name?lower_case != "actor">
			<!-- ${class.name} -->
			<#list 1..NUM_POPULATE as num>
				<bean id="${class.name?uncap_first}${num}" class="domain.${class.name}">
				<#list class.attributes as atb>
					<#if atb.association == true>
						<#if atb.multiplicity != "OneToMany">
							<property name="${atb.name?uncap_first}" ref="${atb.name?lower_case+num}" />
						</#if>
					<#else>
						<property name="${atb.name?uncap_first}" value="${atb.name?lower_case+num}" />
					</#if>
				</#list>
				</bean>
			</#list>
			
			<!-- /${class.name}-->
			
		</#if>
	</#list>
	
	
</beans>