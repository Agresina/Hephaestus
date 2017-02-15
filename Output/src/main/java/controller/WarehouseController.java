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

import services.WarehouseService;
import controllers.AbstractController;
import domain.Warehouse;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private WarehouseService warehouseService;
	
	//Constructors----------------------------------------------
	
	public WarehouseController(){
		super();
	}
	

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView warehouseList() {
		
		ModelAndView result;
		Collection<Warehouse> warehouses;
		
		warehouses = warehouseService.findAllWarehousesGroupByCategory();
		result = new ModelAndView("warehouse/list");
		result.addObject("warehouses", warehouses);
		result.addObject("requestURI","warehouse/administrator/list.do");
		
		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Warehouse warehouse = warehouseService.create();
		result = createEditModelAndView(warehouse);
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int warehouseId){
        ModelAndView result;
        Warehouse warehouse;
         
        warehouse= warehouseService.findOne(warehouseId);
        Assert.notNull(warehouse);
        result= createEditModelAndView(warehouse);
         
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Warehouse warehouse, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(warehouse);
        }else{
            try{
                warehouseService.save(warehouse);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(warehouse, "warehouse.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Warehouse warehouse){
        ModelAndView result;
        try{
            warehouseService.delete(warehouse);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(warehouse, "warehouse.commit.error");
        }
         
        return result;   
    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Warehouse warehouse){
        ModelAndView result;
         
        result= createEditModelAndView(warehouse, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Warehouse warehouse, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("warehouse/edit");
        result.addObject("warehouse", warehouse);
        result.addObject("message", message);
         
        return result;
 
    }


}
