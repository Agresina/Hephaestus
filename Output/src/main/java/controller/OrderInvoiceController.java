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

import services.OrderInvoiceService;
import controllers.AbstractController;
import domain.OrderInvoice;

@Controller
@RequestMapping("/orderInvoice")
public class OrderInvoiceController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private OrderInvoiceService orderInvoiceService;
	
	//Constructors----------------------------------------------
	
	public OrderInvoiceController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView orderInvoiceList() {
		
		ModelAndView result;
		Collection<OrderInvoice> orderInvoices;
		
		orderInvoices = orderInvoiceService.findAllOrderInvoicesGroupByCategory();
		result = new ModelAndView("orderInvoice/list");
		result.addObject("orderInvoices", orderInvoices);
		result.addObject("requestURI","orderInvoice/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		OrderInvoice orderInvoice = orderInvoiceService.create();
		result = createEditModelAndView(orderInvoice);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int orderInvoiceId){
        ModelAndView result;
        OrderInvoice orderInvoice;
         
        orderInvoice= orderInvoiceService.findOne(orderInvoiceId);
        Assert.notNull(orderInvoice);
        result= createEditModelAndView(orderInvoice);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid OrderInvoice orderInvoice, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(orderInvoice);
        }else{
            try{
                orderInvoiceService.save(orderInvoice);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(orderInvoice, "orderInvoice.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(OrderInvoice orderInvoice){
        ModelAndView result;
        try{
            orderInvoiceService.delete(orderInvoice);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(orderInvoice, "orderInvoice.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(OrderInvoice orderInvoice){
        ModelAndView result;
         
        result= createEditModelAndView(orderInvoice, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(OrderInvoice orderInvoice, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("orderInvoice/edit");
        result.addObject("orderInvoice", orderInvoice);
        result.addObject("message", message);
         
        return result;
 
    }


}
