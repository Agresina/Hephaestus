<#if class.attributes??>
	<#list class.attributes as atb>
${class.name?uncap_first}.${atb.name?capitalize?replace(" ", "")?uncap_first} = ${atb.name?capitalize}
	</#list>
</#if>

${class.name?uncap_first}.edit = Editar
${class.name?uncap_first}.delete = Borrar
${class.name?uncap_first}.save = Guardar
${class.name?uncap_first}.cancel = Cancelar
${class.name?uncap_first}.create = Crear ${class.name}

${class.name?uncap_first}.confirm.delete = ¿Está seguro?
${class.name?uncap_first}.commit.error = Error