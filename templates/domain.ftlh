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
	private ${atb.type} ${atb.name};
			</#if>
		</#list>
	</#if>
	
	// Relationships ---------------------------------------------------------
	<#if class.attributes??>
		<#list class.attributes as atb>
			<#if atb.association>
	private ${atb.type} ${atb.name};
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
	@${con}
					</#list>
	</#if>
	<#if atb.association==true>
	@${atb.multiplicity}<#rt>
		<#if atb.optional==false && atb.multiplicity=="OneToMany">
			<#lt>(mappedBy = "${class.name?lower_case}", optional = false)
			<#else>
				<#if atb.optional==false>
					<#lt>(optional = false)
					<#else>
						<#lt>(mappedBy = "${class.name?lower_case}")
				</#if>
		</#if>
	@Valid
	</#if>
	public <#if atb.association==true><#rt>
				<#if atb.multiplicity=="OneToMany" || atb.multiplicity=="ManyToMany">
					<#t>Collection<<#rt>
				</#if>
				<#t>${atb.type?capitalize?replace(" ", "")}<#t>
				<#if atb.association==true>
					<#if atb.multiplicity=="OneToMany" || atb.multiplicity=="ManyToMany">
						<#lt>><#rt>
					</#if>
				</#if>
			</#if>
				<#lt>get${atb.name?capitalize?replace(" ", "")}() {
		return ${atb.name?capitalize?replace(" ", "")?uncap_first};
	}

	public void set${atb.name?capitalize?capitalize?replace(" ", "")}(${atb.type?capitalize?replace(" ", "")} ${atb.name?capitalize?replace(" ", "")?uncap_first}) {
		this.${atb.name?capitalize?replace(" ", "")?uncap_first} = ${atb.name?capitalize?replace(" ", "")?uncap_first};
	}
	
	
	</#list>
}