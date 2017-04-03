package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
<#list class.imports as imp>
import ${imp};
</#list>

@Entity
@Access(AccessType.PROPERTY)
public class ${class.name} extends ${class.superclass} {
	
	// Attributes ------------------------------------------------------------
	<#if class.attributes??>
		<#list class.attributes as atb>
			<#if !atb.association>
	private <#if isMultiple(atb)>Collection<</#if>${atb.type}<#if isMultiple(atb)>></#if> ${atb.name}<#if isMultiple(atb)>s</#if>;
			</#if>
		</#list>
	</#if>
	
	// Relationships ---------------------------------------------------------
	<#if class.attributes??>
		<#list class.attributes as atb>
			<#if atb.association>
	private <#if isMultiple(atb)>Collection<</#if>${atb.type}<#if isMultiple(atb)>></#if> ${atb.name}<#if isMultiple(atb)>s</#if>;
			</#if>
		</#list>
	</#if>
	
	// Constructors -----------------------------------------------------------
	public ${class.name}() {
		super();		
	}
	
	
	<#list class.attributes as atb>
	<#if atb.constraints??>
					<#list atb.constraints as con>
	@${con?no_esc}<#rt>
					</#list>
	</#if>
	<#if atb.association==true>
	@${atb.multiplicity}<#rt>
		<#if atb.optional==false && atb.multiplicity=="OneToMany">
			<#lt>(mappedBy = "${class.name?uncap_first}", optional = false)
			<#else>
				<#if atb.optional==false>
					<#lt>(optional = false)
					<#else>
						<#if atb.multiplicity=="OneToMany">
							<#lt>(mappedBy = "${class.name}")
						</#if>
				</#if>
		</#if>
	@Valid
	</#if>
	public <#if isMultiple(atb)><#rt>
					<#t>Collection<<#rt>
			</#if>
			<#lt>${atb.type}<#t>
				<#if isMultiple(atb)><#rt>
						<#lt>><#rt>
				</#if>
				<#lt> get${atb.name?cap_first}<#if isMultiple(atb)>s</#if>() {
		return ${atb.name}<#if isMultiple(atb)>s</#if>;
	}

	public void set${atb.name?cap_first}<#rt>
	<#lt><#if isMultiple(atb)>s</#if><#lt>(<#rt>
	<#if isBasic(atb.type)><#lt>${atb.type}<#rt>
		<#else><#lt>${atb.type?cap_first}<#rt>
	</#if>
	<#lt> ${atb.name}<#rt>
	<#lt>) {
		this.${atb.name}<#if isMultiple(atb)>s</#if> = ${atb.name}<#if isMultiple(atb)>s</#if>;
	}
	
	
	</#list>
	
	<#function isBasic type>
		<#local res = false>
		<#if type?lower_case == "int" || type?lower_case == "string" || type?lower_case == "double">
			<#local res = true>
		</#if>
		<#return res>
	</#function>
	
	<#function isMultiple atb>
		<#local res = false>
			<#if atb.association == true>
				<#if atb.multiplicity=="OneToMany" || atb.multiplicity=="ManyToMany">
					<#local res = true>
				</#if>
			</#if>
		<#return res>
	</#function>
}