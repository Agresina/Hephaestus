<#if class.attributes??>
	<#list class.attributes as atb>
${class.name?uncap_first}.${atb.name?replace(" ", "")?uncap_first} = ${atb.name}
	</#list>
</#if>

${class.name?uncap_first}.edit = Edit
${class.name?uncap_first}.delete = Delete
${class.name?uncap_first}.save = Save
${class.name?uncap_first}.cancel = Cancel
${class.name?uncap_first}.create = Create ${class.name}

${class.name?uncap_first}.confirm.delete = Are you sure?
${class.name?uncap_first}.commit.error = Error