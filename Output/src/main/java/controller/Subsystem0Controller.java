package controllers.administrator;


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

import services.Subsystem0Service;
import controllers.AbstractController;
import domain.Subsystem0;

@Controller
@RequestMapping("/subsystem0")
public class Subsystem0Controller extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private Subsystem0Service subsystem0Service;
	
	//Constructors----------------------------------------------
	
	public Subsystem0Controller(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView subsystem0List() {
		
		ModelAndView result;
		Collection<Subsystem0> subsystem0s;
		
		subsystem0s = subsystem0Service.findAllSubsystem0sGroupByCategory();
		result = new ModelAndView("subsystem0/list");
		result.addObject("subsystem0s", subsystem0s);
		result.addObject("requestURI","subsystem0/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Subsystem0 subsystem0 = subsystem0Service.create();
		result = createEditModelAndView(subsystem0);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int subsystem0Id){
        ModelAndView result;
        Subsystem0 subsystem0;
         
        subsystem0= subsystem0Service.findOne(subsystem0Id);
        Assert.notNull(subsystem0);
        result= createEditModelAndView(subsystem0);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Subsystem0 subsystem0, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(subsystem0);
        }else{
            try{
                subsystem0Service.save(subsystem0);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(subsystem0, "subsystem0.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Subsystem0 subsystem0){
        ModelAndView result;
        try{
            subsystem0Service.delete(subsystem0);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(subsystem0, "subsystem0.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Subsystem0 subsystem0){
        ModelAndView result;
         
        result= createEditModelAndView(subsystem0, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Subsystem0 subsystem0, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("subsystem0/edit");
        result.addObject("subsystem0", subsystem0);
        result.addObject("message", message);
         
        return result;
 
    }


}
