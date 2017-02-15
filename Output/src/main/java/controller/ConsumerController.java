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

import services.ConsumerService;
import controllers.AbstractController;
import domain.Consumer;

@Controller
@RequestMapping("/consumer")
public class ConsumerController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ConsumerService consumerService;
	
	//Constructors----------------------------------------------
	
	public ConsumerController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView consumerList() {
		
		ModelAndView result;
		Collection<Consumer> consumers;
		
		consumers = consumerService.findAllConsumersGroupByCategory();
		result = new ModelAndView("consumer/list");
		result.addObject("consumers", consumers);
		result.addObject("requestURI","consumer/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Consumer consumer = consumerService.create();
		result = createEditModelAndView(consumer);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int consumerId){
        ModelAndView result;
        Consumer consumer;
         
        consumer= consumerService.findOne(consumerId);
        Assert.notNull(consumer);
        result= createEditModelAndView(consumer);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Consumer consumer, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(consumer);
        }else{
            try{
                consumerService.save(consumer);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(consumer, "consumer.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Consumer consumer){
        ModelAndView result;
        try{
            consumerService.delete(consumer);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(consumer, "consumer.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Consumer consumer){
        ModelAndView result;
         
        result= createEditModelAndView(consumer, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Consumer consumer, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("consumer/edit");
        result.addObject("consumer", consumer);
        result.addObject("message", message);
         
        return result;
 
    }


}
