package com.netcracker.Controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.services.ReportService;
import com.netcracker.services.RouteService;

@RestController
@RequestMapping("routes")
//@CrossOrigin(origins="*", maxAge=3600)
public class RouteController {

	private static final Logger LOG = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;
    
    @GetMapping ("")
    String home ()
    {
        return "routes";
    }
	
    @GetMapping("/routeByCity/{id}")
    public ArrayList<Route> getRoutesByCityId(@PathVariable(value="id") Long id){
        LOG.info("[ getRoutesByCityId : {}", id);

        ArrayList<Route> routes = routeService.getRoutesByCityId(id);

        LOG.info("] return : {}", routes.toString());
        return routes;
    }
    
    /*
     * adress - coordinates of departure point (Xcord:YCord)
     * radius - maximum distance between passenger and departure point
     * departure - departure tine (Hours:Minutes)
     */
    @GetMapping("/closestRoutes/{adress}/{radius}/{departure}")
    public ArrayList<Route> getClosestRoutes(@PathVariable(value="adress") String adress, 
    										 @PathVariable(value="radius") Integer radius,
    										 @PathVariable(value="departure") String depart){
				
    	Double Xcord = Double.parseDouble(adress.split(":")[0]);
    	Double Ycord = Double.parseDouble(adress.split(":")[1]);
    	
    	Calendar dep = new GregorianCalendar();
    	dep.set(Calendar.HOUR_OF_DAY, Integer.parseInt(depart.split(":")[0]));
    	dep.set(Calendar.MINUTE, Integer.parseInt(depart.split(":")[1]));
    	
    	return routeService.getClosestRoutes(Xcord, Ycord, radius, dep);
    	
    }
    
}
