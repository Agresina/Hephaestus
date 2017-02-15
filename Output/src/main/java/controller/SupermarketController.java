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

import services.SupermarketService;
import controllers.AbstractController;
import domain.Supermarket;

@Controller
@RequestMapping("/supermarket")
public class SupermarketController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private SupermarketService supermarketService;
	
	//Constructors----------------------------------------------
	
	public SupermarketController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView supermarketList() {
		
		ModelAndView result;
		Collection<Supermarket> supermarkets;
		
		supermarkets = supermarketService.findAllSupermarketsGroupByCategory();
		result = new ModelAndView("supermarket/list");
		result.addObject("supermarkets", supermarkets);
		result.addObject("requestURI","supermarket/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Supermarket supermarket = supermarketService.create();
		result = createEditModelAndView(supermarket);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int supermarketId){
        ModelAndView result;
        Supermarket supermarket;
         
        supermarket= supermarketService.findOne(supermarketId);
        Assert.notNull(supermarket);
        result= createEditModelAndView(supermarket);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Supermarket supermarket, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(supermarket);
        }else{
            try{
                supermarketService.save(supermarket);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(supermarket, "supermarket.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Supermarket supermarket){
        ModelAndView result;
        try{
            supermarketService.delete(supermarket);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(supermarket, "supermarket.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Supermarket supermarket){
        ModelAndView result;
         
        result= createEditModelAndView(supermarket, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Supermarket supermarket, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("supermarket/edit");
        result.addObject("supermarket", supermarket);
        result.addObject("message", message);
         
        return result;
 
    }


}
