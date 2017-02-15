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

import services.ClerkService;
import controllers.AbstractController;
import domain.Clerk;

@Controller
@RequestMapping("/clerk")
public class ClerkController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ClerkService clerkService;
	
	//Constructors----------------------------------------------
	
	public ClerkController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView clerkList() {
		
		ModelAndView result;
		Collection<Clerk> clerks;
		
		clerks = clerkService.findAllClerksGroupByCategory();
		result = new ModelAndView("clerk/list");
		result.addObject("clerks", clerks);
		result.addObject("requestURI","clerk/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Clerk clerk = clerkService.create();
		result = createEditModelAndView(clerk);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int clerkId){
        ModelAndView result;
        Clerk clerk;
         
        clerk= clerkService.findOne(clerkId);
        Assert.notNull(clerk);
        result= createEditModelAndView(clerk);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Clerk clerk, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(clerk);
        }else{
            try{
                clerkService.save(clerk);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(clerk, "clerk.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Clerk clerk){
        ModelAndView result;
        try{
            clerkService.delete(clerk);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(clerk, "clerk.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Clerk clerk){
        ModelAndView result;
         
        result= createEditModelAndView(clerk, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Clerk clerk, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("clerk/edit");
        result.addObject("clerk", clerk);
        result.addObject("message", message);
         
        return result;
 
    }


}
