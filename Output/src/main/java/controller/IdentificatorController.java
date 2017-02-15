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

import services.IdentificatorService;
import controllers.AbstractController;
import domain.Identificator;

@Controller
@RequestMapping("/identificator")
public class IdentificatorController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private IdentificatorService identificatorService;
	
	//Constructors----------------------------------------------
	
	public IdentificatorController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView identificatorList() {
		
		ModelAndView result;
		Collection<Identificator> identificators;
		
		identificators = identificatorService.findAllIdentificatorsGroupByCategory();
		result = new ModelAndView("identificator/list");
		result.addObject("identificators", identificators);
		result.addObject("requestURI","identificator/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Identificator identificator = identificatorService.create();
		result = createEditModelAndView(identificator);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int identificatorId){
        ModelAndView result;
        Identificator identificator;
         
        identificator= identificatorService.findOne(identificatorId);
        Assert.notNull(identificator);
        result= createEditModelAndView(identificator);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Identificator identificator, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(identificator);
        }else{
            try{
                identificatorService.save(identificator);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(identificator, "identificator.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Identificator identificator){
        ModelAndView result;
        try{
            identificatorService.delete(identificator);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(identificator, "identificator.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Identificator identificator){
        ModelAndView result;
         
        result= createEditModelAndView(identificator, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Identificator identificator, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("identificator/edit");
        result.addObject("identificator", identificator);
        result.addObject("message", message);
         
        return result;
 
    }


}
