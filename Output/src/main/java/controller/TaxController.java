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

import services.TaxService;
import controllers.AbstractController;
import domain.Tax;

@Controller
@RequestMapping("/tax")
public class TaxController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private TaxService taxService;
	
	//Constructors----------------------------------------------
	
	public TaxController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView taxList() {
		
		ModelAndView result;
		Collection<Tax> taxs;
		
		taxs = taxService.findAllTaxsGroupByCategory();
		result = new ModelAndView("tax/list");
		result.addObject("taxs", taxs);
		result.addObject("requestURI","tax/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Tax tax = taxService.create();
		result = createEditModelAndView(tax);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int taxId){
        ModelAndView result;
        Tax tax;
         
        tax= taxService.findOne(taxId);
        Assert.notNull(tax);
        result= createEditModelAndView(tax);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Tax tax, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(tax);
        }else{
            try{
                taxService.save(tax);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(tax, "tax.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Tax tax){
        ModelAndView result;
        try{
            taxService.delete(tax);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(tax, "tax.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Tax tax){
        ModelAndView result;
         
        result= createEditModelAndView(tax, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Tax tax, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("tax/edit");
        result.addObject("tax", tax);
        result.addObject("message", message);
         
        return result;
 
    }


}
