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

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	//Constructors----------------------------------------------
	
	public ActorController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView actorList() {
		
		ModelAndView result;
		Collection<Actor> actors;
		
		actors = actorService.findAllActorsGroupByCategory();
		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("requestURI","actor/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Actor actor = actorService.create();
		result = createEditModelAndView(actor);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int actorId){
        ModelAndView result;
        Actor actor;
         
        actor= actorService.findOne(actorId);
        Assert.notNull(actor);
        result= createEditModelAndView(actor);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Actor actor, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(actor);
        }else{
            try{
                actorService.save(actor);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(actor, "actor.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Actor actor){
        ModelAndView result;
        try{
            actorService.delete(actor);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(actor, "actor.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Actor actor){
        ModelAndView result;
         
        result= createEditModelAndView(actor, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Actor actor, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("actor/edit");
        result.addObject("actor", actor);
        result.addObject("message", message);
         
        return result;
 
    }


}
