<#assign _rand = 0.36 />
<?xml version="1.0" encoding="UTF-8"?>

<!-- * PopulateDatabase.xml *
	 * The use of this project is hereby constrained to the conditions of the 
	 * TDG Licence, a copy of which you may download from 
	 * http://www.tdg-seville.info/License.html -->

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	">

	<!-- UserAccounts -->
	<#list classes as class>
		<#if class.superclass?lower_case == "actor">
		
			<!-- ${class.name} -->
			<#list 1..NUM_POPULATE as num>
			
	<bean id="userAccount${class.name}${num}" class="security.UserAccount">
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
		<#if !(class.superclass?lower_case == "actor") && class.name?lower_case != "actor" && !(class.name?lower_case == "authority") && !(class.name?lower_case == "useraccount")>
	<!-- ${class.name} -->
			<#list 1..NUM_POPULATE as num>
	
	<bean id="${class.name?uncap_first}${num}" class="domain.${class.name}">
				<#list class.attributes as atb>
					<#if atb.association == true>
						<#if atb.multiplicity != "OneToMany">
		<property name="${atb.name?uncap_first}" ref="${atb.name?lower_case+num}" />
						</#if>
					<#else>
		<property name="${atb.name?uncap_first}" value="${generateValue(atb,num)}" />
					</#if>
				</#list>
	</bean>
	
			</#list>
			
	<!-- /${class.name}-->
			
		</#if>
	</#list>
</beans>

<#function randInt min max>
  <#local now = .now?long?c />
  <#local randomNum = _rand +
    ("0." + now?substring(now?length-1) + now?substring(now?length-2))?number />
  <#if (randomNum > 1)>
    <#assign _rand = randomNum % 1 />
  <#else>
    <#assign _rand = randomNum />
  </#if>
  <#return (min + ((max - min) * _rand))?round />
</#function>
<#function randDouble min max>
  <#local now = .now?long?c />
  <#local randomNum = _rand +
    ("0." + now?substring(now?length-1) + now?substring(now?length-2))?number />
  <#if (randomNum > 1)>
    <#assign _rand = randomNum % 1 />
  <#else>
    <#assign _rand = randomNum />
  </#if>
  <#return (min + ((max - min) * _rand)) />
</#function>
<#function randDate>
	<#local date1 = randInt(1,30) />
	<#local date2 = randInt(1,12) />
	<#local date3 = randInt(2000,2100)?c />
	<#if (date2==2) && (date1>28)>
		<#assign date1 = 28 />
	</#if>
	<#return date1?string+"/"+date2?string+"/"+date3?string+" 00:00" />
</#function>
<#function generateValue atb num>
	<#local value = "fail"/>
	<#if atb.type == "Double">
		<#local value = randInt(0,10)/>
	</#if>
	<#if atb.type == "int" || atb.type == "Integer">
		<#local value = randInt(0,10)/>
	</#if>
	<#if atb.type == "Date" >
		<#local value = randDate()/>
	</#if>
	<#if atb.type == "String">
		<#local value = atb.name?uncap_first+num />
	</#if>
	<#if atb.type == "boolean" || atb.type == "Boolean">
		<#local value = "false" />
	</#if>
	<#return value />
</#function>