package com.netcracker.DTO.mappers;

import com.netcracker.DTO.RouteDto;
import com.netcracker.entities.Route;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RouteMapper extends AbstractMapper<Route, RouteDto> {

	public RouteMapper() {
        super(Route.class, RouteDto.class);
    }

    @PostConstruct
    public void setupMapper() {
    	// Dto -> Ent
        TypeMap<double[], Point> rateDTORateTypeMap = mapper.getTypeMap(double[].class, Point.class);
        if(rateDTORateTypeMap == null) {
            rateDTORateTypeMap = mapper.createTypeMap(double[].class, Point.class);
        }
        rateDTORateTypeMap.setProvider(request -> {
            double[] source = double[].class.cast(request.getSource());
            GeometryFactory geometryFactory = new GeometryFactory();
            Coordinate coordsBegin = new Coordinate(source[0], source[1]);
            return geometryFactory.createPoint(coordsBegin);
        });
        
        // Ent -> Dto
        TypeMap<Point, double[]> rateEntityRateTypeMap = mapper.getTypeMap(Point.class, double[].class);
        if(rateEntityRateTypeMap == null) {
        	rateEntityRateTypeMap = mapper.createTypeMap(Point.class, double[].class);
        }
        rateEntityRateTypeMap.setProvider(request -> {
            Point source = Point.class.cast(request.getSource());
            double[] res = new double[2];
            res[0] = source.getX();
            res[1] = source.getY();
            return res;
        });
        
        
        // Dto -> Ent
        mapper.createTypeMap(RouteDto.class, Route.class).addMapping(RouteDto::getRouteBegin, Route::setRouteBegin).setPostConverter(
                context -> {
                    LOG.debug("RouteDto Convert");
                    RouteDto routeDto = context.getSource();
                    Route route = context.getDestination();
                    GeometryFactory geometryFactory = new GeometryFactory();
                    Coordinate coordBegin = new Coordinate(routeDto.getRouteBegin()[0], routeDto.getRouteBegin()[1]);
                    Point pointBegin = geometryFactory.createPoint(coordBegin);
                    route.setRouteBegin(pointBegin);
                    return route;
                }
        ).addMapping(RouteDto::getRouteEnd, Route::setRouteEnd).setPostConverter(
                context -> {
                    RouteDto routeDto = context.getSource();
                    Route route = context.getDestination();
                    GeometryFactory geometryFactory = new GeometryFactory();
                    Coordinate coordBegin = new Coordinate(routeDto.getRouteEnd()[0], routeDto.getRouteEnd()[1]);
                    Point pointBegin = geometryFactory.createPoint(coordBegin);
                    route.setRouteEnd(pointBegin);
                    return route;
                }
        );
//        mapper.createTypeMap(double[].class, Point.class).setPostConverter(context -> {
//            LOG.debug("Double to Point");
//            double[] coordsDto = context.getSource();
//            Point coors = context.getDestination();
//            GeometryFactory geometryFactory = new GeometryFactory();
//            Coordinate coordsBegin = new Coordinate(coordsDto[0], coordsDto[1]);
//            coors = geometryFactory.createPoint(coordsBegin);
//            return context.getDestination();
//        });

        // Ent -> Dto
        mapper.createTypeMap(Route.class, RouteDto.class).addMapping(Route::getRouteBegin, RouteDto::setRouteBegin).setPostConverter(
                context -> {
                    LOG.debug("Route Convert");
                    Route route = context.getSource();
                    RouteDto routeDto = context.getDestination();
                    double[] res = new double[2];
                    res[0] = route.getRouteBegin().getX();
                    res[1] = route.getRouteBegin().getY();
                    routeDto.setRouteBegin(res);
                    return routeDto;
                }
        ).addMapping(Route::getRouteEnd, RouteDto::setRouteEnd).setPostConverter(
                context -> {
                	Route route = context.getSource();
                    RouteDto routeDto = context.getDestination();
                    double[] res = new double[2];
                    res[0] = route.getRouteEnd().getX();
                    res[1] = route.getRouteEnd().getY();
                    routeDto.setRouteEnd(res);
                    return routeDto;
                }
        );
        
    }
	
}
