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

import services.ExchangeRateTableService;
import controllers.AbstractController;
import domain.ExchangeRateTable;

@Controller
@RequestMapping("/exchangeRateTable")
public class ExchangeRateTableController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private ExchangeRateTableService exchangeRateTableService;
	
	//Constructors----------------------------------------------
	
	public ExchangeRateTableController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView exchangeRateTableList() {
		
		ModelAndView result;
		Collection<ExchangeRateTable> exchangeRateTables;
		
		exchangeRateTables = exchangeRateTableService.findAllExchangeRateTablesGroupByCategory();
		result = new ModelAndView("exchangeRateTable/list");
		result.addObject("exchangeRateTables", exchangeRateTables);
		result.addObject("requestURI","exchangeRateTable/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		ExchangeRateTable exchangeRateTable = exchangeRateTableService.create();
		result = createEditModelAndView(exchangeRateTable);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int exchangeRateTableId){
        ModelAndView result;
        ExchangeRateTable exchangeRateTable;
         
        exchangeRateTable= exchangeRateTableService.findOne(exchangeRateTableId);
        Assert.notNull(exchangeRateTable);
        result= createEditModelAndView(exchangeRateTable);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid ExchangeRateTable exchangeRateTable, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(exchangeRateTable);
        }else{
            try{
                exchangeRateTableService.save(exchangeRateTable);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(exchangeRateTable, "exchangeRateTable.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(ExchangeRateTable exchangeRateTable){
        ModelAndView result;
        try{
            exchangeRateTableService.delete(exchangeRateTable);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(exchangeRateTable, "exchangeRateTable.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(ExchangeRateTable exchangeRateTable){
        ModelAndView result;
         
        result= createEditModelAndView(exchangeRateTable, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(ExchangeRateTable exchangeRateTable, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("exchangeRateTable/edit");
        result.addObject("exchangeRateTable", exchangeRateTable);
        result.addObject("message", message);
         
        return result;
 
    }


}
