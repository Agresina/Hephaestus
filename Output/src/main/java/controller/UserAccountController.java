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

import services.UserAccountService;
import controllers.AbstractController;
import domain.UserAccount;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private UserAccountService userAccountService;
	
	//Constructors----------------------------------------------
	
	public UserAccountController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView userAccountList() {
		
		ModelAndView result;
		Collection<UserAccount> userAccounts;
		
		userAccounts = userAccountService.findAllUserAccountsGroupByCategory();
		result = new ModelAndView("userAccount/list");
		result.addObject("userAccounts", userAccounts);
		result.addObject("requestURI","userAccount/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		UserAccount userAccount = userAccountService.create();
		result = createEditModelAndView(userAccount);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int userAccountId){
        ModelAndView result;
        UserAccount userAccount;
         
        userAccount= userAccountService.findOne(userAccountId);
        Assert.notNull(userAccount);
        result= createEditModelAndView(userAccount);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid UserAccount userAccount, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(userAccount);
        }else{
            try{
                userAccountService.save(userAccount);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(userAccount, "userAccount.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(UserAccount userAccount){
        ModelAndView result;
        try{
            userAccountService.delete(userAccount);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(userAccount, "userAccount.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(UserAccount userAccount){
        ModelAndView result;
         
        result= createEditModelAndView(userAccount, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(UserAccount userAccount, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("userAccount/edit");
        result.addObject("userAccount", userAccount);
        result.addObject("message", message);
         
        return result;
 
    }


}
