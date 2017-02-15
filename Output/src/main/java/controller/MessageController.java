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

import services.MessageService;
import controllers.AbstractController;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private MessageService messageService;
	
	//Constructors----------------------------------------------
	
	public MessageController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView messageList() {
		
		ModelAndView result;
		Collection<Message> messages;
		
		messages = messageService.findAllMessagesGroupByCategory();
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI","message/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Message message = messageService.create();
		result = createEditModelAndView(message);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int messageId){
        ModelAndView result;
        Message message;
         
        message= messageService.findOne(messageId);
        Assert.notNull(message);
        result= createEditModelAndView(message);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Message message, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(message);
        }else{
            try{
                messageService.save(message);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(message, "message.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Message message){
        ModelAndView result;
        try{
            messageService.delete(message);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(message, "message.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Message message){
        ModelAndView result;
         
        result= createEditModelAndView(message, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Message message, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("message/edit");
        result.addObject("message", message);
        result.addObject("message", message);
         
        return result;
 
    }


}
