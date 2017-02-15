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

import services.StockService;
import controllers.AbstractController;
import domain.Stock;

@Controller
@RequestMapping("/stock")
public class StockController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private StockService stockService;
	
	//Constructors----------------------------------------------
	
	public StockController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView stockList() {
		
		ModelAndView result;
		Collection<Stock> stocks;
		
		stocks = stockService.findAllStocksGroupByCategory();
		result = new ModelAndView("stock/list");
		result.addObject("stocks", stocks);
		result.addObject("requestURI","stock/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Stock stock = stockService.create();
		result = createEditModelAndView(stock);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int stockId){
        ModelAndView result;
        Stock stock;
         
        stock= stockService.findOne(stockId);
        Assert.notNull(stock);
        result= createEditModelAndView(stock);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Stock stock, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(stock);
        }else{
            try{
                stockService.save(stock);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(stock, "stock.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Stock stock){
        ModelAndView result;
        try{
            stockService.delete(stock);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(stock, "stock.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Stock stock){
        ModelAndView result;
         
        result= createEditModelAndView(stock, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Stock stock, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("stock/edit");
        result.addObject("stock", stock);
        result.addObject("message", message);
         
        return result;
 
    }


}
