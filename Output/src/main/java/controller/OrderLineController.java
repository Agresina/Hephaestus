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

import services.OrderLineService;
import controllers.AbstractController;
import domain.OrderLine;

@Controller
@RequestMapping("/orderLine")
public class OrderLineController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private OrderLineService orderLineService;
	
	//Constructors----------------------------------------------
	
	public OrderLineController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView orderLineList() {
		
		ModelAndView result;
		Collection<OrderLine> orderLines;
		
		orderLines = orderLineService.findAllOrderLinesGroupByCategory();
		result = new ModelAndView("orderLine/list");
		result.addObject("orderLines", orderLines);
		result.addObject("requestURI","orderLine/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		OrderLine orderLine = orderLineService.create();
		result = createEditModelAndView(orderLine);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int orderLineId){
        ModelAndView result;
        OrderLine orderLine;
         
        orderLine= orderLineService.findOne(orderLineId);
        Assert.notNull(orderLine);
        result= createEditModelAndView(orderLine);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid OrderLine orderLine, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(orderLine);
        }else{
            try{
                orderLineService.save(orderLine);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(orderLine, "orderLine.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(OrderLine orderLine){
        ModelAndView result;
        try{
            orderLineService.delete(orderLine);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(orderLine, "orderLine.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(OrderLine orderLine){
        ModelAndView result;
         
        result= createEditModelAndView(orderLine, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(OrderLine orderLine, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("orderLine/edit");
        result.addObject("orderLine", orderLine);
        result.addObject("message", message);
         
        return result;
 
    }


}
