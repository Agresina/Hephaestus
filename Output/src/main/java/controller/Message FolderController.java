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

import services.Message FolderService;
import controllers.AbstractController;
import domain.Message Folder;

@Controller
@RequestMapping("/message Folder")
public class Message FolderController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private Message FolderService message FolderService;
	
	//Constructors----------------------------------------------
	
	public Message FolderController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView message FolderList() {
		
		ModelAndView result;
		Collection<Message Folder> message Folders;
		
		message Folders = message FolderService.findAllMessage FoldersGroupByCategory();
		result = new ModelAndView("message Folder/list");
		result.addObject("message Folders", message Folders);
		result.addObject("requestURI","message Folder/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Message Folder message Folder = message FolderService.create();
		result = createEditModelAndView(message Folder);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int message FolderId){
        ModelAndView result;
        Message Folder message Folder;
         
        message Folder= message FolderService.findOne(message FolderId);
        Assert.notNull(message Folder);
        result= createEditModelAndView(message Folder);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Message Folder message Folder, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(message Folder);
        }else{
            try{
                message FolderService.save(message Folder);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(message Folder, "message Folder.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Message Folder message Folder){
        ModelAndView result;
        try{
            message FolderService.delete(message Folder);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(message Folder, "message Folder.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Message Folder message Folder){
        ModelAndView result;
         
        result= createEditModelAndView(message Folder, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Message Folder message Folder, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("message Folder/edit");
        result.addObject("message Folder", message Folder);
        result.addObject("message", message);
         
        return result;
 
    }


}
