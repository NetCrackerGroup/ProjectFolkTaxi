package com.netcracker.services;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.UserRouteDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RouteMapper extends AbstractMapper<Route, RouteDto> {

    public RouteMapper() {
        super(Route.class, RouteDto.class);
    }


    @Autowired
    private UserRouteMapper userRouteMapper;

    @PostConstruct
    public void setupMapper() {

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
        TypeMap<Point, double[]> rateDTORateTypeMap2 = mapper.getTypeMap(Point.class, double[].class);

        if(rateDTORateTypeMap2 == null) {
            rateDTORateTypeMap2 = mapper.createTypeMap(Point.class, double[].class);
        }
        rateDTORateTypeMap2.setProvider(request -> {
            Point source = Point.class.cast(request.getSource());
            return new double[] {source.getCoordinate().getX(), source.getCoordinate().getY()};
        });


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

        mapper.createTypeMap(Route.class, RouteDto.class).addMapping(Route::getRouteBegin, RouteDto::setRouteBegin).setPostConverter(
                context -> {
                    Route route = context.getSource();
                    RouteDto routeDto = context.getDestination();
                    Point point =  route.getRouteBegin();
                    double[] startPoint = new double[] {point.getCoordinate().getX(), point.getCoordinate().getY()};
                    routeDto.setRouteBegin(startPoint);
                    return routeDto;
                }
        ).addMapping(Route::getRouteEnd, RouteDto::setRouteEnd).setPostConverter(
                context -> {
                    Route route = context.getSource();
                    RouteDto routeDto = context.getDestination();
                    Point point = route.getRouteEnd();
                    double[] endPoint = new double[] {point.getCoordinate().getX(), point.getCoordinate().getY()};
                    routeDto.setRouteEnd(endPoint);
                    return routeDto;
                }
        ).addMapping(Route::getDriverId, RouteDto::setUserRouteDto).setPostConverter(
                context -> {
                    Route route = context.getSource();
                    RouteDto routeDto = context.getDestination();
                    User user = route.getDriverId();
                    UserRouteDto userRouteDto = userRouteMapper.toDto(user);
                    routeDto.setUserRouteDto(userRouteDto);
                    return routeDto;
                }
        );


    }
}
