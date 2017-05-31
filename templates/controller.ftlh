package controllers;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.${class.name}Service;
import controllers.AbstractController;
import domain.${class.name};

@Controller
@RequestMapping("/${class.name?uncap_first}")
public class ${class.name}Controller extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ${class.name}Service ${class.name?uncap_first}Service;
	
	//Constructors----------------------------------------------
	
	public ${class.name}Controller(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView ${class.name?uncap_first}List() {
		
		ModelAndView result;
		Collection<${class.name}> ${class.name?uncap_first}s;
		
		${class.name?uncap_first}s = ${class.name?uncap_first}Service.findAll();
		result = new ModelAndView("${class.name?uncap_first}/list");
		result.addObject("${class.name?uncap_first}s", ${class.name?uncap_first}s);
		result.addObject("requestURI","${class.name?uncap_first}/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Service.create();
		result = createEditModelAndView(${class.name?uncap_first});
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int ${class.name?uncap_first}Id){
        ModelAndView result;
        ${class.name} ${class.name?uncap_first};
         
        ${class.name?uncap_first}= ${class.name?uncap_first}Service.findOne(${class.name?uncap_first}Id);
        Assert.notNull(${class.name?uncap_first});
        result= createEditModelAndView(${class.name?uncap_first});
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid ${class.name} ${class.name?uncap_first}, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(${class.name?uncap_first});
        }else{
            try{
                ${class.name?uncap_first}Service.save(${class.name?uncap_first});
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(${class.name?uncap_first}, "${class.name?uncap_first}.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(${class.name} ${class.name?uncap_first}){
        ModelAndView result;
        try{
            ${class.name?uncap_first}Service.delete(${class.name?uncap_first});
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(${class.name?uncap_first}, "${class.name?uncap_first}.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(${class.name} ${class.name?uncap_first}){
        ModelAndView result;
         
        result= createEditModelAndView(${class.name?uncap_first}, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(${class.name} ${class.name?uncap_first}, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("${class.name?uncap_first}/edit");
        result.addObject("${class.name?uncap_first}", ${class.name?uncap_first});
        result.addObject("message", message);
         
        return result;
 
    }


}
