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

import services.QuantityService;
import controllers.AbstractController;
import domain.Quantity;

@Controller
@RequestMapping("/quantity")
public class QuantityController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private QuantityService quantityService;
	
	//Constructors----------------------------------------------
	
	public QuantityController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView quantityList() {
		
		ModelAndView result;
		Collection<Quantity> quantitys;
		
		quantitys = quantityService.findAllQuantitysGroupByCategory();
		result = new ModelAndView("quantity/list");
		result.addObject("quantitys", quantitys);
		result.addObject("requestURI","quantity/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Quantity quantity = quantityService.create();
		result = createEditModelAndView(quantity);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int quantityId){
        ModelAndView result;
        Quantity quantity;
         
        quantity= quantityService.findOne(quantityId);
        Assert.notNull(quantity);
        result= createEditModelAndView(quantity);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Quantity quantity, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(quantity);
        }else{
            try{
                quantityService.save(quantity);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(quantity, "quantity.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Quantity quantity){
        ModelAndView result;
        try{
            quantityService.delete(quantity);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(quantity, "quantity.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Quantity quantity){
        ModelAndView result;
         
        result= createEditModelAndView(quantity, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Quantity quantity, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("quantity/edit");
        result.addObject("quantity", quantity);
        result.addObject("message", message);
         
        return result;
 
    }


}
