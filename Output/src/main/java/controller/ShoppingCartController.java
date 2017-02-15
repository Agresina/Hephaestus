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

import services.ShoppingCartService;
import controllers.AbstractController;
import domain.ShoppingCart;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//Constructors----------------------------------------------
	
	public ShoppingCartController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView shoppingCartList() {
		
		ModelAndView result;
		Collection<ShoppingCart> shoppingCarts;
		
		shoppingCarts = shoppingCartService.findAllShoppingCartsGroupByCategory();
		result = new ModelAndView("shoppingCart/list");
		result.addObject("shoppingCarts", shoppingCarts);
		result.addObject("requestURI","shoppingCart/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		ShoppingCart shoppingCart = shoppingCartService.create();
		result = createEditModelAndView(shoppingCart);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int shoppingCartId){
        ModelAndView result;
        ShoppingCart shoppingCart;
         
        shoppingCart= shoppingCartService.findOne(shoppingCartId);
        Assert.notNull(shoppingCart);
        result= createEditModelAndView(shoppingCart);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid ShoppingCart shoppingCart, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(shoppingCart);
        }else{
            try{
                shoppingCartService.save(shoppingCart);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(shoppingCart, "shoppingCart.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(ShoppingCart shoppingCart){
        ModelAndView result;
        try{
            shoppingCartService.delete(shoppingCart);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(shoppingCart, "shoppingCart.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(ShoppingCart shoppingCart){
        ModelAndView result;
         
        result= createEditModelAndView(shoppingCart, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(ShoppingCart shoppingCart, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("shoppingCart/edit");
        result.addObject("shoppingCart", shoppingCart);
        result.addObject("message", message);
         
        return result;
 
    }


}
