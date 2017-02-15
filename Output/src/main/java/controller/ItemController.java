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

import services.ItemService;
import controllers.AbstractController;
import domain.Item;

@Controller
@RequestMapping("/item")
public class ItemController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	//Constructors----------------------------------------------
	
	public ItemController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView itemList() {
		
		ModelAndView result;
		Collection<Item> items;
		
		items = itemService.findAllItemsGroupByCategory();
		result = new ModelAndView("item/list");
		result.addObject("items", items);
		result.addObject("requestURI","item/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Item item = itemService.create();
		result = createEditModelAndView(item);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int itemId){
        ModelAndView result;
        Item item;
         
        item= itemService.findOne(itemId);
        Assert.notNull(item);
        result= createEditModelAndView(item);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Item item, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(item);
        }else{
            try{
                itemService.save(item);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(item, "item.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Item item){
        ModelAndView result;
        try{
            itemService.delete(item);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(item, "item.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Item item){
        ModelAndView result;
         
        result= createEditModelAndView(item, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Item item, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("item/edit");
        result.addObject("item", item);
        result.addObject("message", message);
         
        return result;
 
    }


}
