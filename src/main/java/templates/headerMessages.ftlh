# messages.properties
#
# The use of this project is hereby constrained to the conditions of the 
# TDG Licence, a copy of which you may download from 
# http://www.tdg-seville.info/License.html

master.page.administrator			= Administrator

master.page.login 					= Login
master.page.logout 					= Logout
master.page.register				= Register

welcome.help = Terms and conditions of use 
welcome.Cookies = Cookies Politics

<#list classes as clas>

master.page.${clas.name?uncap_first} 			= ${clas.name}
master.page.${clas.name?uncap_first}s 			= ${class.name}s
master.page.${class.name?uncap_first}.list 		= List ${class.name}
master.page.${class.name?uncap_first}.create 	= Create ${class.name}

</#list>