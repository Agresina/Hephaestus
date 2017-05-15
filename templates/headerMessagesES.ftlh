# messages.properties
#
# The use of this project is hereby constrained to the conditions of the 
# TDG Licence, a copy of which you may download from 
# http://www.tdg-seville.info/License.html

master.page.administrator			= Administrator

master.page.login 					= Entrar
master.page.logout 					= Salir
master.page.register				= Registro

welcome.help = Terminos and condiciones de uso 
welcome.Cookies = Politica de cookies
<#list classes as clas>

master.page.${clas.name?uncap_first} 			= ${clas.name}
master.page.${clas.name?uncap_first}s 			= ${clas.name}s
master.page.${clas.name?uncap_first}.list 		= Listar ${clas.name}
master.page.${clas.name?uncap_first}.create 	= Crear ${clas.name}
</#list>