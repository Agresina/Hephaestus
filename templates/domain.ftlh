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
				<#if atb.constraints??>
					<#list atb.constraints as con>
	@${con}
					</#list></#if>
	private ${atb.type} ${atb.name};
			</#if>
		</#list>
	</#if>
	
	// Relationships ---------------------------------------------------------
	<#if class.attributes??>
		<#list class.attributes as atb>
			<#if atb.association>
				<#if atb.constraints??>
					<#list atb.constraints as con>
	@${con}
					</#list></#if>
	private ${atb.type} ${atb.name};
			</#if>
		</#list>
	</#if>
	
	// Constructors -----------------------------------------------------------
	public ${class.name}() {
		super();		
	}
	
	
	<#list class.attributes as atb>
	public ${atb.type?cap_first} get${atb.name}() {
		return ${atb.name};
	}
		
	public void set${atb.name?cap_first}(${atb.type} ${atb.name}) {
		this.${atb.name} = ${atb.name};
	}	
	</#list>
}